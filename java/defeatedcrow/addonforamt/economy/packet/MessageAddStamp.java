package defeatedcrow.addonforamt.economy.packet;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class MessageAddStamp implements IMessage {

	public int data;
	public boolean red;

	public MessageAddStamp() {
	}

	public MessageAddStamp(int par1, boolean reduce) {
		data = par1;
		red = reduce;
	}

	// read
	@Override
	public void fromBytes(ByteBuf buf) {
		data = buf.readInt();
		red = buf.readBoolean();
	}

	// write
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(data);
		buf.writeBoolean(red);
	}
}
