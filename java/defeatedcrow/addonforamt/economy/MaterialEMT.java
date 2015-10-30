package defeatedcrow.addonforamt.economy;

import mods.defeatedcrow.common.block.appliance.ItemMachineBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import defeatedcrow.addonforamt.economy.common.block.BlockDistributor;
import defeatedcrow.addonforamt.economy.common.block.BlockENMotor;
import defeatedcrow.addonforamt.economy.common.block.BlockENTank;
import defeatedcrow.addonforamt.economy.common.block.BlockGeneratorEMT;
import defeatedcrow.addonforamt.economy.common.block.BlockKariShop;
import defeatedcrow.addonforamt.economy.common.block.ItemENTank;
import defeatedcrow.addonforamt.economy.common.item.ItemCoodTicket;
import defeatedcrow.addonforamt.economy.common.item.ItemENTicket;
import defeatedcrow.addonforamt.economy.common.item.ItemOilCan;

public class MaterialEMT {

	private MaterialEMT() {
	}

	public static void init() {
		blocks();
		items();

		GameRegistry.registerBlock(EcoMTCore.distributor, "defeatedcrow.emt.distributor");
		GameRegistry.registerBlock(EcoMTCore.generator, ItemMachineBlock.class, "defeatedcrow.emt.generator");
		GameRegistry.registerBlock(EcoMTCore.enTank, ItemENTank.class, "defeatedcrow.emt.en_tank");
		GameRegistry.registerBlock(EcoMTCore.motor, ItemMachineBlock.class, "defeatedcrow.emt.en_motor");

		GameRegistry.registerBlock(EcoMTCore.kariShop, "defeatedcrow.emt.kari_shop");

		GameRegistry.registerItem(EcoMTCore.ticket, "defeatedcrow.emt.en_ticket");
		GameRegistry.registerItem(EcoMTCore.checker, "defeatedcrow.emt.cood_ticket");

		GameRegistry.registerItem(EcoMTCore.fuelCan, "defeatedcrow.emt.fuel_can");
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

		EcoMTCore.kariShop = (new BlockKariShop()).setBlockName("defeatedcrow.emt.kari_shop").setCreativeTab(
				EcoMTCore.economy);
	}

	private static void items() {
		EcoMTCore.ticket = (new ItemENTicket()).setUnlocalizedName("defeatedcrow.emt.en_ticket").setCreativeTab(
				EcoMTCore.economy);

		EcoMTCore.checker = (new ItemCoodTicket()).setUnlocalizedName("defeatedcrow.emt.cood_ticket").setCreativeTab(
				EcoMTCore.economy);

		EcoMTCore.fuelCan = (new ItemOilCan()).setUnlocalizedName("defeatedcrow.emt.fuel_can").setCreativeTab(
				EcoMTCore.economy);
	}

}
