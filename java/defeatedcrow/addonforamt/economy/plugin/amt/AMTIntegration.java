package defeatedcrow.addonforamt.economy.plugin.amt;

import java.util.ArrayList;

import defeatedcrow.addonforamt.economy.api.RecipeManagerEMT;
import defeatedcrow.addonforamt.economy.api.order.OrderBiome;
import defeatedcrow.addonforamt.economy.api.order.OrderSeason;
import defeatedcrow.addonforamt.economy.api.order.OrderType;
import defeatedcrow.addonforamt.economy.plugin.mce.EMTProduct;
import mods.defeatedcrow.common.DCsAppleMilk;
import mods.defeatedcrow.common.config.PropertyHandler;
import net.minecraft.item.ItemStack;
import shift.mceconomy2.api.shop.IProduct;

public class AMTIntegration {

	public static int RFrate = 10;
	public static int EUrate = 2;
	public static int GFrate = 3;

	private AMTIntegration() {}

	public static void load() {
		loadProperty();
		addOrder();
	}

	public static boolean getDebug() {
		return DCsAppleMilk.debugMode;
	}

	static void loadProperty() {
		RFrate = PropertyHandler.rateRF();
		EUrate = PropertyHandler.rateEU();
		GFrate = PropertyHandler.rateGF();

		EnergyRate.rateRF = RFrate;
		EnergyRate.rateGF = GFrate;
		EnergyRate.rateEU = EUrate;
	}

	static void addOrder() {
		// LONG
		RecipeManagerEMT.orderRegister.addRecipe("cropTea", 10000, 250000, OrderType.LONG, OrderSeason.NONE,
				OrderBiome.PLANE, "dcs.emt.ordername.long_plane_amt_tea");

		RecipeManagerEMT.orderRegister.addRecipe("cropSpiceleaf", 6000, 180000, OrderType.LONG, OrderSeason.NONE,
				OrderBiome.PLANE, "dcs.emt.ordername.long_plane_amt_mint");

		RecipeManagerEMT.orderRegister.addRecipe("cropCamellia", 5000, 220000, OrderType.LONG, OrderSeason.NONE,
				OrderBiome.PLANE, "dcs.emt.ordername.long_plane_amt_camellia");

		RecipeManagerEMT.orderRegister.addRecipe("foodClam", 8000, 300000, OrderType.LONG, OrderSeason.NONE,
				OrderBiome.PLANE, "dcs.emt.ordername.long_plane_amt_clam");

		RecipeManagerEMT.orderRegister.addRecipe("cropTea", 10000, 350000, OrderType.LONG, OrderSeason.NONE,
				OrderBiome.ARID, "dcs.emt.ordername.long_arid_amt_tea");

		RecipeManagerEMT.orderRegister.addRecipe("cropSpiceleaf", 6000, 360000, OrderType.LONG, OrderSeason.NONE,
				OrderBiome.COLD, "dcs.emt.ordername.long_cold_amt_mint");

		RecipeManagerEMT.orderRegister.addRecipe("cropCamellia", 5000, 300000, OrderType.LONG, OrderSeason.NONE,
				OrderBiome.COLD, "dcs.emt.ordername.long_cold_amt_camellia");

		RecipeManagerEMT.orderRegister.addRecipe("foodClam", 12000, 540000, OrderType.LONG, OrderSeason.NONE,
				OrderBiome.HELL, "dcs.emt.ordername.long_hell_amt_clam");

		// MIDDLE
		// AMT作物はデフォルトで含まれるため、MIDDLE枠には改めて追加はしない

		// SHORT
		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(DCsAppleMilk.EXItems, 1, 1), 32, 10000, OrderType.SHORT,
				OrderSeason.NONE, OrderBiome.NONE, "dcs.emt.ordername.short_amt_nikawa");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(DCsAppleMilk.condensedMIlk, 1, 0), 54, 12000,
				OrderType.SHORT, OrderSeason.NONE, OrderBiome.NONE, "dcs.emt.ordername.short_amt_condencedmilk");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(DCsAppleMilk.condensedMIlk, 1, 3), 64, 8000,
				OrderType.SHORT, OrderSeason.NONE, OrderBiome.NONE, "dcs.emt.ordername.short_amt_yuzujam");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(DCsAppleMilk.chalcedony, 1, 32767), 32, 50000,
				OrderType.SHORT, OrderSeason.NONE, OrderBiome.NONE, "dcs.emt.ordername.short_amt_chal");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(DCsAppleMilk.icyCrystal, 1, 0), 32, 50000,
				OrderType.SHORT, OrderSeason.NONE, OrderBiome.NONE, "dcs.emt.ordername.short_amt_icycristal");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(DCsAppleMilk.wipeBox, 1, 1), 72, 69000, OrderType.SHORT,
				OrderSeason.NONE, OrderBiome.NONE, "dcs.emt.ordername.short_amt_kimwipes");

		// SINGLE AMTは春
		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(DCsAppleMilk.yuzuBat, 1, 0), 3, 8000, OrderType.SINGLE,
				OrderSeason.SPRING, OrderBiome.NONE, "dcs.emt.ordername.single_amt_yuzubat");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 50), 8, 18000,
				OrderType.SINGLE, OrderSeason.SPRING, OrderBiome.NONE, "dcs.emt.ordername.single_amt_beer");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(DCsAppleMilk.itemLargeBottle, 1, 49), 2, 3000,
				OrderType.SINGLE, OrderSeason.SPRING, OrderBiome.NONE, "dcs.emt.ordername.single_amt_sake");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(DCsAppleMilk.itemCordial, 1, 3), 4, 9000,
				OrderType.SINGLE, OrderSeason.SPRING, OrderBiome.NONE, "dcs.emt.ordername.single_amt_appleliqueur");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(DCsAppleMilk.bottleCamOil, 1, 0), 12, 10000,
				OrderType.SINGLE, OrderSeason.SPRING, OrderBiome.NONE, "dcs.emt.ordername.single_amt_camoil");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(DCsAppleMilk.appleTart, 1, 1), 8, 4000, OrderType.SINGLE,
				OrderSeason.SPRING, OrderBiome.NONE, "dcs.emt.ordername.single_amt_cassistart");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(DCsAppleMilk.cLamp, 1, 4), 12, 30000, OrderType.SINGLE,
				OrderSeason.SPRING, OrderBiome.NONE, "dcs.emt.ordername.single_amt_challamp");

	}

	public static ArrayList<IProduct> getColdProductList() {
		ArrayList<IProduct> list = new ArrayList<IProduct>();
		list.add(new EMTProduct(new ItemStack(DCsAppleMilk.blockIcecream, 1, 0), 120));
		list.add(new EMTProduct(new ItemStack(DCsAppleMilk.blockIcecream, 1, 1), 120));
		list.add(new EMTProduct(new ItemStack(DCsAppleMilk.blockIcecream, 1, 2), 120));
		list.add(new EMTProduct(new ItemStack(DCsAppleMilk.blockIcecream, 1, 3), 120));
		list.add(new EMTProduct(new ItemStack(DCsAppleMilk.EXItems, 1, 4), 300));
		list.add(new EMTProduct(new ItemStack(DCsAppleMilk.icyToffyApple, 1, 0), 200));
		return list;
	}

}
