package defeatedcrow.addonforamt.economy.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import defeatedcrow.addonforamt.economy.EcoMTCore;

public class SlotStampCatalog extends Slot {
	public SlotStampCatalog(IInventory inv, int par2, int par3, int par4) {
		super(inv, par2, par3, par4);
	}

	/*
	 * このアイテムは動かせない、つかめないようにする。
	 */
	@Override
	public boolean canTakeStack(EntityPlayer player) {
		return !(getHasStack() && getStack().getItem() == EcoMTCore.giftCatalog);
	}

}
