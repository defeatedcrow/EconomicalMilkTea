package defeatedcrow.addonforamt.economy.client.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import defeatedcrow.addonforamt.economy.common.item.ItemStamp;

public class SlotForStamp extends Slot {
	public SlotForStamp(IInventory inv, int par2, int par3, int par4) {
		super(inv, par2, par3, par4);
	}

	/*
	 * stampのみ受け付ける
	 */
	@Override
	public boolean isItemValid(ItemStack stack) {
		if (stack == null || stack.getItem() == null)
			return false;
		return stack.getItem() instanceof ItemStamp;
	}
}
