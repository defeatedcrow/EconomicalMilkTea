package defeatedcrow.addonforamt.economy.common.quest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class CustomTooltipEvent {

	@SubscribeEvent
	public void advancedTooltip(ItemTooltipEvent event) {
		EntityPlayer player = event.entityPlayer;
		ItemStack target = event.itemStack;

		if (target != null) {
			NBTTagCompound tag = target.getTagCompound();
			if (tag != null) {
				if (tag.hasKey("EMT_M") && tag.getBoolean("EMT_M")) {
					event.toolTip.add(EnumChatFormatting.YELLOW + "Meta Compatibility" + EnumChatFormatting.RESET);
				}
				if (tag.hasKey("EMT_O")) {
					String s = tag.getString("EMT_O");
					event.toolTip.add("OreDictionary: " + EnumChatFormatting.YELLOW + s + EnumChatFormatting.RESET);
				}
			}
		}
	}

}
