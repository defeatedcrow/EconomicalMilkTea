package defeatedcrow.addonforamt.economy.api;

public enum OrderSeason {

	SPRING(0), SUMMER(1), AUTUMN(2), WINTER(3), NONE(4);

	private final int id;

	public static final OrderSeason[] SEASONS = {
			SPRING,
			SUMMER,
			AUTUMN,
			WINTER,
			NONE };

	private OrderSeason(int i) {
		id = i;
	}

	public int getNum() {
		return id;
	}

	public static OrderSeason getSeason(int i) {
		if (i < 0)
			i = 0;
		if (i > 4)
			i = 4;
		return SEASONS[i];
	}

}
