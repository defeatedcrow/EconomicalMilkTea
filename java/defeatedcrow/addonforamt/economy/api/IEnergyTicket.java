package defeatedcrow.addonforamt.economy.api;

import net.minecraft.item.ItemStack;

public interface IEnergyTicket {

	int getENGan(ItemStack stack);

	int getStartDay(ItemStack stack);

	int getUsableSlotNum(ItemStack stack);

}
