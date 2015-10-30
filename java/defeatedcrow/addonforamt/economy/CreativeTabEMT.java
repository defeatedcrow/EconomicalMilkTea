package defeatedcrow.addonforamt.economy;

import mods.defeatedcrow.common.DCsAppleMilk;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabEMT extends CreativeTabs {

	// クリエイティブタブのアイコン画像や名称の登録クラス
	public CreativeTabEMT(String type) {
		super(type);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel() {
		return "EconomicalMilkTea!";
	}

	@Override
	public Item getTabIconItem() {
		return DCsAppleMilk.batteryItem;
	}

}
