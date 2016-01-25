package defeatedcrow.addonforamt.economy.client.gui;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import defeatedcrow.addonforamt.economy.EMTLogger;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.packet.EMTPacketHandler;
import defeatedcrow.addonforamt.economy.packet.MessageAddStamp;
import defeatedcrow.addonforamt.economy.packet.MessageAddStampReward;

public class GuiStampCatalog extends GuiContainer {

	private final EntityPlayer thePlayer;
	private short stamp = 0;

	public GuiStampCatalog(EntityPlayer player) {
		super(new ContainerStampCatalog(player));
		this.ySize = 200;
		thePlayer = player;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		// インベントリ名の描画
		String s = I18n.format("Stamp Gift Catalog", new Object[0]);
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);

		this.fontRendererObj.drawString("" + stamp, 105, 27, 0x505030);
	}

	@Override
	public void drawScreen(int x, int y, float par3) {
		super.drawScreen(x, y, par3);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(
				new ResourceLocation(EcoMTCore.PACKAGE, "textures/gui/stampcatalog_gui.png"));

		// かまど描画処理
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		int[] req = {
				3,
				5,
				10,
				12,
				20,
				25,
				50,
				100 };
		for (int i = 0; i < req.length; i++) {
			if (stamp >= req[i]) {
				int ix = i & 3;
				int iy = i > 3 ? 1 : 0;
				this.drawTexturedModalRect(k + 52 + ix * 18, l + 43 + 25 * iy, 176 + ix * 16, 24 * iy, 16, 23);
			}
		}
	}

	@Override
	protected void mouseClicked(int x, int y, int button) {
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		int ix = x - k;
		int iy = y - l;
		String s = "x:" + ix + ", y:" + iy;
		boolean flag = false;
		if (ix > 0 && ix < this.xSize && iy > 0 && iy < this.ySize / 2) {
			EMTLogger.debugInfo(s);
			int reduce = 0;
			int num = 0;
			boolean b = false;
			if (ix > 52 && ix < 68 && iy > 43 && iy < 59) {
				if (stamp >= 3) {
					reduce = 3;
					num = 0;
					b = true;
				}
			}
			if (ix > 70 && ix < 86 && iy > 43 && iy < 59) {
				if (stamp >= 5) {
					reduce = 5;
					num = 1;
					b = true;
				}
			}
			if (ix > 88 && ix < 104 && iy > 43 && iy < 59) {
				if (stamp >= 10) {
					reduce = 10;
					num = 2;
					b = true;
				}
			}
			if (ix > 106 && ix < 122 && iy > 43 && iy < 59) {
				if (stamp >= 12) {
					reduce = 12;
					num = 3;
					b = true;
				}
			}
			if (ix > 52 && ix < 68 && iy > 68 && iy < 84) {
				if (stamp >= 20) {
					reduce = 20;
					num = 4;
					b = true;
				}
			}
			if (ix > 70 && ix < 86 && iy > 68 && iy < 84) {
				if (stamp >= 25) {
					reduce = 25;
					num = 5;
					b = true;
				}
			}
			if (ix > 88 && ix < 104 && iy > 68 && iy < 84) {
				if (stamp >= 50) {
					reduce = 50;
					num = 6;
					b = true;
				}
			}
			if (ix > 106 && ix < 122 && iy > 68 && iy < 84) {
				if (stamp >= 100) {
					reduce = 100;
					num = 7;
					b = true;
				}
			}
			if (b) {
				MessageAddStamp message = new MessageAddStamp(reduce, true);
				EMTPacketHandler.INSTANCE.sendToServer(message);
				MessageAddStampReward message2 = new MessageAddStampReward(num);
				EMTPacketHandler.INSTANCE.sendToServer(message2);
				this.mc.getSoundHandler().playSound(
						PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
			}
		}
		super.mouseClicked(x, y, button);
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		if (thePlayer.inventory.getCurrentItem().hasTagCompound()
				&& thePlayer.inventory.getCurrentItem().getTagCompound().hasKey("emt.stamp")) {
			stamp = thePlayer.inventory.getCurrentItem().getTagCompound().getShort("emt.stamp");
		}
	}
}
