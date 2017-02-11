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
		try {
			return new EUSourceChannelEMT(tile, cap, tier);
		} catch (NullPointerException e) {
			return null; // Pass, EnergyNet probably not initialized 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
