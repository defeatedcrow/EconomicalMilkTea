package defeatedcrow.addonforamt.economy.packet;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class EMTPacketHandler {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("DCsEcoMT");

	public static void init() {
		INSTANCE.registerMessage(MessageHandlerGuiButton.class, MessageGuiButton.class, 0, Side.SERVER);
		INSTANCE.registerMessage(MessageHandlerGuiWithdraw.class, MessageWithdrawButton.class, 1, Side.SERVER);
		INSTANCE.registerMessage(MessageHandlerUpdateMP.class, MessageUpdateMP.class, 2, Side.CLIENT);
	}
}
