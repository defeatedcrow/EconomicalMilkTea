package defeatedcrow.addonforamt.economy.common.block;

import mods.defeatedcrow.api.charge.IChargeGenerator;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

/*
 * チャージタンク。
 * 5方向から集めたチャージを16/tickの固定量で送る調整機能もある。
 * */
public class TileENTank extends GeneratorBase {

	// 方向制御
	private ForgeDirection[] sendDir = {
			ForgeDirection.NORTH,
			ForgeDirection.EAST,
			ForgeDirection.SOUTH,
			ForgeDirection.WEST };

	public TileENTank() {
		super();
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
	}

	@Override
	public Packet getDescriptionPacket() {
		return super.getDescriptionPacket();
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
	}

	/* chargeの受け取りは正面以外から */
	@Override
	public boolean onCharge() {
		boolean flag = this.isFullCharged();
		boolean flag2 = false;
		ForgeDirection ref = this.getTileDir();

		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			ForgeDirection opposite = dir.getOpposite();
			if (dir == ref)
				continue;

			TileEntity tile = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
			if (!flag && tile instanceof IChargeGenerator) {
				IChargeGenerator device = (IChargeGenerator) tile;
				int get = device.generateCharge(opposite, true);

				int j = this.chargeAmount + get;

				if (j <= this.getMaxChargeAmount())// 指定したチャージ上限より小さいかどうか
				{
					this.chargeAmount = j;
					device.generateCharge(opposite, false);
					flag2 = get > 0;
				}

				if (this.chargeAmount > this.getMaxChargeAmount()) {
					this.chargeAmount = this.getMaxChargeAmount();
				}
			}
		}

		return flag2;
	}

	@Override
	public boolean onDischarge() {
		return false;
	}

	@Override
	public boolean onCoolTime(boolean b) {
		return false;
	}

	private ForgeDirection getTileDir() {
		int m = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		m = Math.min(m, 3);
		return this.sendDir[m];
	}

	/* === charge interface === */

	/* 正面であればチャージを送り出す */
	@Override
	public int generateCharge(ForgeDirection dir, boolean flag) {
		if (dir == this.getTileDir()) {
			return 0;
		}

		int fuel = this.sendAmount();
		int ret = Math.min(chargeAmount, fuel);
		if (ret > 0) {
			if (!flag)
				this.chargeAmount -= ret;
			return ret;
		}
		return 0;
	}

	@Override
	protected short sendAmount() {
		return 16;
	}

	/* === inventory === */

	@Override
	protected int[] slotsTop() {
		return new int[] { 0 };
	}

	@Override
	protected int[] slotsBottom() {
		return new int[] { 1 };
	}

	@Override
	protected int[] slotsSides() {
		return new int[] {
				0,
				1 };
	}

	@Override
	public String getInventoryName() {
		return StatCollector.translateToLocal("dcs.emt.tankInv.gui");
	}

}
