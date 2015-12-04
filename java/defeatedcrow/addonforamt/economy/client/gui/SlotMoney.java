package defeatedcrow.addonforamt.economy.client.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import defeatedcrow.addonforamt.economy.api.IMPCoin;

public class SlotMoney extends Slot {
	public SlotMoney(IInventory inv, int par2, int par3, int par4) {
		super(inv, par2, par3, par4);
	}

	/*
	 * お金以外受け付けない
	 */
	@Override
	public boolean isItemValid(ItemStack stack) {
		if (stack == null || stack.getItem() == null)
			return false;
		return stack.getItem() instanceof IMPCoin;
	}
}
