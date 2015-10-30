package defeatedcrow.addonforamt.economy.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import shift.mceconomy2.api.MCEconomyAPI;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.plugin.mce.KariShopList;

public class BlockKariShop extends Block {

	public BlockKariShop() {
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

		if (world.isRemote) {
			return true;
		} else {
			MCEconomyAPI.openShopGui(KariShopList.thisShopId, player, world, x, y, z);
		}

		return true;
	}

}
