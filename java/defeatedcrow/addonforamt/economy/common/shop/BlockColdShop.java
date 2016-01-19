package defeatedcrow.addonforamt.economy.common.shop;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.plugin.mce.ColdShopList;

public class BlockColdShop extends ShopBlockBase {

	public BlockColdShop() {
		super();
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileColdShop();
	}

	@Override
	protected int getShopID() {
		return ColdShopList.thisShopId;
	}

	@Override
	protected String getShopIconName() {
		return EcoMTCore.PACKAGE + ":gadget/shop_kari";
	}

}
