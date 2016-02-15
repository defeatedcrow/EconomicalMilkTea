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
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EcoMTCore;

public class BlockOrderExchanger extends BlockContainer {

	protected Random rand = new Random();

	public BlockOrderExchanger() {
		super(Material.clay);
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(0.2F);
		this.setResistance(60000.0F);
		this.setBlockUnbreakable();
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
		return EcoMTCore.dummyRB;
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
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":gadget/shop_kari");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7,
			float par8, float par9) {

		ItemStack item = player.inventory.getCurrentItem();
		TileEntity tileentity = world.getTileEntity(x, y, z);
		OrderExchanger tile = null;
		if (tileentity != null && tileentity instanceof OrderExchanger) {
			tile = (OrderExchanger) tileentity;
		}
		if (tile != null && tile.canHandleMP(player)) {
			if (world.isRemote) {
				return true;
			} else {
				player.openGui(EcoMTCore.instance, EcoMTCore.instance.guiOrder, world, x, y, z);
				return true;
			}

		}

		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new OrderExchanger();
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		if (world.isRemote)
			return;
		TileEntity tile = world.getTileEntity(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		OrderExchanger safe = null;
		if (tile != null && tile instanceof OrderExchanger) {
			safe = (OrderExchanger) tile;
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
		OrderExchanger tileentity = null;
		if (tile != null && tile instanceof OrderExchanger) {
			tileentity = (OrderExchanger) tile;
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
		OrderExchanger tileentity = null;
		if (tile != null && tile instanceof OrderExchanger) {
			tileentity = (OrderExchanger) tile;
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

	// Redstone power
	@Override
	public boolean canProvidePower() {
		return true;
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
		boolean b = ((OrderExchanger) world.getTileEntity(x, y, z)).isOpen();
		return b ? 15 : 0;
	}

	@Override
	public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int side) {
		return side == 1 ? this.isProvidingWeakPower(world, x, y, z, side) : 0;
	}

}
