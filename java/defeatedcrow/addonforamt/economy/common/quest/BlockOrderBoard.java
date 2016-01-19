package defeatedcrow.addonforamt.economy.common.quest;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EcoMTCore;

public class BlockOrderBoard extends BlockContainer {

	public BlockOrderBoard() {
		super(Material.clay);
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(0.2F);
		this.setResistance(1.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":gadget/stamp");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7,
			float par8, float par9) {

		ItemStack item = player.inventory.getCurrentItem();
		TileEntity tileentity = world.getTileEntity(x, y, z);
		TileOrderBoard tile = null;
		if (tileentity != null && tileentity instanceof TileOrderBoard) {
			tile = (TileOrderBoard) tileentity;
		}
		if (tile != null) {
			if (world.isRemote) {
				return true;
			} else {
				player.openGui(EcoMTCore.instance, EcoMTCore.instance.guiBoard, world, x, y, z);
				return true;
			}

		}

		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileOrderBoard();
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return EcoMTCore.boardRB;
	}

	@Override
	public int damageDropped(int par1) {
		return 0;
	}

	@Override
	public void onBlockPlacedBy(World world, int par2, int par3, int par4, EntityLivingBase living, ItemStack stack) {
		int dir = MathHelper.floor_double(living.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		int meta = stack.getItemDamage();
		byte facing = 0;

		if (dir == 0) {
			world.setBlockMetadataWithNotify(par2, par3, par4, 0, 3);
			facing = 0;
		}

		if (dir == 1) {
			world.setBlockMetadataWithNotify(par2, par3, par4, 1, 3);
			facing = 1;
		}

		if (dir == 2) {
			world.setBlockMetadataWithNotify(par2, par3, par4, 2, 3);
			facing = 2;
		}

		if (dir == 3) {
			world.setBlockMetadataWithNotify(par2, par3, par4, 3, 3);
			facing = 3;
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
		return super.getSelectedBoundingBoxFromPool(par1World, par2, par3, par4);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		this.thisBoundingBox(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
	}

	public void thisBoundingBox(int par1) {
		int dir = par1 & 3;
		float f = 0.0625F * 3;

		if (dir == 0)
			this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
		else if (dir == 2)
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
		else if (dir == 1)
			this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
		else if (dir == 3)
			this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

}
