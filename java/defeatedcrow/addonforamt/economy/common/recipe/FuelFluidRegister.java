package defeatedcrow.addonforamt.economy.common.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import defeatedcrow.addonforamt.economy.EMTLogger;
import defeatedcrow.addonforamt.economy.api.IFuelFluid;
import defeatedcrow.addonforamt.economy.api.IFuelFluidRegister;
import defeatedcrow.addonforamt.economy.api.RecipeManagerEMT;

public class FuelFluidRegister implements IFuelFluidRegister {

	private static List<FuelFluid> recipes;

	public FuelFluidRegister() {
		this.recipes = new ArrayList<FuelFluid>();
	}

	public IFuelFluidRegister instance() {
		return RecipeManagerEMT.fuelRegister;
	}

	@Override
	public void addFuel(String name, int amount) {
		Fluid fluid = FluidRegistry.getFluid(name);
		if (fluid != null) {
			recipes.add(new FuelFluid(fluid, amount));
			String in = fluid == null ? "Empty" : fluid.getName();
			EMTLogger.debugInfo("Add Fuel: input: " + in + ", amount: " + amount);
		}
	}

	@Override
	public void addFuel(Fluid fluid, int amount) {
		if (fluid != null) {
			recipes.add(new FuelFluid(fluid, amount));
			String in = fluid == null ? "Empty" : fluid.getName();
			EMTLogger.debugInfo("Add Fuel: input: " + in + ", amount: " + amount);
		}
	}

	@Override
	public List<? extends IFuelFluid> getRecipes() {
		return this.recipes;
	}

	@Override
	public int getAmount(Fluid input) {
		if (input == null)
			return 0;
		for (FuelFluid recipe : this.recipes) {
			if (recipe.input == input) {
				return recipe.amount;
			}
		}
		return 0;
	}

	public class FuelFluid implements IFuelFluid {

		private Fluid input;
		private int amount;

		private final int damage = Short.MAX_VALUE;

		public FuelFluid(Fluid f, int amo) {
			this.input = f;
			this.amount = amo;
		}

		@Override
		public Fluid getInput() {
			return this.input;
		}

		@Override
		public int getGenerateAmount() {
			return this.amount;
		}

	}

}
