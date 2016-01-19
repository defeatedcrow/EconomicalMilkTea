package defeatedcrow.addonforamt.economy.common.shop;

import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.plugin.mce.IndustrialShopList;

public class BlockIndustrialShop extends ShopBlockBase {

	public BlockIndustrialShop() {
		super();
	}

	@Override
	protected int getShopID() {
		return IndustrialShopList.thisShopId;
	}

	@Override
	protected String getShopIconName() {
		return EcoMTCore.PACKAGE + ":gadget/shop_gear";
	}

}
