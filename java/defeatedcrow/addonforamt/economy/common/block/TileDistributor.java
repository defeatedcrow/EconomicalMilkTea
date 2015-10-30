package defeatedcrow.addonforamt.economy.common.block;

import mods.defeatedcrow.api.charge.IChargeableMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import defeatedcrow.addonforamt.economy.EMTLogger;
import defeatedcrow.addonforamt.economy.api.IEnergyTicket;
import defeatedcrow.addonforamt.economy.common.item.ItemCoodTicket;
import defeatedcrow.addonforamt.economy.util.TimeUtil;

/*
 * Distributer
 * スロットの内容に応じて遠隔でチャージを送信する。
 * 隣接Tileとのやりとりがないためチャージ系インターフェイス実装なし。
 * チャンクローダー機能を有する。
 * */
public class TileDistributor extends TileEntity implements ISidedInventory {

	private int lastState = 0;

	@Override
	public void readFromNBT(NBTTagCompound tab) {
		super.readFromNBT(tab);

		NBTTagList nbttaglist = tab.getTagList("Items", 10);
		this.itemstacks = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.itemstacks.length) {
				this.itemstacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tab) {
		super.writeToNBT(tab);

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.itemstacks.length; ++i) {
			if (this.itemstacks[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.itemstacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		tab.setTag("Items", nbttaglist);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		this.writeToNBT(nbtTagCompound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTagCompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());
	}

	/* === 更新処理 === */

	@Override
	public void updateEntity() {
		boolean active = this.isTicketActive();

		if (!this.worldObj.isRemote) {
			if (active) {
				int slot = this.getSlotLim();
				int en = this.getSendEN();

				for (int i = 1; i < slot + 1; i++) {
					this.sendEnergy(i);
				}
			}

			this.updateTicket();
			this.markDirty();

			this.onServerUpdate();
		}
	}

	private void onServerUpdate() {

		int current = this.getSlotLim() + this.getSendEN() * 13;
		if (current != this.lastState) {
			this.lastState = current;
			this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}

	/* === 専用処理 === */

	public boolean hasTicket() {
		ItemStack stack = this.getStackInSlot(0);
		return isTicket(stack);
	}

	public boolean hasCoodTicket(int i) {
		ItemStack stack = this.getStackInSlot(i);
		return isCoodTicket(stack);
	}

	public boolean isTicketActive() {
		return this.getRemainingDay() > 0;
	}

	public boolean updateTicket() {
		if (this.hasTicket()) {
			if (this.getRemainingDay() > 0) {
				return true;
			} else {
				this.decrStackSize(0, 1);
				return false;
			}
		}
		return false;
	}

	public static boolean isTicket(ItemStack stack) {
		if (stack != null && stack.getItem() != null) {
			return stack.getItem() instanceof IEnergyTicket;
		}
		return false;
	}

	public static boolean isCoodTicket(ItemStack stack) {
		if (stack != null && stack.getItem() != null) {
			if (stack.getItem() instanceof ItemCoodTicket) {
				return ((ItemCoodTicket) stack.getItem()).isRegistered(stack);
			}
		}
		return false;
	}

	public int getRemainingDay() {
		ItemStack stack = this.getStackInSlot(0);
		int day = TimeUtil.getDay(worldObj);
		boolean ticket = this.hasTicket();
		if (ticket) {
			NBTTagCompound tag = stack.getTagCompound();
			if (tag == null) {
				tag = new NBTTagCompound();
			}
			if (!tag.hasKey("StartDay")) {
				tag.setInteger("StartDay", day);
				stack.setTagCompound(tag);
				EMTLogger.debugInfo("Now Start : " + day + " day");
				this.markDirty();
			}

			int start = tag.getInteger("StartDay");
			return 30 - day + start;
		}
		return -1;
	}

	public int getSlotLim() {
		ItemStack stack = this.getStackInSlot(0);
		if (stack != null && stack.getItem() != null) {
			if (stack.getItem() instanceof IEnergyTicket) {
				IEnergyTicket ticket = (IEnergyTicket) stack.getItem();
				return ticket.getUsableSlotNum(stack);
			}
		}
		return 0;
	}

	public int getSendEN() {
		ItemStack stack = this.getStackInSlot(0);
		if (stack != null && stack.getItem() != null) {
			if (stack.getItem() instanceof IEnergyTicket) {
				IEnergyTicket ticket = (IEnergyTicket) stack.getItem();
				return ticket.getENGan(stack);
			}
		}
		return 0;
	}

	public void sendEnergy(int slot) {
		ItemStack stack = this.getStackInSlot(slot);
		int send = this.getSendEN();
		if (this.isCoodTicket(stack)) {
			ItemCoodTicket tic = (ItemCoodTicket) stack.getItem();
			int[] cood = tic.getCood(stack);
			int dim = tic.getDim(stack);
			if (cood != null && dim == worldObj.provider.dimensionId) {
				TileEntity tile = worldObj.getTileEntity(cood[0], cood[1], cood[2]);
				if (tile != null && tile instanceof IChargeableMachine) {
					IChargeableMachine app = (IChargeableMachine) tile;
					app.addCharge(send, false);
				}
			}
		}
	}

	/* === 描画用 === */

	public boolean[] activeSlot() {
		boolean[] ret = {
				false,
				false,
				false,
				false };
		if (this.isTicketActive()) {
			int lim = this.getSlotLim() / 2;
			for (int i = 0; i < lim; i++) {
				if (this.hasCoodTicket(i * 2 + 1) || hasCoodTicket(i * 2 + 2)) {
					ret[i] = true;
				}
			}
		}
		return ret;
	}

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	/*
	 * 0 : 燃料搬入
	 * 1 : 燃料の空容器搬出
	 * 2~ : 各Tileで実装される。
	 */
	protected int[] slotsTop() {
		return new int[] { 0 };
	}

	protected int[] slotsBottom() {
		return new int[] { 0 };
	}

	protected int[] slotsSides() {
		return new int[] { 0 };
	}

	public ItemStack[] itemstacks = new ItemStack[getSizeInventory()];

	// スロット数は各Tileでオーバーライドして増やすこと。2は最低限の値。
	@Override
	public int getSizeInventory() {
		return 9;
	}

	// インベントリ内の任意のスロットにあるアイテムを取得
	@Override
	public ItemStack getStackInSlot(int par1) {
		return par1 < this.getSizeInventory() ? this.itemstacks[par1] : null;
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		if (this.itemstacks[par1] != null) {
			ItemStack itemstack;

			if (this.itemstacks[par1].stackSize <= par2) {
				itemstack = this.itemstacks[par1];
				this.itemstacks[par1] = null;
				return itemstack;
			} else {
				itemstack = this.itemstacks[par1].splitStack(par2);

				if (this.itemstacks[par1].stackSize == 0) {
					this.itemstacks[par1] = null;
				}

				return itemstack;
			}
		} else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
		if (this.itemstacks[par1] != null) {
			ItemStack itemstack = this.itemstacks[par1];
			this.itemstacks[par1] = null;
			return itemstack;
		} else
			return null;
	}

	// インベントリ内のスロットにアイテムを入れる
	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {

		if (par1 > this.getSizeInventory()) {
			par1 = 0;// 存在しないスロットに入れようとすると強制的に材料スロットに変更される。
		}

		this.itemstacks[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}
	}

	// インベントリの名前
	@Override
	public String getInventoryName() {
		return StatCollector.translateToLocal("dcs.energy.distributor.gui");
	}

	// 多言語対応かどうか
	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	// インベントリ内のスタック限界値
	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public void markDirty() {
		super.markDirty();
	}

	// par1EntityPlayerがTileEntityを使えるかどうか
	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer
				.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack stack) {
		return (par1 > 0) ? false : this.isTicket(stack);
	}

	// ホッパーにアイテムの受け渡しをする際の優先度
	@Override
	public int[] getAccessibleSlotsFromSide(int par1) {
		return par1 == 0 ? slotsBottom() : (par1 == 1 ? slotsTop() : slotsSides());
	}

	// ホッパーからアイテムを入れられるかどうか
	@Override
	public boolean canInsertItem(int par1, ItemStack stack, int par3) {
		return this.isTicket(stack);
	}

	// 隣接するホッパーにアイテムを送れるかどうか
	@Override
	public boolean canExtractItem(int par1, ItemStack stack, int par3) {
		return false;
	}

	// 追加メソッド
	protected static boolean isItemStackable(ItemStack target, ItemStack current) {
		if (target == null || current == null)
			return false;

		if (target.getItem() == current.getItem() && target.getItemDamage() == current.getItemDamage()) {
			return (current.stackSize + target.stackSize) <= current.getMaxStackSize();
		}

		return false;
	}

	protected void incrStackInSlot(int i, ItemStack input) {
		if (i < this.getSizeInventory() && input != null && this.itemstacks[i] != null) {
			if (this.itemstacks[i].getItem() == input.getItem()
					&& this.itemstacks[i].getItemDamage() == input.getItemDamage()) {
				this.itemstacks[i].stackSize += input.stackSize;
				if (this.itemstacks[i].stackSize > this.getInventoryStackLimit()) {
					this.itemstacks[i].stackSize = this.getInventoryStackLimit();
				}
			}
		} else {
			this.setInventorySlotContents(i, input);
		}
	}

}
