package defeatedcrow.addonforamt.economy.client;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.client.block.ItemRenderDistributor;
import defeatedcrow.addonforamt.economy.client.block.ItemRenderENTank;
import defeatedcrow.addonforamt.economy.client.block.ItemRenderMotor;
import defeatedcrow.addonforamt.economy.client.block.RBDummy;
import defeatedcrow.addonforamt.economy.client.block.TESRDistributor;
import defeatedcrow.addonforamt.economy.client.block.TESRENMotor;
import defeatedcrow.addonforamt.economy.client.block.TESRENTank;
import defeatedcrow.addonforamt.economy.client.entity.BeamEffect;
import defeatedcrow.addonforamt.economy.client.entity.BeamRenderer;
import defeatedcrow.addonforamt.economy.common.CommonProxyEMT;
import defeatedcrow.addonforamt.economy.common.block.TileDistributor;
import defeatedcrow.addonforamt.economy.common.block.TileENMotor;
import defeatedcrow.addonforamt.economy.common.block.TileENTank;
import defeatedcrow.addonforamt.economy.common.block.TileGeneratorEMT;
import defeatedcrow.addonforamt.economy.common.quest.TileOrderBoard;
import defeatedcrow.addonforamt.economy.common.quest.TileSafetyBox;
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

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EcoMTCore.distributor),
				new ItemRenderDistributor());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EcoMTCore.motor), new ItemRenderMotor());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(EcoMTCore.enTank), new ItemRenderENTank());
	}

	@Override
	public void registerTileEntity() {
		ClientRegistry.registerTileEntity(TileDistributor.class, "defeatedcrow.emt.distributorTile",
				new TESRDistributor());
		ClientRegistry.registerTileEntity(TileENMotor.class, "defeatedcrow.emt.motorTile", new TESRENMotor());
		ClientRegistry.registerTileEntity(TileENTank.class, "defeatedcrow.emt.entankTile", new TESRENTank());
		GameRegistry.registerTileEntity(TileGeneratorEMT.class, "defeatedcrow.emt.generatorTile");
		GameRegistry.registerTileEntity(TileOrderBoard.class, "defeatedcrow.emt.orderBoardTile");
		GameRegistry.registerTileEntity(TileSafetyBox.class, "defeatedcrow.emt.safetyTile");
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
}
