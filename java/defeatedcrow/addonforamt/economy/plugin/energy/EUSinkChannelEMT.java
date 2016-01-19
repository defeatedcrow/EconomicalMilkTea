package defeatedcrow.addonforamt.economy.plugin.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import ic2.api.energy.prefab.BasicSink;

public class EUSinkChannelEMT extends BasicSink implements IEUSinkChannelEMT {

	public EUSinkChannelEMT(TileEntity parent1, int capacity1, int tier1) {
		super(parent1, capacity1, tier1);
	}

	@Override
	public void readFromNBT2(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);

		NBTTagCompound data = par1NBTTagCompound.getCompoundTag("IC2BasicSink");

		energyStored = data.getDouble("energy");
	}

	@Override
	public void writeToNBT2(NBTTagCompound par1NBTTagCompound) {
		try {
			super.writeToNBT(par1NBTTagCompound);
		} catch (RuntimeException e) {
			// happens if this is a delegate, ignore
		}

		NBTTagCompound data = new NBTTagCompound();

		data.setDouble("energy", energyStored);

		par1NBTTagCompound.setTag("IC2BasicSink", data);
	}

	@Override
	public void invalidate2() {
		super.invalidate();
	}

	@Override
	public void onChunkUnload2() {
		super.onChunkUnload();
	}

	@Override
	public void updateEntity2() {
		super.updateEntity();
	}

	@Override
	public double getEnergyStored2() {
		return super.getEnergyStored();
	}

	@Override
	public void setEnergyStored2(double amount) {
		super.setEnergyStored(amount);
	}

	@Override
	public boolean useEnergy2(double amount) {
		return super.useEnergy(amount);
	}

}
