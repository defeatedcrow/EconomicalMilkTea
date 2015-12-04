package defeatedcrow.addonforamt.economy.api;

import net.minecraft.entity.player.EntityPlayer;

public interface IMPStorageBlock {

	String getOwnerName();

	String getOwnerUUID();

	long getCurrentMP();

	long getMaxMP();

	boolean canHandleMP(EntityPlayer player);

	int reduceMP(EntityPlayer player, int amount, boolean isSimulate);

	int addMP(EntityPlayer player, int amount, boolean isSimulate);

	int getMode();

	void setMode(int i);

}
