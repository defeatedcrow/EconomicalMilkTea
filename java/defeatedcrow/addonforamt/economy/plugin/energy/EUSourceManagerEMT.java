package defeatedcrow.addonforamt.economy.plugin.energy;

import net.minecraft.tileentity.TileEntity;

public class EUSourceManagerEMT {

	private EUSourceManagerEMT() {
	}

	public static IEUSourceChannelEMT getChannel(TileEntity tile, int cap, int tier) {
		return new EUSourceChannelEMT(tile, cap, tier);
	}

}
