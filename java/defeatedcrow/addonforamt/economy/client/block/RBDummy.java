package defeatedcrow.addonforamt.economy.client.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EcoMTCore;

/*
 * インベントリ内ではアイテムアイコン、設置時には何もレンダーしないダミー用レンダークラス。
 * ブロックのモデルはTileEntityのレンダーのみを使う。
 */
@SideOnly(Side.CLIENT)
public class RBDummy implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderer) {

		if (modelId == this.getRenderId()) {
			renderer.clearOverrideBlockTexture();
			block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			renderer.setRenderBoundsFromBlock(block);
			return true;
		}

		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int a) {
		return false;
	}

	@Override
	public int getRenderId() {

		return EcoMTCore.dummyRB;
	}
}
