package defeatedcrow.addonforamt.economy.plugin.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public interface IEUSourceChannelEMT {

	public void updateEntity2();

	public void onLoaded2();

	public void invalidate2();

	public void onChunkUnload2();

	public void readFromNBT2(NBTTagCompound tag);

	public void writeToNBT2(NBTTagCompound tag);

	public double addEnergy2(double amount);

	public double getEnergyStored();

	public boolean emitsEnergyTo2(TileEntity receiver, ForgeDirection direction);

	public double getOfferedEnergy2();

	public void drawEnergy2(double amount);

	public int getSourceTier2();

}
