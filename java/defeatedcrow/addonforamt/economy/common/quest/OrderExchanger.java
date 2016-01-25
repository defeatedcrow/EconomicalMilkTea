package defeatedcrow.addonforamt.economy.common.quest;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.eventhandler.Event.Result;
import defeatedcrow.addonforamt.economy.EMTLogger;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.api.IMPStorageBlock;
import defeatedcrow.addonforamt.economy.api.RecipeManagerEMT;
import defeatedcrow.addonforamt.economy.api.order.IOrder;
import defeatedcrow.addonforamt.economy.api.order.IRewardItem;
import defeatedcrow.addonforamt.economy.api.order.OnOrderExchangeEvent;
import defeatedcrow.addonforamt.economy.api.order.OrderType;
import defeatedcrow.addonforamt.economy.common.block.IOpenChecker;
import defeatedcrow.addonforamt.economy.plugin.mce.MPHandler;

public class OrderExchanger extends OrderTileBase implements IMPStorageBlock, IOpenChecker {

	protected long longMP = 0L;
	protected long maxStorage = 99999900L;
	protected String owner = "none";
	protected String ownerID = "";
	protected int mode = 0;
	private long lastMP = 0L;
	private int coolTime = 20;

	public boolean isOpen = false;
	private boolean lastOpen = false;
	private int openCount = 0;

	public int[] keep = {
			0,
			0,
			0,
			0 };

	// メイン処理部分

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			onInvUpdate();
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
		boolean b = false;
		// まずはこのTileのInventoryから回収
		for (int i = 4; i < 13; i++) {
			ItemStack slot = this.getStackInSlot(i);
			if (slot == null)
				continue;
			if (slot.getItem() == null || slot.getItem() instanceof IRewardItem) {
				continue;
			}
			for (int j = 0; j < 4; j++) {
				EMTLogger.debugInfo("checking! ID" + id[j]);
				IOrder order = RecipeManagerEMT.orderRegister.getOrderFromID(id[j], OrderType.getType(j));
				if (order == null)
					continue;
				Object check = order.getRequest();

				if (check == null)
					continue;
				if (check instanceof ItemStack) {
					ItemStack target = (ItemStack) check;
					EMTLogger.debugInfo("target : " + target.getDisplayName());
					EMTLogger.debugInfo("vs : " + slot.getDisplayName());
					if (slot.getItem() == target.getItem()
							&& (slot.getItemDamage() == target.getItemDamage() || target.getItemDamage() == 32767)) {
						int red = slot.stackSize;
						keep[j] += red;
						this.decrStackSize(i, red);
						this.markDirty();
						b = true;
						EMTLogger.debugInfo("matchItem +" + red);
						break;
					}
				} else if (check instanceof String) {
					String ore = (String) check;
					EMTLogger.debugInfo("target : " + ore);
					EMTLogger.debugInfo("vs : " + slot.getDisplayName());
					int target = OreDictionary.getOreID(ore);
					int[] get = OreDictionary.getOreIDs(slot);
					for (int k : get) {
						if (k == target) {
							int red = slot.stackSize;
							keep[j] += red;
							this.decrStackSize(i, red);
							this.markDirty();
							b = true;
							EMTLogger.debugInfo("matchItem +" + red);
							break;
						}
					}
				}
			}
		}

		// 周囲のインベントリ検知
		List<ItemStack> list = new ArrayList<ItemStack>();
		for (int j = 0; j < 4; j++) {
			IOrder order = RecipeManagerEMT.orderRegister.getOrderFromID(id[j], OrderType.getType(j));
			if (order == null)
				continue;
			Object check = order.getRequest();
			if (check == null)
				continue;
			if (check instanceof ItemStack) {
				ItemStack target = (ItemStack) check;
				list.add(target);
			} else if (check instanceof String) {
				String ore = (String) check;
				List<ItemStack> ores = OreDictionary.getOres(ore);
				if (ores != null && !ores.isEmpty()) {
					list.addAll(ores);
				}
			}

			if (coolTime > 0) {
				coolTime--;
			} else if (!list.isEmpty()) {
				coolTime = 20;
				for (int ix = -1; ix <= 1; ix++) {
					for (int iz = -1; iz <= 1; iz++) {
						if (ix == 0 && iz == 0)
							continue;
						TileEntity tile = worldObj.getTileEntity(xCoord + ix, yCoord, zCoord + iz);
						if (tile != null && tile instanceof IInventory) {
							IInventory targetInv = (IInventory) tile;
							if (targetInv instanceof ISidedInventory) {
								int[] slots = ((ISidedInventory) targetInv).getAccessibleSlotsFromSide(0);
								for (int l : slots) {
									ItemStack slot = targetInv.getStackInSlot(l);
									if (slot == null)
										continue;
									for (ItemStack target : list) {
										if (slot.getItem() == target.getItem()
												&& (slot.getItemDamage() == target.getItemDamage() || target
														.getItemDamage() == 32767)) {
											int red = slot.stackSize;
											keep[j] += red;
											targetInv.decrStackSize(l, red);
											this.markDirty();
											b = true;
											break;
										}
									}
								}
							} else {
								for (int l = 0; l < targetInv.getSizeInventory(); l++) {
									ItemStack slot = targetInv.getStackInSlot(l);
									if (slot == null)
										continue;
									for (ItemStack target : list) {
										if (slot.getItem() == target.getItem()
												&& (slot.getItemDamage() == target.getItemDamage() || target
														.getItemDamage() == 32767)) {
											int red = slot.stackSize;
											keep[j] += red;
											targetInv.decrStackSize(l, red);
											this.markDirty();
											b = true;
											break;
										}
									}
								}
							}
						}
					}
				}
			}
		}

		if (lastOpen != isOpen) {
			if (isOpen && openCount == 0) {
				openCount = 20;
			}
			lastOpen = isOpen;
			b = true;
			worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, getBlockType());
			worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord - 1, zCoord, getBlockType());
		}

		if (openCount > 0) {
			openCount--;
		} else {
			isOpen = false;
		}

		if (b) {
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}

	@Override
	protected void onTrade(OrderType type, int count) {
		int slot = type.getSlot();
		ItemStack disp = displayItems[slot];
		int rew = rewards[slot];
		int req = requires[slot];
		int cur = keep[slot];

		// いずれの結果でも累積アイテムカウントだけはリセットする
		keep[slot] = 0;

		if (disp == null || req == 0)
			return;

		float f = cur * 1.0F / req;
		// 0.5F以上で成功
		if (f > 0.5F) {
			// 報酬上限は5倍
			if (f > 5.0F) {
				f = 5.0F;
			}
			List<ItemStack> rewards = new ArrayList<ItemStack>(); // 追加報酬

			// good判定
			if (f >= 2.0F) {
				rew *= 1.2F;// 2割増し
				rewards.add(new ItemStack(EcoMTCore.stamp, type.getSlot() * 2 + 1, 0));
			}

			// まずEventをよんでおく
			OnOrderExchangeEvent event = new OnOrderExchangeEvent(worldObj, this, owner, disp, req, rew, rewards, f);

			MinecraftForge.EVENT_BUS.post(event);

			if (event.hasResult()) {
				if (event.getResult() == Result.DENY)
					return;
				else if (event.getResult() == Result.ALLOW) {
					rew = event.rewardMP;
					rewards = event.rewards;
					f = event.grade;
				}
			}

			// 最後に処理
			int add = (int) (rew * f);
			add = (add / 100) * 100;
			this.addMP(add);

			EMTLogger.debugInfo("Reward!: " + add + "MP, Bonus Item " + rewards.size());

			for (ItemStack item : rewards) {
				if (item == null || item.getItem() == null)
					continue;
				for (int i = 4; i < 13; i++) {
					if (this.incrStackInSlot(i, item)) {
						this.markDirty();
						break;
					} else
						continue;
				}
			}
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
		this.isOpen = tag.getBoolean("Open");

		for (int j = 0; j < 4; j++) {
			this.keep[j] = tag.getInteger("Keep_" + j);
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
		tag.setBoolean("Open", this.isOpen);

		for (int j = 0; j < 4; j++) {
			tag.setInteger("Keep_" + j, this.keep[j]);
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

	public int getKeep(int slot) {
		return keep[slot];
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
			i -= 4;
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
			if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
				stack.stackSize = this.getInventoryStackLimit();
			}
			this.inv[i] = stack;
		}
	}

	// インベントリの名前
	@Override
	public String getInventoryName() {
		return StatCollector.translateToLocal("dcs.emt.transaction.gui");
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
		return true;
	}

	// IInventoryにはないけど作っておくと便利なので
	protected boolean incrStackInSlot(int i, ItemStack input) {
		i = MathHelper.clamp_int(i, 0, this.getSizeInventory() - 1);
		if (i < 4) {
			return false;
		} else {
			i -= 4;
			if (i < this.getSizeInventory() && input != null) {
				if (this.inv[i] != null) {
					if (this.inv[i].getItem() == input.getItem()
							&& this.inv[i].getItemDamage() == input.getItemDamage()) {
						int get = this.inv[i].stackSize + input.stackSize;
						if (get <= this.getInventoryStackLimit()) {
							this.inv[i].stackSize += input.stackSize;
						}
						return true;
					} else {
						return false;
					}
				} else {
					this.setInventorySlotContents(i + 4, input);
					return true;
				}
			} else {
				this.setInventorySlotContents(i, input);
				return true;
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

		for (int j = 0; j < 4; j++) {
			tag.setInteger("Keep_", this.keep[j]);
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

		for (int j = 0; j < 4; j++) {
			this.keep[j] = tag.getInteger("Keep_" + j);
		}
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

	// プレイヤーからではなく、報酬によってMPを増加させる処理
	private void addMP(int amount) {
		this.longMP += amount;
		if (longMP > maxStorage) {
			longMP = maxStorage;
		}
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

	/* OpenChecker */

	@Override
	public boolean isOpen() {
		return this.isOpen;
	}

	@Override
	public void setOpen(boolean b) {
		this.isOpen = b;
		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, getBlockType());
		worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord - 1, zCoord, getBlockType());
	}

}
