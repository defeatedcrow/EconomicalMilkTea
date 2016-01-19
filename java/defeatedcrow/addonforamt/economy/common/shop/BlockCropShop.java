package defeatedcrow.addonforamt.economy.common.shop;

import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.plugin.mce.CropShopList;

public class BlockCropShop extends ShopBlockBase {

	public BlockCropShop() {
		super();
	}

	@Override
	protected int getShopID() {
		return CropShopList.thisShopId;
	}

	@Override
	protected String getShopIconName() {
		return EcoMTCore.PACKAGE + ":gadget/shop_crop";
	}

}
