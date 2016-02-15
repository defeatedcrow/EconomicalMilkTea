package defeatedcrow.addonforamt.economy.client.gui;

import java.util.ArrayList;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EMTLogger;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.api.RecipeManagerEMT;
import defeatedcrow.addonforamt.economy.api.order.IOrder;
import defeatedcrow.addonforamt.economy.api.order.OrderSeason;
import defeatedcrow.addonforamt.economy.api.order.OrderType;
import defeatedcrow.addonforamt.economy.common.quest.OrderExchanger;
import defeatedcrow.addonforamt.economy.packet.EMTPacketHandler;
import defeatedcrow.addonforamt.economy.packet.MessageWithdrawButton;
import defeatedcrow.addonforamt.economy.plugin.mce.MPHandler;
import defeatedcrow.addonforamt.economy.util.TimeUtil;

@SideOnly(Side.CLIENT)
public class GuiOrderExchanger extends GuiContainer {

	private OrderExchanger tile;

	private final EntityPlayer thePlayer;

	public GuiOrderExchanger(EntityPlayer player, OrderExchanger par2TileEntity) {
		super(new ContainerOrderExchanger(player, par2TileEntity));
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
		String d = StatCollector.translateToLocal("dcs.emt.mp.withdraw.button");
		this.fontRendererObj.drawString(d, (int) ((133 - (d.length() / 2)) * f), (int) ((this.ySize / 2 + 4) * f),
				4210752);

		// order表示
		for (int i = 0; i < 4; i++) {
			int rew = tile.getReward(i);
			int req = tile.getReuire(i);
			int keep = tile.getKeep(i);
			String s1 = rew + "MP";
			String s2 = "/" + req;
			String s3 = "" + keep;
			int color = 0xFFFFFF;
			if (keep < (req / 2))
				color = 0x993030;
			else if (keep < req)
				color = 0xFF5050;
			else if (keep >= req * 2)
				color = 0x9999FF;
			this.fontRendererObj.drawString(s1, (int) ((7 + i * 40 + 12 - (s1.length() / 2)) * f), (int) (50 * f),
					0x50FFFF);
			this.fontRendererObj.drawString(s2, (int) ((7 + i * 40 + 16 - (s2.length() / 2)) * f), (int) (40 * f),
					0xFFFFFF);
			this.fontRendererObj.drawString(s3, (int) ((7 + i * 40 + 14 - (s3.length() / 2)) * f), (int) (33 * f),
					color);
		}

		// Day
		String day = "Day" + TimeUtil.getDay(EcoMTCore.proxy.getClientWorld());
		int seasonNum = TimeUtil.getSeason(EcoMTCore.proxy.getClientWorld());
		String season = OrderSeason.getSeason(seasonNum).name();

		this.fontRendererObj.drawString(day + ", " + season, (int) (12 * f), (int) ((this.ySize / 2 + 3) * f), 4210752);

		// MP
		long current = tile.getCurrentMP();
		int playerMP = MPHandler.INSTANCE.getPlayerMP(thePlayer);
		String mp = "Box: " + String.format("%10s", current) + " MP";
		String pmp = "Player: " + String.format("%7s", playerMP) + " MP";

		this.fontRendererObj.drawString(mp, (int) (95 * f), (int) ((this.ySize / 2 - 12) * f), 0xFFFFFF);
		this.fontRendererObj.drawString(pmp, (int) (10 * f), (int) ((this.ySize / 2 - 12) * f), 0xFFFFFF);

		if (!uni)
			GL11.glScalef(1.25F, 1.25F, 1.25F);
	}

	@Override
	public void drawScreen(int x, int y, float par3) {
		super.drawScreen(x, y, par3);
		boolean[] b = {
				false,
				false,
				false,
				false };
		b[0] = this.func_146978_c(14, 36, 20, 30, x, y);
		b[1] = this.func_146978_c(54, 36, 20, 30, x, y);
		b[2] = this.func_146978_c(94, 36, 20, 30, x, y);
		b[3] = this.func_146978_c(134, 36, 20, 30, x, y);
		for (int i = 0; i < 4; i++) {
			if (b[i]) {
				int id = tile.getOrderID(i);
				OrderType type = OrderType.getType(i);
				IOrder order = RecipeManagerEMT.orderRegister.getOrderFromID(id, type);
				ArrayList<String> list = new ArrayList<String>();
				if (order != null) {
					int limit = i == 0 ? 1 : tile.getStartDay(i) + OrderType.getType(i).getLimit()
							- TimeUtil.getDay(tile.getWorldObj()) + 1;
					String s1 = StatCollector.translateToLocal("dcs.emt.order.word1");
					String s2 = StatCollector.translateToLocal("dcs.emt.order.word2");
					String s3 = type.name();
					list.add(s3);
					list.add(s1 + limit + s2);

					boolean ach = tile.keep[i] >= tile.requires[i];
					if (ach) {
						String s4 = StatCollector.translateToLocal("dcs.emt.order.achieved");
						list.add(s4);
					}

					this.drawHoveringText(list, x, y, fontRendererObj);
				}
			}
		}

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(
				new ResourceLocation(EcoMTCore.PACKAGE, "textures/gui/transaction_gui.png"));

		// かまど描画処理
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
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
			EMTLogger.debugInfo(s);
			int current = (int) tile.getCurrentMP();
			if (ix > 125 && ix < 167 && iy > 101 && iy < 112) {
				// withdraw;
				MessageWithdrawButton message = new MessageWithdrawButton(current, false, tile.xCoord, tile.yCoord,
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
