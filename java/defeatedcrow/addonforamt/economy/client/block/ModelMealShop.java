package defeatedcrow.addonforamt.economy.client.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMealShop extends ModelBase {
	// fields
	ModelRenderer back;
	ModelRenderer Plate1;
	ModelRenderer Plate2;
	ModelRenderer Plate3;

	public ModelMealShop() {
		textureWidth = 64;
		textureHeight = 64;

		back = new ModelRenderer(this, 0, 17);
		back.addBox(-8F, -9F, 7F, 16, 16, 1);
		back.setRotationPoint(0F, 16F, 0F);
		back.setTextureSize(64, 64);
		back.mirror = true;
		setRotation(back, 0F, 0F, 0F);
		Plate1 = new ModelRenderer(this, 0, 0);
		Plate1.addBox(-8F, 7F, -8F, 16, 1, 16);
		Plate1.setRotationPoint(0F, 16F, 0F);
		Plate1.setTextureSize(64, 64);
		Plate1.mirror = true;
		setRotation(Plate1, 0F, 0F, 0F);
		Plate2 = new ModelRenderer(this, 0, 34);
		Plate2.addBox(-8F, 6F, -3.5F, 16, 1, 10);
		Plate2.setRotationPoint(0F, 16F, 0F);
		Plate2.setTextureSize(64, 64);
		Plate2.mirror = true;
		setRotation(Plate2, 0.1047198F, 0F, 0F);
		Plate3 = new ModelRenderer(this, 0, 34);
		Plate3.addBox(-8F, -1F, -3F, 16, 1, 10);
		Plate3.setRotationPoint(0F, 16F, 0F);
		Plate3.setTextureSize(64, 64);
		Plate3.mirror = true;
		setRotation(Plate3, 0.1047198F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		back.render(f5);
		Plate1.render(f5);
		Plate3.render(f5);
	}

	public void renderB(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		back.render(f5);
		Plate2.render(f5);
		Plate3.render(f5);
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
