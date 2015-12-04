package defeatedcrow.addonforamt.economy.common.quest;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSafetyBox extends ItemBlock {

	public ItemSafetyBox(Block b) {
		super(b);
	}

	@Override
	@SideOnly(Side.CLIENT)
	// マウスオーバー時の表示情報
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
		super.addInformation(stack, player, list, par4);
		NBTTagCompound tag = stack.getTagCompound();
		if (tag != null && tag.hasKey("Owner")) {
			String owner = tag.getString("Owner");
			list.add(new String("Owner Player: " + owner));

		}
	}

}
