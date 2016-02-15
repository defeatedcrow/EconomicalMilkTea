package defeatedcrow.addonforamt.economy;

import cpw.mods.fml.common.registry.GameRegistry;
import defeatedcrow.addonforamt.economy.common.block.BlockDistributor;
import defeatedcrow.addonforamt.economy.common.block.BlockENMotor;
import defeatedcrow.addonforamt.economy.common.block.BlockENTank;
import defeatedcrow.addonforamt.economy.common.block.BlockGeneratorEMT;
import defeatedcrow.addonforamt.economy.common.block.ItemENTank;
import defeatedcrow.addonforamt.economy.common.block.ItemMachineBase;
import defeatedcrow.addonforamt.economy.common.build.ItemBoardBuild;
import defeatedcrow.addonforamt.economy.common.build.ItemClearBuild;
import defeatedcrow.addonforamt.economy.common.build.ItemPlateBuild;
import defeatedcrow.addonforamt.economy.common.build.ItemRoofBuild;
import defeatedcrow.addonforamt.economy.common.build.ItemSimpleBuild;
import defeatedcrow.addonforamt.economy.common.build.ItemVillageBuild;
import defeatedcrow.addonforamt.economy.common.item.ItemBill;
import defeatedcrow.addonforamt.economy.common.item.ItemCoodTicket;
import defeatedcrow.addonforamt.economy.common.item.ItemDummyForDispray;
import defeatedcrow.addonforamt.economy.common.item.ItemEMT;
import defeatedcrow.addonforamt.economy.common.item.ItemENTicket;
import defeatedcrow.addonforamt.economy.common.item.ItemGiftCatalog;
import defeatedcrow.addonforamt.economy.common.item.ItemOilCan;
import defeatedcrow.addonforamt.economy.common.item.ItemStaffCard;
import defeatedcrow.addonforamt.economy.common.item.ItemStamp;
import defeatedcrow.addonforamt.economy.common.quest.BlockOrderBoard;
import defeatedcrow.addonforamt.economy.common.quest.BlockOrderExchanger;
import defeatedcrow.addonforamt.economy.common.quest.BlockSafetyBox;
import defeatedcrow.addonforamt.economy.common.quest.BlockSafetyChest;
import defeatedcrow.addonforamt.economy.common.quest.ItemSafetyBox;
import defeatedcrow.addonforamt.economy.common.shop.BlockBuilderShop;
import defeatedcrow.addonforamt.economy.common.shop.BlockColdShop;
import defeatedcrow.addonforamt.economy.common.shop.BlockCropShop;
import defeatedcrow.addonforamt.economy.common.shop.BlockIndustrialShop;
import defeatedcrow.addonforamt.economy.common.shop.BlockKariShop;
import defeatedcrow.addonforamt.economy.common.shop.BlockMealShop;
import defeatedcrow.addonforamt.economy.common.shop.BlockOrderToolShop;

public class MaterialEMT {

	private MaterialEMT() {
	}

	public static void init() {
		blocks();
		items();

		GameRegistry.registerBlock(EcoMTCore.distributor, "defeatedcrow.emt.distributor");
		GameRegistry.registerBlock(EcoMTCore.generator, ItemMachineBase.class, "defeatedcrow.emt.generator");
		GameRegistry.registerBlock(EcoMTCore.enTank, ItemENTank.class, "defeatedcrow.emt.en_tank");
		GameRegistry.registerBlock(EcoMTCore.motor, ItemMachineBase.class, "defeatedcrow.emt.en_motor");

		GameRegistry.registerBlock(EcoMTCore.emtShop, "defeatedcrow.emt.energy_shop");
		GameRegistry.registerBlock(EcoMTCore.energyShop, "defeatedcrow.emt.emt_shop");
		GameRegistry.registerBlock(EcoMTCore.coldShop, "defeatedcrow.emt.cold_shop");
		GameRegistry.registerBlock(EcoMTCore.cropShop, "defeatedcrow.emt.crop_shop");
		GameRegistry.registerBlock(EcoMTCore.mealShop, "defeatedcrow.emt.meal_shop");
		GameRegistry.registerBlock(EcoMTCore.engeneerShop, "defeatedcrow.emt.engeneer_shop");
		GameRegistry.registerBlock(EcoMTCore.buildShop, "defeatedcrow.emt.building_shop");

		GameRegistry.registerBlock(EcoMTCore.questKanban, "defeatedcrow.emt.order_board");
		GameRegistry.registerBlock(EcoMTCore.questBlock, ItemSafetyBox.class, "defeatedcrow.emt.order_transaction");
		GameRegistry.registerBlock(EcoMTCore.safetyBox, ItemSafetyBox.class, "defeatedcrow.emt.safety");
		GameRegistry.registerBlock(EcoMTCore.safetyChest, ItemSafetyBox.class, "defeatedcrow.emt.safety_chest");

		GameRegistry.registerItem(EcoMTCore.yukiti, "defeatedcrow.emt.bill");
		GameRegistry.registerItem(EcoMTCore.ticket, "defeatedcrow.emt.en_ticket");
		GameRegistry.registerItem(EcoMTCore.checker, "defeatedcrow.emt.cood_ticket");
		GameRegistry.registerItem(EcoMTCore.stamp, "defeatedcrow.emt.stamp");
		GameRegistry.registerItem(EcoMTCore.giftCatalog, "defeatedcrow.emt.stamp_catalog");
		GameRegistry.registerItem(EcoMTCore.staffCard, "defeatedcrow.emt.staffcard");
		GameRegistry.registerItem(EcoMTCore.dummyItem, "defeatedcrow.emt.dummy");

		GameRegistry.registerItem(EcoMTCore.fuelCan, "defeatedcrow.emt.fuel_can");

		GameRegistry.registerItem(EcoMTCore.buildCard_b, "defeatedcrow.emt.buildcard_b");
		GameRegistry.registerItem(EcoMTCore.buildCard_p, "defeatedcrow.emt.buildcard_p");
		GameRegistry.registerItem(EcoMTCore.buildCard_s, "defeatedcrow.emt.buildcard_s");
		GameRegistry.registerItem(EcoMTCore.buildCard_r, "defeatedcrow.emt.buildcard_r");
		GameRegistry.registerItem(EcoMTCore.buildCard_c, "defeatedcrow.emt.buildcard_c");
		GameRegistry.registerItem(EcoMTCore.villageCard, "defeatedcrow.emt.villagecard");

		GameRegistry.registerItem(EcoMTCore.EMT, "defeatedcrow.emt.emtItem");
	}

	private static void blocks() {
		// machines
		EcoMTCore.distributor = (new BlockDistributor()).setBlockName("defeatedcrow.emt.distributor").setCreativeTab(
				EcoMTCore.economy);

		EcoMTCore.generator = (new BlockGeneratorEMT()).setBlockName("defeatedcrow.emt.generator").setCreativeTab(
				EcoMTCore.economy);

		EcoMTCore.enTank = (new BlockENTank()).setBlockName("defeatedcrow.emt.en_tank").setCreativeTab(
				EcoMTCore.economy);

		EcoMTCore.motor = (new BlockENMotor()).setBlockName("defeatedcrow.emt.en_motor").setCreativeTab(
				EcoMTCore.economy);

		EcoMTCore.emtShop = (new BlockOrderToolShop()).setBlockName("defeatedcrow.emt.emt_shop").setCreativeTab(
				EcoMTCore.economy);

		EcoMTCore.energyShop = (new BlockKariShop()).setBlockName("defeatedcrow.emt.energy_shop").setCreativeTab(
				EcoMTCore.economy);

		EcoMTCore.coldShop = (new BlockColdShop()).setBlockName("defeatedcrow.emt.cold_shop").setCreativeTab(
				EcoMTCore.economy);

		EcoMTCore.engeneerShop = (new BlockIndustrialShop()).setBlockName("defeatedcrow.emt.engeneer_shop")
				.setCreativeTab(EcoMTCore.economy);

		EcoMTCore.buildShop = (new BlockBuilderShop()).setBlockName("defeatedcrow.emt.building_shop").setCreativeTab(
				EcoMTCore.economy);

		EcoMTCore.cropShop = (new BlockCropShop()).setBlockName("defeatedcrow.emt.crop_shop").setCreativeTab(
				EcoMTCore.economy);

		EcoMTCore.mealShop = (new BlockMealShop()).setBlockName("defeatedcrow.emt.meal_shop").setCreativeTab(
				EcoMTCore.economy);

		EcoMTCore.questKanban = (new BlockOrderBoard()).setBlockName("defeatedcrow.emt.order_board").setCreativeTab(
				EcoMTCore.economy);

		EcoMTCore.questBlock = (new BlockOrderExchanger()).setBlockName("defeatedcrow.emt.order_transaction")
				.setCreativeTab(EcoMTCore.economy);

		EcoMTCore.safetyBox = (new BlockSafetyBox()).setBlockName("defeatedcrow.emt.safety").setCreativeTab(
				EcoMTCore.economy);

		EcoMTCore.safetyChest = (new BlockSafetyChest()).setBlockName("defeatedcrow.emt.safety_chest").setCreativeTab(
				EcoMTCore.economy);
	}

	private static void items() {
		EcoMTCore.yukiti = (new ItemBill()).setUnlocalizedName("defeatedcrow.emt.bill").setCreativeTab(
				EcoMTCore.economy);

		EcoMTCore.ticket = (new ItemENTicket()).setUnlocalizedName("defeatedcrow.emt.en_ticket").setCreativeTab(
				EcoMTCore.economy);

		EcoMTCore.checker = (new ItemCoodTicket()).setUnlocalizedName("defeatedcrow.emt.cood_ticket").setCreativeTab(
				EcoMTCore.economy);

		EcoMTCore.fuelCan = (new ItemOilCan()).setUnlocalizedName("defeatedcrow.emt.fuel_can").setCreativeTab(
				EcoMTCore.economy);

		EcoMTCore.stamp = (new ItemStamp()).setUnlocalizedName("defeatedcrow.emt.stamp").setCreativeTab(
				EcoMTCore.economy);

		EcoMTCore.giftCatalog = (new ItemGiftCatalog()).setUnlocalizedName("defeatedcrow.emt.stamp_catalog")
				.setCreativeTab(EcoMTCore.economy);

		EcoMTCore.staffCard = (new ItemStaffCard()).setUnlocalizedName("defeatedcrow.emt.staffcard").setCreativeTab(
				EcoMTCore.economy);

		EcoMTCore.dummyItem = (new ItemDummyForDispray()).setUnlocalizedName("defeatedcrow.emt.dummy");

		EcoMTCore.EMT = (new ItemEMT()).setUnlocalizedName("defeatedcrow.emt.emtItem")
				.setCreativeTab(EcoMTCore.economy);

		EcoMTCore.buildCard_b = (new ItemBoardBuild()).setUnlocalizedName("defeatedcrow.emt.buildcard_b")
				.setCreativeTab(EcoMTCore.economyBuild);

		EcoMTCore.buildCard_p = (new ItemPlateBuild()).setUnlocalizedName("defeatedcrow.emt.buildcard_p")
				.setCreativeTab(EcoMTCore.economyBuild);

		EcoMTCore.buildCard_s = (new ItemSimpleBuild()).setUnlocalizedName("defeatedcrow.emt.buildcard_s")
				.setCreativeTab(EcoMTCore.economyBuild);

		EcoMTCore.buildCard_r = (new ItemRoofBuild()).setUnlocalizedName("defeatedcrow.emt.buildcard_r")
				.setCreativeTab(EcoMTCore.economyBuild);

		EcoMTCore.buildCard_c = (new ItemClearBuild()).setUnlocalizedName("defeatedcrow.emt.buildcard_c")
				.setCreativeTab(EcoMTCore.economyBuild);

		EcoMTCore.villageCard = (new ItemVillageBuild()).setUnlocalizedName("defeatedcrow.emt.villagecard")
				.setCreativeTab(EcoMTCore.economyBuild);
	}

}
