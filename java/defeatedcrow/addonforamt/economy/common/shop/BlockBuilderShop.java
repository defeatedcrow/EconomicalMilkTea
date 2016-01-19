package defeatedcrow.addonforamt.economy.common.shop;

import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.plugin.mce.BuildingShopList;

public class BlockBuilderShop extends ShopBlockBase {

	public BlockBuilderShop() {
		super();
	}

	@Override
	protected int getShopID() {
		return BuildingShopList.thisShopId;
	}

	@Override
	protected String getShopIconName() {
		return EcoMTCore.PACKAGE + ":gadget/shop_build";
	}

}
