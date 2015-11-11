package defeatedcrow.addonforamt.economy.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.common.quest.TileOrderBoard;

public class ContainerOrderDisplay extends Container {

	private TileOrderBoard tile;

	public ContainerOrderDisplay(EntityPlayer player, TileOrderBoard t) {
		this.tile = t;

		this.addSlotToContainer(new SlotDisplay(this.tile, 0, 26, 24));
		this.addSlotToContainer(new SlotDisplay(this.tile, 1, 26, 61));
		this.addSlotToContainer(new SlotDisplay(this.tile, 2, 26, 98));
		this.addSlotToContainer(new SlotDisplay(this.tile, 3, 26, 135));

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
		return this.tile.isUseableByPlayer(par1EntityPlayer);
	}

	// Shiftクリック
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		return null;
	}
}
