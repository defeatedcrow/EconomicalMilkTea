package defeatedcrow.addonforamt.economy.api.energy;

import java.util.List;

import net.minecraftforge.fluids.Fluid;

public interface IFuelFluidRegister {

	/**
	 * Generatorへの燃料登録<br>
	 * Fluidのみ取り扱う <br>
	 * 
	 * @param amount
	 *            : 発電量
	 * @param fluid
	 *            : 燃料
	 */
	void addFuel(Fluid fluid, int amount);

	/**
	 * Generatorへの燃料登録<br>
	 * String形式で登録 <br>
	 * 
	 * @param amount
	 *            : 発電量
	 * @param fluidName
	 *            : 燃料の内部名称
	 */
	void addFuel(String fluidName, int amount);

	List<? extends IFuelFluid> getRecipes();

	int getAmount(Fluid input);

}
