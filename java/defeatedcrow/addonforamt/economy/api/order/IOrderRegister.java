package defeatedcrow.addonforamt.economy.api.order;

import java.util.List;

public interface IOrderRegister {

	/**
	 * 納品クエスト(Order)の登録を行う。
	 * 納品物はItemStackまたはString(鉱石辞書名)のいずれかで登録できる。 <br>
	 * 
	 * @param item
	 *            : 要求アイテム。ItemStack or String
	 * @param require
	 *            : 必要個数
	 * @param reward
	 *            : 報酬額
	 * @param type
	 *            : 期限のタイプ
	 * @param season
	 *            : 期限に設定する季節
	 * @param biome
	 *            : 出現バイオーム
	 * @param name
	 *            : オーダーのタイトル
	 */
	void addRecipe(Object item, int require, int reward, OrderType type, OrderSeason season, OrderBiome biome,
			String name);

	List<? extends IOrder> getSingleOrders();

	List<? extends IOrder> getShortOrders();

	List<? extends IOrder> getMiddleOrders();

	List<? extends IOrder> getLongOrders();

	IOrder getOrderFromID(int id, OrderType type);

	/**
	 * DummyOrderを返す。<br>
	 * アイテムの存在しない鉱石辞書名を使ったOrderなど、実行不可能なOrderが発生した場合に渡す代理のOrder。<br>
	 * IDを0で参照した場合もコレが出てくる。
	 */
	IOrder getDummyOrder(OrderType type);

}
