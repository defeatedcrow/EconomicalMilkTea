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
import defeatedcrow.addonforamt.economy.common.quest.OrderExchanger;

@SideOnly(Side.CLIENT)
public class TESRTransactionBox extends TileEntitySpecialRenderer {

	private static final ResourceLocation tex = new ResourceLocation(EcoMTCore.PACKAGE
			+ ":textures/blocks/tileentity/transactionbox.png");
	public static TESRTransactionBox renderer;
	private ModelTransactionBox model = new ModelTransactionBox();

	public void renderTileEntitySteakAt(OrderExchanger par1Tile, double par2, double par4, double par6, float par8) {
		this.setRotation(par1Tile, (float) par2, (float) par4, (float) par6);
	}

	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		renderer = this;
	}

	public void setRotation(OrderExchanger tile, float par1, float par2, float par3) {
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
		GL11.glTranslatef(par1 + 0.5F, par2 + 1.5F, par3 + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntitySteakAt((OrderExchanger) par1TileEntity, par2, par4, par6, par8);
	}
}
