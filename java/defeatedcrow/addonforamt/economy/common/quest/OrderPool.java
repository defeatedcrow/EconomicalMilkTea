package defeatedcrow.addonforamt.economy.common.quest;

import java.util.ArrayList;
import java.util.List;

import defeatedcrow.addonforamt.economy.api.RecipeManagerEMT;
import defeatedcrow.addonforamt.economy.api.order.IOrder;
import defeatedcrow.addonforamt.economy.api.order.IOrderRegister;
import defeatedcrow.addonforamt.economy.api.order.OrderBiome;
import defeatedcrow.addonforamt.economy.api.order.OrderSeason;
import defeatedcrow.addonforamt.economy.api.order.OrderType;

public class OrderPool implements IOrderRegister {

	private static List<OrderBase> orderSingle;
	private static List<OrderBase> orderShort;
	private static List<OrderBase> orderMiddle;
	private static List<OrderBase> orderLong;
	private static List<OrderBase> orderDummy;
	private static List<OrderBase> orderAll;

	private static int totalID = 0;

	public OrderPool() {
		orderSingle = new ArrayList<OrderBase>();
		orderShort = new ArrayList<OrderBase>();
		orderMiddle = new ArrayList<OrderBase>();
		orderLong = new ArrayList<OrderBase>();
		orderAll = new ArrayList<OrderBase>();
	}

	public IOrderRegister instance() {
		return RecipeManagerEMT.orderRegister;
	}

	@Override
	public void addRecipe(Object item, int require, int reward, OrderType type, OrderSeason season, OrderBiome biome,
			String name) {
		if (item != null && require > 0 && reward > 0) {
			totalID++;
			OrderBase newOrder = new OrderBase(totalID, item, require, reward, type, season, biome, name);
			orderAll.add(newOrder);
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

	@Override
	public IOrder getOrderFromID(int id, OrderType type) {
		List<IOrder> list = new ArrayList<IOrder>();
		if (id == 0) {
			return this.getDummyOrder(type);
		}
		// switch (type) {
		// case SINGLE:
		// list.addAll(orderSingle);
		// break;
		// case SHORT:
		// list.addAll(orderShort);
		// break;
		// case MIDDLE:
		// list.addAll(orderMiddle);
		// break;
		// case LONG:
		// list.addAll(orderLong);
		// break;
		// default:
		// list.addAll(orderSingle);
		// break;
		// }
		for (IOrder order : orderAll) {
			if (order.getID() == id)
				return order;
		}
		return null;
	}

	@Override
	public IOrder getDummyOrder(OrderType type) {
		for (IOrder o : orderDummy) {
			if (o.getType() == type)
				return o;
		}
		return null;
	}

	public static void addDummy() {
		orderDummy = new ArrayList<OrderBase>();
		orderDummy.add(new OrderBase(0, "cropWheat", 12000, 240000, OrderType.LONG, OrderSeason.NONE, OrderBiome.PLANE,
				"dcs.emt.ordername.default"));
		orderDummy.add(new OrderBase(0, "cropWheat", 512, 20000, OrderType.MIDDLE, OrderSeason.NONE, OrderBiome.PLANE,
				"dcs.emt.ordername.default"));
		orderDummy.add(new OrderBase(0, "cropWheat", 64, 5000, OrderType.SHORT, OrderSeason.NONE, OrderBiome.PLANE,
				"dcs.emt.ordername.default"));
		orderDummy.add(new OrderBase(0, "cropWheat", 10, 1200, OrderType.SINGLE, OrderSeason.NONE, OrderBiome.PLANE,
				"dcs.emt.ordername.default"));
	}

}
