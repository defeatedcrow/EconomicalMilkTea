package defeatedcrow.addonforamt.economy.plugin.energy;

import net.minecraft.tileentity.TileEntity;

public class EUSourceManagerEMT {

	private EUSourceManagerEMT() {
	}

	public static IEUSourceChannelEMT getChannel(TileEntity tile, int cap, int tier) {
		try {
			return new EUSourceChannelEMT(tile, cap, tier);
		} catch (NullPointerException e) {
			return null; // Pass, EnergyNet probably not initialized 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
