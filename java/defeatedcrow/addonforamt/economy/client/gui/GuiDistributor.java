package defeatedcrow.addonforamt.economy.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.common.block.TileDistributor;

public class GuiDistributor extends GuiContainer {

	private TileDistributor tileentity;

	public GuiDistributor(EntityPlayer player, TileDistributor par2TileEntity) {
		super(new ContainerDistributor(player, par2TileEntity));
		this.tileentity = par2TileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		// インベントリ名の描画
		String s = this.tileentity.hasCustomInventoryName() ? this.tileentity.getInventoryName() : I18n.format(
				this.tileentity.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);

		int rem = tileentity.getRemainingDay();
		if (rem > 0) {
			String s2 = rem + "day";
			this.fontRendererObj.drawString(s2, this.xSize / 2 - 55 - this.fontRendererObj.getStringWidth(s2) / 2,
					this.ySize - 106, 16777215);
		}
	}

	@Override
	public void drawScreen(int x, int y, float par3) {
		super.drawScreen(x, y, par3);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(
				new ResourceLocation(EcoMTCore.PACKAGE, "textures/gui/distributor_gui.png"));

		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		boolean tic = tileentity.hasTicket();
		int slot = tileentity.getSlotLim();
		if (tic) {
			this.drawTexturedModalRect(k + 23, l + 40, 176, 18, 18, 13);

			int i1 = Math.min(slot, 4);
			int i2 = slot - 4;

			for (int i = 0; i < i1; i++) {
				this.drawTexturedModalRect(k + 61 + 24 * i, l + 16, 176, 0, 18, 31);
			}
			for (int i = 0; i < i2; i++) {
				this.drawTexturedModalRect(k + 61 + 24 * i, l + 49, 176, 0, 18, 31);
			}
		}
	}
}
