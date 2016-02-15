package defeatedcrow.addonforamt.economy.client.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ItemRenderSafetyChest extends ItemRenderBase {

	public ItemRenderSafetyChest(ModelBase model, String pass) {
		super(model, pass);
	}

	@Override
	protected void renderModel(ModelBase model) {
		if (model != null && model instanceof ModelSafetyChest)
			((ModelSafetyChest) model).render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, false);
	}
}
