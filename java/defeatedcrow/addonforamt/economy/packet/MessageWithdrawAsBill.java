package defeatedcrow.addonforamt.economy.packet;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class MessageWithdrawAsBill implements IMessage {

	public int data;
	public int x;
	public int y;
	public int z;

	public MessageWithdrawAsBill() {
	}

	public MessageWithdrawAsBill(int par1, int posx, int posy, int posz) {
		data = par1;
		x = posx;
		y = posy;
		z = posz;
	}

	// read
	@Override
	public void fromBytes(ByteBuf buf) {
		data = buf.readInt();
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
	}

	// write
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(data);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}
}
