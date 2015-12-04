package defeatedcrow.addonforamt.economy.common.quest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.MathHelper;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.api.IMPStorageBlock;
import defeatedcrow.addonforamt.economy.api.OrderType;
import defeatedcrow.addonforamt.economy.plugin.mce.MPHandler;

public class QuestExchanger extends QuestTileBase implements IMPStorageBlock {

	protected long longMP = 0L;
	protected long maxStorage = 99999900L;
	protected String owner = "none";
	protected String ownerID = "";
	protected int mode = 0;
	private long lastMP = 0L;

	private int[] keep = {
			0,
			0,
			0,
			0 };

	// メイン処理部分

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			onServerUpdate();
		}
		super.updateEntity();
	}

	private void onServerUpdate() {
		if (lastMP != longMP) {
			lastMP = longMP;
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}

	private void onInvUpdate() {
		// TODO
	}

	@Override
	protected void onTrade(OrderType type, int count) {
		int slot = type.getSlot();
		ItemStack disp = displayItems[slot];
		int rew = rewards[slot];
		int req = requires[slot];
		int cur = this.keep[slot];
		if (disp == null || req == 0)
			return;

		if (cur > 0) {
			float f = cur * 1.0F / req;
			// f > 0.5f で成功判定
			// TODO
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

		for (int j = 0; j < 4; j++) {
			this.keep[j] = tag.getShort("Keep_" + j);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		// 燃焼時間や調理時間などの書き込み
		tag.setLong("MpL", this.longMP);
		tag.setString("Owner", this.owner);
		tag.setString("OwnerID", this.ownerID);
		tag.setByte("Mode", (byte) this.mode);

		for (int j = 0; j < 4; j++) {
			tag.setShort("Keep_" + j, (short) this.keep[j]);
		}

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
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		this.writeToNBT(nbtTagCompound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbtTagCompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());
	}

	/* ========== 以下、ISidedInventoryのメソッド ========== */

	/* baseが持っているinventoryはアクセス不可なので、スロット番号から判定して条件わけする */

	public ItemStack[] inv = new ItemStack[9];

	// スロット数
	@Override
	public int getSizeInventory() {
		return this.displayItems.length + inv.length;
	}

	// インベントリ内の任意のスロットにあるアイテムを取得
	@Override
	public ItemStack getStackInSlot(int i) {
		if (i < 4)
			return super.getStackInSlot(i);
		else if (i < 13)
			return this.inv[i - 4];
		else
			return null;
	}

	@Override
	public ItemStack decrStackSize(int i, int num) {
		if (i < 4)
			return super.decrStackSize(i, num);
		else if (i < 13) {
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
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		i = MathHelper.clamp_int(i, 0, this.getSizeInventory() - 1);
		if (i < 4) {
			return super.getStackInSlotOnClosing(i);
		} else {
			i -= 4;
			if (this.inv[i] != null) {
				ItemStack itemstack = this.inv[i];
				this.inv[i] = null;
				return itemstack;
			} else {
				return null;
			}
		}
	}

	// インベントリ内のスロットにアイテムを入れる
	@Override
	public void setInventorySlotContents(int i, ItemStack stack) {
		i = MathHelper.clamp_int(i, 0, this.getSizeInventory() - 1);
		if (i < 4) {
			super.setInventorySlotContents(i, stack);
			return;
		} else {
			i -= 4;
			this.inv[i] = stack;

			if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
				stack.stackSize = this.getInventoryStackLimit();
			}
		}
	}

	// インベントリの名前
	@Override
	public String getInventoryName() {
		return "ExchangerTileInventory";
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
	public boolean isItemValidForSlot(int i, ItemStack stack) {
		return i > 4;
	}

	// ホッパーにアイテムの受け渡しをする際の優先度
	@Override
	public int[] getAccessibleSlotsFromSide(int par1) {
		return new int[] {
				4,
				5,
				6,
				7,
				8,
				9,
				10,
				11,
				12 };
	}

	// ホッパーからアイテムを入れられるかどうか
	@Override
	public boolean canInsertItem(int i, ItemStack stack, int side) {
		return true;
	}

	// 隣接するホッパーにアイテムを送れるかどうか
	@Override
	public boolean canExtractItem(int i, ItemStack stack, int side) {
		return false;
	}

	// IInventoryにはないけど作っておくと便利なので
	protected void incrStackInSlot(int i, ItemStack input) {
		i = MathHelper.clamp_int(i, 0, this.getSizeInventory() - 1);
		if (i < 4) {
			return;
		} else {
			i -= 4;
			if (i < this.getSizeInventory() && input != null && this.inv[i] != null) {
				if (this.inv[i].getItem() == input.getItem() && this.inv[i].getItemDamage() == input.getItemDamage()) {
					this.inv[i].stackSize += input.stackSize;
					if (this.inv[i].stackSize > this.getInventoryStackLimit()) {
						this.inv[i].stackSize = this.getInventoryStackLimit();
					}
				}
			} else {
				this.setInventorySlotContents(i, input);
			}
		}
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
		// 預け入れ不可
		return 0;
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

}