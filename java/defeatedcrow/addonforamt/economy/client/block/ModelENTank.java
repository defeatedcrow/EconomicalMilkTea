package defeatedcrow.addonforamt.economy.client.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelENTank extends ModelBase {
	// fields
	ModelRenderer Bottom;
	ModelRenderer Top;
	ModelRenderer Body;
	ModelRenderer Leg1;
	ModelRenderer Leg2;
	ModelRenderer Leg3;
	ModelRenderer Leg4;
	ModelRenderer Panel1;
	ModelRenderer Panel2;

	public ModelENTank() {
		textureWidth = 64;
		textureHeight = 64;

		Bottom = new ModelRenderer(this, 0, 0);
		Bottom.addBox(-8F, 7F, -8F, 16, 1, 16);
		Bottom.setRotationPoint(0F, 16F, 0F);
		Bottom.setTextureSize(64, 64);
		Bottom.mirror = true;
		setRotation(Bottom, 0F, 0F, 0F);
		Top = new ModelRenderer(this, 0, 0);
		Top.addBox(-8F, -8F, -8F, 16, 2, 16);
		Top.setRotationPoint(0F, 16F, 0F);
		Top.setTextureSize(64, 64);
		Top.mirror = true;
		setRotation(Top, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 0, 18);
		Body.addBox(-7F, -6F, -7F, 12, 13, 14);
		Body.setRotationPoint(0F, 16F, 0F);
		Body.setTextureSize(64, 64);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		Leg1 = new ModelRenderer(this, 54, 20);
		Leg1.addBox(-8F, -6F, -8F, 1, 13, 1);
		Leg1.setRotationPoint(0F, 16F, 0F);
		Leg1.setTextureSize(64, 64);
		Leg1.mirror = true;
		setRotation(Leg1, 0F, 0F, 0F);
		Leg2 = new ModelRenderer(this, 54, 20);
		Leg2.addBox(-8F, -3F, 7F, 1, 13, 1);
		Leg2.setRotationPoint(0F, 13F, 0F);
		Leg2.setTextureSize(64, 64);
		Leg2.mirror = true;
		setRotation(Leg2, 0F, 0F, 0F);
		Leg3 = new ModelRenderer(this, 54, 20);
		Leg3.addBox(7F, -6F, -8F, 1, 13, 1);
		Leg3.setRotationPoint(0F, 16F, 0F);
		Leg3.setTextureSize(64, 64);
		Leg3.mirror = true;
		setRotation(Leg3, 0F, 0F, 0F);
		Leg4 = new ModelRenderer(this, 54, 20);
		Leg4.addBox(7F, -6F, 7F, 1, 13, 1);
		Leg4.setRotationPoint(0F, 16F, 0F);
		Leg4.setTextureSize(64, 64);
		Leg4.mirror = true;
		setRotation(Leg4, 0F, 0F, 0F);
		Panel1 = new ModelRenderer(this, 0, 45);
		Panel1.addBox(5F, -6F, -4F, 2, 8, 10);
		Panel1.setRotationPoint(0F, 16F, 0F);
		Panel1.setTextureSize(64, 64);
		Panel1.mirror = true;
		setRotation(Panel1, 0F, 0F, 0F);
		Panel2 = new ModelRenderer(this, 24, 45);
		Panel2.addBox(5F, 2F, -4F, 2, 5, 10);
		Panel2.setRotationPoint(0F, 16F, 0F);
		Panel2.setTextureSize(64, 64);
		Panel2.mirror = true;
		setRotation(Panel2, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Bottom.render(f5);
		Top.render(f5);
		Body.render(f5);
		Leg1.render(f5);
		Leg2.render(f5);
		Leg3.render(f5);
		Leg4.render(f5);
		Panel1.render(f5);
		Panel2.render(f5);
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
