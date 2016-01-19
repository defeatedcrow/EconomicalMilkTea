package defeatedcrow.addonforamt.economy.client.gui;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.common.quest.TileSafetyBox;
import defeatedcrow.addonforamt.economy.packet.EMTPacketHandler;
import defeatedcrow.addonforamt.economy.packet.MessageWithdrawAsBill;
import defeatedcrow.addonforamt.economy.packet.MessageWithdrawButton;
import defeatedcrow.addonforamt.economy.plugin.mce.MPHandler;

@SideOnly(Side.CLIENT)
public class GuiSafetyBox extends GuiContainer {

	private TileSafetyBox tile;

	private int temporaryMP = 000;
	private int[] cursolMP = {
			0,
			0,
			0,
			0 };
	private int cursolNum = 0;

	private boolean dep = false;
	private boolean wit = false;

	private final EntityPlayer thePlayer;

	public GuiSafetyBox(EntityPlayer player, TileSafetyBox par2TileEntity) {
		super(new ContainerSafetyBox(player, par2TileEntity));
		this.tile = par2TileEntity;
		this.ySize = 200;
		thePlayer = player;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		// インベントリ名の描画

		String s = this.tile.hasCustomInventoryName() ? this.tile.getInventoryName() : I18n.format(
				this.tile.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 4, 4210752);

		boolean uni = this.fontRendererObj.getUnicodeFlag();
		float f = 1.0F;
		if (uni) {
			GL11.glScalef(1.0F, 1.0F, 1.0F);
		} else {
			GL11.glScalef(0.8F, 0.8F, 0.8F);
			f = 1.25F;
		}

		// ボタン
		// String m = "Mode";
		String d = StatCollector.translateToLocal("dcs.emt.mp.deposit.button");
		String w = StatCollector.translateToLocal("dcs.emt.mp.withdraw.button");
		String b = StatCollector.translateToLocal("dcs.emt.mp.withdraw.bill");
		// this.fontRendererObj.drawString(m, 15, this.ySize / 2 + 3, 4210752);
		this.fontRendererObj.drawString(d, (int) ((85 - (d.length() / 2)) * f), (int) ((this.ySize / 2 + 4) * f),
				4210752);
		this.fontRendererObj.drawString(w, (int) ((130 - (w.length() / 2)) * f), (int) ((this.ySize / 2 + 4) * f),
				4210752);
		this.fontRendererObj.drawString(b, (int) ((15 - (w.length() / 2)) * f), (int) ((this.ySize / 2 + 4) * f),
				4210752);

		// MP
		long current = tile.getCurrentMP();
		int playerMP = MPHandler.INSTANCE.getPlayerMP(thePlayer);
		String mp = "Box: " + String.format("%10s", current) + " MP";
		String pmp = "Player: " + String.format("%7s", playerMP) + " MP";

		String temp = String.format("%06d", temporaryMP) + " MP";

		this.fontRendererObj.drawString(mp, (int) ((95) * f), (int) (14 * f), 0xFFFFFF);
		this.fontRendererObj.drawString(pmp, (int) ((10) * f), (int) (14 * f), 0xFFFFFF);
		this.fontRendererObj.drawString(temp, (int) ((55) * f), (int) ((this.ySize / 2 - 12) * f), 0xFFFFFF);

		if (!uni)
			GL11.glScalef(1.25F, 1.25F, 1.25F);
	}

	@Override
	public void drawScreen(int x, int y, float par3) {
		super.drawScreen(x, y, par3);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(EcoMTCore.PACKAGE, "textures/gui/safety_gui.png"));

		// かまど描画処理
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		this.drawTexturedModalRect(k + 64 - cursolNum * 4, l + 86, 176, 26, 9, 12);
	}

	@Override
	protected void mouseClicked(int x, int y, int button) {
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		int ix = x - k;
		int iy = y - l;
		String s = "x:" + ix + ", y:" + iy;
		boolean flag = false;
		if (ix > 0 && ix < this.xSize && iy > 0 && iy < this.ySize) {
			// EMTLogger.debugInfo(s);
			// left
			if (ix > 111 && ix < 123 && iy > 87 && iy < 97) {
				if (cursolNum < 3) {
					cursolNum++;
					flag = true;
				}
			}
			// right
			if (ix > 155 && ix < 167 && iy > 87 && iy < 97) {
				if (cursolNum > 0) {
					cursolNum--;
					flag = true;
				}
			}
			// +
			if (ix > 125 && ix < 136 && iy > 87 && iy < 97) {
				if (cursolMP[cursolNum] < 9) {
					cursolMP[cursolNum]++;
					flag = true;
				}
			}
			// -
			if (ix > 141 && ix < 153 && iy > 87 && iy < 97) {
				if (cursolMP[cursolNum] > 0) {
					cursolMP[cursolNum]--;
					flag = true;
				}
			}
			temporaryMP = cursolMP[0] * 100 + cursolMP[1] * 1000 + cursolMP[2] * 10000 + cursolMP[3] * 100000;
			// deposit
			if (ix > 80 && ix < 122 && iy > 101 && iy < 112) {
				// deposit
				MessageWithdrawButton message = new MessageWithdrawButton(temporaryMP, true, tile.xCoord, tile.yCoord,
						tile.zCoord);
				EMTPacketHandler.INSTANCE.sendToServer(message);
				flag = true;
			}
			if (ix > 125 && ix < 167 && iy > 101 && iy < 112) {
				// withdraw;
				MessageWithdrawButton message = new MessageWithdrawButton(temporaryMP, false, tile.xCoord, tile.yCoord,
						tile.zCoord);
				EMTPacketHandler.INSTANCE.sendToServer(message);
				flag = true;
			}
			if (ix > 10 && ix < 75 && iy > 101 && iy < 112) {
				// withdraw as bill;
				MessageWithdrawAsBill message = new MessageWithdrawAsBill(temporaryMP, tile.xCoord, tile.yCoord,
						tile.zCoord);
				EMTPacketHandler.INSTANCE.sendToServer(message);
				flag = true;
			}
			if (flag) {
				this.mc.getSoundHandler().playSound(
						PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
			}
		}
		super.mouseClicked(x, y, button);
	}
}
