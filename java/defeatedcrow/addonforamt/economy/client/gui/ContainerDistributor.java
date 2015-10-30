package defeatedcrow.addonforamt.economy.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.common.block.TileDistributor;

public class ContainerDistributor extends Container {

	private TileDistributor tileentity;

	public ContainerDistributor(EntityPlayer player, TileDistributor tile) {
		this.tileentity = tile;

		// 燃料
		this.addSlotToContainer(new Slot(this.tileentity, 0, 24, 23));

		int i;

		for (i = 0; i < 2; ++i) {
			for (int j = 0; j < 4; ++j) {
				this.addSlotToContainer(new Slot(this.tileentity, j + i * 4 + 1, 62 + j * 24, 17 + i * 34));
			}
		}

		// 1 ～ 3段目のインベントリ
		for (i = 0; i < 3; ++i) {
			for (int h = 0; h < 9; ++h) {
				this.addSlotToContainer(new Slot(player.inventory, h + i * 9 + 9, 8 + h * 18, 84 + i * 18));
			}
		}

		// 4段目のインベントリ
		for (i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
		}
	}

	@Override
	public void addCraftingToCrafters(ICrafting par1ICrafting) {
		super.addCraftingToCrafters(par1ICrafting);
	}

	// 更新を送る
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
	}

	// 更新する
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
	}

	// InventorySample内のisUseableByPlayerメソッドを参照
	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
		return this.tileentity.isUseableByPlayer(par1EntityPlayer);
	}

	// Shiftクリック
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);
		int sLim = 45;

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (par2 > 8) {
				if (this.tileentity.isTicket(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false))
						return null;
				} else {
					if (!this.mergeItemStack(itemstack1, 1, 8, false))
						return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 9, sLim, false))
				return null;

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize)
				return null;

			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}

		return itemstack;
	}
}
