package defeatedcrow.addonforamt.economy.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class SlotDisplay extends Slot {
	public SlotDisplay(IInventory inv, int par2, int par3, int par4) {
		super(inv, par2, par3, par4);
	}

	/*
	 * このアイテムは動かせない、つかめないようにする。
	 */
	@Override
	public boolean canTakeStack(EntityPlayer player) {
		return false;
	}

}
