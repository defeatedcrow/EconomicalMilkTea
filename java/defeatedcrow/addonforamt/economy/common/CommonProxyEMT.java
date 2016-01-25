package defeatedcrow.addonforamt.economy.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.client.gui.ContainerDistributor;
import defeatedcrow.addonforamt.economy.client.gui.ContainerENMotor;
import defeatedcrow.addonforamt.economy.client.gui.ContainerENTank;
import defeatedcrow.addonforamt.economy.client.gui.ContainerGeneratorEMT;
import defeatedcrow.addonforamt.economy.client.gui.ContainerOrderDisplay;
import defeatedcrow.addonforamt.economy.client.gui.ContainerOrderExchanger;
import defeatedcrow.addonforamt.economy.client.gui.ContainerSafetyBox;
import defeatedcrow.addonforamt.economy.client.gui.ContainerShopDisp;
import defeatedcrow.addonforamt.economy.client.gui.ContainerStampCatalog;
import defeatedcrow.addonforamt.economy.client.gui.GuiDistributor;
import defeatedcrow.addonforamt.economy.client.gui.GuiENMotor;
import defeatedcrow.addonforamt.economy.client.gui.GuiENTank;
import defeatedcrow.addonforamt.economy.client.gui.GuiGeneratorEMT;
import defeatedcrow.addonforamt.economy.client.gui.GuiOrderDisplay;
import defeatedcrow.addonforamt.economy.client.gui.GuiOrderExchanger;
import defeatedcrow.addonforamt.economy.client.gui.GuiSafetyBox;
import defeatedcrow.addonforamt.economy.client.gui.GuiShopDisp;
import defeatedcrow.addonforamt.economy.client.gui.GuiStampCatalog;
import defeatedcrow.addonforamt.economy.common.block.TileDistributor;
import defeatedcrow.addonforamt.economy.common.block.TileENMotor;
import defeatedcrow.addonforamt.economy.common.block.TileENTank;
import defeatedcrow.addonforamt.economy.common.block.TileGeneratorEMT;
import defeatedcrow.addonforamt.economy.common.quest.OrderExchanger;
import defeatedcrow.addonforamt.economy.common.quest.TileOrderBoard;
import defeatedcrow.addonforamt.economy.common.quest.TileSafetyBox;
import defeatedcrow.addonforamt.economy.common.shop.TileColdShop;
import defeatedcrow.addonforamt.economy.common.shop.TileDisplayShop;
import defeatedcrow.addonforamt.economy.common.shop.TileMealShop;
import defeatedcrow.addonforamt.economy.common.shop.TileShopMonitor;
import defeatedcrow.showcase.common.SCLogger;

public class CommonProxyEMT implements IGuiHandler {

	public int getRenderID() {
		return -1;
	}

	public void registerRenderers() {
	}

	public int addArmor(String armor) {
		return 0;
	}

	public World getClientWorld() {
		return null;
	}

	public EntityPlayer getClientPlayer() {
		return null;
	}

	public void registerFluidTex() {
	}

	public void registerTileEntity() {
		// machine
		GameRegistry.registerTileEntity(TileDistributor.class, "defeatedcrow.emt.distributerTile");
		GameRegistry.registerTileEntity(TileENMotor.class, "defeatedcrow.emt.motorTile");
		GameRegistry.registerTileEntity(TileENTank.class, "defeatedcrow.emt.entankTile");
		GameRegistry.registerTileEntity(TileGeneratorEMT.class, "defeatedcrow.emt.generatorTile");
		// order
		GameRegistry.registerTileEntity(TileOrderBoard.class, "defeatedcrow.emt.orderBoardTile");
		GameRegistry.registerTileEntity(OrderExchanger.class, "defeatedcrow.emt.orderTransactionTile");
		GameRegistry.registerTileEntity(TileSafetyBox.class, "defeatedcrow.emt.safetyTile");
		// shop
		GameRegistry.registerTileEntity(TileShopMonitor.class, "defeatedcrow.emt.shopMonitorTile");
		GameRegistry.registerTileEntity(TileColdShop.class, "defeatedcrow.emt.coldShopTile");
		GameRegistry.registerTileEntity(TileDisplayShop.class, "defeatedcrow.emt.cropShopTile");
		GameRegistry.registerTileEntity(TileMealShop.class, "defeatedcrow.emt.mealShopTile");
	}

	// GUIの登録
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (!world.blockExists(x, y, z))
			return null;
		TileEntity tileentity = world.getTileEntity(x, y, z);
		if (tileentity instanceof TileGeneratorEMT) {
			return new ContainerGeneratorEMT(player, (TileGeneratorEMT) tileentity);
		} else if (tileentity instanceof TileENTank) {
			return new ContainerENTank(player, (TileENTank) tileentity);
		} else if (tileentity instanceof TileENMotor) {
			return new ContainerENMotor(player, (TileENMotor) tileentity);
		} else if (tileentity instanceof TileDistributor) {
			return new ContainerDistributor(player, (TileDistributor) tileentity);
		} else if (tileentity instanceof TileOrderBoard) {
			return new ContainerOrderDisplay(player, (TileOrderBoard) tileentity);
		} else if (tileentity instanceof TileSafetyBox) {
			return new ContainerSafetyBox(player, (TileSafetyBox) tileentity);
		} else if (tileentity instanceof OrderExchanger) {
			return new ContainerOrderExchanger(player, (OrderExchanger) tileentity);
		} else if (tileentity instanceof TileDisplayShop) {
			return new ContainerShopDisp(player, (TileDisplayShop) tileentity);
		} else if (ID == EcoMTCore.guiCatalog) {
			return new ContainerStampCatalog(player);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (!world.blockExists(x, y, z))
			return null;
		TileEntity tileentity = world.getTileEntity(x, y, z);
		if (tileentity instanceof TileGeneratorEMT) {
			return new GuiGeneratorEMT(player, (TileGeneratorEMT) tileentity);
		} else if (tileentity instanceof TileENTank) {
			return new GuiENTank(player, (TileENTank) tileentity);
		} else if (tileentity instanceof TileENMotor) {
			return new GuiENMotor(player, (TileENMotor) tileentity);
		} else if (tileentity instanceof TileDistributor) {
			return new GuiDistributor(player, (TileDistributor) tileentity);
		} else if (tileentity instanceof TileOrderBoard) {
			return new GuiOrderDisplay(player, (TileOrderBoard) tileentity);
		} else if (tileentity instanceof TileSafetyBox) {
			return new GuiSafetyBox(player, (TileSafetyBox) tileentity);
		} else if (tileentity instanceof OrderExchanger) {
			return new GuiOrderExchanger(player, (OrderExchanger) tileentity);
		} else if (tileentity instanceof TileDisplayShop) {
			return new GuiShopDisp(player, (TileDisplayShop) tileentity);
		} else if (ID == EcoMTCore.guiCatalog) {
			return new GuiStampCatalog(player);
		}
		return null;
	}

	public void loadNEI() {
	}

	public void loadInit() {

	}

	public boolean getOP(String name) {
		if (!MinecraftServer.getServer().isSinglePlayer()) {
			String ops[] = MinecraftServer.getServer().getConfigurationManager().func_152603_m().func_152685_a();
			for (String op : ops) {
				if (op != null && op.equalsIgnoreCase(name))
					SCLogger.debugInfo("Server OP was detected. " + op);
				return true;
			}
		}
		return false;
	}

	public boolean isShiftKeyDown() {
		return false;
	}

	public boolean isJumpKeyDown() {
		return false;
	}

}
