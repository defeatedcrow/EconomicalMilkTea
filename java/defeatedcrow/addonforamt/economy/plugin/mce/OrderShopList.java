package defeatedcrow.addonforamt.economy.plugin.mce;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.IProduct;
import shift.mceconomy2.api.shop.IShop;
import defeatedcrow.addonforamt.economy.EcoMTCore;

public class OrderShopList implements IShop {

	private static ArrayList<IProduct> thisProducts = new ArrayList<IProduct>();
	public static int thishopId = -1;

	public void load() {
		registerProducts();
		thishopId = MCEconomyAPI.registerShop(this);
	}

	void registerProducts() {
		thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.questBlock, 1, 0), 3000));
		thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.questKanban, 1, 0), 1000));
		thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.safetyBox, 1, 0), 20000));
		thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.emtShop, 1, 0), 5000));
		thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.energyShop, 1, 0), 5000));
	}

	@Override
	public String getShopName(World world, EntityPlayer player) {
		return "EMT Shoji Trade Shop";
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
