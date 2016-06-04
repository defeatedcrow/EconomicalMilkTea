package defeatedcrow.addonforamt.economy.common.block;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import buildcraft.api.transport.IPipeConnection;
import buildcraft.api.transport.IPipeTile.PipeType;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.api.RecipeManagerEMT;

/*
 * 発電機。
 * 液体燃料を消費してチャージ・EUを得る。
 * インベントリ・液体タンク有り
 * */
@Optional.InterfaceList({ @Optional.Interface(
		iface = "buildcraft.api.transport.IPipeConnection",
		modid = "BuildCraft|Core"), })
public class TileGeneratorEMT extends GeneratorBase implements IFluidHandler, IPipeConnection {

	// FluidTank
	public EMTTank productTank = new EMTTank(8000);

	private int lastAmount = 0;

	// 送電量が燃料によって可変のため、この変数で記憶
	private short lastSend = 4;

	public TileGeneratorEMT() {
		super();
	}

	@Override
	public void readFromNBT(NBTTagCompound tab) {
		super.readFromNBT(tab);

		this.productTank = new EMTTank(8000);
		if (tab.hasKey("productTank")) {
			this.productTank.readFromNBT(tab.getCompoundTag("productTank"));
		}
		this.lastSend = tab.getShort("SendAmount");
	}

	@Override
	public void writeToNBT(NBTTagCompound tab) {
		super.writeToNBT(tab);

		NBTTagCompound tank = new NBTTagCompound();
		this.productTank.writeToNBT(tank);
		tab.setTag("productTank", tank);
		tab.setShort("SendAmount", this.lastSend);
	}

	@Override
	public Packet getDescriptionPacket() {
		return super.getDescriptionPacket();
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
	}

	// 調理中の矢印の描画
	@SideOnly(Side.CLIENT)
	public int getFluidAmountScaled(int par1) {
		return this.productTank.getFluidAmount() * par1 / 8000;
	}

	// GUI用にコンテナからの更新を受け取るメソッド。
	public void getGuiFluidUpdate(int id, int val) {
		if (id == 2)// ID
		{
			if (productTank.getFluid() == null) {
				productTank.setFluidById(val);
			} else {
				int amo = productTank.getFluidAmount();
				productTank.setFluidById(val);
			}
		} else if (id == 3)// amount
		{
			if (productTank.getFluid() == null) {
				productTank.setFluid((FluidStack) null);
			} else {
				productTank.getFluid().amount = val;
			}
		}
	}

	@Override
	public int getMaxCoolTime() {
		return 4;
	}

	/**
	 * Tick毎の処理。
	 */
	@Override
	public void updateEntity() {
		super.updateEntity();

		if (!this.worldObj.isRemote) {
			this.drainContainer();
			this.fillContainer();
			this.onServerUpdate();
		}
	}

	@Override
	public boolean onCharge() {
		boolean flag = this.isFullCharged();
		boolean flag2 = false;
		// チャージが満タンではないか？
		if (!flag && this.isFluidFuel()) {
			// チャージ残量＋アイテムのチャージ量
			int i = getFluidBurn();
			this.lastSend = (short) i;
			int j = this.chargeAmount + i;

			if (j <= this.getMaxChargeAmount())// 指定したチャージ上限より小さいかどうか
			{
				this.chargeAmount = j;
				flag2 = true;
			}

			if (this.chargeAmount > this.getMaxChargeAmount()) {
				this.chargeAmount = this.getMaxChargeAmount();
			}
		}
		return flag2;
	}

	@Override
	public boolean onDischarge() {
		boolean flag = false;
		// 送信処理
		ForgeDirection[] dirs = ForgeDirection.VALID_DIRECTIONS;
		for (ForgeDirection dir : dirs) {
			if (this.getChargeAmount() > 0) {
				int i = Math.min(this.lastSend, this.chargeAmount);
				int send = this.sendChargeFromDir(i, dir);
				if (send > 0) {
					flag = true;
					this.chargeAmount -= send;
				}
			}
		}
		return false;
	}

	@Override
	public boolean onCoolTime(boolean b) {
		if (b && this.getFluidBurn() > 0) {
			if (this.productTank.getFluidAmount() > 0) {
				FluidStack ret = this.drain(ForgeDirection.DOWN, 1, true);
				if (ret != null) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * タンク内の燃料チェック
	 */
	public int getFluidBurn() {
		if (productTank.getFluidType() != null) {
			return getFluidBurn(productTank.getFluidType());
		}
		return 0;
	}

	static int getFluidBurn(Fluid f) {
		if (f != null) {
			return RecipeManagerEMT.fuelRegister.getAmount(f);
		}
		return 0;
	}

	/**
	 * チャージできる燃料であるかどうか
	 */
	public boolean isFluidFuel() {
		if (productTank.getFluidType() != null) {
			return isFluidFuel(productTank.getFluidType());
		}
		return false;
	}

	static boolean isFluidFuel(Fluid f) {
		if (f != null) {
			return RecipeManagerEMT.fuelRegister.getAmount(f) > 0;
		}
		return false;
	}

	public static boolean isItemFuel(ItemStack item) {
		if (item != null && item.getItem() != null) {
			if (FluidContainerRegistry.isContainer(item)) {
				FluidStack f = FluidContainerRegistry.getFluidForFilledItem(item);
				if (f != null && f.getFluid() != null) {
					return isFluidFuel(f.getFluid());
				}
			}
		}
		return false;
	}

	/**
	 * 各方向へのチャージ送信処理
	 */
	public int sendChargeFromDir(int i, ForgeDirection dir) {
		return 0;
	}

	private void onServerUpdate() {
		int count = 0;
		if (!this.productTank.isEmpty()) {
			count += this.productTank.getFluidAmount();
		}

		if (lastAmount != count) {
			lastAmount = count;
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}

	@Override
	protected short sendAmount() {
		return this.lastSend;
	}

	/* 液体容器の処理 */

	// スロットの液体コンテナを処理する
	private void drainContainer() {
		ItemStack in = this.getStackInSlot(2);
		if (in != null && FluidContainerRegistry.isFilledContainer(in)) {
			FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(in);
			ItemStack empty = FluidContainerRegistry.drainFluidContainer(in.copy());
			ItemStack current = this.getStackInSlot(3);

			boolean flag1 = fluid.amount <= this.fill(ForgeDirection.UNKNOWN, fluid, false);
			boolean flag2 = false;
			if (empty == null || current == null)
				flag2 = true;
			else if (this.isItemStackable(empty, current))
				flag2 = true;

			if (flag1 && flag2) {
				this.fill(ForgeDirection.UNKNOWN, fluid, true);
				this.incrStackInSlot(3, empty);

				if (this.decrStackSize(2, 1) == null) {
					this.markDirty();
				}
			}
		}
	}

	// スロットの空コンテナを満たす
	private void fillContainer() {
		ItemStack in = this.getStackInSlot(2);
		ItemStack current = this.getStackInSlot(3);
		FluidStack fluid = this.productTank.getFluid();

		if (in != null && fluid != null && FluidContainerRegistry.isEmptyContainer(in)) {
			ItemStack ret = FluidContainerRegistry.fillFluidContainer(fluid, in);

			boolean flag1 = ret != null;
			boolean flag2 = false;
			if (current == null)
				flag2 = true;
			else if (this.isItemStackable(ret, current))
				flag2 = true;

			if (flag1 && flag2) {
				this.drain(ForgeDirection.UNKNOWN, FluidContainerRegistry.getContainerCapacity(ret), true);
				this.incrStackInSlot(3, ret);
				if (this.decrStackSize(2, 1) == null) {
					this.markDirty();
				}
			}
		}
	}

	/* IChargeableMachineのメソッド */

	// チャージゲージ上限も変更可能に。
	@Override
	public int getMaxChargeAmount() {
		return 25600;
	}

	// 燃料はアイテムでは入れられない
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

	@Override
	public int getSizeInventory() {
		return 4;
	}

	@Override
	protected int[] slotsTop() {
		return new int[] {
				0,
				2 };
	}

	@Override
	protected int[] slotsBottom() {
		return new int[] {
				1,
				3 };
	}

	@Override
	protected int[] slotsSides() {
		return new int[] {
				0,
				1,
				2,
				3 };
	}

	@Override
	public String getInventoryName() {
		return StatCollector.translateToLocal("dcs.emt.generatorInv.gui");
	}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack stack) {
		if (par1 > 3)
			return false;
		return par1 > 1 ? (par1 == 2 ? this.isItemFuel(stack) : true) : super.isItemValidForSlot(par1, stack);
	}

	/* ========== 以下、IFluidHandlerのメソッド ========== */

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (resource == null)
			return null;
		if (productTank.getFluidType() == resource.getFluid())
			return productTank.drain(resource.amount, doDrain);
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return this.productTank.drain(maxDrain, doDrain);
	}

	// 外部からの液体の受け入れはなし
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if (from == ForgeDirection.DOWN || resource == null || resource.getFluid() == null) {
			return 0;
		}

		FluidStack current = this.productTank.getFluid();
		FluidStack resourceCopy = resource.copy();
		if (current != null && current.amount > 0 && !current.isFluidEqual(resourceCopy)) {
			return 0;
		}

		int i = 0;
		int used = this.productTank.fill(resourceCopy, doFill);
		resourceCopy.amount -= used;
		i += used;

		return i;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if (fluid == null || this.productTank.isFull())
			return false;

		if (this.productTank.isEmpty())
			return true;
		else {
			return this.productTank.getFluidType() == fluid;
		}
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] { productTank.getInfo() };
	}

	// BuildCraft対応
	@Optional.Method(modid = "BuildCraft|Core")
	@Override
	public ConnectOverride overridePipeConnection(PipeType type, ForgeDirection with) {
		return type == PipeType.FLUID || type == PipeType.ITEM ? ConnectOverride.CONNECT : ConnectOverride.DISCONNECT;
	}
}
