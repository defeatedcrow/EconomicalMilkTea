package defeatedcrow.addonforamt.economy.packet;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.common.quest.TileSafetyBox;

public class MessageHandlerWithdrawAsBill implements IMessageHandler<MessageWithdrawAsBill, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageWithdrawAsBill message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().playerEntity;
		if (player != null) {
			World world = player.worldObj;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile != null && tile instanceof TileSafetyBox) {
				TileSafetyBox box = (TileSafetyBox) tile;
				int reduce = box.reduceMP(player, message.data, true);
				int mB = 0;
				int kB = 0;
				if (reduce >= 500000) {
					mB = reduce / 500000;
					reduce -= mB * 500000;
				}
				if (reduce >= 10000) {
					kB = reduce / 10000;
					reduce -= kB * 10000;
				}
				reduce = kB * 10000 + mB * 500000;
				if (reduce > 0) {
					long l = box.getCurrentMP() - reduce;
					box.setCurrentMP(l);
					if (mB > 0) {
						ItemStack bill1 = new ItemStack(EcoMTCore.yukiti, mB, 1);
						EntityItem drop1 = new EntityItem(world, player.posX, player.posY, player.posZ, bill1);
						world.spawnEntityInWorld(drop1);
					}
					if (kB > 0) {
						ItemStack bill2 = new ItemStack(EcoMTCore.yukiti, kB, 0);
						EntityItem drop2 = new EntityItem(world, player.posX, player.posY, player.posZ, bill2);
						world.spawnEntityInWorld(drop2);
					}
				}
			}
		}
		return null;
	}

}
