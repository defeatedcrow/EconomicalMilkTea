package defeatedcrow.addonforamt.economy.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.common.item.ItemStamp;

// inventoryにアイテムは入れられない、疑似インベントリ。
public class InventoryCatalog implements IInventory {
	private InventoryPlayer inventoryPlayer;
	private ItemStack currentItem;

	public InventoryCatalog(InventoryPlayer inventory) {
		inventoryPlayer = inventory;
		currentItem = inventoryPlayer.getCurrentItem();
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return null;
	}

	@Override
	public ItemStack decrStackSize(int slot, int size) {
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		NBTTagCompound tag = new NBTTagCompound();
		if (currentItem.hasTagCompound()) {
			tag = currentItem.getTagCompound();
		}
		short stamp = 0;

		if (stack != null && stack.getItem() instanceof ItemStamp) {
			if (tag.hasKey("emt.stamp")) {
				stamp = tag.getShort("emt.stamp");
			}
			stamp += stack.stackSize;
			if (stamp > 9999)
				stamp = 9999;

			ItemStack result = currentItem.copy();
			tag.setShort("emt.stamp", stamp);
			result.setTagCompound(tag);

			inventoryPlayer.mainInventory[inventoryPlayer.currentItem] = result;

		}

		this.markDirty();
		inventoryPlayer.markDirty();
	}

	@Override
	public String getInventoryName() {
		return "Stamp Catalog";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	// stamp専用
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return stack != null && stack.getItem() == EcoMTCore.stamp;
	}

	public void setStampCount(short s) {
		NBTTagCompound tag = new NBTTagCompound();
		if (currentItem.hasTagCompound()) {
			tag = currentItem.getTagCompound();
		}

		ItemStack result = currentItem.copy();
		tag.setShort("emt.stamp", s);
		result.setTagCompound(tag);

		inventoryPlayer.mainInventory[inventoryPlayer.currentItem] = result;
		this.markDirty();
		inventoryPlayer.markDirty();
	}

	public short getStampCount() {
		short s = 0;
		NBTTagCompound tag = new NBTTagCompound();
		if (currentItem.hasTagCompound()) {
			tag = currentItem.getTagCompound();
		}
		if (tag.hasKey("emt.stamp")) {
			s = tag.getShort("emt.stamp");
		}

		return s;
	}

}