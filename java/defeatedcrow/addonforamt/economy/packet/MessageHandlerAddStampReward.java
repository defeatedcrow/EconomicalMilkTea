package defeatedcrow.addonforamt.economy.packet;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import defeatedcrow.addonforamt.economy.EcoMTCore;

public class MessageHandlerAddStampReward implements IMessageHandler<MessageAddStampReward, IMessage> {

	@Override
	// IMessageHandlerのメソッド
	public IMessage onMessage(MessageAddStampReward message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().playerEntity;
		if (player != null) {
			World world = player.worldObj;
			int add = message.data;
			ItemStack rew = this.gerReward(add);
			if (rew != null) {
				EntityItem drop = new EntityItem(world, player.posX, player.posY + 0.25D, player.posZ, rew);
				if (rew.hasTagCompound()) {
					drop.getEntityItem().setTagCompound((NBTTagCompound) rew.getTagCompound().copy());
				}
				world.spawnEntityInWorld(drop);
			}
		}
		return null;
	}

	private ItemStack gerReward(int data) {
		switch (data) {
		case 0:
			return new ItemStack(Items.iron_pickaxe, 1, 0);
		case 1:
			return new ItemStack(Items.diamond_pickaxe, 1, 0);
		case 2:
			return new ItemStack(EcoMTCore.villageCard, 1, 0);
		case 3:
			return new ItemStack(EcoMTCore.ticket, 1, 0);
		case 4:
			return new ItemStack(EcoMTCore.buildShop, 1, 0);
		case 5:
			return new ItemStack(EcoMTCore.engeneerShop, 1, 0);
		case 6:
			return new ItemStack(Items.nether_star, 1, 0);
		case 7:
			return new ItemStack(Blocks.dragon_egg, 1, 0);
		default:
			return new ItemStack(Items.iron_pickaxe, 1, 0);
		}
	}

}
