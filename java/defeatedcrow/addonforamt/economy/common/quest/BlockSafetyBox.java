package defeatedcrow.addonforamt.economy.common.quest;

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

public class BlockSafetyBox extends BlockContainer {

	protected Random rand = new Random();

	@SideOnly(Side.CLIENT)
	private IIcon texT;
	@SideOnly(Side.CLIENT)
	private IIcon texB;
	@SideOnly(Side.CLIENT)
	private IIcon texF;
	@SideOnly(Side.CLIENT)
	private IIcon texS;

	public BlockSafetyBox() {
		super(Material.anvil);
		this.setStepSound(Block.soundTypeAnvil);
		this.setHardness(1.0F);
		this.setResistance(60000.0F);
		this.setBlockUnbreakable();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7,
			float par8, float par9) {

		ItemStack item = player.inventory.getCurrentItem();
		TileEntity tileentity = world.getTileEntity(x, y, z);
		TileSafetyBox tile = null;
		if (tileentity != null && tileentity instanceof TileSafetyBox) {
			tile = (TileSafetyBox) tileentity;
		}
		if (tile != null) {
			if (world.isRemote) {
				return true;
			} else {
				player.openGui(EcoMTCore.instance, EcoMTCore.instance.guiSafety, world, x, y, z);
				return true;
			}

		}
		return true;
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		if (world.isRemote)
			return;
		TileEntity tile = world.getTileEntity(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		TileSafetyBox safe = null;
		if (tile != null && tile instanceof TileSafetyBox) {
			safe = (TileSafetyBox) tile;
			if (safe.canHandleMP(player)) {
				world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "random.pop", 0.5F,
						this.rand.nextFloat() * 0.15F + 1.2F);
				world.setBlockToAir(x, y, z);
			}
		}
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
		TileEntity tile = par1World.getTileEntity(par2, par3, par4);
		TileSafetyBox tileentity = null;
		if (tile != null && tile instanceof TileSafetyBox) {
			tileentity = (TileSafetyBox) tile;
		}

		if (tileentity != null) {

			ItemStack drop = new ItemStack(this, 1, 0);
			NBTTagCompound tag = new NBTTagCompound();
			tag = tileentity.getNBT(tag);
			drop.setTagCompound(tag);
			EntityItem entityitem = new EntityItem(par1World, par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, drop);
			float f3 = 0.05F;
			entityitem.motionX = (float) this.rand.nextGaussian() * f3;
			entityitem.motionY = (float) this.rand.nextGaussian() * f3 + 0.2F;
			entityitem.motionZ = (float) this.rand.nextGaussian() * f3;
			par1World.spawnEntityInWorld(entityitem);

			par1World.func_147453_f(par2, par3, par4, par5);
		}
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
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		int i = par2;
		if (i > 4)
			i = 4;
		switch (par1) {
		case 0:
			return texB;
		case 1:
			return texT;
		case 2:
			return i == 0 ? texF : texS;
		case 3:
			return i == 2 ? texF : texS;
		case 4:
			return i == 3 ? texF : texS;
		case 5:
			return i == 1 ? texF : texS;
		default:
			return this.texF;
		}
	}

	@Override
	public void onBlockPlacedBy(World world, int par2, int par3, int par4, EntityLivingBase living, ItemStack stack) {
		int l = MathHelper.floor_double(living.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		int meta = stack.getItemDamage();
		byte facing = 0;

		if (l == 0) {
			world.setBlockMetadataWithNotify(par2, par3, par4, 0, 3);
			facing = 0;
		}

		if (l == 1) {
			world.setBlockMetadataWithNotify(par2, par3, par4, 1, 3);
			facing = 1;
		}

		if (l == 2) {
			world.setBlockMetadataWithNotify(par2, par3, par4, 2, 3);
			facing = 2;
		}

		if (l == 3) {
			world.setBlockMetadataWithNotify(par2, par3, par4, 3, 3);
			facing = 3;
		}

		TileEntity tile = world.getTileEntity(par2, par3, par4);
		TileSafetyBox tileentity = null;
		if (tile != null && tile instanceof TileSafetyBox) {
			tileentity = (TileSafetyBox) tile;
		}

		if (tileentity != null) {
			NBTTagCompound tag = stack.getTagCompound();
			if (tag != null) {
				tileentity.setNBT(tag);
			}

			if (!world.isRemote && living instanceof EntityPlayer && tileentity.getOwnerName().equalsIgnoreCase("none")) {
				EntityPlayer player = (EntityPlayer) living;
				String name = player.getCommandSenderName();
				String uuid = player.getUniqueID().toString();
				tileentity.setOwnerName(name);
				tileentity.setOwnerUUID(uuid);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":gadget/storage_s");
		this.texS = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":gadget/storage_s");
		this.texF = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":gadget/storage_f");
		this.texT = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":gadget/storage_t");
		this.texB = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":gadget/storage_b");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int a) {
		return new TileSafetyBox();
	}

}
