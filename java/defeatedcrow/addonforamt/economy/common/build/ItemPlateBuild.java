package defeatedcrow.addonforamt.economy.common.build;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import defeatedcrow.addonforamt.economy.api.BuildType;

public class ItemPlateBuild extends ItemSimpleBuild {

	public ItemPlateBuild() {
		super();
		this.setTextureName("economical:tools/buildcard_plate");
	}

	@Override
	public boolean onBuild(World world, int x, int y, int z, int dir, EntityPlayer player, ItemStack stack) {
		int range = this.getArea(stack.getItemDamage());
		int minX = x - range;
		int maxX = x + range;
		int minZ = z - range;
		int maxZ = z + range;
		int minY = y;
		int maxY = y + 1;
		BlockSet set = this.getPlaceBlock(stack.getItemDamage());
		for (int i = minX; i <= maxX; i++) {
			for (int j = minY; j < maxY; j++) {
				for (int k = minZ; k <= maxZ; k++) {
					if (this.isReplaceable(world, i, j, k)) {
						world.setBlock(i, j, k, set.block, set.meta, 3);
					}
				}
			}
		}
		return true;
	}

	@Override
	public BuildType getType(int meta) {
		return BuildType.PLATE;
	}

	@Override
	public boolean forceReplace() {
		return true;
	}

}
