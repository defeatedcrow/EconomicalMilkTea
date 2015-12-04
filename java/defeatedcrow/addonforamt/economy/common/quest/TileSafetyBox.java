package defeatedcrow.addonforamt.economy.common.quest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.api.IMPCoin;
import defeatedcrow.addonforamt.economy.api.IMPStorageBlock;
import defeatedcrow.addonforamt.economy.plugin.mce.MPHandler;

public class TileSafetyBox extends TileEntity implements IMPStorageBlock, ISidedInventory {

	protected long longMP = 0L;
	protected long maxStorage = 9999999900L;
	protected String owner = "none";
	protected String ownerID = "";
	protected int mode = 0;
	private long lastMP = 0L;

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			onServerUpdate();
		}
	}

	private void onServerUpdate() {
		if (lastMP != longMP) {
			lastMP = longMP;
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		// アイテムの読み込み
		NBTTagList nbttaglist = tag.getTagList("InvItems", 10);
		inv = new ItemStack[inv.length];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < inv.length) {
				inv[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.longMP = tag.getLong("MpL");
		this.owner = tag.getString("Owner");
		this.ownerID = tag.getString("OwnerID");
		this.mode = tag.getByte("Mode");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		// 燃焼時間や調理時間などの書き込み
		tag.setLong("MpL", this.longMP);
		tag.setString("Owner", this.owner);
		tag.setString("OwnerID", this.ownerID);
		tag.setByte("Mode", (byte) this.mode);

		// アイテムの書き込み
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < inv.length; ++i) {
			if (inv[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				inv[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		tag.setTag("InvItems", nbttaglist);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());
	}

	// ドロップアイテムとのやりとり

	public NBTTagCompound getNBT(NBTTagCompound tag) {

		// 燃焼時間や調理時間などの書き込み
		tag.setLong("MpL", this.longMP);
		tag.setString("Owner", this.owner);
		tag.setString("OwnerID", this.ownerID);
		tag.setByte("Mode", (byte) this.mode);

		// アイテムの書き込み
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < inv.length; ++i) {
			if (inv[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				inv[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		tag.setTag("InvItems", nbttaglist);
		return tag;
	}

	public void setNBT(NBTTagCompound tag) {
		// アイテムの読み込み
		NBTTagList nbttaglist = tag.getTagList("InvItems", 10);
		inv = new ItemStack[inv.length];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < inv.length) {
				inv[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		this.longMP = tag.getLong("MpL");
		this.owner = tag.getString("Owner");
		this.ownerID = tag.getString("OwnerID");
		this.mode = tag.getByte("Mode");
	}

	/* === IMPStorageBlock === */

	@Override
	public String getOwnerName() {
		return owner;
	}

	@Override
	public String getOwnerUUID() {
		return ownerID;
	}

	@Override
	public long getCurrentMP() {
		return longMP;
	}

	@Override
	public long getMaxMP() {
		return maxStorage;
	}

	// OP判定の場合、鯖蔵で結果が異なるので注意すること
	@Override
	public boolean canHandleMP(EntityPlayer player) {
		if (player == null)
			return false;
		String name = player.getCommandSenderName();
		String id = player.getUniqueID().toString();
		if (id.equalsIgnoreCase(ownerID))
			return true;
		else if (EcoMTCore.proxy.getOP(name))
			return true;
		else if (EcoMTCore.debug)
			return true;
		return false;
	}

	@Override
	public int reduceMP(EntityPlayer player, int amount, boolean sim) {
		// 100MP単位でしかやりとりをしない
		if (player.worldObj.isRemote || !this.canHandleMP(player))
			return 0;
		int p = MPHandler.INSTANCE.addPlayerMP(player, amount, true);
		if (amount > longMP)
			amount = (int) longMP;
		if (amount > p)
			amount = p;
		int red = (amount / 100) * 100;
		if (!sim) {
			longMP -= red;
			MPHandler.INSTANCE.addPlayerMP(player, red, false);
		}
		return red;
	}

	@Override
	public int addMP(EntityPlayer player, int amount, boolean sim) {
		// 100MP単位でしかやりとりをしない
		if (player.worldObj.isRemote || !this.canHandleMP(player))
			return 0;
		int p = MPHandler.INSTANCE.reducePlayerMP(player, amount, true);
		long i = maxStorage - longMP;
		if (amount > i)
			amount = (int) i;
		if (amount > p)
			amount = p;
		int get = (amount / 100) * 100;
		if (!sim) {
			longMP += get;
			MPHandler.INSTANCE.reducePlayerMP(player, get, false);
		}
		return get;
	}

	@Override
	public int getMode() {
		return mode;
	}

	@Override
	public void setMode(int i) {
		mode = i;
	}

	/* === getter setter === */

	public void setOwnerName(String s) {
		owner = s;
	}

	public void setOwnerUUID(String s) {
		ownerID = s;
	}

	public void setCurrentMP(long mp) {
		longMP = mp;
	}

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	public ItemStack[] inv = new ItemStack[27];

	// スロット数
	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	// インベントリ内の任意のスロットにあるアイテムを取得
	@Override
	public ItemStack getStackInSlot(int i) {
		if (i < inv.length)
			return this.inv[i];
		else
			return null;
	}

	@Override
	public ItemStack decrStackSize(int i, int num) {
		if (i < 0 || i >= this.getSizeInventory())
			return null;
		if (this.inv[i] != null) {
			ItemStack itemstack;

			if (this.inv[i].stackSize <= num) {
				itemstack = this.inv[i];
				this.inv[i] = null;
				return itemstack;
			} else {
				itemstack = this.inv[i].splitStack(num);
				if (this.inv[i].stackSize == 0) {
					this.inv[i] = null;
				}
				return itemstack;
			}
		} else
			return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		i = MathHelper.clamp_int(i, 0, this.getSizeInventory() - 1);
		if (i < inv.length) {
			if (this.inv[i] != null) {
				ItemStack itemstack = this.inv[i];
				this.inv[i] = null;
				return itemstack;
			}
		}
		return null;
	}

	// インベントリ内のスロットにアイテムを入れる
	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		i = MathHelper.clamp_int(i, 0, this.getSizeInventory() - 1);
		if (i < 0 || i >= this.getSizeInventory()) {
			return;
		} else {
			this.inv[i] = stack;

			if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
				stack.stackSize = this.getInventoryStackLimit();
			}
		}
	}

	// インベントリの名前
	@Override
	public String getInventoryName() {
		return "emt.SafetyBoxInventory";
	}

	// 多言語対応かどうか
	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	// インベントリ内のスタック限界値
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
		super.markDirty();
	}

	// par1EntityPlayerがTileEntityを使えるかどうか
	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		if (!this.canHandleMP(player))
			return false;
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player
				.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return stack != null && stack.getItem() instanceof IMPCoin;
	}

	// ホッパーにアイテムの受け渡しをする際の優先度
	@Override
	public int[] getAccessibleSlotsFromSide(int par1) {
		return new int[] {};
	}

	// ホッパーからアイテムを入れられるかどうか
	@Override
	public boolean canInsertItem(int i, ItemStack stack, int side) {
		return false;
	}

	// 隣接するホッパーにアイテムを送れるかどうか
	@Override
	public boolean canExtractItem(int i, ItemStack stack, int side) {
		return false;
	}

}
