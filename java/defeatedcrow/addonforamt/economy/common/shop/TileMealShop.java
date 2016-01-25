package defeatedcrow.addonforamt.economy.common.shop;


public class TileMealShop extends TileDisplayShop {

	public boolean isSameUnderBlock() {
		return worldObj.getBlock(xCoord, yCoord - 1, zCoord) == this.blockType;
	}

}
