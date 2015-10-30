package defeatedcrow.addonforamt.economy.common.block;

import mods.defeatedcrow.api.charge.ChargeItemManager;
import mods.defeatedcrow.api.charge.IChargeGenerator;
import mods.defeatedcrow.api.charge.IChargeItem;
import mods.defeatedcrow.api.charge.IChargeableMachine;
import mods.defeatedcrow.api.energy.IBattery;
import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.config.PropertyHandler;
import mods.defeatedcrow.plugin.IC2.EUItemHandler;
import mods.defeatedcrow.plugin.IC2.EUSinkManager;
import mods.defeatedcrow.plugin.IC2.IEUSinkChannel;
import mods.defeatedcrow.plugin.cofh.RFDeviceHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidContainerRegistry;
import cofh.api.energy.IEnergyProvider;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModAPIManager;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.plugin.ss2.SS2DeviceHandlerEMT;

@Optional.InterfaceList({ @Optional.Interface(iface = "cofh.api.energy.IEnergyProvider", modid = "CoFHAPI|energy"), })
public class TileENMotor extends TileEntity implements ISidedInventory, IChargeableMachine, IEnergyProvider {

	// 現在のチャージ量
	protected int chargeAmount = 0;
	// チャージアイテムを溶かす際の判定発生間隔
	protected int coolTime = 4;
	// 作業中カウント
	public int cookTime = 0;
	public int motorR = 0;
	private int lastCook = 0;

	public int sendAmo = 4;

	// 方向制御
	private ForgeDirection[] sendDir = {
			ForgeDirection.NORTH,
			ForgeDirection.EAST,
			ForgeDirection.SOUTH,
			ForgeDirection.WEST };

	// EU受け入れ用のチャンネル
	protected IEUSinkChannel EUChannel;

	public TileENMotor() {
		super();
		if (Loader.isModLoaded("IC2")) {
			EUChannel = EUSinkManager.getChannel(this, 128, 3);
		}
	}

	public static int exchangeRateRF() {
		// RF -> Charge
		return PropertyHandler.rateRF();
	}

	public static int exchangeRateEU() {
		// EU -> Charge
		return PropertyHandler.rateEU();
	}

	public static int exchangeRateGF() {
		// GF -> Charge
		return PropertyHandler.rateGF();
	}

	@Override
	public void readFromNBT(NBTTagCompound tab) {
		if (EUChannel != null) {
			EUChannel.readFromNBT2(tab);
		}

		super.readFromNBT(tab);

		NBTTagList nbttaglist = tab.getTagList("Items", 10);
		this.itemstacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.itemstacks.length) {
				this.itemstacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.chargeAmount = tab.getShort("ChargeAmount");
		this.cookTime = tab.getShort("CookTime");
		this.coolTime = tab.getByte("CoolTime");
		this.sendAmo = tab.getShort("SendAmount");
		this.motorR = tab.getInteger("MotorR");

	}

	@Override
	public void writeToNBT(NBTTagCompound tab) {
		if (EUChannel != null) {
			EUChannel.writeToNBT2(tab);
		}

		super.writeToNBT(tab);

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.itemstacks.length; ++i) {
			if (this.itemstacks[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.itemstacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		tab.setTag("Items", nbttaglist);

		// 燃焼時間や調理時間などの書き込み
		tab.setShort("ChargeAmount", (short) this.chargeAmount);
		tab.setShort("CookTime", (short) this.cookTime);
		tab.setByte("CoolTime", (byte) this.coolTime);
		tab.setShort("SendAmount", (short) this.sendAmo);
		tab.setInteger("MotorR", this.motorR);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		this.writeToNBT(nbtTagCompound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTagCompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());
	}

	@Override
	public void invalidate() {
		if (EUChannel != null) {
			EUChannel.invalidate2();
		}
		super.invalidate();
	}

	// 以下はSinkChannel用のメソッド
	@Override
	public void onChunkUnload() {
		if (EUChannel != null) {
			EUChannel.onChunkUnload2();
		}
		super.onChunkUnload();
	}

	// 調理中の矢印の描画
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int par1) {
		return this.cookTime * par1 / maxCookTime();
	}

	// チャージゲージの描画
	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int par1) {
		return this.chargeAmount * par1 / this.getMaxChargeAmount();
	}

	// 調理中
	@Override
	public boolean isActive() {
		return this.cookTime > 0;
	}

	// チャージが満タンである
	public boolean isFullCharged() {
		return this.chargeAmount >= this.getMaxChargeAmount();
	}

	// 以下はパケット送受信用メソッド
	public void setChargeAmount(int par1) {
		this.chargeAmount = par1;
	}

	@Override
	public int getChargeAmount() {
		return this.chargeAmount;
	}

	// setting
	public int getMaxCoolTime() {
		return 4;
	}

	protected int maxCookTime() {
		return 17;
	}

	// RS信号がOFの時に送信できる
	private boolean isActiveSendEN() {
		return worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
	}

	/**
	 * Tick毎の処理。
	 */
	@Override
	public void updateEntity() {
		boolean flag = this.isFullCharged();
		boolean flag2 = false;
		if (this.chargeAmount < 0) {
			this.chargeAmount = 0;
		}

		// まずEUChannel更新
		if (!this.worldObj.isRemote && EUChannel != null) {
			EUChannel.updateEntity2();
		}

		// 硬直時間：燃料の消費に利用
		if (this.coolTime > 0) {
			--this.coolTime;
		}

		if (!this.worldObj.isRemote) {
			// ここではチャージ・EUの受け入れを行う
			if (!flag) {
				flag = this.onCharge();
			}

			if (this.coolTime == 0) {
				this.onCoolTime(flag);
				// 電池スロットの処理
				this.coolTime = this.getMaxCoolTime();
			}

			// RF・GF発生部分
			if (this.isActiveSendEN()) {
				flag2 = this.onDischarge();
			}

			/*
			 * GUIの炎ゲージを動かす
			 */
			if (flag2) {
				if (this.cookTime < maxCookTime()) {
					this.cookTime++;
				} else {
					this.cookTime = 1;
				}
			} else {
				this.cookTime = 0;
			}

			this.markDirty();

			this.updateServer();
		}

		if (this.cookTime > 0) {
			this.motorR++;
			if (this.motorR > 360) {
				this.motorR = 0;
			}
		}
	}

	public void updateServer() {
		int current = this.cookTime;
		if (current != this.lastCook) {
			this.lastCook = current;
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}

	/*
	 * AMTチャージの場合、チャージ受け入れ側が自発的に取り込む
	 */
	public boolean onCharge() {
		boolean flag2 = false;
		int s = 0;

		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			ForgeDirection opposite = dir.getOpposite();
			if (dir == this.getTileDir())
				continue;

			if (this.isFullCharged())
				continue;

			int get = this.acceptChargeFromDir(dir);
			if (get > 0) {
				int j = this.chargeAmount + get;

				if (j > this.getMaxChargeAmount())// 指定したチャージ上限より小さいかどうか
				{
					j = this.getMaxChargeAmount();
				}
				this.chargeAmount = j;
				s += get;
				flag2 = get > 0;

				if (this.chargeAmount > this.getMaxChargeAmount()) {
					this.chargeAmount = this.getMaxChargeAmount();
				}
			}
		}

		if (this.chargeAmount == 0) {
			this.sendAmo = 0;
		} else {
			int i = Math.max(s, this.chargeAmount);
			if (i > 32)
				i = 32;
			this.sendAmo = i;
		}

		return false;
	}

	public int acceptChargeFromDir(ForgeDirection dir) {
		if (this.isFullCharged())
			return 0;
		int ret = 0;
		int rec = this.getMaxChargeAmount() - this.chargeAmount;

		// EU受入量は指定する必要があるので、128とする。
		if (EUChannel != null) {
			int i = this.getChargeAmount();
			double eu = Math.min(EUChannel.getEnergyStored2(), 128.0D);
			double get = eu / this.exchangeRateEU();
			if (get > rec) {
				get = rec;
				eu = rec * this.exchangeRateEU() * 1.0D;
			}

			if (eu > 0.0D && EUChannel.useEnergy2(eu)) {
				ret = (int) get;
			}
		}

		if (ret == 0) {
			ForgeDirection opposite = dir.getOpposite();
			TileEntity tile = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
			if (tile instanceof IChargeGenerator) {
				IChargeGenerator device = (IChargeGenerator) tile;
				int get = device.generateCharge(opposite, true);
				get = Math.min(get, rec);

				if (get > 0) {
					device.generateCharge(opposite, false);
					ret = get;
				}
			}
		}
		return ret;
	}

	/*
	 * GF・RFの送信処理
	 */
	public boolean onDischarge() {
		ForgeDirection dir = this.getTileDir();
		ForgeDirection opp = dir.getOpposite();
		TileEntity tile = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
		if (tile != null) {
			int ext = Math.min(this.sendAmo, this.chargeAmount);
			boolean b = false;

			if (!b && Loader.isModLoaded("SextiarySector")) {
				int ext2 = ext * this.exchangeRateGF();
				ext2 = SS2DeviceHandlerEMT.inputEnergy(tile, opp, 2, ext2, true);
				if (SS2DeviceHandlerEMT.isGFDevice(tile) && ext2 > 0) {
					int i = SS2DeviceHandlerEMT.inputEnergy(tile, opp, 2, ext2, false);
					i = i / this.exchangeRateGF();
					this.chargeAmount -= i;
					b = true;
				}
			}
			if (!b && ModAPIManager.INSTANCE.hasAPI("CoFHAPI|energy")) {
				int ext2 = ext * this.exchangeRateRF();
				ext2 = RFDeviceHandler.inputEnergy(opp, tile, ext2, true);
				if (RFDeviceHandler.isRFDevice(tile) && ext2 > 0) {
					int i = RFDeviceHandler.inputEnergy(opp, tile, ext2, false);
					AMTLogger.debugInfo("send RF : " + i);
					i = i / this.exchangeRateRF();
					this.chargeAmount -= i;
					b = true;
				}
			}
			return b;
		}
		return false;
	}

	/*
	 * 電池消費部分
	 */
	public boolean onCoolTime(boolean b) {
		ItemStack retItem = null;
		ItemStack target = this.getStackInSlot(0);

		boolean flag1 = false;
		if (!this.isFullCharged() && this.isItemFuel(target)) {
			// チャージ残量＋アイテムのチャージ量
			int i = this.chargeAmount + getItemBurnTime(target);
			int j = getItemBurnTime(target);

			if (i <= this.getMaxChargeAmount())// 指定したチャージ上限より小さいかどうか
			{
				if (target.getItem() instanceof IBattery) {
					// IBatteryの場合、ここでは空容器のスロット移動は行わない。
					IBattery bat = (IBattery) target.getItem();
					bat.discharge(target, 16, true);
					this.chargeAmount = i;
					flag1 = true;
				} else if (this.batteryContainerItem(target) != null) {
					// containerがある場合
					if (this.itemstacks[1] == null
							|| (this.itemstacks[1].getItem() == this.batteryContainerItem(target).getItem()
									&& this.itemstacks[1].getItemDamage() == this.batteryContainerItem(target)
											.getItemDamage() && this.itemstacks[1].stackSize < this.itemstacks[1]
									.getMaxStackSize())) {
						retItem = this.batteryContainerItem(target);

						this.chargeAmount = i;
						flag1 = true;
					}
				} else// スロット1のアイテムを減らす
				{
					int ret = this.discharge(target, j, 0);
					if (ret > 0) {
						this.chargeAmount += ret;
						flag1 = true;
					} else {
						retItem = target;
						flag1 = true;
					}
				}
			}

		}

		// からになったIBatteryのスタック移動はここ
		if (target != null && target.getItem() instanceof IBattery) {
			IBattery bat = (IBattery) target.getItem();
			if (bat.getChargeAmount(target) == 0 && this.itemstacks[1] == null) {
				this.setInventorySlotContents(1, target.copy());
				this.decrStackSize(0, 1);
			}
		} else if (retItem != null && flag1) {
			if (this.itemstacks[1] == null) {
				this.setInventorySlotContents(1, retItem.copy());
			} else {
				++this.itemstacks[1].stackSize;
			}
			this.decrStackSize(0, 1);
		}
		return false;
	}

	private ForgeDirection getTileDir() {
		int m = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		m = Math.min(m, 3);
		return this.sendDir[m];
	}

	public ItemStack batteryContainerItem(ItemStack item) {
		if (item != null && item.getItem() != null) {
			if (item.getItem() instanceof IChargeItem) {
				return ((IChargeItem) item.getItem()).returnItem();
			} else if (FluidContainerRegistry.isFilledContainer(item)) {
				return FluidContainerRegistry.drainFluidContainer(item);
			} else {
				return item.getItem().getContainerItem(item);
			}
		}

		return null;
	}

	/* === IChargeableMachineのメソッド === */

	// チャージゲージ上限も変更可能に。
	@Override
	public int getMaxChargeAmount() {
		return 25600;
	}

	// 電池アイテムを受け入れられる状態
	@Override
	public boolean canReceiveChargeItem(ItemStack item) {
		boolean flag = false;
		boolean flag2 = false;
		if (item != null) {
			int i = this.getItemBurnTime(item);
			flag = i > 0 && (this.getChargeAmount() + i <= this.getMaxChargeAmount());
		}

		if (this.getStackInSlot(0) == null) {
			flag2 = true;
		} else {
			ItemStack current = this.getStackInSlot(0);
			flag2 = item.isItemEqual(current) && (current.stackSize + item.stackSize < current.getMaxStackSize());
		}

		return flag && flag2;
	}

	@Override
	public int addCharge(int amount, boolean isSimulate) {
		int eng = this.getChargeAmount();
		int get = amount;
		if (this.isFullCharged())
			return 0;

		int ret = Math.min(this.getMaxChargeAmount() - eng, get);

		if (!isSimulate) {
			this.setChargeAmount(eng + ret);
		}

		return ret;
	}

	@Override
	public int extractCharge(int amount, boolean isSimulate) {
		int eng = this.getChargeAmount();
		int get = amount;

		int ret = Math.min(eng, get);

		if (!isSimulate) {
			this.setChargeAmount(eng - ret);
		}

		return ret;
	}

	public boolean isItemFuel(ItemStack stack) {
		return getItemBurnTime(stack) > 0;
	}

	public int getItemBurnTime(ItemStack stack) {
		if (stack == null)
			return 0;
		else {
			int ret = 0;
			int inc = 16; // 速度はチャージバッテリーと同じ

			if (Loader.isModLoaded("IC2") && ret == 0) {
				int i = EUItemHandler.dischargeAmount(stack, inc * exchangeRateEU(), true);
				ret = Math.round(i / exchangeRateEU());
			}
			if (ret == 0) {
				if (ChargeItemManager.chargeItem.getChargeAmount(stack) > 0) {
					return ChargeItemManager.chargeItem.getChargeAmount(stack);
				} else if (stack.getItem() instanceof IBattery) {
					// 充電池の場合、16/4tickずつ減少する。
					IBattery bat = (IBattery) stack.getItem();
					ret = bat.discharge(stack, 16, false);
				}
			}
			return ret;
		}
	}

	public int discharge(ItemStack item, int amount, int slot) {
		if (item == null)
			return 0;
		else {
			int ret = 0;
			int inc = amount;

			if (Loader.isModLoaded("IC2") && ret == 0) {
				int i = EUItemHandler.dischargeAmount(item, inc * exchangeRateEU(), false);
				ret = Math.round(i / exchangeRateEU());

				if (ret > 0 && EUItemHandler.getAmount(item) == 0 && this.itemstacks[1] == null) {
					if (item == null || item.stackSize == 0) {
						this.setInventorySlotContents(0, null);
					} else {
						this.setInventorySlotContents(1, item.copy());
						this.decrStackSize(slot, 1);
					}
				}
			}

			if (ret == 0) {
				this.decrStackSize(slot, 1);
				ret = this.getItemBurnTime(item);
			}

			return ret;
		}
	}

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	protected int[] slotsTop() {
		return new int[] { 0 };
	}

	protected int[] slotsBottom() {
		return new int[] { 1 };
	}

	protected int[] slotsSides() {
		return new int[] {
				0,
				1 };
	}

	public ItemStack[] itemstacks = new ItemStack[getSizeInventory()];

	// スロット数は各Tileでオーバーライドして増やすこと。2は最低限の値。
	@Override
	public int getSizeInventory() {
		return 2;
	}

	// インベントリ内の任意のスロットにあるアイテムを取得
	@Override
	public ItemStack getStackInSlot(int par1) {
		return par1 < this.getSizeInventory() ? this.itemstacks[par1] : null;
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		if (this.itemstacks[par1] != null) {
			ItemStack itemstack;

			if (this.itemstacks[par1].stackSize <= par2) {
				itemstack = this.itemstacks[par1];
				this.itemstacks[par1] = null;
				return itemstack;
			} else {
				itemstack = this.itemstacks[par1].splitStack(par2);

				if (this.itemstacks[par1].stackSize == 0) {
					this.itemstacks[par1] = null;
				}

				return itemstack;
			}
		} else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
		if (this.itemstacks[par1] != null) {
			ItemStack itemstack = this.itemstacks[par1];
			this.itemstacks[par1] = null;
			return itemstack;
		} else
			return null;
	}

	// インベントリ内のスロットにアイテムを入れる
	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {

		if (par1 > this.getSizeInventory()) {
			par1 = 0;// 存在しないスロットに入れようとすると強制的に材料スロットに変更される。
		}

		this.itemstacks[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	// インベントリの名前
	@Override
	public String getInventoryName() {
		return StatCollector.translateToLocal("dcs.energy.motorInv.gui");
	}

	// 多言語対応かどうか
	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	// インベントリ内のスタック限界値
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
		super.markDirty();
	}

	// par1EntityPlayerがTileEntityを使えるかどうか
	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer
				.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
		if (par1 > 0) {
			return false;
		} else {
			return this.isItemFuel(par2ItemStack);
		}
	}

	// ホッパーにアイテムの受け渡しをする際の優先度
	@Override
	public int[] getAccessibleSlotsFromSide(int par1) {
		return par1 == 0 ? slotsBottom() : (par1 == 1 ? slotsTop() : slotsSides());
	}

	// ホッパーからアイテムを入れられるかどうか
	@Override
	public boolean canInsertItem(int par1, ItemStack stack, int par3) {
		return this.isItemValidForSlot(par1, stack);
	}

	// 隣接するホッパーにアイテムを送れるかどうか
	@Override
	public boolean canExtractItem(int par1, ItemStack stack, int par3) {
		if (stack != null) {
			if (stack.getItem() instanceof IBattery) {
				IBattery bat = (IBattery) stack.getItem();
				return bat.isFullCharged(stack);
			}
		}
		return true;
	}

	// 追加メソッド
	protected static boolean isItemStackable(ItemStack target, ItemStack current) {
		if (target == null || current == null)
			return false;

		if (target.getItem() == current.getItem() && target.getItemDamage() == current.getItemDamage()) {
			return (current.stackSize + target.stackSize) <= current.getMaxStackSize();
		}

		return false;
	}

	protected void incrStackInSlot(int i, ItemStack input) {
		if (i < this.getSizeInventory() && input != null && this.itemstacks[i] != null) {
			if (this.itemstacks[i].getItem() == input.getItem()
					&& this.itemstacks[i].getItemDamage() == input.getItemDamage()) {
				this.itemstacks[i].stackSize += input.stackSize;
				if (this.itemstacks[i].stackSize > this.getInventoryStackLimit()) {
					this.itemstacks[i].stackSize = this.getInventoryStackLimit();
				}
			}
		} else {
			this.setInventorySlotContents(i, input);
		}
	}

	/* IEnergyProvider */

	@Optional.Method(modid = "CoFHAPI|energy")
	@Override
	public boolean canConnectEnergy(ForgeDirection paramForgeDirection) {
		return paramForgeDirection == this.getTileDir();
	}

	@Optional.Method(modid = "CoFHAPI|energy")
	@Override
	public int extractEnergy(ForgeDirection paramForgeDirection, int paramInt, boolean paramBoolean) {
		if (paramForgeDirection != this.getTileDir())
			return 0;

		int ret = Math.min(this.chargeAmount, this.sendAmo);
		int extract = ret * this.exchangeRateRF();

		if (ret > 0) {
			if (paramBoolean) {
				AMTLogger.debugInfo("send RF2 : " + extract);
				this.chargeAmount -= ret;
			}
			return extract;
		}
		return 0;
	}

	@Optional.Method(modid = "CoFHAPI|energy")
	@Override
	public int getEnergyStored(ForgeDirection paramForgeDirection) {
		return this.chargeAmount * this.exchangeRateRF();
	}

	@Optional.Method(modid = "CoFHAPI|energy")
	@Override
	public int getMaxEnergyStored(ForgeDirection paramForgeDirection) {
		return this.getMaxChargeAmount() * this.exchangeRateRF();
	}
}
