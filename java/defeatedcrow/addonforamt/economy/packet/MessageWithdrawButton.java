package defeatedcrow.addonforamt.economy.packet;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class MessageWithdrawButton implements IMessage {

	public int data;
	public boolean deposit;
	public int x;
	public int y;
	public int z;

	public MessageWithdrawButton() {
	}

	public MessageWithdrawButton(int par1, boolean isDeposit, int posx, int posy, int posz) {
		data = par1;
		deposit = isDeposit;
		x = posx;
		y = posy;
		z = posz;
	}

	// read
	@Override
	public void fromBytes(ByteBuf buf) {
		deposit = buf.readBoolean();
		data = buf.readInt();
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
	}

	// write
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(deposit);
		buf.writeInt(data);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}
}
