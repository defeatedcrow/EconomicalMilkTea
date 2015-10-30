package defeatedcrow.addonforamt.economy.util;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

public class EMTPos {

	public final int x;
	public final int y;
	public final int z;

	public EMTPos(int i, int j, int k) {
		x = i;
		y = j;
		z = k;
	}

	public Block getBlock(IBlockAccess world) {
		return world.getBlock(x, y, z);
	}

	public TileEntity getTile(IBlockAccess world) {
		return world.getTileEntity(x, y, z);
	}

	public int getMeta(IBlockAccess world) {
		return world.getBlockMetadata(x, y, z);
	}

	public boolean isSamePos(int i, int j, int k) {
		return i == x && j == y && k == z;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof EMTPos) {
			EMTPos p = (EMTPos) obj;
			return p.x == x && p.y == y && p.z == z;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int i = x + z * 31 + y * 953;
		return i;
	}
}
