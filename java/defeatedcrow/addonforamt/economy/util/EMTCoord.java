package defeatedcrow.addonforamt.economy.util;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class EMTCoord {

	public final int x;
	public final int z;

	public EMTCoord(int i, int j) {
		x = i;
		z = j;
	}

	public Chunk getChunk(World world) {
		return world.getChunkFromBlockCoords(x, z);
	}

	public boolean sameCood(int i, int j) {
		return i == x && j == z;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof EMTCoord) {
			EMTCoord p = (EMTCoord) obj;
			return p.x == x && p.z == z;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int i = x + z * 953;
		return i;
	}

}
