package defeatedcrow.addonforamt.economy.common.recipe;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import defeatedcrow.addonforamt.economy.EcoMTCore;

public class RegisterBasicRecipe {

	private RegisterBasicRecipe() {
	}

	public static void addRecipe() {
		addCont();
		addBasicRecipe();
	}

	static void addCont() {
		Fluid cam = FluidRegistry.getFluid("camellia_oil");
		Fluid veg = FluidRegistry.getFluid("vegitable_oil");
		if (cam != null) {
			FluidContainerRegistry.registerFluidContainer(new FluidStack(cam, 200), new ItemStack(EcoMTCore.fuelCan, 1,
					0), new ItemStack(Items.paper));
		}
		if (veg != null) {
			FluidContainerRegistry.registerFluidContainer(new FluidStack(veg, 200), new ItemStack(EcoMTCore.fuelCan, 1,
					1), new ItemStack(Items.paper));
		}
	}

	static void addBasicRecipe() {
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(EcoMTCore.checker, 1, 0),
				new Object[] { new ItemStack(EcoMTCore.checker, 1, 0) }));

		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(EcoMTCore.kariShop, 1, 0), new Object[] {
				new ItemStack(DCsAppleMilk.leafTea, 1, 0),
				new ItemStack(Item.getItemFromBlock(Blocks.crafting_table), 1, 0) }));
	}

}
