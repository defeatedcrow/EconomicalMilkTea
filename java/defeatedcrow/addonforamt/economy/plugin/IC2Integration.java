package defeatedcrow.addonforamt.economy.plugin;

import ic2.api.item.IC2Items;

import java.util.ArrayList;

import shift.mceconomy2.api.shop.IProduct;
import defeatedcrow.addonforamt.economy.api.RecipeManagerEMT;
import defeatedcrow.addonforamt.economy.api.order.OrderBiome;
import defeatedcrow.addonforamt.economy.api.order.OrderSeason;
import defeatedcrow.addonforamt.economy.api.order.OrderType;
import defeatedcrow.addonforamt.economy.plugin.mce.EMTProduct;

public class IC2Integration {

	private IC2Integration() {
	}

	public static void load() {
		addOrder();
	}

	static void addOrder() {
		// LONGはなし

		// IC2系など工業系は冬に出現する
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
		// winter
		for (int i = 0; i < 5; i++) {
			if (IC2Items.getItem("coffeeBeans") != null) {
				RecipeManagerEMT.orderRegister.addRecipe(IC2Items.getItem("coffeeBeans"), 320, 25000 * ad[i],
						OrderType.MIDDLE, OrderSeason.WINTER, biome[i], "dcs.emt.ordername.middle_ic2_coffee");
			}

			if (IC2Items.getItem("hops") != null) {
				RecipeManagerEMT.orderRegister.addRecipe(IC2Items.getItem("hops"), 320, 32000 * ad[i],
						OrderType.MIDDLE, OrderSeason.WINTER, biome[i], "dcs.emt.ordername.middle_ic2_hops");
			}

			if (IC2Items.getItem("terraWart") != null) {
				RecipeManagerEMT.orderRegister.addRecipe(IC2Items.getItem("terraWart"), 640, 50000 * ad[i],
						OrderType.MIDDLE, OrderSeason.WINTER, biome[i], "dcs.emt.ordername.middle_ic2_wart");
			}

			if (IC2Items.getItem("grinPowder") != null) {
				RecipeManagerEMT.orderRegister.addRecipe(IC2Items.getItem("grinPowder"), 128, 20000 * ad[i],
						OrderType.MIDDLE, OrderSeason.WINTER, biome[i], "dcs.emt.ordername.middle_ic2_grin");
			}

			if (IC2Items.getItem("rubberSapling") != null) {
				RecipeManagerEMT.orderRegister.addRecipe(IC2Items.getItem("rubberSapling"), 128, 25000 * ad[i],
						OrderType.MIDDLE, OrderSeason.WINTER, biome[i], "dcs.emt.ordername.middle_ic2_rubberSap");
			}
		}

		// SHORT 金属系素材など
		RecipeManagerEMT.orderRegister.addRecipe("itemRubber", 64, 24000, OrderType.SHORT, OrderSeason.WINTER,
				OrderBiome.NONE, "dcs.emt.ordername.short_ic2_rubber");

		RecipeManagerEMT.orderRegister.addRecipe("plateBronze", 10, 12000, OrderType.SHORT, OrderSeason.WINTER,
				OrderBiome.NONE, "dcs.emt.ordername.short_ic2_bronzeP");

		RecipeManagerEMT.orderRegister.addRecipe("plateSteel", 64, 88000, OrderType.SHORT, OrderSeason.WINTER,
				OrderBiome.NONE, "dcs.emt.ordername.short_ic2_steelP");

		if (IC2Items.getItem("scrap") != null) {
			RecipeManagerEMT.orderRegister.addRecipe(IC2Items.getItem("scrap"), 320, 50000, OrderType.SHORT,
					OrderSeason.WINTER, OrderBiome.NONE, "dcs.emt.ordername.short_ic2_scrap");
		}

		if (IC2Items.getItem("casingcopper") != null) {
			RecipeManagerEMT.orderRegister.addRecipe(IC2Items.getItem("casingcopper"), 32, 16000, OrderType.SHORT,
					OrderSeason.WINTER, OrderBiome.NONE, "dcs.emt.ordername.short_ic2_copperC");
		}

		if (IC2Items.getItem("casingtin") != null) {
			RecipeManagerEMT.orderRegister.addRecipe(IC2Items.getItem("casingtin"), 32, 16000, OrderType.SHORT,
					OrderSeason.WINTER, OrderBiome.NONE, "dcs.emt.ordername.short_ic2_tinC");
		}

		// SINGLE 完成機械
		if (IC2Items.getItem("solarPanel") != null) {
			RecipeManagerEMT.orderRegister.addRecipe(IC2Items.getItem("solarPanel"), 2, 18000, OrderType.SINGLE,
					OrderSeason.WINTER, OrderBiome.NONE, "dcs.emt.ordername.single_ic2_solar");
		}

		if (IC2Items.getItem("extractor") != null) {
			RecipeManagerEMT.orderRegister.addRecipe(IC2Items.getItem("extractor"), 2, 22000, OrderType.SINGLE,
					OrderSeason.WINTER, OrderBiome.NONE, "dcs.emt.ordername.single_ic2_extractor");
		}

		if (IC2Items.getItem("electroFurnace") != null) {
			RecipeManagerEMT.orderRegister.addRecipe(IC2Items.getItem("electroFurnace"), 2, 20000, OrderType.SINGLE,
					OrderSeason.WINTER, OrderBiome.NONE, "dcs.emt.ordername.single_ic2_efurnace");
		}

		if (IC2Items.getItem("pump") != null) {
			RecipeManagerEMT.orderRegister.addRecipe(IC2Items.getItem("pump"), 2, 20000, OrderType.SINGLE,
					OrderSeason.WINTER, OrderBiome.NONE, "dcs.emt.ordername.single_ic2_pump");
		}

		if (IC2Items.getItem("cesuUnit") != null) {
			RecipeManagerEMT.orderRegister.addRecipe(IC2Items.getItem("cesuUnit"), 2, 20000, OrderType.SINGLE,
					OrderSeason.WINTER, OrderBiome.NONE, "dcs.emt.ordername.single_ic2_cesu");
		}

	}

	public static ArrayList<IProduct> getIC2ProductList() {
		ArrayList<IProduct> list = new ArrayList<IProduct>();
		if (IC2Items.getItem("treetap") != null) {
			list.add(new EMTProduct(IC2Items.getItem("treetap"), 800));
		}
		if (IC2Items.getItem("wrench") != null) {
			list.add(new EMTProduct(IC2Items.getItem("wrench"), 5000));
		}
		if (IC2Items.getItem("cutter") != null) {
			list.add(new EMTProduct(IC2Items.getItem("cutter"), 10000));
		}
		if (IC2Items.getItem("ForgeHammer") != null) {
			list.add(new EMTProduct(IC2Items.getItem("ForgeHammer"), 12000));
		}
		if (IC2Items.getItem("cell") != null) {
			list.add(new EMTProduct(IC2Items.getItem("cell"), 500));
		}
		if (IC2Items.getItem("reBattery") != null) {
			list.add(new EMTProduct(IC2Items.getItem("reBattery"), 4000));
		}
		if (IC2Items.getItem("electronicCircuit") != null) {
			list.add(new EMTProduct(IC2Items.getItem("electronicCircuit"), 10000));
		}
		if (IC2Items.getItem("tinCableItem") != null) {
			list.add(new EMTProduct(IC2Items.getItem("tinCableItem"), 1500));
		}
		if (IC2Items.getItem("insulatedCopperCableItem") != null) {
			list.add(new EMTProduct(IC2Items.getItem("insulatedCopperCableItem"), 3000));
		}
		return list;
	}

}
