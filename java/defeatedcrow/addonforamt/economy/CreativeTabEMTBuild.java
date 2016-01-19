package defeatedcrow.addonforamt.economy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabEMTBuild extends CreativeTabs {

	// クリエイティブタブのアイコン画像や名称の登録クラス
	public CreativeTabEMTBuild(String type) {
		super(type);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel() {
		return "EconomicalMilkTea!:BuildTickets";
	}

	@Override
	public Item getTabIconItem() {
		return EcoMTCore.buildCard_b;
	}

}
