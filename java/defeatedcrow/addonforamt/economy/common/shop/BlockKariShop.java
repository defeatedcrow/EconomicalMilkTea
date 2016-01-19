package defeatedcrow.addonforamt.economy.common.shop;

import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.plugin.mce.KariShopList;

public class BlockKariShop extends ShopBlockBase {

	public BlockKariShop() {
		super();
	}

	@Override
	protected int getShopID() {
		return KariShopList.thisShopId;
	}

	@Override
	protected String getShopIconName() {
		return EcoMTCore.PACKAGE + ":gadget/shop_kari";
	}

}
