package defeatedcrow.addonforamt.economy.common.block;

import ic2.api.energy.tile.IEnergySource;
import mods.defeatedcrow.api.charge.IChargeGenerator;
import mods.defeatedcrow.api.charge.IChargeableMachine;
import mods.defeatedcrow.api.energy.IBattery;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.plugin.amt.AMTIntegration;
import defeatedcrow.addonforamt.economy.plugin.energy.EUItemHandlerEMT;
import defeatedcrow.addonforamt.economy.plugin.energy.EUSourceManagerEMT;
import defeatedcrow.addonforamt.economy.plugin.energy.IEUSourceChannelEMT;

/**
 * ChargeItemを燃料とするTileEntityのベースクラス。 <br>
 * 基本的には本体の {@link mods.defeatedcrow.common.tile.appliance.MachineBase} と同等だが、<br>
 * アイテム生産の必要が無いため該当部分を除去している。<br>
 * チャージゲージスロットは電池放電ではなく、電池やツールの充電のためのもの。
 */
@Optional.InterfaceList({ @Optional.Interface(iface = "ic2.api.energy.tile.IEnergySource", modid = "IC2API"), })
public abstract class GeneratorBase extends TileEntity implements ISidedInventory, IChargeableMachine,
		IChargeGenerator, IEnergySource {

	// 現在のチャージ量
	protected int chargeAmount = 0;
	// チャージアイテムを溶かす際の判定発生間隔
	protected int coolTime = 4;
	// 作業中カウント
	public int cookTime = 0;

	// EU受け入れ用のチャンネル
	protected IEUSourceChannelEMT EUChannel;

	public GeneratorBase() {
		super();
		if (Loader.isModLoaded("IC2")) {
			EUChannel = EUSourceManagerEMT.getChannel(this, this.getMaxChargeAmount() * exchangeRateEU(), 1);
		}
	}

	public static int exchangeRateRF() {
		// RF -> Charge
		return AMTIntegration.RFrate;
	}

	public static int exchangeRateEU() {
		// EU -> Charge
		return AMTIntegration.EUrate;
	}

	public static int exchangeRateGF() {
		// GF -> Charge
		return AMTIntegration.GFrate;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		if (EUChannel != null) {
			EUChannel.readFromNBT2(par1NBTTagCompound);
		}

		super.readFromNBT(par1NBTTagCompound);

		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 10);
		this.itemstacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.itemstacks.length) {
				this.itemstacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.chargeAmount = par1NBTTagCompound.getShort("ChargeAmount");
		this.cookTime = par1NBTTagCompound.getShort("CookTime");
		this.coolTime = par1NBTTagCompound.getByte("CoolTime");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		if (EUChannel != null) {
			EUChannel.writeToNBT2(par1NBTTagCompound);
		}

		super.writeToNBT(par1NBTTagCompound);

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.itemstacks.length; ++i) {
			if (this.itemstacks[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.itemstacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		par1NBTTagCompound.setTag("Items", nbttaglist);

		// 燃焼時間や調理時間などの書き込み
		par1NBTTagCompound.setShort("ChargeAmount", (short) this.chargeAmount);
		par1NBTTagCompound.setShort("CookTime", (short) this.cookTime);
		par1NBTTagCompound.setByte("CoolTime", (byte) this.coolTime);
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

	/**
	 * Tick毎の処理。
	 */
	@Override
	public void updateEntity() {
		boolean flag = false;
		boolean flag2 = false;

		// まずEUChannel更新
		if (!this.worldObj.isRemote && EUChannel != null) {
			EUChannel.updateEntity2();
		}

		// 硬直時間：燃料の消費に利用
		if (this.coolTime > 0) {
			--this.coolTime;
		}

		if (!this.worldObj.isRemote) {
			flag = this.onCharge();

			if (this.coolTime == 0) {
				this.onCoolTime(flag);
				// 最後に硬直時間を設定。更新間隔は変えられる
				this.coolTime = this.getMaxCoolTime();
			}

			// 充電が優先
			ItemStack current = this.itemstacks[0];
			int charge = this.getChargeAmount();
			int inc = Math.min(charge, 4);

			if (current != null && inc > 0) {
				if (current.getItem() instanceof IBattery) {
					IBattery bat = (IBattery) current.getItem();
					int ret = bat.charge(current, inc, true);
					this.setChargeAmount(charge - ret);
				} else if (this.isChargeableBattery(current)) {
					int ret = this.chargeAnotherBattery(current, inc, false);
					this.setChargeAmount(charge - ret);
				}
			}

			// 送信処理
			this.onDischarge();

			if (Loader.isModLoaded("IC2") && EUChannel != null)
				this.updateEU();
			/*
			 * GUIの炎ゲージを動かす
			 */
			if (flag || flag2) {
				if (this.cookTime < maxCookTime()) {
					this.cookTime++;
				} else {
					this.cookTime = 1;
				}
			} else {
				this.cookTime = 0;
			}

			this.markDirty();
		}
	}

	/**
	 * 内部チャージ増加処理
	 */
	public abstract boolean onCharge();

	/**
	 * 内部チャージ減少処理
	 */
	public abstract boolean onDischarge();

	/**
	 * クールタイム時処理<br>
	 * 数Tickおきに分散したい処理（燃料消費）など
	 */
	public abstract boolean onCoolTime(boolean b);

	/**
	 * 他MODの電池アイテムを対応させるためのメソッド。
	 * 残量確認なども含めて、実際に充電できる時のみTrueを返すこと。
	 */
	public boolean isChargeableBattery(ItemStack item) {
		boolean flag = false;

		if (Loader.isModLoaded("IC2")) {
			flag = EUItemHandlerEMT.isChargeable(item);
		} else if (item != null && item.getItem() instanceof IBattery) {
			flag = true;
		}

		return flag;
	}

	/**
	 * チャージ充電メソッド。
	 * charge、EUのみ。
	 */
	public int chargeAnotherBattery(ItemStack item, int inc, boolean isSimulate) {
		int ret = 0;
		if (Loader.isModLoaded("IC2")) {
			int i = EUItemHandlerEMT.chargeAmount(item, inc * this.exchangeRateEU(), isSimulate);
			ret = Math.round(i / this.exchangeRateEU());
		}
		return ret;
	}

	protected void updateEU() {
		// 一定量を充電する。無くなっていたら追加。
		double e = 1.0D * this.exchangeRateEU() * this.sendAmount();
		if (EUChannel != null && EUChannel.getEnergyStored() < e) {
			double current = EUChannel.getEnergyStored();
			double d1 = e - current;
			double d2 = d1 * 0.5D;
			int ret = MathHelper.floor_double(d2);
			if (this.getChargeAmount() >= ret) {
				this.extractCharge(ret, false);
				EUChannel.addEnergy2(d1);
			}
		}
	}

	/* IChargeGenerator */

	@Override
	public boolean canGenerate() {
		return this.chargeAmount > 0;
	}

	@Override
	public int generateCharge(ForgeDirection dir, boolean flag) {
		int fuel = this.sendAmount();
		int ret = Math.min(chargeAmount, fuel);
		if (ret > 0) {
			if (!flag)
				this.chargeAmount -= ret;
			return ret;
		}
		return 0;
	}

	protected short sendAmount() {
		return 4;
	}

	/* IChargeableMachineのメソッド */

	// チャージゲージ上限も変更可能に。
	@Override
	public int getMaxChargeAmount() {
		return 25600;
	}

	// 電池アイテムを受け入れられる状態
	@Override
	public boolean canReceiveChargeItem(ItemStack item) {
		return false;
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

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	/*
	 * 0 : 燃料搬入
	 * 1 : 燃料の空容器搬出
	 * 2~ : 各Tileで実装される。
	 */
	protected abstract int[] slotsTop();

	protected abstract int[] slotsBottom();

	protected abstract int[] slotsSides();

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
	public abstract String getInventoryName();

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
	public boolean isItemValidForSlot(int par1, ItemStack stack) {
		return (par1 == 1) ? false : (par1 == 0 ? this.isChargeableBattery(stack) : true);
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

	/* -- IEnergySource -- */

	@Optional.Method(modid = "IC2")
	@Override
	public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
		return EUChannel.emitsEnergyTo2(receiver, direction);
	}

	@Optional.Method(modid = "IC2")
	@Override
	public double getOfferedEnergy() {
		return EUChannel.getOfferedEnergy2();
	}

	@Optional.Method(modid = "IC2")
	@Override
	public void drawEnergy(double amount) {
		EUChannel.drawEnergy2(amount);
	}

	@Optional.Method(modid = "IC2")
	@Override
	public int getSourceTier() {
		return EUChannel.getSourceTier2();
	}
}
