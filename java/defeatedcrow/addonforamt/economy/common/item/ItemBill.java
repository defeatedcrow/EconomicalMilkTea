package defeatedcrow.addonforamt.economy.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.api.IMPCoin;
import defeatedcrow.addonforamt.economy.plugin.mce.MPHandler;

public class ItemBill extends Item implements IMPCoin {

	@SideOnly(Side.CLIENT)
	private IIcon iconType[];

	public ItemBill() {
		super();
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMP(int meta) {
		return meta == 0 ? 10000 : 500000;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		int j = MathHelper.clamp_int(par1, 0, 1);
		return this.iconType[j];
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int j = MathHelper.clamp_int(stack.getItemDamage(), 0, 1);
		if (j > 0) {
			return super.getUnlocalizedName() + "_wad";
		}
		return super.getUnlocalizedName();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(this, 1, 0));
		par3List.add(new ItemStack(this, 1, 1));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.iconType = new IIcon[2];

		this.iconType[0] = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":tools/bill");
		this.iconType[1] = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":tools/wad");
	}

	@Override
	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		super.addInformation(stack, player, list, par4);
		int mp = this.getMP(stack.getItemDamage());
		list.add(new String(mp + " MP"));
	}

	// 使用時
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4,
			int par5, int par6, int par7, float par8, float par9, float par10) {
		if (par2EntityPlayer == null)
			return true;
		this.onItemRightClick(par1ItemStack, par3World, par2EntityPlayer);
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (player == null || stack == null || world.isRemote) {
			return stack;
		} else {
			int mp = this.getMP(stack.getItemDamage());
			int add = MPHandler.INSTANCE.addPlayerMP(player, mp, true);
			if (add >= mp) {
				MPHandler.INSTANCE.addPlayerMP(player, mp, false);
				if (!player.capabilities.isCreativeMode && --stack.stackSize <= 0) {
					player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
					player.inventory.markDirty();
				}
			}
		}
		return stack;
	}

}
