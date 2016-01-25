package defeatedcrow.addonforamt.economy.plugin.mce;

import net.minecraft.item.ItemStack;
import shift.mceconomy2.api.MCEconomyAPI;
import defeatedcrow.addonforamt.economy.EcoMTCore;

public class MCEPlugin {

	private MCEPlugin() {

	}

	public static void load() {
		(new OrderShopList()).load();
		(new KariShopList()).load();
		(new ColdShopList()).load();
		(new IndustrialShopList()).load();
		(new BuildingShopList()).load();
		(new CropShopList()).load();
		(new MealShopList()).load();
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

		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.emtShop, 1, 0), 2500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.energyShop, 1, 0), 2500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.coldShop, 1, 0), 2500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.cropShop, 1, 0), 2500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.mealShop, 1, 0), 2500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.buildShop, 1, 0), 2500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.engeneerShop, 1, 0), 2500);

		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.questBlock, 1, 0), 1500);
		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.questKanban, 1, 0), 1000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.safetyBox, 1, 0), 10000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.giftCatalog, 1, 0), 1000);
		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.staffCard, 1, 0), 500);

		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.yukiti, 1, 0), 10000); // 同額換金
		MCEconomyAPI.addPurchaseItem(new ItemStack(EcoMTCore.yukiti, 1, 1), 500000); // 同額換金
	}

}
