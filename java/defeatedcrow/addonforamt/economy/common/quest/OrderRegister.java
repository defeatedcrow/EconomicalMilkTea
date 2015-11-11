package defeatedcrow.addonforamt.economy.common.quest;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import defeatedcrow.addonforamt.economy.api.OrderBiome;
import defeatedcrow.addonforamt.economy.api.OrderSeason;
import defeatedcrow.addonforamt.economy.api.OrderType;
import defeatedcrow.addonforamt.economy.api.RecipeManagerEMT;

public class OrderRegister {

	private OrderRegister() {
	}

	public static void addBasicOrder() {
		longOrder();
		middleOrder();
		shortOrder();
		singleOrder();
	}

	// 年間Order。バニラ作物の大量納品が多い。
	static void longOrder() {
		// year
		// plane
		RecipeManagerEMT.orderRegister.addRecipe("cropRice", 16000, 300000, OrderType.LONG, OrderSeason.NONE,
				OrderBiome.PLANE, "dcs.emt.ordername.long_plane_rice");

		RecipeManagerEMT.orderRegister.addRecipe("cropWheat", 32000, 280000, OrderType.LONG, OrderSeason.NONE,
				OrderBiome.PLANE, "dcs.emt.ordername.long_plane_wheat");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Blocks.melon_block, 1, 0), 6400, 150000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.long_plane_melon");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Blocks.cactus, 1, 0), 12000, 320000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.long_plane_cactus");

		// arid
		RecipeManagerEMT.orderRegister.addRecipe("cropRice", 16000, 450000, OrderType.LONG, OrderSeason.NONE,
				OrderBiome.ARID, "dcs.emt.ordername.long_arid_rice");

		RecipeManagerEMT.orderRegister.addRecipe("cropWheat", 32000, 280000, OrderType.LONG, OrderSeason.NONE,
				OrderBiome.ARID, "dcs.emt.ordername.long_arid_wheat");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Blocks.pumpkin, 1, 0), 6400, 180000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.ARID, "dcs.emt.ordername.long_arid_pumpkin");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.dye, 1, 3), 8000, 200000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.ARID, "dcs.emt.ordername.long_arid_choco");

		// damp
		RecipeManagerEMT.orderRegister.addRecipe("cropRice", 32000, 240000, OrderType.LONG, OrderSeason.NONE,
				OrderBiome.DAMP, "dcs.emt.ordername.long_damp_rice");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.reeds, 1, 0), 16000, 120000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.DAMP, "dcs.emt.ordername.long_damp_reeds");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Blocks.red_mushroom, 1, 0), 6400, 300000,
				OrderType.LONG, OrderSeason.NONE, OrderBiome.DAMP, "dcs.emt.ordername.long_damp_redmush");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Blocks.brown_mushroom, 1, 0), 6400, 300000,
				OrderType.LONG, OrderSeason.NONE, OrderBiome.DAMP, "dcs.emt.ordername.long_damp_brownmush");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Blocks.cactus, 1, 0), 8800, 180000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.DAMP, "dcs.emt.ordername.long_damp_cactus");

		// cold
		RecipeManagerEMT.orderRegister.addRecipe("cropRice", 16000, 500000, OrderType.LONG, OrderSeason.NONE,
				OrderBiome.COLD, "dcs.emt.ordername.long_cold_rice");

		RecipeManagerEMT.orderRegister.addRecipe("cropWheat", 32000, 360000, OrderType.LONG, OrderSeason.NONE,
				OrderBiome.COLD, "dcs.emt.ordername.long_cold_wheat");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.reeds, 1, 0), 16000, 360000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.COLD, "dcs.emt.ordername.long_cold_reeds");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.carrot, 1, 0), 16000, 250000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.COLD, "dcs.emt.ordername.long_cold_carrot");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.potato, 1, 0), 16000, 250000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.COLD, "dcs.emt.ordername.long_cold_potato");

		// hell
		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Blocks.pumpkin, 1, 0), 6400, 640000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.HELL, "dcs.emt.ordername.long_hell_pumpkin");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Blocks.melon_block, 1, 0), 6400, 500000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.HELL, "dcs.emt.ordername.long_hell_melon");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.reeds, 1, 0), 16000, 500000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.HELL, "dcs.emt.ordername.long_hell_reeds");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.carrot, 1, 0), 16000, 400000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.HELL, "dcs.emt.ordername.long_hell_carrot");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.potato, 1, 0), 16000, 400000, OrderType.LONG,
				OrderSeason.NONE, OrderBiome.HELL, "dcs.emt.ordername.long_hell_potato");
	}

	// 月間Order。MOD作物が中心。Biomeでは価格のみ変化する。
	static void middleOrder() {
		int[] ad = new int[] {
				2,
				3,
				2,
				4,
				5 };
		OrderBiome[] biome = new OrderBiome[] {
				OrderBiome.PLANE,
				OrderBiome.ARID,
				OrderBiome.DAMP,
				OrderBiome.COLD,
				OrderBiome.HELL };
		// spring
		for (int i = 0; i < 5; i++) {
			RecipeManagerEMT.orderRegister.addRecipe("cropApple", 640, 18000 * ad[i], OrderType.MIDDLE,
					OrderSeason.SPRING, biome[i], "dcs.emt.ordername.middle_spring_apple");

			RecipeManagerEMT.orderRegister.addRecipe("cropCassis", 640, 1500 * ad[i], OrderType.MIDDLE,
					OrderSeason.SPRING, biome[i], "dcs.emt.ordername.middle_spring_cassis");

			RecipeManagerEMT.orderRegister.addRecipe("foodClam", 320, 18000 * ad[i], OrderType.MIDDLE,
					OrderSeason.SPRING, biome[i], "dcs.emt.ordername.middle_spring_apple");

			RecipeManagerEMT.orderRegister.addRecipe("dropHoney", 320, 28000 * ad[i], OrderType.MIDDLE,
					OrderSeason.SPRING, biome[i], "dcs.emt.ordername.middle_spring_honey");

			RecipeManagerEMT.orderRegister.addRecipe("treeSapling", 512, 8000 * ad[i], OrderType.MIDDLE,
					OrderSeason.SPRING, biome[i], "dcs.emt.ordername.middle_spring_sap");
		}

		// summer
		for (int i = 0; i < 4; i++) {
			RecipeManagerEMT.orderRegister.addRecipe("cropTomato", 320, 20000 * ad[i], OrderType.MIDDLE,
					OrderSeason.SUMMER, biome[i], "dcs.emt.ordername.middle_summer_tomato");

			RecipeManagerEMT.orderRegister.addRecipe("foodSalt", 320, 16000 * ad[i], OrderType.MIDDLE,
					OrderSeason.SUMMER, biome[i], "dcs.emt.ordername.middle_summer_salt");

			RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.fish, 1, 0), 128, 10000 * ad[i],
					OrderType.MIDDLE, OrderSeason.SUMMER, biome[i], "dcs.emt.ordername.middle_summer_fish");

			RecipeManagerEMT.orderRegister.addRecipe("cropMint", 512, 9000 * ad[i], OrderType.MIDDLE,
					OrderSeason.SUMMER, biome[i], "dcs.emt.ordername.middle_summer_mint");

			RecipeManagerEMT.orderRegister.addRecipe("foodTea", 512, 17500 * ad[i], OrderType.MIDDLE,
					OrderSeason.SUMMER, biome[i], "dcs.emt.ordername.middle_summer_tea");
		}

		// autumn
		for (int i = 0; i < 4; i++) {
			RecipeManagerEMT.orderRegister.addRecipe("cropRice", 512, 12000 * ad[i], OrderType.MIDDLE,
					OrderSeason.AUTUMN, biome[i], "dcs.emt.ordername.middle_autumn_rice");

			RecipeManagerEMT.orderRegister.addRecipe("cropGrape", 512, 28000 * ad[i], OrderType.MIDDLE,
					OrderSeason.AUTUMN, biome[i], "dcs.emt.ordername.middle_autumn_grape");

			RecipeManagerEMT.orderRegister.addRecipe("logWood", 1280, 10000 * ad[i], OrderType.MIDDLE,
					OrderSeason.AUTUMN, biome[i], "dcs.emt.ordername.middle_autumn_log");

			RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.fish, 1, 1), 128, 50000 * ad[i],
					OrderType.MIDDLE, OrderSeason.AUTUMN, biome[i], "dcs.emt.ordername.middle_autumn_salmon");
		}

		// winter
		for (int i = 0; i < 4; i++) {
			RecipeManagerEMT.orderRegister.addRecipe("cropYuzu", 1280, 9000 * ad[i], OrderType.MIDDLE,
					OrderSeason.WINTER, biome[i], "dcs.emt.ordername.middle_winter_yuzu");

			RecipeManagerEMT.orderRegister.addRecipe("cropApple", 512, 12000 * ad[i], OrderType.MIDDLE,
					OrderSeason.WINTER, biome[i], "dcs.emt.ordername.middle_winter_apple");

			RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Blocks.double_plant, 1, 0), 320, 8000 * ad[i],
					OrderType.MIDDLE, OrderSeason.WINTER, biome[i], "dcs.emt.ordername.middle_winter_rose");

			RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Blocks.double_plant, 1, 1), 320, 8000 * ad[i],
					OrderType.MIDDLE, OrderSeason.WINTER, biome[i], "dcs.emt.ordername.middle_winter_botan");
		}

	}

	// shortはモブドロップなど
	static void shortOrder() {

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.bone, 1, 0), 128, 4000, OrderType.SHORT,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.short_bone_128");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.ghast_tear, 1, 0), 5, 5000, OrderType.SHORT,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.short_tear_5");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.porkchop, 1, 0), 64, 2500, OrderType.SHORT,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.short_pork_64");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.string, 1, 0), 64, 2000, OrderType.SHORT,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.short_string_64");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.rotten_flesh, 1, 0), 16, 1000, OrderType.SHORT,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.short_rotten_16");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.beef, 1, 0), 128, 4000, OrderType.SHORT,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.short_beef_128");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.skull, 1, 0), 5, 20000, OrderType.SHORT,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.short_skull_5");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.egg, 1, 0), 64, 5000, OrderType.SHORT,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.short_egg_64");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Blocks.obsidian, 1, 0), 64, 10000, OrderType.SHORT,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.short_obsidian_64");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.cake, 1, 0), 64, 20000, OrderType.SHORT,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.short_cake_64");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.blaze_rod, 1, 0), 16, 5000, OrderType.SHORT,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.short_brazerod_16");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.rotten_flesh, 1, 0), 64, 2000, OrderType.SHORT,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.short_rotten_64");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.bone, 1, 0), 64, 3000, OrderType.SHORT,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.short_bone_64");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Blocks.ice, 1, 0), 16, 2500, OrderType.SHORT,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.short_ice_16");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Blocks.yellow_flower, 1, 0), 32, 3000, OrderType.SHORT,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.short_yellowf_32");

	}

	// singleは加工品の高額買い取りか、作物の単発
	static void singleOrder() {
		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Blocks.bookshelf, 1, 0), 5, 4000, OrderType.SINGLE,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.single_bookshelf_5");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.arrow, 1, 0), 32, 1000, OrderType.SINGLE,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.single_arrow_32");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Blocks.tnt, 1, 0), 3, 2000, OrderType.SINGLE,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.single_tnt_3");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.bed, 1, 0), 1, 1000, OrderType.SINGLE,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.single_bed_1");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.apple, 1, 0), 5, 1000, OrderType.SINGLE,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.single_apple_5");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Blocks.waterlily, 1, 0), 16, 3000, OrderType.SINGLE,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.single_waterlily_16");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.book, 1, 0), 12, 1200, OrderType.SINGLE,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.single_book_10");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.minecart, 1, 0), 5, 3000, OrderType.SINGLE,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.single_minecart_5");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Blocks.dispenser, 1, 0), 3, 1000, OrderType.SINGLE,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.single_dispenser_3");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Blocks.chest, 1, 0), 16, 2000, OrderType.SINGLE,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.single_chest_16");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.boat, 1, 0), 3, 800, OrderType.SINGLE,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.single_boat_3");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Blocks.piston, 1, 0), 8, 7500, OrderType.SINGLE,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.single_piston_8");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Items.reeds, 1, 0), 32, 5000, OrderType.SINGLE,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.single_reeds_32");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(Blocks.grass, 1, 0), 16, 2000, OrderType.SINGLE,
				OrderSeason.NONE, OrderBiome.PLANE, "dcs.emt.ordername.single_grass_16");

	}
}
