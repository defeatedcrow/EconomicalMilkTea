package defeatedcrow.addonforamt.economy.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ISimpleBuildingItem {

	boolean onBuild(World world, int x, int y, int z, int dir, EntityPlayer player, ItemStack stack);

	boolean isReplaceable(World world, int x, int y, int z);

	int getArea(int meta);

	boolean forceReplace();

	BuildType getType(int meta);

}
