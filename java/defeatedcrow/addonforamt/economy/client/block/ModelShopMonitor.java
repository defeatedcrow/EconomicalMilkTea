package defeatedcrow.addonforamt.economy.client.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelShopMonitor extends ModelBase {
	// fields
	ModelRenderer base;
	ModelRenderer leg;
	ModelRenderer panelbase;
	ModelRenderer panel1;
	ModelRenderer panel2;
	ModelRenderer panel3;
	ModelRenderer panel4;

	public ModelShopMonitor() {
		textureWidth = 64;
		textureHeight = 32;

		base = new ModelRenderer(this, 0, 0);
		base.addBox(-6F, 7F, -6F, 12, 1, 12);
		base.setRotationPoint(0F, 16F, 0F);
		base.setTextureSize(64, 32);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		leg = new ModelRenderer(this, 0, 0);
		leg.addBox(-2F, -2F, -2F, 4, 9, 4);
		leg.setRotationPoint(0F, 16F, 0F);
		leg.setTextureSize(64, 32);
		leg.mirror = true;
		setRotation(leg, 0F, 0F, 0F);
		panelbase = new ModelRenderer(this, 0, 0);
		panelbase.addBox(-7F, -3F, -7F, 14, 2, 14);
		panelbase.setRotationPoint(0F, 16F, 0F);
		panelbase.setTextureSize(64, 32);
		panelbase.mirror = true;
		setRotation(panelbase, 0.9250245F, 0F, 0F);
		panel1 = new ModelRenderer(this, 0, 0);
		panel1.addBox(-7F, -4F, -7F, 14, 1, 3);
		panel1.setRotationPoint(0F, 16F, 0F);
		panel1.setTextureSize(64, 32);
		panel1.mirror = true;
		setRotation(panel1, 0.9250245F, 0F, 0F);
		panel2 = new ModelRenderer(this, 0, 0);
		panel2.addBox(-7F, -4F, 4F, 14, 1, 3);
		panel2.setRotationPoint(0F, 16F, 0F);
		panel2.setTextureSize(64, 32);
		panel2.mirror = true;
		setRotation(panel2, 0.9250245F, 0F, 0F);
		panel3 = new ModelRenderer(this, 0, 0);
		panel3.addBox(-7F, -4F, -4F, 3, 1, 8);
		panel3.setRotationPoint(0F, 16F, 0F);
		panel3.setTextureSize(64, 32);
		panel3.mirror = true;
		setRotation(panel3, 0.9250245F, 0F, 0F);
		panel4 = new ModelRenderer(this, 0, 0);
		panel4.addBox(4F, -4F, -4F, 3, 1, 8);
		panel4.setRotationPoint(0F, 16F, 0F);
		panel4.setTextureSize(64, 32);
		panel4.mirror = true;
		setRotation(panel4, 0.9250245F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		base.render(f5);
		leg.render(f5);
		panelbase.render(f5);
		panel1.render(f5);
		panel2.render(f5);
		panel3.render(f5);
		panel4.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	}
}
