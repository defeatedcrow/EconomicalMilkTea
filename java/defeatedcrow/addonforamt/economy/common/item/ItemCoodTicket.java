package defeatedcrow.addonforamt.economy.common.item;

import java.util.List;

import mods.defeatedcrow.api.charge.IChargeableMachine;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EcoMTCore;

public class ItemCoodTicket extends Item {

	public ItemCoodTicket() {
		super();
		this.setMaxStackSize(16);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":tools/coodticket");
	}

	@Override
	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		super.addInformation(stack, player, list, par4);
		int l = stack.getItemDamage();
		NBTTagCompound tag = stack.getTagCompound();
		if (this.getCood(stack) != null) {
			int[] ret = this.getCood(stack);
			String dim = this.getDimName(stack);
			list.add(new String("Registered pos: " + ret[0] + " " + ret[1] + " " + ret[2] + ", " + dim));
			TileEntity tile = player.worldObj.getTileEntity(ret[0], ret[1], ret[2]);
			if (tile != null && tile instanceof IChargeableMachine) {
				list.add(new String("Chargeable machine is active."));
			}

		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	// エフェクト
	public boolean hasEffect(ItemStack stack) {
		return this.isRegistered(stack);
	}

	public boolean isRegistered(ItemStack stack) {
		return stack.getTagCompound() != null && stack.getTagCompound().hasKey("DCsCoodSet");
	}

	public int[] getCood(ItemStack stack) {
		if (stack == null || stack.getItem() == null)
			return null;

		int[] ret = {
				0,
				0,
				0 };
		if (stack.hasTagCompound()) {
			NBTTagCompound tagX = stack.getTagCompound();
			if (tagX.hasKey("DCsCoodSet")) {
				ret[0] = tagX.getInteger("DCsCoodX");
				ret[1] = tagX.getInteger("DCsCoodY");
				ret[2] = tagX.getInteger("DCsCoodZ");
				return ret;
			}
		}
		return null;
	}

	public int getDim(ItemStack stack) {
		if (stack == null || stack.getItem() == null)
			return 0;

		int ret = 0;
		if (stack.hasTagCompound()) {
			NBTTagCompound tagX = stack.getTagCompound();
			if (tagX.hasKey("DCsCoodSet")) {
				ret = tagX.getInteger("DCsCoodDim");
				return ret;
			}
		}
		return 0;
	}

	public String getDimName(ItemStack stack) {
		if (stack == null || stack.getItem() == null)
			return "";

		String ret = "";
		if (stack.hasTagCompound()) {
			NBTTagCompound tagX = stack.getTagCompound();
			if (tagX.hasKey("DCsCoodSet")) {
				ret = tagX.getString("DCsDimName");
				return ret;
			}
		}
		return "";
	}

	// 使用時
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
			float fx, float fy, float fz) {
		if (world.isRemote) {
			return true;
		} else {
			TileEntity tile = player.worldObj.getTileEntity(x, y, z);
			if (tile != null && tile instanceof IChargeableMachine) {
				IChargeableMachine app = (IChargeableMachine) tile;
				NBTTagCompound nbt = stack.getTagCompound();
				boolean flag = false;

				if (nbt == null) {
					nbt = new NBTTagCompound();
					flag = true;
				} else if (!nbt.hasKey("DCsCoodSet")) {
					flag = true;
				}

				if (flag) {
					int dim = world.provider.dimensionId;
					String dimName = world.provider.getDimensionName();

					nbt.setByte("DCsCoodSet", (byte) 1);
					nbt.setInteger("DCsCoodX", x);
					nbt.setInteger("DCsCoodY", y);
					nbt.setInteger("DCsCoodZ", z);
					nbt.setInteger("DCsCooddim", dim);
					nbt.setString("DCsDimName", dimName);

					stack.setTagCompound(nbt);
					world.playSoundAtEntity(player, "random.pop", 0.4F, 1.8F);
					player.addChatMessage(new ChatComponentText("Registered machine pos : " + x + ", " + (y + 1) + ", "
							+ z));
					return true;
				}

			}
			return false;
		}
	}

}
