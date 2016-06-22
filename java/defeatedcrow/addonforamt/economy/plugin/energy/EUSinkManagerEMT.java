package defeatedcrow.addonforamt.economy.plugin.energy;

import net.minecraft.tileentity.TileEntity;

public class EUSinkManagerEMT {

	private EUSinkManagerEMT() {
	}

	public static IEUSinkChannelEMT getChannel(TileEntity tile, int cap, int tier) {
		try {
			return new EUSinkChannelEMT(tile, cap, tier);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
