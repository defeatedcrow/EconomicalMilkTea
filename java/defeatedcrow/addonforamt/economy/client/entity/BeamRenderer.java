package defeatedcrow.addonforamt.economy.client.entity;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import defeatedcrow.addonforamt.economy.EcoMTCore;

/**
 * Original code was created by Furia on 2015/10/23.
 */
public class BeamRenderer extends Render {
	private static final ResourceLocation tex = new ResourceLocation(EcoMTCore.PACKAGE + ":textures/entity/redbeam.png");
	private static final ResourceLocation tex2 = new ResourceLocation(EcoMTCore.PACKAGE
			+ ":textures/entity/redbeam_2.png");

	@Override
	public void doRender(Entity entity, double dx, double dy, double dz, float r, float partialTikcs) {
		if (!(entity instanceof BeamEffect))
			return;

		BeamEffect beam = (BeamEffect) entity;
		Tessellator tessellator = Tessellator.instance;

		double x = dx;
		double y = dy;
		double z = dz;

		double u = 0.0D;
		double U = 1.0D;
		double v = 0.0D;
		double V = 1.0D;

		// try rendering
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		this.bindTexture(tex);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, 10497.0F);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, 10497.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDepthMask(true);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glColor4f(2.0F, 1.0F, 1.0F, 0.5F);

		tessellator.startDrawingQuads();

		double d = 0.05D;
		double d1 = 16.0D;
		double y1 = Math.min(254.0D, y + 125.0D);

		tessellator.addVertexWithUV(x + d, y - d1, z, U, v);
		tessellator.addVertexWithUV(x - d, y - d1, z, u, v);
		tessellator.addVertexWithUV(x - d, y1, z, u, V);
		tessellator.addVertexWithUV(x + d, y1, z, U, V);

		tessellator.addVertexWithUV(x, y - d1, z + d, U, v);
		tessellator.addVertexWithUV(x, y - d1, z - d, u, v);
		tessellator.addVertexWithUV(x, y1, z - d, u, V);
		tessellator.addVertexWithUV(x, y1, z + d, U, V);
		tessellator.draw();

		this.bindTexture(tex2);

		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(x - d1, y - d, z, U, v);
		tessellator.addVertexWithUV(x + d1, y - d, z, u, v);
		tessellator.addVertexWithUV(x + d1, y + d, z, u, V);
		tessellator.addVertexWithUV(x - d1, y + d, z, U, V);

		tessellator.addVertexWithUV(x, y - d, z + d1, U, v);
		tessellator.addVertexWithUV(x, y - d, z - d1, u, v);
		tessellator.addVertexWithUV(x, y + d, z - d1, u, V);
		tessellator.addVertexWithUV(x, y + d, z + d1, U, V);

		tessellator.addVertexWithUV(x - d1, y, z - d, U, v);
		tessellator.addVertexWithUV(x + d1, y, z - d, u, v);
		tessellator.addVertexWithUV(x + d1, y, z + d, u, V);
		tessellator.addVertexWithUV(x - d1, y, z + d, U, V);

		tessellator.addVertexWithUV(x - d, y, z + d1, U, v);
		tessellator.addVertexWithUV(x - d, y, z - d1, u, v);
		tessellator.addVertexWithUV(x + d, y, z - d1, u, V);
		tessellator.addVertexWithUV(x + d, y, z + d1, U, V);
		tessellator.draw();

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDepthMask(true);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}

}
