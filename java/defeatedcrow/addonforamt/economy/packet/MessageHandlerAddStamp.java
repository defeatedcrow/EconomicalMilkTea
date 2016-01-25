package defeatedcrow.addonforamt.economy.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import defeatedcrow.addonforamt.economy.common.item.ItemGiftCatalog;

public class MessageHandlerAddStamp implements IMessageHandler<MessageAddStamp, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageAddStamp message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().playerEntity;
		if (player != null) {
			World world = player.worldObj;
			int add = message.data;
			boolean reduce = message.red;
			ItemStack held = player.inventory.getCurrentItem();
			if (held != null && held.getItem() instanceof ItemGiftCatalog) {
				short s = 0;
				NBTTagCompound tag = new NBTTagCompound();
				if (held.hasTagCompound()) {
					tag = held.getTagCompound();
				}
				if (tag.hasKey("emt.stamp")) {
					s = tag.getShort("emt.stamp");
				}
				if (reduce)
					s -= add;
				else
					s += add;
				if (s < 0)
					s = 0;

				ItemStack result = held.copy();
				tag.setShort("emt.stamp", s);
				result.setTagCompound(tag);
				held.setTagCompound(tag);

				player.inventory.mainInventory[player.inventory.currentItem] = result;
				player.inventory.markDirty();
			}
		}
		return null;
	}

}
