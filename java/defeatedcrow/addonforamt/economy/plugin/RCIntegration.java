package defeatedcrow.addonforamt.economy.plugin;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import shift.mceconomy2.api.shop.IProduct;
import cpw.mods.fml.common.registry.GameRegistry;
import defeatedcrow.addonforamt.economy.plugin.mce.EMTProduct;

public class RCIntegration {

	private RCIntegration() {
	}

	public static void load() {
		addOrder();
	}

	static void addOrder() {

	}

	public static ArrayList<IProduct> getProductList() {
		ArrayList<IProduct> list = new ArrayList<IProduct>();
		if (GameRegistry.findItem("Railcraft", "tool.crowbar") != null) {
			list.add(new EMTProduct(new ItemStack(GameRegistry.findItem("Railcraft", "tool.crowbar"), 1, 0), 4000));
		}
		if (GameRegistry.findItem("Railcraft", "armor.overalls") != null) {
			list.add(new EMTProduct(new ItemStack(GameRegistry.findItem("Railcraft", "armor.overalls"), 1, 0), 1500));
		}
		return list;
	}

}
