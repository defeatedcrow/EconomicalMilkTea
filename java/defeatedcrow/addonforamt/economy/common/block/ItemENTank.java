package defeatedcrow.addonforamt.economy.common.block;

import mods.defeatedcrow.api.energy.BatteyItemBlockBase;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemENTank extends BatteyItemBlockBase {

	public ItemENTank(Block block) {
		super(block);
		this.setMaxStackSize(1);
	}

	@Override
	public int getMaxAmount(ItemStack item) {
		return 25600;
	}
}
