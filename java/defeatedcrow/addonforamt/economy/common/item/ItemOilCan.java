package defeatedcrow.addonforamt.economy.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EcoMTCore;

public class ItemOilCan extends Item implements IFuelHandler {

	private static String[] sackName = new String[] {
			"camellia",
			"vegeoil" };

	@SideOnly(Side.CLIENT)
	private IIcon iconItemType[];

	public ItemOilCan() {
		super();
		this.setContainerItem(Items.paper);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	/* -- 牛乳のみ飲食効果がある -- */

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		int j = MathHelper.clamp_int(par1, 0, 1);
		return this.iconItemType[j];
	}

	@Override
	public int getMetadata(int par1) {
		return par1;
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		int meta = par1ItemStack.getItemDamage();
		int i = MathHelper.clamp_int(meta, 0, 1);
		return meta < 2 ? super.getUnlocalizedName() + "_" + sackName[i] : super.getUnlocalizedName() + "_" + meta;
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
		this.iconItemType = new IIcon[2];

		for (int i = 0; i < 2; ++i) {
			this.iconItemType[i] = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":tools/pack_" + sackName[i]);
		}
	}

	@Override
	public int getBurnTime(ItemStack fuel) {
		if (fuel != null)
			return fuel.getItemDamage() == 0 ? 2400 : 1600;

		return 0;
	}

}
