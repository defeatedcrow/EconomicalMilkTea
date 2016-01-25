package defeatedcrow.addonforamt.economy.packet;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class MessageAddStampReward implements IMessage {

	public int data;

	public MessageAddStampReward() {
	}

	public MessageAddStampReward(int par1) {
		data = par1;
	}

	// read
	@Override
	public void fromBytes(ByteBuf buf) {
		data = buf.readInt();
	}

	// write
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(data);
	}
}
