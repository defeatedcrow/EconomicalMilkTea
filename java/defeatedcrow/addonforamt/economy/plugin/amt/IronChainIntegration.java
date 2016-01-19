package defeatedcrow.addonforamt.economy.plugin.amt;

import java.util.ArrayList;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import shift.mceconomy2.api.shop.IProduct;
import defeatedcrow.addonforamt.economy.plugin.mce.EMTProduct;
import defeatedcrow.ironchain.DCsIronChain;

public class IronChainIntegration {

	private IronChainIntegration() {
	}

	public static void load() {
		addOrder();
	}

	static void addOrder() {
		// none

	}

	public static ArrayList<IProduct> getProductList() {
		ArrayList<IProduct> list = new ArrayList<IProduct>();
		ItemStack met = new ItemStack(DCsIronChain.anzenMet, 1, 0);
		met.addEnchantment(Enchantment.blastProtection, 1);
		list.add(new EMTProduct(met, 1500));
		ItemStack boots = new ItemStack(DCsIronChain.anzenBoots, 1, 0);
		boots.addEnchantment(Enchantment.featherFalling, 1);
		list.add(new EMTProduct(boots, 2000));
		list.add(new EMTProduct(new ItemStack(DCsIronChain.sagyougi, 1, 0), 3500));
		list.add(new EMTProduct(new ItemStack(DCsIronChain.altimeter, 1, 0), 3000));
		return list;
	}

}
