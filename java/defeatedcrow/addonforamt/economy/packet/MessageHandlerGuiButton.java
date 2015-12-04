package defeatedcrow.addonforamt.economy.packet;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageHandlerGuiButton implements IMessageHandler<MessageGuiButton, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageGuiButton message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().playerEntity;
		return null;
	}

}
