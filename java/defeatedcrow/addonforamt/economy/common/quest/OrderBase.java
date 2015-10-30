package defeatedcrow.addonforamt.economy.common.quest;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import defeatedcrow.addonforamt.economy.api.IOrder;
import defeatedcrow.addonforamt.economy.api.OrderBiome;
import defeatedcrow.addonforamt.economy.api.OrderSeason;
import defeatedcrow.addonforamt.economy.api.OrderType;
import defeatedcrow.addonforamt.economy.util.TimeUtil;

public class OrderBase implements IOrder {

	private final Object reqItem;
	private final ArrayList<ItemStack> reqItemList;
	private final int require;
	private final int reward;
	private final OrderType type;
	private final OrderSeason season;
	private final OrderBiome biome;

	public OrderBase(Object item, int req, int rew, OrderType t, OrderSeason s, OrderBiome b) {
		reqItem = item;
		reqItemList = new ArrayList<ItemStack>();
		require = req;
		reward = rew;
		type = t;
		season = s;
		biome = b;

		if (item != null) {
			if (item instanceof String && !OreDictionary.getOres((String) item).isEmpty()) {
				reqItemList.addAll(OreDictionary.getOres((String) item));
			} else if (item instanceof ItemStack) {
				reqItemList.add(((ItemStack) item).copy());
			} else if (item instanceof Item) {
				reqItemList.add(new ItemStack((Item) item, 1, 0));
			} else if (item instanceof Block) {
				reqItemList.add(new ItemStack((Block) item, 1, 0));
			} else {
				throw new IllegalArgumentException("Unknown Object passed to recipe!");
			}
		}
	}

	@Override
	public Object getRequest() {
		return reqItem;
	}

	@Override
	public ArrayList<ItemStack> getProcessedRequests() {
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
		ArrayList<ItemStack> required = new ArrayList<ItemStack>(this.reqItemList);
		boolean match = false;

		for (ItemStack tar : required) {
			if (OreDictionary.itemMatches(tar, items, false)) {
				match = true;
			}
		}

		return match;
	}

}
