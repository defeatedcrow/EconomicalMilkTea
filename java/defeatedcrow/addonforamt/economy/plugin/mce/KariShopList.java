package defeatedcrow.addonforamt.economy.plugin.mce;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.IProduct;
import shift.mceconomy2.api.shop.IShop;
import defeatedcrow.addonforamt.economy.EcoMTCore;

public class KariShopList implements IShop {

	private static ArrayList<IProduct> thisProducts = new ArrayList<IProduct>();
	public static int thisShopId = -1;

	public void load() {
		registerProducts();
		thisShopId = MCEconomyAPI.registerShop(this);
	}

	void registerProducts() {
		thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.ticket, 1, 2), 80000)); // 4-8
		thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.ticket, 1, 0), 80000)); // 8-4
		thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.ticket, 1, 3), 160000)); // 8-8
		thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.ticket, 1, 1), 160000)); // 16-4
		thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.ticket, 1, 4), 320000)); // 16-8
		thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.checker, 1, 0), 50)); // cood
		thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.fuelCan, 1, 0), 480)); // cam
		thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.fuelCan, 1, 1), 240)); // veg

		thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.distributor, 1, 0), 50000)); // machine
		thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.enTank, 1, 0), 3000)); // machine
		thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.motor, 1, 0), 20000)); // machine
		thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.generator, 1, 0), 10000)); // machine
	}

	@Override
	public String getShopName(World world, EntityPlayer player) {
		return "EMT Gadgets Shop";
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
