package defeatedcrow.addonforamt.economy.client.block;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.common.shop.TileDisplayShop;
import defeatedcrow.addonforamt.economy.common.shop.TileMealShop;

@SideOnly(Side.CLIENT)
public class TESRMealShop extends TileEntitySpecialRenderer {

	private static final ResourceLocation tex = new ResourceLocation(EcoMTCore.PACKAGE
			+ ":textures/blocks/tileentity/mealshop.png");
	public static TESRMealShop renderer;
	private ModelMealShop model = new ModelMealShop();

	public void renderTileEntitySteakAt(TileMealShop tile, double par2, double par4, double par6, float par8) {
		this.setRotation(tile, (float) par2, (float) par4, (float) par6);
	}

	public void setTileEntityRenderer(TileEntityRendererDispatcher par1TileEntityRenderer) {
		super.func_147497_a(par1TileEntityRenderer);
		renderer = this;
	}

	public void setRotation(TileMealShop tile, float x, float y, float z) {
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

		boolean b = tile.isSameUnderBlock();

		boolean alt = (m & 4) != 0;

		this.bindTexture(tex);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef(x + 0.5F, y + 1.5F, z + 0.5F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glRotatef(j, 0.0F, 1.0F, 0.0F);
		if (b) {
			model.renderB((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		} else {
			model.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();

		float[] fx = {
				0.2F,
				-0.2F,
				0.2F,
				-0.2F };
		float[] fy = {
				0.1F,
				0.1F,
				-0.3F,
				-0.3F };
		float[] fz = {
				-0.05F,
				-0.05F,
				-0.25F,
				-0.25F };
		float[] fr = {
				0.0F,
				-90.0F,
				180.0F,
				90.0F };
		for (int i = 0; i < 4; i++) {
			if (tile.getStackInSlot(i) == null)
				continue;
			GL11.glPushMatrix();
			GL11.glTranslatef(x + 0.5F, y + 0.5F, z + 0.5F);
			GL11.glRotatef(fr[l], 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(fx[i], fy[i], b ? 0.0F : fz[i]);
			this.renderInner(tile, i);
			GL11.glPopMatrix();
		}
	}

	private void renderInner(TileDisplayShop tile, int i) {
		ItemStack slot = tile.getStackInSlot(i);

		if (slot != null) {
			ItemStack item = slot.copy();
			EntityItem entityitem = new EntityItem(tile.getWorldObj(), 0.0D, 0.0D, 0.0D, item);
			entityitem.getEntityItem().stackSize = 1;
			entityitem.hoverStart = 0.0F;

			if (item.getItem() instanceof ItemBlock) {
				GL11.glScalef(0.65F, 0.65F, 0.65F);
			} else {
				GL11.glScalef(0.65F, 0.65F, 0.65F);
			}

			RenderItem.renderInFrame = true;
			RenderManager.instance.renderEntityWithPosYaw(entityitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
			RenderItem.renderInFrame = false;
		}
	}

	@Override
	public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
		this.renderTileEntitySteakAt((TileMealShop) par1TileEntity, par2, par4, par6, par8);
	}
}
