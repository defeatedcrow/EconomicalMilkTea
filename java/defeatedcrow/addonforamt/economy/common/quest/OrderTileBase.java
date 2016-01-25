package defeatedcrow.addonforamt.economy.common.quest;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
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
import net.minecraft.world.biome.BiomeGenBase;
import defeatedcrow.addonforamt.economy.EMTLogger;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.api.RecipeManagerEMT;
import defeatedcrow.addonforamt.economy.api.order.IOrder;
import defeatedcrow.addonforamt.economy.api.order.OrderBiome;
import defeatedcrow.addonforamt.economy.api.order.OrderSeason;
import defeatedcrow.addonforamt.economy.api.order.OrderType;
import defeatedcrow.addonforamt.economy.util.TimeUtil;

public abstract class OrderTileBase extends TileEntity implements ISidedInventory {

	// 表示用データ
	public ItemStack[] displayItems = new ItemStack[4];

	public int[] requires = {
			0,
			0,
			0,
			0 };

	public int[] rewards = {
			0,
			0,
			0,
			0 };

	public int[] start = {
			0,
			0,
			0,
			0 };

	public int[] id = {
			0,
			0,
			0,
			0 };

	public String[] name = {
			"dcs.emt.ordername.null",
			"dcs.emt.ordername.null",
			"dcs.emt.ordername.null",
			"dcs.emt.ordername.null" };

	public int lastDay = 0;
	public int lastWeek = 0;
	public int lastSeason = 0;
	public int lastYear = 0;

	private int cool = 0;

	public OrderTileBase() {
		cool = 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		// アイテムの読み込み
		NBTTagList nbttaglist = tag.getTagList("DispItems", 10);
		this.displayItems = new ItemStack[4];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");

			if (b0 >= 0 && b0 < this.displayItems.length) {
				this.displayItems[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		for (int j = 0; j < 4; j++) {
			this.requires[j] = tag.getShort("Require_" + j);
			this.rewards[j] = tag.getInteger("Reward_" + j);
			this.start[j] = tag.getInteger("Start_" + j);
			this.id[j] = tag.getInteger("OrderID_" + j);
			this.name[j] = tag.getString("OrderName_" + j);
		}

		this.lastDay = tag.getInteger("LDay");
		this.lastWeek = tag.getInteger("LWeek");
		this.lastSeason = tag.getInteger("LSeason");
		this.lastYear = tag.getInteger("LYear");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		// 燃焼時間や調理時間などの書き込み
		for (int j = 0; j < 4; j++) {
			tag.setShort("Require_" + j, (short) this.requires[j]);
			tag.setInteger("Reward_" + j, this.rewards[j]);
			tag.setInteger("Start_" + j, this.start[j]);
			tag.setInteger("OrderID_" + j, this.id[j]);
			tag.setString("OrderName_" + j, this.name[j]);
		}

		tag.setInteger("LDay", this.lastDay);
		tag.setInteger("LWeek", this.lastWeek);
		tag.setInteger("LSeason", this.lastSeason);
		tag.setInteger("LYear", this.lastYear);

		// アイテムの書き込み
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.displayItems.length; ++i) {
			if (this.displayItems[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.displayItems[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		tag.setTag("DispItems", nbttaglist);

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

	/* === getter, setter === */

	public int getCurrentDay() {
		return TimeUtil.getDay(worldObj);
	}

	public int getCurrentWeek() {
		return TimeUtil.getWeek(worldObj);
	}

	public int getCurrentSeason() {
		return TimeUtil.getSeason(worldObj);
	}

	public int getCurrentYear() {
		return TimeUtil.getYear(worldObj);
	}

	public void setDisplayItem(int slot, ItemStack item) {
		this.displayItems[slot] = item;
	}

	public ItemStack getDosplayItem(int slot) {
		return this.displayItems[slot];
	}

	public void setRequire(int slot, int amo) {
		this.requires[slot] = amo;
	}

	public int getReuire(int slot) {
		return this.requires[slot];
	}

	public void setReward(int slot, int amo) {
		this.rewards[slot] = amo;
	}

	public int getReward(int slot) {
		return this.rewards[slot];
	}

	public void setStartDay(int slot, int amo) {
		this.start[slot] = amo;
	}

	public int getStartDay(int slot) {
		return this.start[slot];
	}

	public void setOrderID(int slot, int i) {
		this.id[slot] = i;
	}

	public int getOrderID(int slot) {
		return this.id[slot];
	}

	public void setOrderName(int slot, String n) {
		this.name[slot] = n;
	}

	public String getOrderName(int slot) {
		return this.name[slot];
	}

	/* === on update === */

	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			boolean flag = false;
			int day = this.getCurrentDay();
			int week = this.getCurrentWeek();
			int season = this.getCurrentSeason();
			int year = this.getCurrentYear();
			OrderSeason s = OrderSeason.getSeason(season);
			if (s == null)
				s = OrderSeason.NONE;

			// 夜明けに一度だけ判定を行う
			if (cool == 100 && (this.displayItems[0] == null || (TimeUtil.isDayTime(worldObj) && lastDay != day))) {
				// 前orderの納品とdateの更新
				this.onTrade(OrderType.SINGLE, lastDay);
				this.start[0] = day;
				this.setNewOrder(OrderType.SINGLE, day);
				lastDay = day;
				flag = true;
				EMTLogger.debugInfo("received a new single order.");

				if (this.displayItems[1] == null || lastWeek != week) {
					this.onTrade(OrderType.SHORT, lastWeek);
					this.start[1] = week * 7;
					this.setNewOrder(OrderType.SHORT, week);
					lastWeek = week;
					EMTLogger.debugInfo("received a new short order.");
				}

				if (this.displayItems[2] == null || lastSeason != season) {
					this.onTrade(OrderType.MIDDLE, lastSeason);
					this.start[2] = season * 30 + year * 120;
					this.setNewOrder(OrderType.MIDDLE, season);
					lastSeason = season;
					EMTLogger.debugInfo("received a new middle order.");
				}

				if (this.displayItems[3] == null || lastYear != year) {
					this.onTrade(OrderType.LONG, 1);
					this.start[3] = year * 120;
					this.setNewOrder(OrderType.LONG, year);
					lastYear = year;
					EMTLogger.debugInfo("received a new long order.");
				}
			}

			if (flag) {
				this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				EMTLogger.debugInfo("current date: day " + day + ", week " + week + ", season " + s + ", year " + year);
			}

			if (cool < 100) {
				cool++;
			} else {
				cool = 0;
			}
		}
	}

	// 各Tileで実装する
	protected void onTrade(OrderType type, int count) {
	}

	// 表示用のItemStackやint等の更新
	protected void setNewOrder(OrderType type, int count) {
		int slot = type.getSlot();
		OrderBiome biome = this.getBiome();
		IOrder order = this.getOrder(biome, type, count);
		if (order != null) {
			this.requires[slot] = order.getRequestNum();
			this.rewards[slot] = order.getReward();
			this.id[slot] = order.getID();
			this.name[slot] = order.getName();
			ItemStack input = new ItemStack(EcoMTCore.dummyItem, 1, 0);
			if (!order.getProcessedRequests().isEmpty()) {
				ItemStack ret = order.getProcessedRequests().get(0);
				if (ret != null && ret.getItem() != null) {
					input = ret;
					input.stackSize = 1;
				}
			} else if (order.getRequest() instanceof String) {
				String ore = (String) order.getRequest();
				NBTTagCompound tag = new NBTTagCompound();
				tag.setString("OreName", ore);
				input.setTagCompound(tag);
			}
			this.displayItems[slot] = input;
			EMTLogger.debugInfo("New Order: item " + input.getDisplayName() + ", require " + requires[slot]
					+ ", reward " + rewards[slot] + ", ID " + id[slot]);
		} else {
			// ダミーを渡す
			IOrder dummy = RecipeManagerEMT.orderRegister.getDummyOrder(type);
			this.requires[slot] = dummy.getRequestNum();
			this.rewards[slot] = dummy.getReward();
			this.id[slot] = 0;
			this.name[slot] = dummy.getName();
			this.displayItems[slot] = new ItemStack(Items.wheat, 1, 0);
		}
	}

	/* === order === */

	public OrderBiome getBiome() {
		BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(xCoord, zCoord);
		return OrderBiome.getType(biome);
	}

	protected int getKey() {
		int x = this.xCoord >> 4;
		int z = this.zCoord >> 4;
		long seed = worldObj.getSeed() & 32767;
		return (int) seed + x * 7 + z * 13;
	}

	public IOrder getOrder(OrderBiome biome, OrderType type, int count) {
		ArrayList<IOrder> pool = new ArrayList<IOrder>();
		ArrayList<IOrder> list = new ArrayList<IOrder>();

		int season = this.getCurrentSeason();

		switch (type) {
		case SINGLE:
			pool.addAll(RecipeManagerEMT.orderRegister.getSingleOrders());
			break;
		case SHORT:
			pool.addAll(RecipeManagerEMT.orderRegister.getShortOrders());
			break;
		case MIDDLE:
			pool.addAll(RecipeManagerEMT.orderRegister.getMiddleOrders());
			break;
		case LONG:
			pool.addAll(RecipeManagerEMT.orderRegister.getLongOrders());
			break;
		default:
			pool.addAll(RecipeManagerEMT.orderRegister.getSingleOrders());
		}

		if (pool.isEmpty())
			return null;

		for (IOrder o : pool) {
			boolean b = false;
			if (o.getBiome() == biome || o.getBiome() == OrderBiome.NONE) {
				b = true;
			}
			if (b && (o.getSeason() == OrderSeason.NONE || o.getSeason().getNum() == season)) {
				list.add(o);
			}
		}
		EMTLogger.debugInfo("list size:" + list.size());
		if (!list.isEmpty()) {

			if (list.size() > 1) {
				int i = list.size() - 1;
				int key = Math.abs(this.getKey() + count);
				int getID = key % i;
				EMTLogger.debugInfo("debug : num = " + getID);
				return list.get(getID);
			} else {
				return list.get(0);
			}
		}

		return null;
	}

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
		return StatCollector.translateToLocal("dcs.emt.orderbasetile.gui");
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
