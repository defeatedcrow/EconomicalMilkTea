package defeatedcrow.addonforamt.economy.plugin.mce;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.IProduct;
import shift.mceconomy2.api.shop.IShop;

public class MealShopList implements IShop {

	private static ArrayList<IProduct> thisProducts = new ArrayList<IProduct>();
	public static int thisShopId = -1;

	public void load() {
		registerProducts();
		thisShopId = MCEconomyAPI.registerShop(this);
	}

	void registerProducts() {
		thisProducts.add(new EMTProduct(new ItemStack(Items.egg), 200));
		thisProducts.add(new EMTProduct(new ItemStack(Items.bread), 200));
		thisProducts.add(new EMTProduct(new ItemStack(Items.cookie), 150));
		thisProducts.add(new EMTProduct(new ItemStack(Items.pumpkin_pie), 500));
		register("foodBakedApple", 100);
		register("foodAppleTart", 250);
		register("foodApricotCake", 400);
		register("foodTea", 180);
		register("foodGreenTea", 150);
		register("foodsakuraTea", 500);
		register("foodRoastedBarley", 200);
		register("bottleSoySauce", 400);
		register("bottleVinegar", 300);
		register("cheese", 500);
		register("mayonnaise", 300);
	}

	private void register(String s, int price) {
		if (OreDictionary.doesOreNameExist(s) && !OreDictionary.getOres(s).isEmpty()) {
			ItemStack ore = OreDictionary.getOres(s).get(0);
			thisProducts.add(new EMTProduct(ore, price));
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
