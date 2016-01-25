package defeatedcrow.addonforamt.economy.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.client.block.ItemRenderColdShop;
import defeatedcrow.addonforamt.economy.client.block.ItemRenderCropShop;
import defeatedcrow.addonforamt.economy.client.block.ItemRenderDistributor;
import defeatedcrow.addonforamt.economy.client.block.ItemRenderENTank;
import defeatedcrow.addonforamt.economy.client.block.ItemRenderMealShop;
import defeatedcrow.addonforamt.economy.client.block.ItemRenderMotor;
import defeatedcrow.addonforamt.economy.client.block.ItemRenderSafetyBox;
import defeatedcrow.addonforamt.economy.client.block.ItemRenderShopMonitor;
import defeatedcrow.addonforamt.economy.client.block.ItemRenderTransactionBox;
import defeatedcrow.addonforamt.economy.client.block.RBDummy;
import defeatedcrow.addonforamt.economy.client.block.RBOrderBoard;
import defeatedcrow.addonforamt.economy.client.block.TESRColdShop;
import defeatedcrow.addonforamt.economy.client.block.TESRCropShop;
import defeatedcrow.addonforamt.economy.client.block.TESRDistributor;
import defeatedcrow.addonforamt.economy.client.block.TESRENMotor;
import defeatedcrow.addonforamt.economy.client.block.TESRENTank;
import defeatedcrow.addonforamt.economy.client.block.TESRMealShop;
import defeatedcrow.addonforamt.economy.client.block.TESRSafetyBox;
import defeatedcrow.addonforamt.economy.client.block.TESRShopMonitor;
import defeatedcrow.addonforamt.economy.client.block.TESRTransactionBox;
import defeatedcrow.addonforamt.economy.client.entity.BeamEffect;
import defeatedcrow.addonforamt.economy.client.entity.BeamRenderer;
import defeatedcrow.addonforamt.economy.common.CommonProxyEMT;
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
import defeatedcrow.addonforamt.economy.event.HandleCoodTicketEvent;

public class ClientProxyEMT extends CommonProxyEMT {

	@Override
	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}

	@Override
	public EntityPlayer getClientPlayer() {
		return FMLClientHandler.instance().getClient().thePlayer;
	}

	@Override
	public int getRenderID() {
		return RenderingRegistry.getNextAvailableRenderId();
	}

	@Override
	public void registerRenderers() {
		RenderingRegistry.registerBlockHandler(new RBDummy());
		RenderingRegistry.registerBlockHandler(new RBOrderBoard());

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EcoMTCore.distributor),
				new ItemRenderDistributor());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EcoMTCore.motor), new ItemRenderMotor());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EcoMTCore.enTank), new ItemRenderENTank());

		MinecraftForgeClient
				.registerItemRenderer(Item.getItemFromBlock(EcoMTCore.safetyBox), new ItemRenderSafetyBox());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EcoMTCore.questBlock),
				new ItemRenderTransactionBox());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EcoMTCore.energyShop),
				new ItemRenderShopMonitor());
		MinecraftForgeClient
				.registerItemRenderer(Item.getItemFromBlock(EcoMTCore.emtShop), new ItemRenderShopMonitor());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EcoMTCore.engeneerShop),
				new ItemRenderShopMonitor());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EcoMTCore.buildShop),
				new ItemRenderShopMonitor());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EcoMTCore.cropShop), new ItemRenderCropShop());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EcoMTCore.mealShop), new ItemRenderMealShop());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EcoMTCore.coldShop), new ItemRenderColdShop());
	}

	@Override
	public void registerTileEntity() {
		// machine
		ClientRegistry.registerTileEntity(TileDistributor.class, "defeatedcrow.emt.distributorTile",
				new TESRDistributor());
		ClientRegistry.registerTileEntity(TileENMotor.class, "defeatedcrow.emt.motorTile", new TESRENMotor());
		ClientRegistry.registerTileEntity(TileENTank.class, "defeatedcrow.emt.entankTile", new TESRENTank());
		GameRegistry.registerTileEntity(TileGeneratorEMT.class, "defeatedcrow.emt.generatorTile");
		// order
		GameRegistry.registerTileEntity(TileOrderBoard.class, "defeatedcrow.emt.orderBoardTile");
		ClientRegistry.registerTileEntity(OrderExchanger.class, "defeatedcrow.emt.orderTransactionTile",
				new TESRTransactionBox());
		ClientRegistry.registerTileEntity(TileSafetyBox.class, "defeatedcrow.emt.safetyTile", new TESRSafetyBox());
		// shop
		ClientRegistry.registerTileEntity(TileShopMonitor.class, "defeatedcrow.emt.shopMonitorTile",
				new TESRShopMonitor());
		ClientRegistry.registerTileEntity(TileColdShop.class, "defeatedcrow.emt.coldShopTile", new TESRColdShop());
		ClientRegistry.registerTileEntity(TileDisplayShop.class, "defeatedcrow.emt.cropShopTile", new TESRCropShop());
		ClientRegistry.registerTileEntity(TileMealShop.class, "defeatedcrow.emt.mealShopTile", new TESRMealShop());
	}

	@Override
	public int addArmor(String armor) {
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}

	@Override
	public void registerFluidTex() {
	}

	@SubscribeEvent
	public void onTextureStitch(TextureStitchEvent.Post event) {
	}

	@Override
	public void loadNEI() {
		if (Loader.isModLoaded("NotEnoughItems")) {
		}
	}

	@Override
	public void loadInit() {
		// 没
		// MinecraftForge.EVENT_BUS.register(new RenderTicketEvent());

		Render render = new BeamRenderer();
		RenderingRegistry.registerEntityRenderingHandler(BeamEffect.class, render);
		MinecraftForge.EVENT_BUS.register(new HandleCoodTicketEvent());
	}

	@Override
	public boolean getOP(String name) {
		return false;
	}

	@Override
	public boolean isShiftKeyDown() {
		return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);
	}

	@Override
	public boolean isJumpKeyDown() {
		return Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode());
	}
}
