package defeatedcrow.addonforamt.economy.api.order;

public enum OrderType {

	SINGLE(1, 0), SHORT(7, 1), MIDDLE(30, 2), LONG(120, 3);

	private final int limit;
	private final int id;

	public static final OrderType[] TYPES = {
			SINGLE,
			SHORT,
			MIDDLE,
			LONG };

	private OrderType(int i, int j) {
		limit = i;
		id = j;
	}

	public int getLimit() {
		return limit;
	}

	public int getSlot() {
		return id;
	}

	public static OrderType getType(int i) {
		if (i < 0)
			i = 0;
		if (i > 3)
			i = 3;
		return TYPES[i];
	}

}
