package defeatedcrow.addonforamt.economy.common.build;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import defeatedcrow.addonforamt.economy.api.BuildType;

public class ItemRoofBuild extends ItemSimpleBuild {

	public ItemRoofBuild() {
		super();
		this.setTextureName("economical:tools/buildcard_roof");
	}

	@Override
	public boolean onBuild(World world, int x, int y, int z, int dir, EntityPlayer player, ItemStack stack) {
		int range = this.getArea(stack.getItemDamage());
		BlockSet set = this.getPlaceBlock(stack.getItemDamage());
		if (dir == 0) {
			for (int i = 0; i < range; i++) {
				for (int j = -range; j < range + 1; j++) {
					if (this.isReplaceable(world, x + j, y + i, z + i)) {
						world.setBlock(x + j, y + i, z + i, set.block, 2, 3);
					}
				}
			}
		} else if (dir == 1) {
			for (int i = 0; i < range; i++) {
				for (int j = -range; j < range + 1; j++) {
					if (this.isReplaceable(world, x - 1, y + i, z + j)) {
						world.setBlock(x - i, y + i, z + j, set.block, 1, 3);
					}
				}
			}
		} else if (dir == 2) {
			for (int i = 0; i < range; i++) {
				for (int j = -range; j < range + 1; j++) {
					if (this.isReplaceable(world, x + j, y + i, z - i)) {
						world.setBlock(x + j, y + i, z - i, set.block, 3, 3);
					}
				}
			}
		} else if (dir == 3) {
			for (int i = 0; i < range; i++) {
				for (int j = -range; j < range + 1; j++) {
					if (this.isReplaceable(world, x + i, y + i, z + j)) {
						world.setBlock(x + i, y + i, z + j, set.block, 0, 3);
					}
				}
			}
		}
		return true;
	}

	@Override
	public BuildType getType(int meta) {
		return BuildType.ROOF;
	}

	@Override
	public boolean forceReplace() {
		return false;
	}

	@Override
	protected BlockSet getPlaceBlock(int meta) {
		int i = meta >> 3;
		switch (i) {
		case 0:
			return new BlockSet(Blocks.oak_stairs, 0);
		case 1:
			return new BlockSet(Blocks.spruce_stairs, 0);
		case 2:
			return new BlockSet(Blocks.birch_stairs, 0);
		case 3:
			return new BlockSet(Blocks.jungle_stairs, 0);
		case 4:
			return new BlockSet(Blocks.brick_stairs, 0);
		case 5:
			return new BlockSet(Blocks.stone_stairs, 0);
		case 6:
			return new BlockSet(Blocks.stone_brick_stairs, 0);
		case 7:
			return new BlockSet(Blocks.nether_brick_stairs, 0);
		default:
			return new BlockSet(Blocks.oak_stairs, 0);
		}
	}

}
