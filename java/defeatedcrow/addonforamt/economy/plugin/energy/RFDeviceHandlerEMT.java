package defeatedcrow.addonforamt.economy.plugin.energy;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyHandler;

/**
 * RFを保持できる装置を操作するための中継クラス。
 */
public class RFDeviceHandlerEMT {

	private RFDeviceHandlerEMT() {
	}

	public static boolean isRFDevice(TileEntity tile) {
		return tile instanceof IEnergyHandler;
	}

	public static int inputEnergy(ForgeDirection dir, TileEntity tile, int amount, boolean simulate) {
		int ret = 0;

		if (isRFDevice(tile)) {
			IEnergyHandler handler = (IEnergyHandler) tile;

			if (handler.canConnectEnergy(dir))
				ret = handler.receiveEnergy(dir, amount, simulate);
		}

		return ret;
	}

}
