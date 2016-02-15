package defeatedcrow.addonforamt.economy.common.build;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.api.BuildType;
import defeatedcrow.addonforamt.economy.api.ISimpleBuildingItem;

// board
public class ItemSimpleBuild extends Item implements ISimpleBuildingItem {

	@SideOnly(Side.CLIENT)
	private IIcon iconNum[];

	public ItemSimpleBuild() {
		super();
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setTextureName("economical:tools/buildcard_square");
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
			float fx, float fy, float fz) {
		if (player == null || stack == null)
			return true;
		int dir = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		if (this.onBuild(world, x, y, z, dir, player, stack)) {
			world.playSoundAtEntity(player, "random.pop", 0.4F, 1.8F);
			if (!player.capabilities.isCreativeMode && --stack.stackSize <= 0) {
				player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
				player.inventory.markDirty();
			}
		}
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (player == null || stack == null || world.isRemote) {
			return stack;
		} else {
			int meta = stack.getItemDamage();
			int t = meta >> 3;
			int r = meta & 7;
			int n = t > 6 ? 0 : t + 1;
			int newMeta = (n << 3) + r;
			world.playSoundAtEntity(player, "random.pop", 0.4F, 1.8F);
			return new ItemStack(stack.getItem(), stack.stackSize, newMeta);
		}
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
					if (i == minX || i == maxX || k == minZ || k == maxZ) {
						if (this.isReplaceable(world, i, j, k)) {
							world.setBlock(i, j, k, set.block, set.meta, 3);
						}
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean isReplaceable(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		if (this.forceReplace()) {
			return EcoMTCore.arrowClearBedrock ? true : block.getBlockHardness(world, x, y, z) >= 0;
		} else {
			return block.isAir(world, x, y, z) || block.isLeaves(world, x, y, z)
					|| block.canReplace(world, x, y, z, 1, new ItemStack(Blocks.cobblestone));
		}
	}

	@Override
	public int getArea(int meta) {
		int i = meta & 7;
		switch (i) {
		case 0:
			return 3;
		case 1:
			return 4;
		case 2:
			return 5;
		case 3:
			return 6;
		case 4:
			return 7;
		case 5:
			return 8;
		case 6:
			return 9;
		case 7:
			return 10;
		default:
			return 3;
		}
	}

	@Override
	public BuildType getType(int meta) {
		return BuildType.SQUARE;
	}

	@Override
	public boolean forceReplace() {
		return false;
	}

	protected BlockSet getPlaceBlock(int meta) {
		int i = meta >> 3;
		switch (i) {
		case 0:
			return new BlockSet(Blocks.planks, 0);
		case 1:
			return new BlockSet(Blocks.planks, 1);
		case 2:
			return new BlockSet(Blocks.planks, 2);
		case 3:
			return new BlockSet(Blocks.glass, 0);
		case 4:
			return new BlockSet(Blocks.brick_block, 0);
		case 5:
			return new BlockSet(Blocks.stonebrick, 0);
		case 6:
			return new BlockSet(Blocks.stone, 0);
		case 7:
			return new BlockSet(Blocks.cobblestone, 0);
		default:
			return new BlockSet(Blocks.planks, 0);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		super.addInformation(stack, player, list, par4);
		int m = stack.getItemDamage();
		int range = this.getArea(m);
		BlockSet set = this.getPlaceBlock(m);
		ItemStack b = new ItemStack(set.block, 1, set.meta);
		list.add(new String("Size: " + range));
		list.add(new String("Block: " + b.getDisplayName()));
		list.add(new String("Can change block by right-click to air."));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(this, 1, 0));
		par3List.add(new ItemStack(this, 1, 1));
		par3List.add(new ItemStack(this, 1, 2));
		par3List.add(new ItemStack(this, 1, 3));
		par3List.add(new ItemStack(this, 1, 4));
		par3List.add(new ItemStack(this, 1, 5));
		par3List.add(new ItemStack(this, 1, 6));
		par3List.add(new ItemStack(this, 1, 7));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		super.registerIcons(par1IconRegister);
		this.iconNum = new IIcon[8];

		for (int i = 0; i < 8; i++) {
			this.iconNum[i] = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":tools/num" + (i + 3));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int i) {
		return this.itemIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int dam, int pass) {
		int i = dam & 7;
		return pass == 1 ? this.iconNum[i] : super.getIconFromDamageForRenderPass(dam, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}
}
