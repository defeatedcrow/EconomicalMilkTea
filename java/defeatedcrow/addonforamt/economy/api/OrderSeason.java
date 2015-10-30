package defeatedcrow.addonforamt.economy.api;

public enum OrderSeason {

	SPRING(1), SUMMER(2), AUTUMN(3), WINTER(4), NONE(0);

	private final int id;

	private OrderSeason(int i) {
		id = i;
	}

	public int getNum(OrderSeason s) {
		return id;
	}

}
