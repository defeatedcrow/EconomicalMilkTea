package defeatedcrow.addonforamt.economy.client.block;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.common.shop.TileColdShop;

@SideOnly(Side.CLIENT)
public class TESRColdShop extends TileEntitySpecialRenderer {

	private static final ResourceLocation tex = new ResourceLocation(EcoMTCore.PACKAGE
			+ ":textures/blocks/tileentity/coldshop.png");
	public static TESRColdShop renderer;
	private ModelColdShop model = new ModelColdShop();

	public void renderTileEntitySteakAt(TileColdShop tile, double par2, double par4, double par6, float par8) {
		this.setRotation(tile, (float) par2, (float) par4, (float) par6);
	}

	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		renderer = this;
	}

	public void setRotation(TileColdShop tile, float x, float y, float z) {
		byte m = (byte) tile.getBlockMetadata();
		byte l = (byte) (m & 3);
		float j = 0;
		if (l == 0)
			j = 180.0F;
		if (l == 1)
			j = -90.0F;
		if (l == 2)
			j = 0.0F;
		if (l == 3)
			j = 90.0F;

		boolean alt = (m & 4) != 0;

		this.bindTexture(tex);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef(x + 0.5F, y + 1.5F, z + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glTranslatef(x + 0.5F, y + 1.5F, z + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
		GL11.glColor4f(2.0F, 2.0F, 2.0F, 0.8F);
		model.renderGlass((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntitySteakAt((TileColdShop) par1TileEntity, par2, par4, par6, par8);
	}
}
