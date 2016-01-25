package defeatedcrow.addonforamt.economy.plugin.mce;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.IProduct;
import shift.mceconomy2.api.shop.IShop;
import cpw.mods.fml.common.Loader;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.plugin.amt.AMTIntegration;
import defeatedcrow.addonforamt.economy.plugin.amt.FluidityIntegration;

public class ColdShopList implements IShop {

	private static ArrayList<IProduct> thisProducts = new ArrayList<IProduct>();
	public static int thisShopId = -1;

	public void load() {
		registerProducts();
		thisShopId = MCEconomyAPI.registerShop(this);
	}

	void registerProducts() {
		thisProducts.add(new EMTProduct(new ItemStack(EcoMTCore.EMT, 1, 0), 100));
		if (Loader.isModLoaded("FluidityDC")) {
			if (!FluidityIntegration.getColdProductList().isEmpty()) {
				thisProducts.addAll(FluidityIntegration.getColdProductList());
			}
		}
		if (Loader.isModLoaded("DCsAppleMilk")) {
			if (!AMTIntegration.getColdProductList().isEmpty()) {
				thisProducts.addAll(AMTIntegration.getColdProductList());
			}
		}
		thisProducts.add(new EMTProduct(new ItemStack(Items.beef), 300));
		thisProducts.add(new EMTProduct(new ItemStack(Items.porkchop), 300));
		thisProducts.add(new EMTProduct(new ItemStack(Items.chicken), 300));
		thisProducts.add(new EMTProduct(new ItemStack(Items.fish), 200));
	}

	@Override
	public String getShopName(World world, EntityPlayer player) {
		return "Cold Shop";
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
