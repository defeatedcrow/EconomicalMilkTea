package defeatedcrow.addonforamt.economy.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.LoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import defeatedcrow.addonforamt.economy.EMTLogger;
import defeatedcrow.addonforamt.economy.EcoMTCore;

/*
 * Original code was made by shift02.
 * */
public class ChunkLoaderController implements LoadingCallback {

	private static ChunkLoaderController instance;

	// 稼働中のチケット
	protected static final HashMap<EMTCoord, Ticket> ticketList = new HashMap<EMTCoord, Ticket>();
	// 発行済みの全チケット
	protected static final ArrayList<Ticket> allTicket = new ArrayList<Ticket>();

	// protected static final HashMap<EMTCood, Integer> posList = new HashMap<EMTCood, Integer>();

	private ChunkLoaderController() {
	}

	public static ChunkLoaderController getInstance() {
		if (instance == null) {
			instance = new ChunkLoaderController();
		}
		return instance;
	}

	public void preInit(FMLPreInitializationEvent event) {
		ForgeChunkManager.setForcedChunkLoadingCallback(EcoMTCore.instance, instance);
	}

	public void load(FMLInitializationEvent event) {
	}

	public void postInit(FMLPostInitializationEvent event) {
	}

	@Override
	public void ticketsLoaded(List<Ticket> tickets, World world) {
		for (Ticket t : tickets) {

			if (this.isBlockTicket(t)) {

				int x = t.getModData().getInteger("x");
				int y = t.getModData().getInteger("y");
				int z = t.getModData().getInteger("z");

				int i = t.getModData().getInteger("i");
				int j = t.getModData().getInteger("j");

				EMTCoord cood = new EMTCoord(i, j);
				EMTPos pos = new EMTPos(x, y, z);

				if (this.getBlock(t, world) instanceof IChunkBlock) {
					IChunkBlock block = (IChunkBlock) this.getBlock(t, world);
					if (block.canLoad(world, x, y, z)) {
						setBlockTicket(world, x, y, z, i, j);
					} else {
						deleteBlockTicket(world, x, y, z, i, j);
					}
				} else {
					deleteBlockTicket(world, x, y, z, i, j);
				}
			}
		}
	}

	/** 指定した座礁のブロックをChunkLoaderとして起動する */
	public static boolean setBlockTicket(World world, int x, int y, int z, int i, int j) {

		EMTCoord cood = new EMTCoord(i, j);
		EMTPos pos = new EMTPos(x, y, z);
		boolean b = false;

		Ticket t = ForgeChunkManager.requestTicket(EcoMTCore.instance, world, Type.NORMAL);

		if (t == null)
			return false;

		setBlockType(t);
		setBlock(t, x, y, z, i, j);
		ticketList.put(cood, t);
		ForgeChunkManager.forceChunk(t, world.getChunkFromBlockCoords(i, j).getChunkCoordIntPair());
		EMTLogger.debugInfo("Succeed to register chunkloader. Cood: " + i + ", " + j);
		return true;
	}

	/** 指定した座礁のChunkLoaderを停止する */
	public static void deleteBlockTicket(World world, int x, int y, int z, int i, int j) {

		EMTCoord cood = new EMTCoord(i, j);
		EMTPos pos = new EMTPos(x, y, z);

		if (ticketList.containsKey(cood)) {

			if (!ForgeChunkManager.getPersistentChunksFor(ticketList.get(cood).world).isEmpty()) {
				ForgeChunkManager.unforceChunk(ticketList.get(cood), world.getChunkFromBlockCoords(i, j)
						.getChunkCoordIntPair());
				ForgeChunkManager.releaseTicket(ticketList.get(cood));
			}

			ticketList.remove(cood);
			EMTLogger.debugInfo("Succeed to delete chunkloader. Cood: " + i + ", " + j);
		}
	}

	public static void setBlockType(Ticket ticket) {
		ticket.getModData().setString("type", "block");
	}

	public boolean isBlockTicket(Ticket ticket) {
		return ticket.getModData().getString("type").equals("block");
	}

	public static void setBlock(Ticket ticket, int x, int y, int z, int i, int j) {
		ticket.getModData().setInteger("x", x);
		ticket.getModData().setInteger("y", y);
		ticket.getModData().setInteger("z", z);
		ticket.getModData().setInteger("i", i);
		ticket.getModData().setInteger("j", j);
	}

	public Block getBlock(Ticket ticket, World world) {
		return world.getBlock(ticket.getModData().getInteger("x"), ticket.getModData().getInteger("y"), ticket
				.getModData().getInteger("z"));
	}

	// Ticketの使い回し
	private static Ticket getTicketFromList(EMTCoord cood) {
		if (allTicket.isEmpty())
			return null;
		for (Ticket t : allTicket) {
			int i = t.getModData().getInteger("i");
			int j = t.getModData().getInteger("j");

			EMTCoord cood1 = new EMTCoord(i, j);

			if (cood1.equals(cood)) {
				return t;
			}
		}
		return null;
	}

	/**
	 * ChunkLoaderに実装するinterface<br>
	 * worldがロードされた時に呼ばれる。trueを返すとChunkLoaderが始まる。
	 */
	public interface IChunkBlock {

		public boolean canLoad(World world, int x, int y, int z);

	}

}
