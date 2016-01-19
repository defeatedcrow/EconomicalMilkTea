package defeatedcrow.addonforamt.economy.plugin.mce;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.IProduct;
import shift.mceconomy2.api.shop.IShop;
import cpw.mods.fml.common.Loader;
import defeatedcrow.addonforamt.economy.plugin.BCIntegration;
import defeatedcrow.addonforamt.economy.plugin.FFMIntegration;
import defeatedcrow.addonforamt.economy.plugin.IC2Integration;
import defeatedcrow.addonforamt.economy.plugin.IR3Integration;
import defeatedcrow.addonforamt.economy.plugin.RCIntegration;
import defeatedcrow.addonforamt.economy.plugin.amt.FluidityIntegration;
import defeatedcrow.addonforamt.economy.plugin.amt.IronChainIntegration;
import defeatedcrow.addonforamt.economy.plugin.ss2.SS2Integration;

public class IndustrialShopList implements IShop {

	private static ArrayList<IProduct> thisProducts = new ArrayList<IProduct>();
	public static int thisShopId = -1;

	public void load() {
		registerProducts();
		thisShopId = MCEconomyAPI.registerShop(this);
	}

	void registerProducts() {

		String[] gears = {
				"gearWood",
				"gearStone",
				"gearIron" };
		int[] price = {
				500,
				1200,
				4000 };
		for (int i = 0; i < 3; i++) {
			if (OreDictionary.doesOreNameExist(gears[i]) && !OreDictionary.getOres(gears[i]).isEmpty()) {
				ItemStack gear = OreDictionary.getOres(gears[i]).get(0);
				thisProducts.add(new EMTProduct(gear, price[i]));
			}
		}

		if (Loader.isModLoaded("SextiarySector")) {
			if (!SS2Integration.getProductList().isEmpty()) {
				thisProducts.addAll(SS2Integration.getProductList());
			}
		}
		if (Loader.isModLoaded("jp-plusplus-ir2")) {
			if (!IR3Integration.getProductList().isEmpty()) {
				thisProducts.addAll(IR3Integration.getProductList());
			}
		}
		if (Loader.isModLoaded("IC2")) {
			if (!IC2Integration.getIC2ProductList().isEmpty()) {
				thisProducts.addAll(IC2Integration.getIC2ProductList());
			}
		}
		if (Loader.isModLoaded("BuildCraft|Core")) {
			if (!BCIntegration.getProductList().isEmpty()) {
				thisProducts.addAll(BCIntegration.getProductList());
			}
		}
		if (Loader.isModLoaded("Forestry")) {
			if (!FFMIntegration.getProductList().isEmpty()) {
				thisProducts.addAll(FFMIntegration.getProductList());
			}
		}
		if (Loader.isModLoaded("Railcraft")) {
			if (!RCIntegration.getProductList().isEmpty()) {
				thisProducts.addAll(RCIntegration.getProductList());
			}
		}
		if (Loader.isModLoaded("DCIronChain")) {
			if (!IronChainIntegration.getProductList().isEmpty()) {
				thisProducts.addAll(IronChainIntegration.getProductList());
			}
		}
		if (Loader.isModLoaded("FluidityDC")) {
			if (!FluidityIntegration.getProductList().isEmpty()) {
				thisProducts.addAll(FluidityIntegration.getProductList());
			}
		}

		thisProducts.add(new EMTProduct(new ItemStack(Blocks.redstone_torch), 500));
		thisProducts.add(new EMTProduct(new ItemStack(Blocks.lever), 500));
	}

	@Override
	public String getShopName(World world, EntityPlayer player) {
		return "EMT Industrial Shop";
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
