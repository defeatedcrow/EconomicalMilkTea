package defeatedcrow.addonforamt.economy.plugin.mce;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import shift.mceconomy2.api.shop.IProduct;
import shift.mceconomy2.api.shop.IShop;

public class EMTProduct implements IProduct {

	private final ItemStack item;
	private final int cost;

	public EMTProduct(ItemStack i, int c) {
		item = i;
		cost = c;
	}

	// ひとまず無条件で購入できる
	@Override
	public ItemStack getItem(IShop shop, World world, EntityPlayer player) {
		return item;
	}

	@Override
	public int getCost(IShop shop, World world, EntityPlayer player) {
		return cost;
	}

	@Override
	public boolean canBuy(IShop shop, World world, EntityPlayer player) {
		return true;
	}

}
