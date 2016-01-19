package defeatedcrow.addonforamt.economy.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.api.order.OrderType;
import defeatedcrow.addonforamt.economy.common.quest.TileOrderBoard;
import defeatedcrow.addonforamt.economy.util.TimeUtil;

@SideOnly(Side.CLIENT)
public class GuiOrderDisplay extends GuiContainer {

	private TileOrderBoard tile;

	public GuiOrderDisplay(EntityPlayer player, TileOrderBoard par2TileEntity) {
		super(new ContainerOrderDisplay(player, par2TileEntity));
		this.tile = par2TileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		// インベントリ名の描画

		for (int i = 0; i < 4; i++) {
			int limit = i == 0 ? 1 : tile.getStartDay(i) + OrderType.getType(i).getLimit()
					- TimeUtil.getDay(tile.getWorldObj()) + 1;
			int req = tile.getReuire(i);
			int rew = tile.getReward(i);
			int id = tile.getOrderID(i);

			String s1 = StatCollector.translateToLocal("dcs.emt.order.word1");
			String s2 = StatCollector.translateToLocal("dcs.emt.order.word2");
			String name = StatCollector.translateToLocal(tile.getOrderName(i));
			int y1 = 37 * i;
			this.fontRendererObj.drawString("x " + req, 48, y1 + 33, 0xFFFFFF);
			this.fontRendererObj.drawString(rew + "MP", 90, y1 + 33, 0xFFFF55);
			this.fontRendererObj.drawString(s1 + limit + s2, 48, y1 + 23, 0xFFFFFF);
			this.fontRendererObj.drawString(name, 22, y1 + 10, 0x55FFFF);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(EcoMTCore.PACKAGE, "textures/gui/board_gui.png"));
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
}
