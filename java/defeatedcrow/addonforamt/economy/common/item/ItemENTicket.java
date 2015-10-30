package defeatedcrow.addonforamt.economy.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.api.IEnergyTicket;
import defeatedcrow.addonforamt.economy.util.TimeUtil;

public class ItemENTicket extends Item implements IEnergyTicket {

	@SideOnly(Side.CLIENT)
	private IIcon iconType[];

	private static int lim = 5;

	public ItemENTicket() {
		super();
		this.setMaxStackSize(16);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		int j = MathHelper.clamp_int(par1, 0, this.lim);
		return this.iconType[j];
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int j = MathHelper.clamp_int(stack.getItemDamage(), 0, this.lim);
		return super.getUnlocalizedName() + "_" + this.getENGan(stack) + "_" + this.getUsableSlotNum(stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(this, 1, 0));
		par3List.add(new ItemStack(this, 1, 1));
		par3List.add(new ItemStack(this, 1, 2));
		par3List.add(new ItemStack(this, 1, 3));
		par3List.add(new ItemStack(this, 1, 4));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.iconType = new IIcon[this.lim];

		this.iconType[0] = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":tools/enticket_4_8");
		this.iconType[1] = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":tools/enticket_4_16");
		this.iconType[2] = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":tools/enticket_8_4");
		this.iconType[3] = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":tools/enticket_8_8");
		this.iconType[4] = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":tools/enticket_8_16");
	}

	@Override
	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		super.addInformation(stack, player, list, par4);
		int l = stack.getItemDamage();
		int gen = this.getENGan(stack);
		int slot = this.getUsableSlotNum(stack);
		list.add(new String("Generate: " + gen + " charge/t, Slot: " + slot));
		NBTTagCompound tag = stack.getTagCompound();
		if (tag != null && tag.hasKey("StartDay")) {
			int start = tag.getInteger("StartDay");
			int day = TimeUtil.getDay(player.worldObj);
			int i = 30 - day + start;
			if (i < 0) {
				list.add(new String(EnumChatFormatting.RED + "Already expired."));
			} else {
				list.add(new String("Remaining Day: " + i + " day"));
			}

		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	// エフェクト
	public boolean hasEffect(ItemStack par1ItemStack) {
		return par1ItemStack.getTagCompound() != null;
	}

	@Override
	public int getENGan(ItemStack stack) {
		if (stack == null || stack.getItem() == null)
			return 0;

		int m = stack.getItemDamage();
		switch (m) {
		case 0:
			return 8;
		case 1:
			return 16;
		case 2:
			return 4;
		case 3:
			return 8;
		default:
			return 16;
		}
	}

	@Override
	public int getStartDay(ItemStack stack) {
		if (stack == null || stack.getItem() == null)
			return 0;

		int ret = 0;
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("StartDay")) {
			ret = stack.getTagCompound().getInteger("StartDay");
		}
		return ret;
	}

	@Override
	public int getUsableSlotNum(ItemStack stack) {
		if (stack == null || stack.getItem() == null)
			return 0;

		int m = stack.getItemDamage();
		switch (m) {
		case 0:
		case 1:
			return 4;
		default:
			return 8;
		}
	}

}
