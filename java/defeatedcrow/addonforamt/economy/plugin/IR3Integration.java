package defeatedcrow.addonforamt.economy.plugin;

import java.util.ArrayList;

import jp.plusplus.ir2.blocks.BlockCore;
import jp.plusplus.ir2.items.ItemCore;
import net.minecraft.item.ItemStack;
import shift.mceconomy2.api.shop.IProduct;
import defeatedcrow.addonforamt.economy.api.RecipeManagerEMT;
import defeatedcrow.addonforamt.economy.api.order.OrderBiome;
import defeatedcrow.addonforamt.economy.api.order.OrderSeason;
import defeatedcrow.addonforamt.economy.api.order.OrderType;
import defeatedcrow.addonforamt.economy.plugin.mce.EMTProduct;

public class IR3Integration {

	private IR3Integration() {
	}

	public static void load() {
		addOrder();
	}

	static void addOrder() {
		// LONGはなし

		// IR3は秋に出現する。Fobiddenも同様
		// MIDDLE
		int[] ad = new int[] {
				1,
				2,
				1,
				2,
				3 };
		OrderBiome[] biome = new OrderBiome[] {
				OrderBiome.PLANE,
				OrderBiome.ARID,
				OrderBiome.DAMP,
				OrderBiome.COLD,
				OrderBiome.HELL };
		// winter
		for (int i = 0; i < 5; i++) {
			RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(ItemCore.cheese), 128, 15000 * ad[i],
					OrderType.MIDDLE, OrderSeason.AUTUMN, biome[i], "dcs.emt.ordername.middle_ir3_cheese");

			RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(ItemCore.cottonRaw), 320, 25000 * ad[i],
					OrderType.MIDDLE, OrderSeason.AUTUMN, biome[i], "dcs.emt.ordername.middle_ir3_cotton");

			RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(BlockCore.saplingRed), 128, 25000 * ad[i],
					OrderType.MIDDLE, OrderSeason.AUTUMN, biome[i], "dcs.emt.ordername.middle_ir3_redSap");

			RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(BlockCore.saplingGold), 128, 32000 * ad[i],
					OrderType.MIDDLE, OrderSeason.AUTUMN, biome[i], "dcs.emt.ordername.middle_ir3_goldSap");

			RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(ItemCore.waterweed), 256, 22000 * ad[i],
					OrderType.MIDDLE, OrderSeason.AUTUMN, biome[i], "dcs.emt.ordername.middle_ir3_seaweed");
		}

		// SHORT
		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(ItemCore.foodSmoked, 1, 3), 16, 32000, OrderType.SHORT,
				OrderSeason.AUTUMN, OrderBiome.NONE, "dcs.emt.ordername.short_ir3_smokesaimon");

		RecipeManagerEMT.orderRegister.addRecipe(new ItemStack(ItemCore.foodSmoked, 1, 1), 24, 24000, OrderType.SHORT,
				OrderSeason.AUTUMN, OrderBiome.NONE, "dcs.emt.ordername.short_ir3_smokebeef");

		// SINGLEは今のところなし

	}

	public static ArrayList<IProduct> getProductList() {
		ArrayList<IProduct> list = new ArrayList<IProduct>();
		list.add(new EMTProduct(new ItemStack(ItemCore.wrench), 3800));
		list.add(new EMTProduct(new ItemStack(ItemCore.hammer), 5000));
		list.add(new EMTProduct(new ItemStack(ItemCore.can), 1000));
		list.add(new EMTProduct(new ItemStack(ItemCore.needle), 300));
		return list;
	}

}
