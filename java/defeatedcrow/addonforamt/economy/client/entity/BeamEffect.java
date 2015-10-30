package defeatedcrow.addonforamt.economy.client.entity;

import net.minecraft.entity.effect.EntityWeatherEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import defeatedcrow.addonforamt.economy.common.item.ItemCoodTicket;

/**
 * Original code was created by Furia on 2015/10/23.
 */
public class BeamEffect extends EntityWeatherEffect {

	EntityPlayer player;

	public BeamEffect(World world, EntityPlayer player) {
		super(world);

		this.player = player;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (this.isDead)
			return;
		if (this.player == null)
			this.setDead();
		else if (this.player.isDead)
			this.setDead();
		else if (this.player.getHeldItem() == null)
			this.setDead();
		else if (!(this.player.getHeldItem().getItem() instanceof ItemCoodTicket))
			this.setDead();
		else {
			ItemStack stack = this.player.getHeldItem();
			ItemCoodTicket tic = (ItemCoodTicket) stack.getItem();
			if (!tic.isRegistered(stack)) {
				this.setDead();
			}
		}

	}

	@Override
	protected void entityInit() {

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_) {

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_) {

	}
}
