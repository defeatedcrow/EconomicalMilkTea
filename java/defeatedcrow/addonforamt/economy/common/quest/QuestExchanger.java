package defeatedcrow.addonforamt.economy.common.quest;

import java.util.Map.Entry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.MathHelper;

public class QuestExchanger extends QuestTileBase {

	protected int currentMPStorage = 0;
	protected String owner = "none";
	protected String ownerID = "";

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);

		// アイテムの読み込み
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 10);
		inv.items = new ItemStack[inv.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < inv.getSizeInventory()) {
				inv.items[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		NBTTagList taglist2 = par1NBTTagCompound.getTagList("MpMap", 10);
		for (int i = 0; i < taglist2.tagCount(); i++) {
			NBTTagCompound tag = taglist2.getCompoundTagAt(i);
			String key = tag.getString("UserName");
			int val = tag.getInteger("UserMp");
			if (key.length() > 1) {
				mpMap.put(key, val);
			}
		}

		this.currentMPStorage = par1NBTTagCompound.getInteger("Mp");
		this.blockOwner = par1NBTTagCompound.getString("BlockOwner");
		this.currentOwner = par1NBTTagCompound.getString("Owner");
		this.blockOwnerID = par1NBTTagCompound.getString("BlockOwnerID");
		this.currentOwnerID = par1NBTTagCompound.getString("CurrentOwnerID");
		this.Mode = par1NBTTagCompound.getByte("Mode");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);

		// 燃焼時間や調理時間などの書き込み
		par1NBTTagCompound.setInteger("Mp", this.currentMPStorage);
		par1NBTTagCompound.setString("BlockOwner", this.blockOwner);
		par1NBTTagCompound.setString("Owner", this.currentOwner);
		par1NBTTagCompound.setString("BlockOwnerID", this.blockOwnerID);
		par1NBTTagCompound.setString("CurrentOwnerID", this.currentOwnerID);
		par1NBTTagCompound.setByte("Mode", (byte) this.Mode);

		// アイテムの書き込み
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < inv.getSizeInventory(); ++i) {
			if (inv.items[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				inv.items[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		par1NBTTagCompound.setTag("Items", nbttaglist);

		// MP保管リスト
		NBTTagList taglist2 = new NBTTagList();
		for (Entry ent : mpMap.entrySet()) {
			String n = (String) ent.getKey();
			int mp = (Integer) ent.getValue();

			NBTTagCompound tag = new NBTTagCompound();
			tag.setString("UserName", n);
			tag.setInteger("UserMp", mp);
			taglist2.appendTag(tag);
		}
		par1NBTTagCompound.setTag("MpMap", taglist2);

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

	/* === ISidedInventory === */

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	// スロット数
	@Override
	public int getSizeInventory() {
		return this.displayItems.length;
	}

	// インベントリ内の任意のスロットにあるアイテムを取得
	@Override
	public ItemStack getStackInSlot(int par1) {
		par1 = MathHelper.clamp_int(par1, 0, this.getSizeInventory() - 1);
		return this.displayItems[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2) {
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
		par1 = MathHelper.clamp_int(par1, 0, this.getSizeInventory() - 1);
		if (this.displayItems[par1] != null) {
			ItemStack itemstack = this.displayItems[par1];
			this.displayItems[par1] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	// インベントリ内のスロットにアイテムを入れる
	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
	}

	// インベントリの名前
	@Override
	public String getInventoryName() {
		return "OrderTileInventory";
	}

	// 多言語対応かどうか
	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	// インベントリ内のスタック限界値
	@Override
	public int getInventoryStackLimit() {
		return 1;// 1個ずつ
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
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
		return false;
	}

	// ホッパーにアイテムの受け渡しをする際の優先度
	@Override
	public int[] getAccessibleSlotsFromSide(int par1) {
		return new int[] {};
	}

	// ホッパーからアイテムを入れられるかどうか
	@Override
	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3) {
		return false;
	}

	// 隣接するホッパーにアイテムを送れるかどうか
	@Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3) {
		return false;
	}

}
