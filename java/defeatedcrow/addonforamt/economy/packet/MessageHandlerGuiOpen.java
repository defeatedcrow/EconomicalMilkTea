package defeatedcrow.addonforamt.economy.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import defeatedcrow.addonforamt.economy.EMTLogger;
import defeatedcrow.addonforamt.economy.common.block.IOpenChecker;

public class MessageHandlerGuiOpen implements IMessageHandler<MessageGuiOpen, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageGuiOpen message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().playerEntity;
		if (player != null) {
			World world = player.worldObj;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile != null && tile instanceof IOpenChecker) {
				IOpenChecker box = (IOpenChecker) tile;
				box.setOpen(message.data);
				EMTLogger.debugInfo("close");
			}
		}
		return null;
	}

}
