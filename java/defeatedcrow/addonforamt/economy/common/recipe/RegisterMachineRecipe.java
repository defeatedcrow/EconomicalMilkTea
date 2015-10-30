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
	}

}
