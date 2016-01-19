package defeatedcrow.addonforamt.economy.plugin.mce;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.IProduct;
import shift.mceconomy2.api.shop.IShop;
import cpw.mods.fml.common.Loader;
import defeatedcrow.addonforamt.economy.plugin.IC2Integration;

public class MealShopList implements IShop {

	private static ArrayList<IProduct> thisProducts = new ArrayList<IProduct>();
	public static int thisShopId = -1;

	public void load() {
		registerProducts();
		thisShopId = MCEconomyAPI.registerShop(this);
	}

	void registerProducts() {
		if (Loader.isModLoaded("IC2")) {
			if (!IC2Integration.getIC2ProductList().isEmpty()) {
				thisProducts.addAll(IC2Integration.getIC2ProductList());
			}
		}
	}

	@Override
	public String getShopName(World world, EntityPlayer player) {
		return "EMT Meal Shop";
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
