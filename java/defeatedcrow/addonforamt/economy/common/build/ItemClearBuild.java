package defeatedcrow.addonforamt.economy.common.build;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.api.BuildType;

public class ItemClearBuild extends ItemSimpleBuild {

	public ItemClearBuild() {
		super();
		this.setTextureName("economical:tools/buildcard_clear");
	}

	@Override
	public boolean onBuild(World world, int x, int y, int z, int dir, EntityPlayer player, ItemStack stack) {
		int range = this.getArea(stack.getItemDamage());
		int minX = x - range;
		int maxX = x + range;
		int minZ = z - range;
		int maxZ = z + range;
		int minY = y + 1;
		int maxY = y + range + 1;
		BlockSet set = this.getPlaceBlock(stack.getItemDamage());
		for (int i = minX; i <= maxX; i++) {
			for (int j = minY; j < maxY; j++) {
				for (int k = minZ; k <= maxZ; k++) {
					if (this.isReplaceable(world, i, j, k)) {
						world.setBlockToAir(i, j, k);
					}
				}
			}
		}
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		return stack;
	}

	@Override
	public BuildType getType(int meta) {
		return BuildType.SQUARE;
	}

	@Override
	public boolean forceReplace() {
		return true;
	}

	@Override
	protected BlockSet getPlaceBlock(int meta) {
		return new BlockSet(Blocks.planks, 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		super.addInformation(stack, player, list, par4);
		int m = stack.getItemDamage();
		int range = this.getArea(m);
		list.add(new String("Size: " + range));
	}

}
