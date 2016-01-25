package defeatedcrow.addonforamt.economy.plugin.amt;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import shift.mceconomy2.api.shop.IProduct;
import defeatedcrow.addonforamt.economy.plugin.mce.EMTProduct;
import defeatedcrow.addonforamt.fluidity.common.FluidityCore;

public class FluidityIntegration {

	private FluidityIntegration() {
	}

	public static void load() {
		addOrder();
	}

	static void addOrder() {
		// none

	}

	public static ArrayList<IProduct> getProductList() {
		ArrayList<IProduct> list = new ArrayList<IProduct>();
		list.add(new EMTProduct(new ItemStack(FluidityCore.fluidIBC, 1, 0), 5000));
		list.add(new EMTProduct(new ItemStack(FluidityCore.fluidHopper, 1, 0), 7500));
		return list;
	}

	public static ArrayList<IProduct> getColdProductList() {
		ArrayList<IProduct> list = new ArrayList<IProduct>();
		list.add(new EMTProduct(new ItemStack(FluidityCore.flourCont, 1, 3), 500));
		list.add(new EMTProduct(new ItemStack(FluidityCore.flourCont, 1, 7), 200));
		list.add(new EMTProduct(new ItemStack(FluidityCore.flourCont, 1, 0), 200));
		list.add(new EMTProduct(new ItemStack(FluidityCore.flourCont, 1, 2), 100));
		return list;
	}

}
