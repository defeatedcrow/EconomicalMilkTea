package defeatedcrow.addonforamt.economy.packet;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class MessageUpdateMP implements IMessage {

	public long data;
	public int x;
	public int y;
	public int z;

	public MessageUpdateMP() {
	}

	public MessageUpdateMP(byte par1, int posx, int posy, int posz) {
		this.data = par1;
		x = posx;
		y = posy;
		z = posz;
	}

	// read
	@Override
	public void fromBytes(ByteBuf buf) {
		this.data = buf.readLong();
		x = buf.getInt(0);
		y = buf.getInt(1);
		z = buf.getInt(2);
	}

	// write
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(this.data);
		buf.setInt(0, x);
		buf.setInt(1, y);
		buf.setInt(2, z);
	}
}
