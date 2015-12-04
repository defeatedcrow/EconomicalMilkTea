package defeatedcrow.addonforamt.economy.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EcoMTCore;

public class BlockGeneratorEMT extends BlockContainer {

	protected Random rand = new Random();

	@SideOnly(Side.CLIENT)
	private IIcon texT;
	@SideOnly(Side.CLIENT)
	private IIcon texB;
	@SideOnly(Side.CLIENT)
	private IIcon texF;
	@SideOnly(Side.CLIENT)
	private IIcon texS;

	public BlockGeneratorEMT() {
		super(Material.ground);
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(0.2F);
		this.setResistance(15.0F);
	}

	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer,
			int par6, float par7, float par8, float par9) {

		ItemStack item = par5EntityPlayer.inventory.getCurrentItem();
		TileEntity tileentity = par1World.getTileEntity(par2, par3, par4);
		TileGeneratorEMT tile = null;
		if (tileentity != null && tileentity instanceof TileGeneratorEMT) {
			tile = (TileGeneratorEMT) tileentity;
		}
		if (tile != null) {
			if (par1World.isRemote) {
				return true;
			} else {
				par5EntityPlayer.openGui(EcoMTCore.instance, EcoMTCore.instance.guiGen, par1World, par2, par3, par4);
				return true;
			}

		}
		return true;
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
		TileEntity tile = par1World.getTileEntity(par2, par3, par4);
		TileGeneratorEMT tileentity = null;
		if (tile != null && tile instanceof TileGeneratorEMT) {
			tileentity = (TileGeneratorEMT) tile;
		}

		if (tileentity != null) {
			int charge = tileentity.getChargeAmount();

			ItemStack block = new ItemStack(this, 1, 0);

			float a = this.rand.nextFloat() * 0.8F + 0.1F;
			float a1 = this.rand.nextFloat() * 0.8F + 0.1F;
			float a2 = this.rand.nextFloat() * 0.8F + 0.1F;
			EntityItem drop = new EntityItem(par1World, par2 + a, par3 + a1, par4 + a2, block);

			if (charge > 0) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setShort("charge", (short) charge);
				drop.getEntityItem().setTagCompound(tag);
			}

			float a3 = 0.05F;
			drop.motionX = (float) this.rand.nextGaussian() * a3;
			drop.motionY = (float) this.rand.nextGaussian() * a3 + 0.2F;
			drop.motionZ = (float) this.rand.nextGaussian() * a3;
			par1World.spawnEntityInWorld(drop);

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

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	// 基本設定

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int i = par2;
		boolean flag = par2 > 0;
		if (i > 1)
			i = 1;
		switch (par1) {
		case 0:
			return texB;
		case 1:
			return texT;
		case 2:
			return flag ? texF : texS;
		case 3:
			return flag ? texF : texS;
		case 4:
			return flag ? texS : texF;
		case 5:
			return flag ? texS : texF;
		default:
			return this.texS;
		}
	}

	@Override
	public int damageDropped(int par1) {
		return 0;
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return null;
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase,
			ItemStack par6ItemStack) {
		int l = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		int meta = par6ItemStack.getItemDamage();
		byte facing = 0;

		if (l == 0) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 3);
			facing = 0;
		}

		if (l == 1) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 3);
			facing = 1;
		}

		if (l == 2) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 1, 3);
			facing = 2;
		}

		if (l == 3) {
			par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 3);
			facing = 4;
		}

		short charge = 0;

		if (par6ItemStack.getItem() == Item.getItemFromBlock(this)) {
			if (par6ItemStack.hasTagCompound() && par6ItemStack.getTagCompound().hasKey("charge")) {
				NBTTagCompound tag = par6ItemStack.getTagCompound();
				charge = tag.getShort("charge");
			}
		}

		TileEntity tile = par1World.getTileEntity(par2, par3, par4);
		if (tile != null && tile instanceof TileGeneratorEMT) {
			((TileGeneratorEMT) tile).setChargeAmount(charge);
			par1World.markBlockForUpdate(par2, par3, par4);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":gadget/x32/generator_D_S");
		this.texS = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":gadget/x32/generator_D_S");
		this.texF = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":gadget/x32/generator_D_F");
		this.texT = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":gadget/x32/generator_D_T");
		this.texB = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":gadget/x32/generator_D_B");

	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return new TileGeneratorEMT();
	}

}