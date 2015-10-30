package defeatedcrow.addonforamt.economy.api;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * 納品注文のインターフェイス
 */
public interface IOrder {

	Object getRequest();

	ArrayList<ItemStack> getProcessedRequests();

	int getRequestNum();

	int getReward();

	int getLimitDay(int start);

	int getRemainDay(World world, int start);

	OrderType getType();

	OrderSeason getSeason();

	OrderBiome getBiome();

	public boolean matches(ItemStack items);

}
