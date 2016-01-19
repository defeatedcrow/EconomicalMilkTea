package defeatedcrow.addonforamt.economy.common.item;

import java.util.ArrayList;

import mods.defeatedcrow.api.edibles.IEdibleItem;
import mods.defeatedcrow.api.potion.AMTPotionManager;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EcoMTCore;

public class ItemEMT extends Item implements IEdibleItem {

	public ItemEMT() {
		super();
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":food/EMT");
	}

	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		int meta = par1ItemStack.getItemDamage();

		if (!par3EntityPlayer.capabilities.isCreativeMode) {
			--par1ItemStack.stackSize;
			this.returnItemStack(par3EntityPlayer, meta);
		}

		if (!par2World.isRemote) {
			if (this.effectOnEaten(par3EntityPlayer, meta) != null) {
				ArrayList<PotionEffect> potion = this.effectOnEaten(par3EntityPlayer, meta);
				if (potion != null && !potion.isEmpty()) {
					for (PotionEffect ret : potion) {
						par3EntityPlayer.addPotionEffect(ret);
					}
				}
			}
		}

		return par1ItemStack;
	}

	/**
	 * ガリガリ咀嚼する時間
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 16;
	}

	/**
	 * 飲食時のエフェクト。
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.drink;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}

	protected boolean returnItemStack(EntityPlayer player, int meta) {
		ItemStack ret = this.getReturnContainer(meta);
		if (ret != null) {
			if (!player.worldObj.isRemote) {
				EntityItem drop = new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, ret);
				player.worldObj.spawnEntityInWorld(drop);
				return true;
			}
		}
		return false;
	}

	@Override
	public ItemStack getReturnContainer(int meta) {
		if (meta == 0) {
			return new ItemStack(this, 1, 1);
		} else {
			return new ItemStack(Items.paper, 1, 0);
		}
	}

	@Override
	public ArrayList<PotionEffect> effectOnEaten(EntityPlayer player, int meta) {
		ArrayList<PotionEffect> list = new ArrayList<PotionEffect>();
		int id = AMTPotionManager.manager.AMTgetPotion("immunization").getId();
		list.add(new PotionEffect(id, 1200, 0));
		list.add(new PotionEffect(Potion.regeneration.id, 200, 0));
		return list;
	}

	@Override
	public int[] hungerOnEaten(int meta) {
		return new int[] {
				0,
				0 };
	}

}
