package defeatedcrow.addonforamt.economy.client.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EcoMTCore;

@SideOnly(Side.CLIENT)
public class RBOrderBoard implements ISimpleBlockRenderingHandler {

	private IIcon coverIcon;
	private IIcon innerIcon;
	private IIcon stampIcon;

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {

		int meta = metadata;
		int dirMeta = (meta & 3);
		this.coverIcon = Blocks.planks.getBlockTextureFromSide(0);
		this.innerIcon = Blocks.stained_hardened_clay.getIcon(0, 13);
		this.stampIcon = block.getBlockTextureFromSide(0);

		if (modelID == this.getRenderId()) {
			// bottom
			renderInvCuboid(renderer, block, 0.0F, 0.0F, 8.0F, 16.0F, 16.0F, 9.0F, this.coverIcon);
			renderInvCuboid(renderer, block, 0.0F, 0.0F, 9.0F, 16.0F, 2.0F, 11.0F, this.coverIcon);
			renderInvCuboid(renderer, block, 0.0F, 14.0F, 9.0F, 16.0F, 16.0F, 11.0F, this.coverIcon);
			renderInvCuboid(renderer, block, 0.0F, 2.0F, 9.0F, 2.0F, 14.0F, 11.0F, this.coverIcon);
			renderInvCuboid(renderer, block, 16.0F, 2.0F, 9.0F, 14.0F, 14.0F, 11.0F, this.coverIcon);

			renderInvCuboid(renderer, block, 2.0F, 2.0F, 9.0F, 14.0F, 14.0F, 10.0F, this.innerIcon);
			renderInvCuboid(renderer, block, 3.0F, 3.0F, 10.0F, 13.0F, 12.0F, 10.1F, this.stampIcon);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {

		int meta = world.getBlockMetadata(x, y, z);
		int dir = (meta & 3);
		this.coverIcon = Blocks.planks.getBlockTextureFromSide(0);
		this.innerIcon = Blocks.stained_hardened_clay.getIcon(0, 13);
		this.stampIcon = block.getBlockTextureFromSide(0);
		float f = 0.0625F;

		if (modelId == this.getRenderId()) {
			if (dir == 0) {
				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(0 * f, 0 * f, 15 * f, 16 * f, 16 * f, 16 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(0 * f, 0 * f, 13 * f, 16 * f, 2 * f, 15 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(0 * f, 14 * f, 13 * f, 16 * f, 16 * f, 15 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(0 * f, 2 * f, 13 * f, 2 * f, 14 * f, 15 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(14 * f, 2 * f, 13 * f, 16 * f, 14 * f, 15 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.innerIcon);
				block.setBlockBounds(2 * f, 2 * f, 14 * f, 14 * f, 14 * f, 15 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.stampIcon);
				block.setBlockBounds(3 * f, 3 * f, 13.9F * f, 13 * f, 12 * f, 14 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (dir == 2) {
				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(0 * f, 0 * f, 0 * f, 16 * f, 16 * f, 1 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(0 * f, 0 * f, 1 * f, 16 * f, 2 * f, 3 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(0 * f, 14 * f, 1 * f, 16 * f, 16 * f, 3 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(0 * f, 2 * f, 1 * f, 2 * f, 14 * f, 3 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(14 * f, 2 * f, 1 * f, 16 * f, 14 * f, 3 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.innerIcon);
				block.setBlockBounds(2 * f, 2 * f, 1 * f, 14 * f, 14 * f, 2 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.stampIcon);
				block.setBlockBounds(3 * f, 3 * f, 2 * f, 13 * f, 12 * f, 2.1F * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (dir == 1) {
				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(0 * f, 0 * f, 0 * f, 1 * f, 16 * f, 16 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(1 * f, 0 * f, 0 * f, 3 * f, 16 * f, 2 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(1 * f, 0 * f, 14 * f, 3 * f, 16 * f, 16 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(1 * f, 0 * f, 2 * f, 3 * f, 2 * f, 14 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(1 * f, 14 * f, 2 * f, 3 * f, 16 * f, 14 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.innerIcon);
				block.setBlockBounds(1 * f, 2 * f, 2 * f, 2 * f, 14 * f, 14 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.stampIcon);
				block.setBlockBounds(2 * f, 3 * f, 3 * f, 2.1F * f, 12 * f, 13 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			} else if (dir == 3) {
				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(15 * f, 0 * f, 0 * f, 16 * f, 16 * f, 16 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(13 * f, 0 * f, 0 * f, 15 * f, 16 * f, 2 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(13 * f, 0 * f, 14 * f, 15 * f, 16 * f, 16 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(13 * f, 0 * f, 2 * f, 15 * f, 2 * f, 14 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.coverIcon);
				block.setBlockBounds(13 * f, 14 * f, 2 * f, 15 * f, 16 * f, 14 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);

				renderer.setOverrideBlockTexture(this.innerIcon);
				block.setBlockBounds(14 * f, 2 * f, 2 * f, 15 * f, 14 * f, 14 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
				renderer.setOverrideBlockTexture(this.stampIcon);
				block.setBlockBounds(13.9F * f, 3 * f, 3 * f, 14 * f, 12 * f, 13 * f);
				renderer.setRenderBoundsFromBlock(block);
				renderer.renderStandardBlock(block, x, y, z);
			}
			renderer.clearOverrideBlockTexture();
			block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			renderer.setRenderBoundsFromBlock(block);
			return true;
		}

		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int a) {
		return true;
	}

	@Override
	public int getRenderId() {
		return EcoMTCore.boardRB;
	}

	private void renderInvCuboid(RenderBlocks renderer, Block block, float mX, float mY, float mZ, float xX, float xY,
			float xZ, IIcon icon) {
		float minX = mX * 0.0625F;
		float minY = mY * 0.0625F;
		float minZ = mZ * 0.0625F;
		float maxX = xX * 0.0625F;
		float maxY = xY * 0.0625F;
		float maxZ = xZ * 0.0625F;
		Tessellator tessellator = Tessellator.instance;
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		block.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		renderer.setRenderBoundsFromBlock(block);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icon);
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		renderer.setRenderBoundsFromBlock(block);
	}
}