package defeatedcrow.addonforamt.economy.plugin.energy;

import net.minecraft.tileentity.TileEntity;

public class EUSourceManagerEMT {

	private static boolean isLoaded;

	private EUSourceManagerEMT() {
	}

	public static void init() {
		isLoaded = loadingCheck();
	}

	public static IEUSourceChannelEMT getChannel(TileEntity tile, int cap, int tier) {
		return new EUSourceChannelEMT(tile, cap, tier);
	}

	public static boolean isEULoaded() {
		return isLoaded;
	}

	private static boolean loadingCheck() {

		Class<?> clazz;
		try {
			clazz = Class.forName("ic2.api.energy.EnergyNet");
		} catch (ClassNotFoundException e) {
			return false;
		} catch (Exception e) {
			return false;
		}

		try {
			return clazz.getField("instance") != null;
		} catch (NoSuchFieldException e) {
			return false;
		} catch (SecurityException e) {
			return false;
		}

	}

}
