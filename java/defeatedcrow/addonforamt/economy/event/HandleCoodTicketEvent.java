package defeatedcrow.addonforamt.economy.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import defeatedcrow.addonforamt.economy.EcoMTCore;
import defeatedcrow.addonforamt.economy.client.entity.BeamEffect;
import defeatedcrow.addonforamt.economy.common.item.ItemCoodTicket;

/**
 * Original code was created by Furia on 2015/10/23.
 */
public class HandleCoodTicketEvent {

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void render(LivingEvent.LivingUpdateEvent event) {
		EntityPlayer player = EcoMTCore.proxy.getClientPlayer();
		if (player == null || event.entity != player)
			return;
		ItemStack hold = player.getCurrentEquippedItem();
		int dim = EcoMTCore.proxy.getClientWorld().provider.dimensionId;

		if (hold != null && hold.getItem() != null && hold.getItem() instanceof ItemCoodTicket) {
			ItemCoodTicket tic = (ItemCoodTicket) hold.getItem();
			if (tic.isRegistered(hold)) {
				int regDim = tic.getDim(hold);
				int[] cood = tic.getCood(hold);
				if (regDim == dim) {
					double x = cood[0] + 0.5D;
					double y = cood[1] + 0.5D;
					double z = cood[2] + 0.5D;

					BeamEffect beam = new BeamEffect(EcoMTCore.proxy.getClientWorld(), player);

					beam.posX = x;
					beam.posY = y;
					beam.posZ = z;

					EcoMTCore.proxy.getClientWorld().addWeatherEffect(beam);
				}
			}
		}
	}

}
