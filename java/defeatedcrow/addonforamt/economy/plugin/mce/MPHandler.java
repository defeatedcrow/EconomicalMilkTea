package defeatedcrow.addonforamt.economy.plugin.mce;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import shift.mceconomy2.api.MCEconomyAPI;

/* 中継クラス */
public class MPHandler {

	private MPHandler() {
	}

	public static int max = 9999900;

	public static final MPHandler INSTANCE = new MPHandler();

	public int getSellMP(ItemStack item) {
		if (item == null)
			return -1;
		int ret = -1;
		if (MCEconomyAPI.hasPurchase(item)) {
			ret = MCEconomyAPI.getPurchase(item);
			if (ret == 0)
				ret = 1;
			ret *= item.stackSize;
		}
		return ret;
	}

	public int getPlayerMP(EntityPlayer player) {
		return MCEconomyAPI.getPlayerMP(player);
	}

	public int addPlayerMP(EntityPlayer player, int amount, boolean sim) {
		if (player.worldObj.isRemote)
			return 0;
		int current = getPlayerMP(player);
		long i = max - current;
		if (amount > i)
			amount = (int) i;
		int get = (amount / 100) * 100;
		get = MCEconomyAPI.addPlayerMP(player, get, sim);
		return get;
	}

	public int reducePlayerMP(EntityPlayer player, int amount, boolean sim) {
		if (player.worldObj.isRemote)
			return 0;
		int current = getPlayerMP(player);
		if (amount > current)
			amount = current;
		int red = (amount / 100) * 100;
		red = MCEconomyAPI.reducePlayerMP(player, red, sim);
		return red;
	}

}
