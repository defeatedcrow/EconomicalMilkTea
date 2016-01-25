package defeatedcrow.addonforamt.economy.common.shop;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.plugin.mce.MealShopList;

public class BlockMealShop extends ShopBlockBase {

	protected Random rand = new Random();

	public BlockMealShop() {
		super();
	}

	@Override
	protected int getShopID() {
		return MealShopList.thisShopId;
	}

	@Override
	protected String getShopIconName() {
		return EcoMTCore.PACKAGE + ":gadget/shop_crop";
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileMealShop();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7,
			float par8, float par9) {

		if (world.isRemote) {
			return true;
		} else {
			boolean b = false;
			for (int i = 0; i < 9; i++) {
				ItemStack check = player.inventory.getStackInSlot(i);
				if (check != null && check.getItem() != null && check.getItem() == EcoMTCore.staffCard)
					b = true;
			}
			if (b) {
				player.openGui(EcoMTCore.instance, EcoMTCore.instance.guiShop, world, x, y, z);
			} else {
				return super.onBlockActivated(world, x, y, z, player, par6, par7, par8, par9);
			}
		}

		return true;
	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
		TileMealShop tileentity = (TileMealShop) par1World.getTileEntity(par2, par3, par4);

		if (tileentity != null) {
			// ItemStack block = new ItemStack(this, 1, 0);
			//
			// float a = this.rand.nextFloat() * 0.8F + 0.1F;
			// float a1 = this.rand.nextFloat() * 0.8F + 0.1F;
			// float a2 = this.rand.nextFloat() * 0.8F + 0.1F;
			// EntityItem drop = new EntityItem(par1World, par2 + a, par3 + a1, par4 + a2, block);
			//
			// float a3 = 0.05F;
			// drop.motionX = (float) this.rand.nextGaussian() * a3;
			// drop.motionY = (float) this.rand.nextGaussian() * a3 + 0.2F;
			// drop.motionZ = (float) this.rand.nextGaussian() * a3;
			// par1World.spawnEntityInWorld(drop);

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

}
