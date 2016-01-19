package defeatedcrow.addonforamt.economy.client;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import defeatedcrow.addonforamt.economy.api.BuildType;
import defeatedcrow.addonforamt.economy.api.ISimpleBuildingItem;

public class RenderBuildCardEvent {

	private static final ResourceLocation tex = new ResourceLocation("textures/entity/redgrid.png");

	private int[] fx = {
			0,
			-1,
			0,
			1 };
	private int[] fz = {
			1,
			0,
			-1,
			0 };

	@SubscribeEvent
	public void RenderLineEvent(DrawBlockHighlightEvent event) {
		EntityPlayer player = event.player;
		ItemStack hold = event.currentItem;
		MovingObjectPosition target = event.target;

		if (player == null || hold == null || hold.getItem() == null || target == null
				|| target.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK)
			return;

		if (hold.getItem() instanceof ISimpleBuildingItem) {
			ISimpleBuildingItem card = (ISimpleBuildingItem) hold.getItem();
			int meta = hold.getItemDamage();

			int r = card.getArea(meta);
			BuildType type = card.getType(meta);
			int dir = MathHelper.floor_double(player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
			int side = target.sideHit;

			double mX = this.getMinX(target.blockX, r, dir, type);
			double mY = this.getMinY(target.blockY, r, dir, type, side);
			double mZ = this.getMinZ(target.blockZ, r, dir, type);
			double xX = 1.0D + this.getMaxX(target.blockX, r, dir, type);
			double xY = this.getMaxY(target.blockY, r, dir, type, side);
			double xZ = 1.0D + this.getMaxZ(target.blockZ, r, dir, type);

			double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * event.partialTicks;
			double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * event.partialTicks;
			double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * event.partialTicks;

			AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(mX, mY, mZ, xX, xY, xZ);
			aabb = aabb.expand(0.002F, 0.002F, 0.002F).getOffsetBoundingBox(-d0, -d1, -d2);

			Tessellator tessellator = Tessellator.instance;

			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glDepthMask(false);
			GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.85F);
			GL11.glLineWidth(3F);

			this.drawOutlinedBoundingBox(aabb);

			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDepthMask(true);
			GL11.glPopMatrix();
		}

	}

	private double getMinX(double pre, int range, int dir, BuildType type) {
		if (type == BuildType.BOARD) {
			return fx[dir] < 0 ? pre + fx[dir] * range : pre;
		} else if (type == BuildType.ROOF) {
			return fx[dir] == 1 ? pre : pre - range;
		}
		return pre - range;
	}

	private double getMaxX(double pre, int range, int dir, BuildType type) {
		if (type == BuildType.BOARD) {
			return fx[dir] < 0 ? pre : pre + fx[dir] * range;
		} else if (type == BuildType.ROOF) {
			return fx[dir] == -1 ? pre : pre + range;
		}
		return pre + range;
	}

	private double getMinZ(double pre, int range, int dir, BuildType type) {
		if (type == BuildType.BOARD) {
			return fz[dir] < 0 ? pre + fz[dir] * range : pre;
		} else if (type == BuildType.ROOF) {
			return fz[dir] == 1 ? pre : pre - range;
		}
		return pre - range;
	}

	private double getMaxZ(double pre, int range, int dir, BuildType type) {
		if (type == BuildType.BOARD) {
			return fz[dir] < 0 ? pre : pre + fz[dir] * range;
		} else if (type == BuildType.ROOF) {
			return fz[dir] == -1 ? pre : pre + range;
		}
		return pre + range;
	}

	private double getMinY(double pre, int range, int dir, BuildType type, int side) {
		if (type == BuildType.BOARD || type == BuildType.SQUARE) {
			return pre + 1.0D;
		}
		return pre;
	}

	private double getMaxY(double pre, int range, int dir, BuildType type, int side) {
		if (type == BuildType.PLATE) {
			return pre + 1.0D;
		} else if (type == BuildType.BOARD || type == BuildType.SQUARE) {
			return pre + range + 1.0D;
		}
		return pre + range;
	}

	private void drawOutlinedBoundingBox(AxisAlignedBB aabb) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawing(3);
		tessellator.addVertex(aabb.minX, aabb.minY, aabb.minZ);
		tessellator.addVertex(aabb.maxX, aabb.minY, aabb.minZ);
		tessellator.addVertex(aabb.maxX, aabb.minY, aabb.maxZ);
		tessellator.addVertex(aabb.minX, aabb.minY, aabb.maxZ);
		tessellator.addVertex(aabb.minX, aabb.minY, aabb.minZ);
		tessellator.draw();
		tessellator.startDrawing(3);
		tessellator.addVertex(aabb.minX, aabb.maxY, aabb.minZ);
		tessellator.addVertex(aabb.maxX, aabb.maxY, aabb.minZ);
		tessellator.addVertex(aabb.maxX, aabb.maxY, aabb.maxZ);
		tessellator.addVertex(aabb.minX, aabb.maxY, aabb.maxZ);
		tessellator.addVertex(aabb.minX, aabb.maxY, aabb.minZ);
		tessellator.draw();
		tessellator.startDrawing(1);
		tessellator.addVertex(aabb.minX, aabb.minY, aabb.minZ);
		tessellator.addVertex(aabb.minX, aabb.maxY, aabb.minZ);
		tessellator.addVertex(aabb.maxX, aabb.minY, aabb.minZ);
		tessellator.addVertex(aabb.maxX, aabb.maxY, aabb.minZ);
		tessellator.addVertex(aabb.maxX, aabb.minY, aabb.maxZ);
		tessellator.addVertex(aabb.maxX, aabb.maxY, aabb.maxZ);
		tessellator.addVertex(aabb.minX, aabb.minY, aabb.maxZ);
		tessellator.addVertex(aabb.minX, aabb.maxY, aabb.maxZ);
		tessellator.draw();
	}
}
