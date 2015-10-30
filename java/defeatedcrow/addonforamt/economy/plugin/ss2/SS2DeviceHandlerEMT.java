package defeatedcrow.addonforamt.economy.plugin.ss2;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import shift.sextiarysector.api.gearforce.tileentity.IGearForceHandler;

public class SS2DeviceHandlerEMT {

	private SS2DeviceHandlerEMT() {
	}

	public static boolean isGFDevice(TileEntity tile) {
		if (!DCsAppleMilk.SuccessLoadSSector)
			return false;
		return tile instanceof IGearForceHandler;
	}

	public static int inputEnergy(TileEntity tile, ForgeDirection dir, int tier, int amount, boolean simulate) {
		if (!DCsAppleMilk.SuccessLoadSSector)
			return 0;
		int ret = 0;

		if (isGFDevice(tile)) {
			IGearForceHandler handler = (IGearForceHandler) tile;

			if (handler.canInterface(dir))
				ret = handler.addEnergy(dir, tier, amount, simulate);
		}

		return ret;
	}

}
