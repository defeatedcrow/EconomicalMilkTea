package defeatedcrow.addonforamt.economy.common.recipe;

import defeatedcrow.addonforamt.economy.api.RecipeManagerEMT;

public class RegisterMachineRecipe {

	private RegisterMachineRecipe() {
	}

	public static void addRecipe() {
		addFuel();
	}

	static void addFuel() {
		RecipeManagerEMT.fuelRegister.addFuel("vegitable_oil", 4);
		RecipeManagerEMT.fuelRegister.addFuel("camellia_oil", 16);
		RecipeManagerEMT.fuelRegister.addFuel("vodka_dc", 16);
		RecipeManagerEMT.fuelRegister.addFuel("oil", 4);
		RecipeManagerEMT.fuelRegister.addFuel("fuel", 16);
		RecipeManagerEMT.fuelRegister.addFuel("hootch", 4);
		RecipeManagerEMT.fuelRegister.addFuel("rocket_fuel", 16);
		RecipeManagerEMT.fuelRegister.addFuel("ic2biomass", 4);
		RecipeManagerEMT.fuelRegister.addFuel("ic2biogas", 8);
		RecipeManagerEMT.fuelRegister.addFuel("biomass", 8);
		RecipeManagerEMT.fuelRegister.addFuel("bioethanol", 16);
		RecipeManagerEMT.fuelRegister.addFuel("seedoil", 8);
		RecipeManagerEMT.fuelRegister.addFuel("creosote", 4);
	}

}
