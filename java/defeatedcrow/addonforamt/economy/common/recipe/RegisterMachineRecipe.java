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
		RecipeManagerEMT.fuelRegister.addFuel("camellia_oil", 8);
		RecipeManagerEMT.fuelRegister.addFuel("vodka_dc", 8);
		RecipeManagerEMT.fuelRegister.addFuel("oil", 4);
		RecipeManagerEMT.fuelRegister.addFuel("fuel", 8);
		RecipeManagerEMT.fuelRegister.addFuel("hootch", 2);
		RecipeManagerEMT.fuelRegister.addFuel("rocket_fuel", 8);
		RecipeManagerEMT.fuelRegister.addFuel("ic2biomass", 2);
		RecipeManagerEMT.fuelRegister.addFuel("ic2biogas", 4);
		RecipeManagerEMT.fuelRegister.addFuel("biomass", 4);
		RecipeManagerEMT.fuelRegister.addFuel("bioethanol", 8);
		RecipeManagerEMT.fuelRegister.addFuel("seedoil", 4);
		RecipeManagerEMT.fuelRegister.addFuel("creosote", 2);
	}

}
