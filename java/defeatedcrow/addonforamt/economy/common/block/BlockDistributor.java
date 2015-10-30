package defeatedcrow.addonforamt.economy.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EMTLogger;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.util.ChunkLoaderController;
import defeatedcrow.addonforamt.economy.util.ChunkLoaderController.IChunkBlock;

public class BlockDistributor extends BlockContainer implements IChunkBlock {

	protected Random rand = new Random();

	public BlockDistributor() {
		super(Material.ground);
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(0.2F);
		this.setResistance(15.0F);
	}

	@Override
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int par6,
			float par7, float par8, float par9) {

		ItemStack item = player.inventory.getCurrentItem();
		TileEntity tileentity = world.getTileEntity(par2, par3, par4);
		TileDistributor tile = null;
		if (tileentity != null && tileentity instanceof TileDistributor) {
			tile = (TileDistributor) tileentity;
		}

		if (tile != null) {
			if (player.isSneaking()) {
				int m = world.getBlockMetadata(par2, par3, par4);
				int next = m > 3 ? m & 3 : m | 4;
				world.setBlockMetadataWithNotify(par2, par3, par4, next, 3);
				world.playSoundEffect(par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, "tile.piston.in", 0.5F,
						this.rand.nextFloat() * 0.15F + 1.2F);
				// if (item == null) {
				// ChunkLoaderController.removeAllList();
				// EMTCood cood1 = new EMTCood(par2, par4);
				// EMTCood cood2 = new EMTCood(par2, par4);
				// boolean a = cood1.equals(cood2);
				// boolean b = cood1.hashCode() == cood2.hashCode();
				// EMTLogger.debugInfo("" + a + ", " + b);
				// }
			} else {
				if (!world.isRemote) {
					player.openGui(EcoMTCore.instance, EcoMTCore.instance.guiDist, world, par2, par3, par4);
				}
				return true;
			}
		}
		return true;
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
		TileEntity tile = par1World.getTileEntity(par2, par3, par4);
		TileDistributor tileentity = null;
		if (tile != null && tile instanceof TileDistributor) {
			tileentity = (TileDistributor) tile;
		}

		if (tileentity != null) {

			for (int j1 = 0; j1 < tileentity.getSizeInventory(); ++j1) {
				ItemStack itemstack = tileentity.getStackInSlot(j1);

				if (itemstack != null) {
					float f = this.rand.nextFloat() * 0.8F + 0.1F;
					float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
					float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

					while (itemstack.stackSize > 0) {
						int k1 = this.rand.nextInt(21) + 10;

						if (k1 > itemstack.stackSize) {
							k1 = itemstack.stackSize;
						}

						itemstack.stackSize -= k1;
						EntityItem entityitem = new EntityItem(par1World, par2 + f, par3 + f1, par4 + f2,
								new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem().setTagCompound(
									(NBTTagCompound) itemstack.getTagCompound().copy());
						}

						float f3 = 0.05F;
						entityitem.motionX = (float) this.rand.nextGaussian() * f3;
						entityitem.motionY = (float) this.rand.nextGaussian() * f3 + 0.2F;
						entityitem.motionZ = (float) this.rand.nextGaussian() * f3;
						par1World.spawnEntityInWorld(entityitem);
					}
				}
			}

			par1World.func_147453_f(par2, par3, par4, par5);
		}

		// 周囲9チャンクのローダーを削除
		int coodX = par2 >> 4;
		int coodZ = par4 >> 4;
		if (!par1World.isRemote) {
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					ChunkLoaderController.deleteBlockTicket(par1World, par2, par3, par4, coodX + i, coodZ + j);
				}
			}
		}

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
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
	public int damageDropped(int par1) {
		return 0;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase live, ItemStack par6ItemStack) {
		int l = MathHelper.floor_double(live.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		int meta = par6ItemStack.getItemDamage();
		byte facing = 0;

		if (l == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 0, 3);
			facing = 0;
		}

		if (l == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 1, 3);
			facing = 1;
		}

		if (l == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 3);
			facing = 2;
		}

		if (l == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 3);
			facing = 3;
		}

		int coodX = x >> 4;
		int coodZ = z >> 4;

		// 周囲9チャンクのローダーを起動する
		if (!world.isRemote) {
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					ChunkLoaderController.setBlockTicket(world, x, y, z, coodX + i, coodZ + j);
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":gadget/x32/generator_D_F");
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileDistributor();
	}

	@Override
	public int getRenderType() {
		return EcoMTCore.dummyRB;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemIconName() {
		return "hopper";
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		int m = world.getBlockMetadata(x, y, z) & 3;
		float f = 0.5F;
		switch (m) {
		case 0:
			this.setBlockBounds(0F, 0F, f, 1F, 1F, 1F);
			break;
		case 1:
			this.setBlockBounds(0F, 0F, 0F, f, 1F, 1F);
			break;
		case 2:
			this.setBlockBounds(0F, 0F, 0F, 1F, 1F, f);
			break;
		case 3:
			this.setBlockBounds(f, 0F, 0F, 1F, 1F, 1F);
			break;
		default:
			this.setBlockBounds(0F, f, 0F, 1F, 1F, 1F);
			break;
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
	public boolean canLoad(World world, int x, int y, int z) {
		EMTLogger.debugInfo("Could be loaded!");
		return true;
	}

}
