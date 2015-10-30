package defeatedcrow.addonforamt.economy.plugin.mce;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.IProductItem;
import shift.mceconomy2.api.shop.ProductItem;
import shift.mceconomy2.api.shop.ProductList;
import defeatedcrow.addonforamt.economy.EcoMTCore;

public class KariShopList extends ProductList {

	private static ArrayList<IProductItem> thisProducts = new ArrayList<IProductItem>();
	public static int thisShopId = -1;

	public void load() {
		thisShopId = MCEconomyAPI.registerProductList(this);
		registerProducts();
	}

	@Override
	public String getProductListName() {

		return "Kari Shop";
	}

	static void registerProducts() {
		thisProducts.add(new ProductItem(new ItemStack(EcoMTCore.ticket, 1, 2), 40000)); // 4-8
		thisProducts.add(new ProductItem(new ItemStack(EcoMTCore.ticket, 1, 0), 40000)); // 8-4
		thisProducts.add(new ProductItem(new ItemStack(EcoMTCore.ticket, 1, 3), 80000)); // 8-8
		thisProducts.add(new ProductItem(new ItemStack(EcoMTCore.ticket, 1, 1), 80000)); // 16-4
		thisProducts.add(new ProductItem(new ItemStack(EcoMTCore.ticket, 1, 4), 160000)); // 16-8
		thisProducts.add(new ProductItem(new ItemStack(EcoMTCore.checker, 1, 0), 50)); // cood
		thisProducts.add(new ProductItem(new ItemStack(EcoMTCore.fuelCan, 1, 0), 480)); // cam
		thisProducts.add(new ProductItem(new ItemStack(EcoMTCore.fuelCan, 1, 1), 240)); // veg

		thisProducts.add(new ProductItem(new ItemStack(EcoMTCore.distributor, 1, 0), 50000)); // machine
		thisProducts.add(new ProductItem(new ItemStack(EcoMTCore.enTank, 1, 0), 3000)); // machine
		thisProducts.add(new ProductItem(new ItemStack(EcoMTCore.motor, 1, 0), 20000)); // machine
		thisProducts.add(new ProductItem(new ItemStack(EcoMTCore.generator, 1, 0), 10000)); // machine
	}

	@Override
	public void addItemProduct(IProductItem item) {
		thisProducts.add(item);
	}

	@Override
	public int getItemProductSize() {
		return thisProducts.size();
	}

	@Override
	public ArrayList<IProductItem> getProductList() {
		return thisProducts;
	}
}
