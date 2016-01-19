package defeatedcrow.addonforamt.economy.plugin.ss2;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import shift.mceconomy2.api.shop.IProduct;
import shift.sextiarysector.SSBlocks;
import shift.sextiarysector.SSItems;
import defeatedcrow.addonforamt.economy.api.RecipeManagerEMT;
import defeatedcrow.addonforamt.economy.api.order.OrderBiome;
import defeatedcrow.addonforamt.economy.api.order.OrderSeason;
import defeatedcrow.addonforamt.economy.api.order.OrderType;
import defeatedcrow.addonforamt.economy.plugin.mce.EMTProduct;

public class SS2Integration {

	private SS2Integration() {
	}

	public static void load() {
		addOrder();
	}

	static void addOrder() {
		// LONG
		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.shiitake), 2000, 150000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.ARID, "dcs.emt.ordername.long_arid_ss2_mushroom");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.cucumber), 2500, 180000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.ARID, "dcs.emt.ordername.long_arid_ss2_cucunber");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.tomato), 2500, 120000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.ARID, "dcs.emt.ordername.long_arid_ss2_tomato");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.turnip), 3000, 150000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.COLD, "dcs.emt.ordername.long_cold_ss2_turnip");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.onion), 3200, 200000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.COLD, "dcs.emt.ordername.long_cold_ss2_onion");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.corn), 3200, 350000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.COLD, "dcs.emt.ordername.long_cold_ss2_corn");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.eggplant), 1200, 100000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.HELL, "dcs.emt.ordername.long_hell_ss2_eggplant");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.sweetPotato), 1500, 80000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.HELL, "dcs.emt.ordername.long_hell_ss2_sweetpotato");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.radish), 1800, 120000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.HELL, "dcs.emt.ordername.long_hell_ss2_radish");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.greenPepper), 3000, 180000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.COLD, "dcs.emt.ordername.long_cold_ss2_gerrnpepper");

		// SS2は秋に出現する。
		// MIDDLE
		int[] ad = new int[] {
				1,
				2,
				1,
				2,
				3 };
		OrderBiome[] biome = new OrderBiome[] {
				OrderBiome.PLANE,
				OrderBiome.ARID,
				OrderBiome.DAMP,
				OrderBiome.COLD,
				OrderBiome.HELL };
		// spring
		for (int i = 0; i < 5; i++) {
			RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.shiitake), 200, 18000 * ad[i],
					OrderType.MIDDLE, OrderSeason.SPRING, biome[i], "dcs.emt.ordername.middle_ss2_siitake");

			RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.cucumber), 128, 12000 * ad[i],
					OrderType.MIDDLE, OrderSeason.SPRING, biome[i], "dcs.emt.ordername.middle_ss2_cucunber");
		}
		// summer
		for (int i = 0; i < 5; i++) {
			RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.corn), 200, 25000 * ad[i], OrderType.MIDDLE,
					OrderSeason.SUMMER, biome[i], "dcs.emt.ordername.middle_ss2_corn");

			RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.onion), 320, 30000 * ad[i],
					OrderType.MIDDLE, OrderSeason.SUMMER, biome[i], "dcs.emt.ordername.middle_ss2_onion");
		}
		// autumn
		for (int i = 0; i < 5; i++) {
			RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.eggplant), 240, 30000 * ad[i],
					OrderType.MIDDLE, OrderSeason.SPRING, biome[i], "dcs.emt.ordername.middle_ss2_eggp");

			RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.greenPepper), 128, 15000 * ad[i],
					OrderType.MIDDLE, OrderSeason.SPRING, biome[i], "dcs.emt.ordername.middle_ss2_greenp");
		}
		// winter
		for (int i = 0; i < 5; i++) {
			RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.radish), 200, 16000 * ad[i],
					OrderType.MIDDLE, OrderSeason.SPRING, biome[i], "dcs.emt.ordername.middle_ss2_radish");

			RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSBlocks.leafBlock), 320, 12000 * ad[i],
					OrderType.MIDDLE, OrderSeason.SPRING, biome[i], "dcs.emt.ordername.middle_ss2_leaves");
		}

		// SHORT
		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.blueGel), 32, 18000, OrderType.SHORT,
				OrderSeason.AUTUMN, OrderBiome.NONE, "dcs.emt.ordername.short_ss2_bluegel");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.dustWaterLily), 16, 10000, OrderType.SHORT,
				OrderSeason.AUTUMN, OrderBiome.NONE, "dcs.emt.ordername.short_ss2_lilypowder");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.brassIngot), 16, 8000, OrderType.SHORT,
				OrderSeason.AUTUMN, OrderBiome.NONE, "dcs.emt.ordername.short_ss2_brass");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSBlocks.zincPlate), 16, 5000, OrderType.SHORT,
				OrderSeason.AUTUMN, OrderBiome.NONE, "dcs.emt.ordername.short_ss2_zinc");

		// SINGLE
		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.riceBall), 64, 8000, OrderType.SINGLE,
				OrderSeason.AUTUMN, OrderBiome.NONE, "dcs.emt.ordername.single_ss2_riceball");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.curryRice), 32, 12000, OrderType.SINGLE,
				OrderSeason.AUTUMN, OrderBiome.NONE, "dcs.emt.ordername.single_ss2_curry");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(SSItems.chocolate), 32, 6000, OrderType.SINGLE,
				OrderSeason.AUTUMN, OrderBiome.NONE, "dcs.emt.ordername.single_ss2_choco");

	}

	public static ArrayList<IProduct> getProductList() {
		ArrayList<IProduct> list = new ArrayList<IProduct>();
		list.add(new EMTProduct(new ItemStack(SSItems.woodWateringCan), 1200));
		list.add(new EMTProduct(new ItemStack(SSItems.ironSpanner), 2000));
		list.add(new EMTProduct(new ItemStack(SSItems.woodGFStorage), 10000));
		list.add(new EMTProduct(new ItemStack(SSBlocks.woodShaft), 8000));
		list.add(new EMTProduct(new ItemStack(SSBlocks.square), 4000));
		return list;
	}

}
