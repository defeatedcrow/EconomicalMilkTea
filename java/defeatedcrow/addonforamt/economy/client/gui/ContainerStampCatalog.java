package defeatedcrow.addonforamt.economy.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EcoMTCore;

public class ContainerStampCatalog extends Container {
	protected InventoryCatalog inventory;
	protected short lastStamp = 0;

	public ContainerStampCatalog(EntityPlayer player) {
		inventory = new InventoryCatalog(player.inventory);
		inventory.openInventory();
		int b0 = 118;
		int i = 8;

		this.addSlotToContainer(new SlotForStamp(inventory, 0, 51, 19));

		for (int j = 0; j < 3; ++j) {
			for (int k = 0; k < 9; ++k) {
				this.addSlotToContainer(new SlotStampCatalog(player.inventory, k + j * 9 + 9, 8 + k * 18, j * 18 + b0));
			}
		}

		for (int j = 0; j < 9; ++j) {
			this.addSlotToContainer(new SlotStampCatalog(player.inventory, j, 8 + j * 18, 58 + b0));
		}

	}

	/*
	 * Containerが開いてられるか
	 */
	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}

	// stamp同期
	@Override
	public void addCraftingToCrafters(ICrafting par1ICrafting) {
		super.addCraftingToCrafters(par1ICrafting);
		par1ICrafting.sendProgressBarUpdate(this, 0, inventory.getStampCount());
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		if (this.crafters.size() > 0) {
			ICrafting icrafting = (ICrafting) this.crafters.get(0);
			if (lastStamp != inventory.getStampCount()) {
				icrafting.sendProgressBarUpdate(this, 0, inventory.getStampCount());
			}
		}
		lastStamp = inventory.getStampCount();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
		if (par1 == 0) {
			inventory.setStampCount((short) par2);
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int stackSlot) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(stackSlot);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (stackSlot == 0) {
				if (!this.mergeItemStack(itemstack1, this.inventory.getSizeInventory(), this.inventorySlots.size(),
						true)) {
					return null;
				}
			}
			// シフトクリック時に、このアイテムだったら動かさない。
			else if (slot.getStack() != null && slot.getStack().getItem() == EcoMTCore.giftCatalog) {
				return null;
			} else if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
				return null;
			}
			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	/*
	 * Containerを閉じるときに呼ばれる
	 */
	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);
		this.inventory.closeInventory();
	}
}
