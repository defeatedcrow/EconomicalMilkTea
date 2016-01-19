package defeatedcrow.addonforamt.economy.plugin.mce;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.IProduct;
import shift.mceconomy2.api.shop.IShop;

public class CropShopList implements IShop {

	private static ArrayList<IProduct> thisProducts = new ArrayList<IProduct>();
	public static int thisShopId = -1;

	public void load() {
		registerProducts();
		thisShopId = MCEconomyAPI.registerShop(this);
	}

	void registerProducts() {
		thisProducts.add(new EMTProduct(new ItemStack(Items.wheat), 300));
		thisProducts.add(new EMTProduct(new ItemStack(Items.carrot), 300));
		thisProducts.add(new EMTProduct(new ItemStack(Items.potato), 300));
		thisProducts.add(new EMTProduct(new ItemStack(Items.apple), 500));
		thisProducts.add(new EMTProduct(new ItemStack(Blocks.pumpkin), 800));
		thisProducts.add(new EMTProduct(new ItemStack(Blocks.melon_block), 1000));
		register("cropRice", 500);
		register("cropOnion", 500);
		register("cropCabagge", 500);
		register("cropLettuce", 500);
		register("cropLeek", 500);
		register("cropCucumber", 500);
		register("cropTomato", 500);
		register("cropEggplant", 500);
		register("cropCorn", 500);
		register("cropRadish", 500);
	}

	private void register(String s, int price) {
		if (OreDictionary.doesOreNameExist(s) && !OreDictionary.getOres(s).isEmpty()) {
			ItemStack ore = OreDictionary.getOres(s).get(0);
			thisProducts.add(new EMTProduct(ore, price));
		}
	}

	@Override
	public String getShopName(World world, EntityPlayer player) {
		return "EMT Crop Shop";
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
