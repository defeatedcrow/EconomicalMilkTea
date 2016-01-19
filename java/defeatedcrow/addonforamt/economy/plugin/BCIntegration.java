package defeatedcrow.addonforamt.economy.plugin;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import shift.mceconomy2.api.shop.IProduct;
import cpw.mods.fml.common.registry.GameRegistry;
import defeatedcrow.addonforamt.economy.plugin.mce.EMTProduct;

public class BCIntegration {

	private BCIntegration() {
	}

	public static void load() {
		addOrder();
	}

	static void addOrder() {
		// 追加作物や素材はないので、ORDERなし
	}

	public static ArrayList<IProduct> getProductList() {
		ArrayList<IProduct> list = new ArrayList<IProduct>();
		if (GameRegistry.findItem("BuildCraft|Core", "wrenchItem") != null) {
			list.add(new EMTProduct(new ItemStack(GameRegistry.findItem("BuildCraft|Core", "wrenchItem"), 1, 0), 2000));
		}
		if (GameRegistry.findBlock("BuildCraft|Builders", "markerBlock") != null) {
			list.add(new EMTProduct(new ItemStack(GameRegistry.findItem("BuildCraft|Builders", "markerBlock"), 1, 0),
					500));
		}
		if (GameRegistry.findItem("BuildCraft|Transport", "item.buildcraftPipe.pipeitemswood") != null) {
			list.add(new EMTProduct(new ItemStack(GameRegistry.findItem("BuildCraft|Transport",
					"item.buildcraftPipe.pipeitemswood"), 1, 0), 1000));
		}
		if (GameRegistry.findItem("BuildCraft|Transport", "item.buildcraftPipe.pipeitemsstone") != null) {
			list.add(new EMTProduct(new ItemStack(GameRegistry.findItem("BuildCraft|Transport",
					"item.buildcraftPipe.pipeitemsstone"), 1, 0), 1200));
		}
		if (GameRegistry.findBlock("BuildCraft|Factry", "tankBlock") != null) {
			list.add(new EMTProduct(new ItemStack(GameRegistry.findItem("BuildCraft|Factry", "tankBlock"), 1, 0), 3000));
		}
		return list;
	}

}
