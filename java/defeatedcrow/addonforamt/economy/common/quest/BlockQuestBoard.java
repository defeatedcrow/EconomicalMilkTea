package defeatedcrow.addonforamt.economy.common.quest;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EcoMTCore;

public class BlockQuestBoard extends BlockContainer {

	public BlockQuestBoard() {
		super(Material.clay);
		this.setStepSound(Block.soundTypeStone);
		this.setHardness(0.2F);
		this.setResistance(1.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon(EcoMTCore.PACKAGE + ":gadget/shop_kari");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7,
			float par8, float par9) {

		ItemStack item = player.inventory.getCurrentItem();
		TileEntity tileentity = world.getTileEntity(x, y, z);
		TileOrderBoard tile = null;
		if (tileentity != null && tileentity instanceof TileOrderBoard) {
			tile = (TileOrderBoard) tileentity;
		}
		if (tile != null) {
			if (world.isRemote) {
				return true;
			} else {
				player.openGui(EcoMTCore.instance, EcoMTCore.instance.guiBoard, world, x, y, z);
				return true;
			}

		}

		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileOrderBoard();
	}

}
