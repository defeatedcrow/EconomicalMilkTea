package defeatedcrow.addonforamt.economy.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import defeatedcrow.addonforamt.economy.api.IMPStorageBlock;

public class MessageHandlerGuiWithdraw implements IMessageHandler<MessageWithdrawButton, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageWithdrawButton message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().playerEntity;
		if (player != null) {
			World world = player.worldObj;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile != null && tile instanceof IMPStorageBlock) {
				IMPStorageBlock box = (IMPStorageBlock) tile;
				if (message.deposit) {
					box.addMP(player, message.data, false);
				} else {
					box.reduceMP(player, message.data, false);
				}
			}
		}
		return null;
	}

}
