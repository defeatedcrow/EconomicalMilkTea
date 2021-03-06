package defeatedcrow.addonforamt.economy.client.gui;

import java.util.ArrayList;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.common.block.TileGeneratorEMT;
import defeatedcrow.addonforamt.economy.plugin.amt.AMTIntegration;

public class GuiGeneratorEMT extends GuiContainer {

	private TileGeneratorEMT tileentity;

	public GuiGeneratorEMT(EntityPlayer player, TileGeneratorEMT par2TileEntity) {
		super(new ContainerGeneratorEMT(player, par2TileEntity));
		this.tileentity = par2TileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		// インベントリ名の描画
		String s = this.tileentity.hasCustomInventoryName() ? this.tileentity.getInventoryName() : I18n.format(
				this.tileentity.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 4,
				4210752);
	}

	@Override
	public void drawScreen(int x, int y, float par3) {
		super.drawScreen(x, y, par3);

		// チャージゲージのマウスオーバー
		boolean b1 = this.func_146978_c(11, 26, 12, 27, x, y);
		if (b1) {
			int charge = this.tileentity.getChargeAmount();
			ArrayList<String> list1 = new ArrayList<String>();
			list1.add("Charge Amount : " + charge + "/" + tileentity.getMaxChargeAmount());

			if (EcoMTCore.proxy.isShiftKeyDown()) { // shiftキー押下時
				int vsRF = charge * AMTIntegration.RFrate;
				int vsEU = charge * AMTIntegration.EUrate;
				int vsGF = charge * AMTIntegration.GFrate;
				list1.add(" - " + vsRF + "/" + tileentity.getMaxChargeAmount() * AMTIntegration.RFrate + " RF");
				list1.add(" - " + vsEU + "/" + tileentity.getMaxChargeAmount() * AMTIntegration.EUrate + " EU");
				list1.add(" - " + vsGF + "/" + tileentity.getMaxChargeAmount() * AMTIntegration.GFrate + " GF");
			} else {
				list1.add(EnumChatFormatting.ITALIC + "LShift: Expand tooltip.");
			}
			this.drawHoveringText(list1, x, y, fontRendererObj);
		}

		// 液体情報
		boolean b2 = this.func_146978_c(84, 23, 16, 41, x, y);
		if (b2) {
			int charge = this.tileentity.getChargeAmount();
			ArrayList<String> list2 = new ArrayList<String>();
			list2.add("Fluid : " + this.tileentity.productTank.getFluidName());
			list2.add("Amount : " + this.tileentity.productTank.getFluidAmount());
			this.drawHoveringText(list2, x, y, fontRendererObj);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(
				new ResourceLocation(EcoMTCore.PACKAGE, "textures/gui/generator_gui.png"));

		// かまど描画処理
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		int i1;
		int i2;
		int i3;
		int i4;

		i1 = this.tileentity.getBurnTimeRemainingScaled(26);
		this.drawTexturedModalRect(k + 11, l + 53 - i1, 176, 43 - i1, 12, i1);

		i2 = this.tileentity.getCookProgressScaled(17);
		this.drawTexturedModalRect(k + 108, l + 70 - i2, 176, 17 - i2, 13, i2);

		drawFluid(this.tileentity.productTank.getFluid(), this.tileentity.getFluidAmountScaled(41), k + 84, l + 23, 16,
				41);
	}

	/**
	 * Original code was made by Shift02.
	 */
	private void drawFluid(FluidStack fluid, int level, int x, int y, int width, int height) {
		if (fluid == null || fluid.getFluid() == null) {
			return;
		}

		ResourceLocation res = null;
		if (fluid.getFluid().getSpriteNumber() == 0) {
			res = TextureMap.locationBlocksTexture;
		} else {
			res = TextureMap.locationItemsTexture;
		}
		mc.getTextureManager().bindTexture(res);

		IIcon icon = fluid.getFluid().getIcon(fluid);
		if (icon == null)
			return;
		mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		setGLColorFromInt(fluid.getFluid().getColor(fluid));

		int widR = width;
		int heiR = level;
		int yR = y + (height - heiR);

		int widL = 0;
		int heiL = 0;

		for (int i = 0; i < widR; i += 16) {
			for (int j = 0; j < heiR; j += 16) {
				widL = Math.min(widR - i, 16);
				heiL = Math.min(heiR - j, 16);
				this.drawTexturedModelRectFromIcon(x + i, yR + j, icon, widL, heiL);
			}
		}
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0F);

	}

	public static void setGLColorFromInt(int color) {
		float red = (color >> 16 & 255) / 255.0F;
		float green = (color >> 8 & 255) / 255.0F;
		float blue = (color & 255) / 255.0F;
		GL11.glColor4f(red, green, blue, 1.0F);
	}

}
