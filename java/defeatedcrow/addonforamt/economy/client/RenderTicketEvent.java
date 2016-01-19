package defeatedcrow.addonforamt.economy.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderWorldEvent;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import defeatedcrow.addonforamt.economy.EMTLogger;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.common.item.ItemCoodTicket;

public class RenderTicketEvent {

	private static final ResourceLocation tex = new ResourceLocation("textures/entity/beacon_beam.png");

	private boolean flag = false;

	@SubscribeEvent
	public void RenderLineEvent(RenderWorldEvent.Post event) {
		EntityPlayer player = EcoMTCore.proxy.getClientPlayer();
		ItemStack hold = player.getCurrentEquippedItem();
		int dim = EcoMTCore.proxy.getClientWorld().provider.dimensionId;
		Tessellator tessellator = Tessellator.instance;

		if (hold != null && hold.getItem() != null) {
			if (hold.getItem() instanceof ItemCoodTicket) {
				ItemCoodTicket tic = (ItemCoodTicket) hold.getItem();
				if (tic.isRegistered(hold)) {
					int regDim = tic.getDim(hold);
					int[] cood = tic.getCood(hold);
					if (regDim == dim) {
						double x = cood[0] + 0.5D;
						double y = cood[1] + 0.5D;
						double z = cood[2] + 0.5D;

						double u = 0.0D;
						double U = 1.0D;
						double v = 0.0D;
						double V = 1.0D;

						// try rendering
						GL11.glPushMatrix();
						GL11.glEnable(GL11.GL_BLEND);
						GL11.glEnable(GL12.GL_RESCALE_NORMAL);
						Minecraft.getMinecraft().renderEngine.bindTexture(tex);
						GL11.glDisable(GL11.GL_LIGHTING);
						GL11.glDepthMask(true);
						OpenGlHelper.glBlendFunc(770, 771, 1, 0);
						GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);

						tessellator.startDrawingQuads();

						double d = 0.15D;

						tessellator.addVertexWithUV(x - d, y, z + d, U, v);
						tessellator.addVertexWithUV(x + d, y, z + d, u, v);
						tessellator.addVertexWithUV(x + d, 255.0D, z + d, u, V);
						tessellator.addVertexWithUV(x - d, 255.0D, z + d, U, V);

						tessellator.addVertexWithUV(x - d, y, z, u, v);
						tessellator.addVertexWithUV(x + d, y, z, U, v);
						tessellator.addVertexWithUV(x + d, 255.0D, z - 0.01D, U, V);
						tessellator.addVertexWithUV(x - d, 255.0D, z - 0.01D, u, V);

						tessellator.addVertexWithUV(x, y, z - d, U, v);
						tessellator.addVertexWithUV(x, y, z + d, u, v);
						tessellator.addVertexWithUV(x, 255.0D, z + d, u, V);
						tessellator.addVertexWithUV(x, 255.0D, z - d, U, V);

						tessellator.addVertexWithUV(x + d, y, z + d, u, v);
						tessellator.addVertexWithUV(x + d, y, z - d, U, v);
						tessellator.addVertexWithUV(x, 255.0D, z - d, U, V);
						tessellator.addVertexWithUV(x, 255.0D, z + d, u, V);
						tessellator.draw();

						Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
						GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
						OpenGlHelper.glBlendFunc(770, 1, 1, 0);

						GL11.glDisable(GL12.GL_RESCALE_NORMAL);
						GL11.glEnable(GL11.GL_LIGHTING);
						GL11.glDisable(GL11.GL_BLEND);
						GL11.glPopMatrix();

						flag = true;
						EMTLogger.debugInfo("I'm here!");
					}
				}
			}
		}

		player.inventory.markDirty();
	}
}
