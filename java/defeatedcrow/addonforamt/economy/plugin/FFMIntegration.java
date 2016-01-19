package defeatedcrow.addonforamt.economy.plugin;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import shift.mceconomy2.api.shop.IProduct;
import cpw.mods.fml.common.registry.GameRegistry;
import defeatedcrow.addonforamt.economy.plugin.mce.EMTProduct;

public class FFMIntegration {

	private FFMIntegration() {
	}

	public static void load() {
		addOrder();
	}

	static void addOrder() {
		// LONG MIDDLE は辞書名のほうで登録するのでなし

	}

	public static ArrayList<IProduct> getProductList() {
		ArrayList<IProduct> list = new ArrayList<IProduct>();
		if (GameRegistry.findItem("Forestry", "wrench") != null) {
			list.add(new EMTProduct(new ItemStack(GameRegistry.findItem("Forestry", "wrench"), 1, 0), 2000));
		}
		if (GameRegistry.findItem("Forestry", "pipette") != null) {
			list.add(new EMTProduct(new ItemStack(GameRegistry.findItem("Forestry", "pipette"), 1, 0), 1000));
		}
		return list;
	}

}
