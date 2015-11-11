package defeatedcrow.addonforamt.economy.common.quest;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.addonforamt.economy.api.IOrder;
import defeatedcrow.addonforamt.economy.api.IOrderRegister;
import defeatedcrow.addonforamt.economy.api.OrderBiome;
import defeatedcrow.addonforamt.economy.api.OrderSeason;
import defeatedcrow.addonforamt.economy.api.OrderType;
import defeatedcrow.addonforamt.economy.api.RecipeManagerEMT;

public class OrderPool implements IOrderRegister {

	private static List<OrderBase> orderSingle;
	private static List<OrderBase> orderShort;
	private static List<OrderBase> orderMiddle;
	private static List<OrderBase> orderLong;

	public OrderPool() {
		this.orderSingle = new ArrayList<OrderBase>();
		this.orderShort = new ArrayList<OrderBase>();
		this.orderMiddle = new ArrayList<OrderBase>();
		this.orderLong = new ArrayList<OrderBase>();
	}

	public IOrderRegister instance() {
		return RecipeManagerEMT.orderRegister;
	}

	@Override
	public void addRecipe(Object item, int require, int reward, OrderType type, OrderSeason season, OrderBiome biome,
			String name) {
		if (item != null && require > 0 && reward > 0) {
			OrderBase newOrder = new OrderBase(item, require, reward, type, season, biome, name);
			switch (type) {
			case SINGLE:
				this.orderSingle.add(newOrder);
				break;
			case SHORT:
				this.orderShort.add(newOrder);
				break;
			case MIDDLE:
				this.orderMiddle.add(newOrder);
				break;
			case LONG:
				this.orderLong.add(newOrder);
				break;
			default:
				this.orderSingle.add(newOrder);
				break;
			}
		}

	}

	@Override
	public List<? extends IOrder> getSingleOrders() {
		return this.orderSingle;
	}

	@Override
	public List<? extends IOrder> getShortOrders() {
		return this.orderShort;
	}

	@Override
	public List<? extends IOrder> getMiddleOrders() {
		return this.orderMiddle;
	}

	@Override
	public List<? extends IOrder> getLongOrders() {
		return this.orderLong;
	}

}
