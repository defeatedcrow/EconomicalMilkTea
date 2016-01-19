package defeatedcrow.addonforamt.economy.common.shop;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileShopMonitor extends TileEntity {

	@SideOnly(Side.CLIENT)
	public IIcon getIcon() {
		Block block = worldObj.getBlock(xCoord, yCoord, zCoord);
		if (block != null) {
			return block.getIcon(0, 0);
		}
		return null;
	}

}
