package defeatedcrow.addonforamt.economy.plugin.mce;

import net.minecraft.item.ItemStack;
import shift.mceconomy2.api.MCEconomyAPI;
import defeatedcrow.addonforamt.economy.EcoMTCore;

public class MCEPlugin {

	private MCEPlugin() {

	}

	public static void load() {
		(new KariShopList()).load();
		registerMP();
	}

	static void registerMP() {
		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.fuelCan, 1, 0), 120); // cam
		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.fuelCan, 1, 1), 60); // veg
		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.ticket, 1, 0), 12000); // 4-8
		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.ticket, 1, 2), 12000); // 8-4
		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.ticket, 1, 3), 24000); // 8-8
		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.ticket, 1, 1), 24000); // 16-4
		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.ticket, 1, 4), 48000); // 16-8
		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.checker, 1, 0), 15); // cood

		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.distributor, 1, 0), 24000); // machine
		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.enTank, 1, 0), 1000); // machine
		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.motor, 1, 0), 6000); // machine
		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.generator, 1, 0), 3000); // machine
	}

}
