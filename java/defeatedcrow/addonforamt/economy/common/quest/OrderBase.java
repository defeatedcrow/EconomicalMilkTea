package defeatedcrow.addonforamt.economy.common.quest;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import defeatedcrow.addonforamt.economy.api.order.IOrder;
import defeatedcrow.addonforamt.economy.api.order.OrderBiome;
import defeatedcrow.addonforamt.economy.api.order.OrderSeason;
import defeatedcrow.addonforamt.economy.api.order.OrderType;
import defeatedcrow.addonforamt.economy.util.TimeUtil;

public class OrderBase implements IOrder {

	private final Object reqItem;
	private final int require;
	private final int reward;
	private final OrderType type;
	private final OrderSeason season;
	private final OrderBiome biome;
	private final String orderName;
	private final int orderID;

	// public OrderBase(Object item, int req, int rew, OrderType t, OrderSeason s, OrderBiome b,
	// String name) {
	// reqItem = item;
	// require = req;
	// reward = rew;
	// type = t;
	// season = s;
	// biome = b;
	// orderName = name;
	// orderTotal++;
	// orderID = orderTotal;
	// }

	public OrderBase(int id, Object item, int req, int rew, OrderType t, OrderSeason s, OrderBiome b, String name) {
		reqItem = item;
		require = req;
		reward = rew;
		type = t;
		season = s;
		biome = b;
		orderName = name;
		orderID = id;
	}

	@Override
	public Object getRequest() {
		return reqItem;
	}

	@Override
	public ArrayList<ItemStack> getProcessedRequests() {
		ArrayList<ItemStack> reqItemList = new ArrayList<ItemStack>();
		if (reqItem != null) {
			if (reqItem instanceof String && !OreDictionary.getOres((String) reqItem).isEmpty()) {
				reqItemList.addAll(OreDictionary.getOres((String) reqItem));
			} else if (reqItem instanceof ItemStack) {
				reqItemList.add(((ItemStack) reqItem).copy());
			} else if (reqItem instanceof Item) {
				reqItemList.add(new ItemStack((Item) reqItem, 1, 0));
			} else if (reqItem instanceof Block) {
				reqItemList.add(new ItemStack((Block) reqItem, 1, 0));
			}
		}
		return reqItemList;
	}

	@Override
	public int getRequestNum() {
		return require;
	}

	@Override
	public int getReward() {
		return reward;
	}

	@Override
	public int getLimitDay(int i) {
		return i + this.getType().getLimit();
	}

	@Override
	public int getRemainDay(World world, int i) {
		return this.getLimitDay(i) - TimeUtil.getDay(world);
	}

	@Override
	public OrderType getType() {
		return type;
	}

	@Override
	public OrderSeason getSeason() {
		return season;
	}

	@Override
	public OrderBiome getBiome() {
		return biome;
	}

	@Override
	public boolean matches(ItemStack items) {
		ArrayList<ItemStack> required = new ArrayList<ItemStack>(this.getProcessedRequests());
		boolean match = false;

		for (ItemStack tar : required) {
			if (OreDictionary.itemMatches(tar, items, false)) {
				match = true;
			}
		}

		return match;
	}

	@Override
	public String getName() {
		return orderName;
	}

	@Override
	public int getID() {
		return orderID;
	}

}
