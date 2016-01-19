package defeatedcrow.addonforamt.economy.client.block;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.common.shop.TileShopMonitor;

@SideOnly(Side.CLIENT)
public class TESRShopMonitor extends TileEntitySpecialRenderer {
	private static final ResourceLocation tex = new ResourceLocation(EcoMTCore.PACKAGE
			+ ":textures/blocks/tileentity/shopmonitor.png");
	public static TESRShopMonitor caseRenderer;
	private ModelShopMonitor model = new ModelShopMonitor();

	public void renderTileEntityCaseAt(TileShopMonitor par1Tile, double par2, double par4, double par6, float par8) {
		this.setRotation(par1Tile, (float) par2, (float) par4, (float) par6);
	}

	/**
	 * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
	 */
	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		caseRenderer = this;
	}

	public void setRotation(TileShopMonitor par0Tile, float par1, float par2, float par3) {
		ModelShopMonitor model = this.model;
		byte l = (byte) par0Tile.getBlockMetadata();
		IIcon icon = par0Tile.getIcon();

		this.bindTexture(tex);

		float f = 0.0F;
		switch (l) {
		case 0:
			f = 180.0F;
			break;
		case 1:
			f = -90.0F;
			break;
		case 2:
			f = 0.0F;
			break;
		case 3:
			f = 90.0F;
			break;
		}

		// base
		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);

		GL11.glTranslatef(par1 + 0.5F, par2 + 1.5F, par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(f, 0.0F, 1.0F, 0.0F);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

		if (icon != null) {
			Tessellator tessellator = Tessellator.instance;

			float u = icon.getMinU();
			float U = icon.getMaxU();
			float v = icon.getMinV();
			float V = icon.getMaxV();
			this.bindTexture(TextureMap.locationBlocksTexture);

			GL11.glColor4f(2.0F, 2.0F, 2.0F, 1.0F);

			tessellator.startDrawingQuads();
			tessellator.setNormal(1.0F, 1.0F, 1.0F);
			tessellator.addVertexWithUV(-0.25D, 1.05D, -0.325D, u, V);
			tessellator.addVertexWithUV(0.25D, 1.05D, -0.325D, U, V);
			tessellator.addVertexWithUV(0.25D, 0.65D, -0.025D, U, v);
			tessellator.addVertexWithUV(-0.25D, 0.65D, -0.025D, u, v);
			tessellator.draw();
		}

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();

	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntityCaseAt((TileShopMonitor) par1TileEntity, par2, par4, par6, par8);
	}
}
