package defeatedcrow.addonforamt.economy.plugin;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import defeatedcrow.addonforamt.economy.api.RecipeManagerEMT;
import defeatedcrow.addonforamt.economy.api.order.OrderBiome;
import defeatedcrow.addonforamt.economy.api.order.OrderSeason;
import defeatedcrow.addonforamt.economy.api.order.OrderType;
import defeatedcrow.addonforamt.economy.plugin.amt.AMTIntegration;
import defeatedcrow.addonforamt.economy.plugin.amt.FluidityIntegration;
import defeatedcrow.addonforamt.economy.plugin.amt.IronChainIntegration;
import defeatedcrow.addonforamt.economy.plugin.ss2.SS2Integration;

public class IntegrationLoader {

	private IntegrationLoader() {
	}

	public static void load() {
		addOrder();
		addOre();

		if (Loader.isModLoaded("DCsAppleMilk")) {
			AMTIntegration.load();
		}
		if (Loader.isModLoaded("FluidityDC")) {
			FluidityIntegration.load();
		}
		if (Loader.isModLoaded("DCIronChain")) {
			IronChainIntegration.load();
		}
		if (Loader.isModLoaded("SextiarySector")) {
			SS2Integration.load();
		}
		if (Loader.isModLoaded("jp-plusplus-ir2")) {
			IR3Integration.load();
		}
		if (Loader.isModLoaded("IC2")) {
			IC2Integration.load();
		}
		if (Loader.isModLoaded("BuildCraft|Core")) {
			BCIntegration.load();
		}
		if (Loader.isModLoaded("Forestry")) {
			FFMIntegration.load();
		}
		if (Loader.isModLoaded("Railcraft")) {
			RCIntegration.load();
		}
	}

	static void addOrder() {
		// LONG
		// ALID 果樹
		if (OreDictionary.doesOreNameExist("cropCherry"))
			RecipeManagerEMT.orderRegister.addRecipe("cropCherry", 5000, 180000, OrderType.LONG, OrderSeason.NONE,
					OrderBiome.ARID, "dcs.emt.ordername.long_arid_cherry");

		if (OreDictionary.doesOreNameExist("cropPeach"))
			RecipeManagerEMT.orderRegister.addRecipe("cropPeach", 5000, 200000, OrderType.LONG, OrderSeason.NONE,
					OrderBiome.ARID, "dcs.emt.ordername.long_arid_peach");

		if (OreDictionary.doesOreNameExist("cropPlum"))
			RecipeManagerEMT.orderRegister.addRecipe("cropPlum", 4200, 200000, OrderType.LONG, OrderSeason.NONE,
					OrderBiome.ARID, "dcs.emt.ordername.long_arid_plum");

		if (OreDictionary.doesOreNameExist("cropLemon"))
			RecipeManagerEMT.orderRegister.addRecipe("cropLemon", 5000, 240000, OrderType.LONG, OrderSeason.NONE,
					OrderBiome.ARID, "dcs.emt.ordername.long_arid_lemon");

		if (OreDictionary.doesOreNameExist("cropOrange"))
			RecipeManagerEMT.orderRegister.addRecipe("cropOrange", 5000, 200000, OrderType.LONG, OrderSeason.NONE,
					OrderBiome.ARID, "dcs.emt.ordername.long_arid_orange");

		if (OreDictionary.doesOreNameExist("cropWalnat"))
			RecipeManagerEMT.orderRegister.addRecipe("cropWalnat", 5000, 300000, OrderType.LONG, OrderSeason.NONE,
					OrderBiome.ARID, "dcs.emt.ordername.long_arid_walnat");

		if (OreDictionary.doesOreNameExist("cropChestnut"))
			RecipeManagerEMT.orderRegister.addRecipe("cropWalnat", 5000, 280000, OrderType.LONG, OrderSeason.NONE,
					OrderBiome.ARID, "dcs.emt.ordername.long_arid_chestnut");

		if (OreDictionary.doesOreNameExist("cropParsimmon"))
			RecipeManagerEMT.orderRegister.addRecipe("cropChestnut", 6000, 250000, OrderType.LONG, OrderSeason.NONE,
					OrderBiome.ARID, "dcs.emt.ordername.long_arid_parsimmon");

		if (OreDictionary.doesOreNameExist("bamboo"))
			RecipeManagerEMT.orderRegister.addRecipe("bamboo", 10000, 150000, OrderType.LONG, OrderSeason.NONE,
					OrderBiome.ARID, "dcs.emt.ordername.long_arid_bamboo");

		// COLD 畑作
		if (OreDictionary.doesOreNameExist("cropOnion"))
			RecipeManagerEMT.orderRegister.addRecipe("cropOnion", 6000, 250000, OrderType.LONG, OrderSeason.NONE,
					OrderBiome.ARID, "dcs.emt.ordername.long_cold_onion");

		if (OreDictionary.doesOreNameExist("cropEggplant"))
			RecipeManagerEMT.orderRegister.addRecipe("cropEggplant", 6800, 400000, OrderType.LONG, OrderSeason.NONE,
					OrderBiome.ARID, "dcs.emt.ordername.long_cold_eggplant");

		if (OreDictionary.doesOreNameExist("cropSoybeans"))
			RecipeManagerEMT.orderRegister.addRecipe("cropSoybeans", 8000, 220000, OrderType.LONG, OrderSeason.NONE,
					OrderBiome.ARID, "dcs.emt.ordername.long_cold_soy");

		if (OreDictionary.doesOreNameExist("cropAzuki"))
			RecipeManagerEMT.orderRegister.addRecipe("cropAzuki", 6000, 320000, OrderType.LONG, OrderSeason.NONE,
					OrderBiome.ARID, "dcs.emt.ordername.long_cold_azuki");

		if (OreDictionary.doesOreNameExist("cropCabbage"))
			RecipeManagerEMT.orderRegister.addRecipe("cropCabbage", 6000, 300000, OrderType.LONG, OrderSeason.NONE,
					OrderBiome.ARID, "dcs.emt.ordername.long_cold_cabbage");

		if (OreDictionary.doesOreNameExist("cropLeek"))
			RecipeManagerEMT.orderRegister.addRecipe("cropLeek", 8000, 180000, OrderType.LONG, OrderSeason.NONE,
					OrderBiome.ARID, "dcs.emt.ordername.long_cold_leek");

		if (OreDictionary.doesOreNameExist("cropLettuce"))
			RecipeManagerEMT.orderRegister.addRecipe("cropLettuce", 6000, 300000, OrderType.LONG, OrderSeason.NONE,
					OrderBiome.ARID, "dcs.emt.ordername.long_cold_lettuce");

		// MIDDLE 汎用オーダーなので通年、安め
		if (OreDictionary.doesOreNameExist("cropOnion"))
			RecipeManagerEMT.orderRegister.addRecipe("cropOnion", 320, 28000, OrderType.MIDDLE, OrderSeason.NONE,
					OrderBiome.NONE, "dcs.emt.ordername.middle_onion");

		if (OreDictionary.doesOreNameExist("cropEggplant"))
			RecipeManagerEMT.orderRegister.addRecipe("cropEggplant", 480, 30000, OrderType.MIDDLE, OrderSeason.NONE,
					OrderBiome.NONE, "dcs.emt.ordername.middle_eggplant");

		if (OreDictionary.doesOreNameExist("cropSoybeans"))
			RecipeManagerEMT.orderRegister.addRecipe("cropSoybeans", 500, 20000, OrderType.MIDDLE, OrderSeason.NONE,
					OrderBiome.NONE, "dcs.emt.ordername.middle_soy");

		if (OreDictionary.doesOreNameExist("cropAzuki"))
			RecipeManagerEMT.orderRegister.addRecipe("cropAzuki", 320, 30000, OrderType.MIDDLE, OrderSeason.NONE,
					OrderBiome.NONE, "dcs.emt.ordername.middle_azuki");

		if (OreDictionary.doesOreNameExist("cropCabbage"))
			RecipeManagerEMT.orderRegister.addRecipe("cropCabbage", 480, 40000, OrderType.MIDDLE, OrderSeason.NONE,
					OrderBiome.NONE, "dcs.emt.ordername.middle_cabbage");

		if (OreDictionary.doesOreNameExist("cropLeek"))
			RecipeManagerEMT.orderRegister.addRecipe("cropLeek", 720, 32000, OrderType.MIDDLE, OrderSeason.NONE,
					OrderBiome.NONE, "dcs.emt.ordername.middle_leek");

		if (OreDictionary.doesOreNameExist("cropLettuce"))
			RecipeManagerEMT.orderRegister.addRecipe("cropLettuce", 480, 40000, OrderType.MIDDLE, OrderSeason.NONE,
					OrderBiome.NONE, "dcs.emt.ordername.middle_lettuce");

		// SHORT 春に金属素材を買い取る
		if (OreDictionary.doesOreNameExist("blockIron"))
			RecipeManagerEMT.orderRegister.addRecipe("blockIron", 18, 120000, OrderType.SHORT, OrderSeason.SPRING,
					OrderBiome.NONE, "dcs.emt.ordername.short_spring_ironblock");

		if (OreDictionary.doesOreNameExist("blockCopper"))
			RecipeManagerEMT.orderRegister.addRecipe("blockCopper", 5, 40000, OrderType.SHORT, OrderSeason.SPRING,
					OrderBiome.NONE, "dcs.emt.ordername.short_spring_copperblock");

		if (OreDictionary.doesOreNameExist("blockSilver"))
			RecipeManagerEMT.orderRegister.addRecipe("blockSilver", 10, 180000, OrderType.SHORT, OrderSeason.SPRING,
					OrderBiome.NONE, "dcs.emt.ordername.short_spring_silverblock");

		if (OreDictionary.doesOreNameExist("blockBrass"))
			RecipeManagerEMT.orderRegister.addRecipe("blockBrass", 10, 100000, OrderType.SHORT, OrderSeason.SPRING,
					OrderBiome.NONE, "dcs.emt.ordername.short_spring_brassblock");

	}

	static void addOre() {
		if (GameRegistry.findItem("BambooMod", "itembean") != null) {
			OreDictionary.registerOre("cropSoybeans", new ItemStack(GameRegistry.findItem("BambooMod", "itembean"), 1,
					0));
		}
	}

}
