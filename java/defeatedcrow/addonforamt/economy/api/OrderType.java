package defeatedcrow.addonforamt.economy.api;

public enum OrderType {

	SINGLE(1), SHORT(7), MIDDLE(30), LONG(120);

	private final int id;

	private OrderType(int i) {
		id = i;
	}

	public int getLimit() {
		return id;
	}

}
