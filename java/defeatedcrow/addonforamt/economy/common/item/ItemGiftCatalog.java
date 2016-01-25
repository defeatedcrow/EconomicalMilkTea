package defeatedcrow.addonforamt.economy.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.api.order.IRewardItem;

public class ItemGiftCatalog extends Item implements IRewardItem {

	public ItemGiftCatalog() {
		super();
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":tools/stamp_catalog");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		player.openGui(EcoMTCore.instance, EcoMTCore.guiCatalog, world, (int) player.posX, (int) player.posY,
				(int) player.posZ);
		return itemStack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
		super.addInformation(item, player, list, par4);
		if (!item.hasTagCompound()) {
			item.setTagCompound(new NBTTagCompound());
			item.getTagCompound().setShort("emt.stamp", (short) 0);
		}
		Short count = item.getTagCompound().getShort("emt.stamp");
		String s = "Stamp count : " + count;
		list.add(s);
	}

}
