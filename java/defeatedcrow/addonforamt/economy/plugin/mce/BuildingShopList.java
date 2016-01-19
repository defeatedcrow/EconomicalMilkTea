package defeatedcrow.addonforamt.economy.plugin.mce;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.IProduct;
import shift.mceconomy2.api.shop.IShop;
import defeatedcrow.addonforamt.economy.EcoMTCore;

public class BuildingShopList implements IShop {

	private static ArrayList<IProduct> thisProducts = new ArrayList<IProduct>();
	public static int thisShopId = -1;

	public void load() {
		registerProducts();
		thisShopId = MCEconomyAPI.registerShop(this);
	}

	void registerProducts() {
		for (int i = 0; i < 8; i++) {
			thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.buildCard_b, 1, i), 1000 + 500 * i));
			thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.buildCard_p, 1, i), 2000 + 500 * i));
			thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.buildCard_s, 1, i), 4000 + 1000 * i));
			thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.buildCard_r, 1, i), 3000 + 1000 * i));
			thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.buildCard_c, 1, i), 1000 + 500 * i));
		}
	}

	@Override
	public String getShopName(World world, EntityPlayer player) {
		return "EMT Builder's Shop";
	}

	@Override
	public void addProduct(IProduct product) {
		thisProducts.add(product);
	}

	@Override
	public ArrayList<IProduct> getProductList(World world, EntityPlayer player) {
		return thisProducts;
	}

}
