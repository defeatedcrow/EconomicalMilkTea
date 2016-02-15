package defeatedcrow.addonforamt.economy.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.common.quest.TileSafetyChest;

@SideOnly(Side.CLIENT)
public class GuiSafetyChest extends GuiContainer {

	private TileSafetyChest tile;

	private final EntityPlayer thePlayer;

	public GuiSafetyChest(EntityPlayer player, TileSafetyChest par2TileEntity) {
		super(new ContainerSafetyChest(player, par2TileEntity));
		this.tile = par2TileEntity;
		this.ySize = 222;
		thePlayer = player;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		// インベントリ名の描画

		String s = this.tile.hasCustomInventoryName() ? this.tile.getInventoryName() : I18n.format(
				this.tile.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 4, 4210752);
	}

	@Override
	public void drawScreen(int x, int y, float par3) {
		super.drawScreen(x, y, par3);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/generic_54.png"));

		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
}
