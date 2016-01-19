package defeatedcrow.addonforamt.economy.client.gui;

import java.util.ArrayList;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.common.block.TileENTank;
import defeatedcrow.addonforamt.economy.plugin.amt.AMTIntegration;

public class GuiENTank extends GuiContainer {

	private TileENTank tileentity;

	public GuiENTank(EntityPlayer player, TileENTank par2TileEntity) {
		super(new ContainerENTank(player, par2TileEntity));
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
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(EcoMTCore.PACKAGE, "textures/gui/tank_gui.png"));

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
		this.drawTexturedModalRect(k + 81, l + 66 - i2, 176, 17 - i2, 13, i2);
	}
}
