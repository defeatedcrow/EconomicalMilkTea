package defeatedcrow.addonforamt.economy.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EcoMTCore;

public class ItemDummyForDispray extends Item {

	public ItemDummyForDispray() {
		super();
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":tools/dummyItem");
	}

	@Override
	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		super.addInformation(stack, player, list, par4);
		String s = StatCollector.translateToLocal("dcs.emt.order.dummy.itemMessage");
		list.add(new String(s));
		NBTTagCompound tag = stack.getTagCompound();
		if (tag != null && tag.hasKey("OreName")) {
			String ore = tag.getString("OreName");
			list.add(EnumChatFormatting.AQUA + ore);
		}
	}

}
