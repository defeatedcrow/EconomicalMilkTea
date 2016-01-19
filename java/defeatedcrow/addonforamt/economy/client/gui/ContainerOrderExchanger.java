package defeatedcrow.addonforamt.economy.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.common.quest.OrderExchanger;

public class ContainerOrderExchanger extends Container {

	private OrderExchanger tileentity;
	private int lastCookTime;
	private int lastBurnTime;

	public ContainerOrderExchanger(EntityPlayer player, OrderExchanger t) {
		this.tileentity = t;

		this.addSlotToContainer(new SlotDisplay(this.tileentity, 0, 7 + 12, 17));
		this.addSlotToContainer(new SlotDisplay(this.tileentity, 1, 7 + 52, 17));
		this.addSlotToContainer(new SlotDisplay(this.tileentity, 2, 7 + 92, 17));
		this.addSlotToContainer(new SlotDisplay(this.tileentity, 3, 7 + 132, 17));

		int i;

		for (int h = 0; h < 9; ++h) {
			this.addSlotToContainer(new Slot(tileentity, h + 4, 8 + h * 18, 66));
		}

		// 1 ～ 3段目のインベントリ
		for (i = 0; i < 3; ++i) {
			for (int h = 0; h < 9; ++h) {
				this.addSlotToContainer(new Slot(player.inventory, h + i * 9 + 9, 8 + h * 18, 118 + i * 18));
			}
		}

		// 4段目のインベントリ
		for (i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 176));
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
		int sLim = 49;

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			// カーソルをプレイヤーのインベントリにあわせている
			if (par2 > 12 && tileentity.isItemValidForSlot(par2, itemstack1)) {
				if (!this.mergeItemStack(itemstack1, 4, 13, false))
					return null;
			} else if (par2 > 4) {
				// カーソルを貯金箱のスロットにあわせているとき
				// プレイヤーインベントリへ
				if (!this.mergeItemStack(itemstack1, 27, sLim, true))
					return null;

				slot.onSlotChange(itemstack1, itemstack);
			} else {
				return null;
			}

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
