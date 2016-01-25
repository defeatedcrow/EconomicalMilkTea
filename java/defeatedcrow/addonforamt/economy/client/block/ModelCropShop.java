package defeatedcrow.addonforamt.economy.client.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCropShop extends ModelBase {
	// fields
	ModelRenderer back;
	ModelRenderer Plate1;
	ModelRenderer Plate2;
	ModelRenderer plate3;
	ModelRenderer plate4;
	ModelRenderer Plate5;
	ModelRenderer Plate6;

	public ModelCropShop() {
		textureWidth = 64;
		textureHeight = 64;

		back = new ModelRenderer(this, 0, 18);
		back.addBox(-8F, -2F, 6F, 16, 9, 2);
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
		Plate2 = new ModelRenderer(this, 0, 32);
		Plate2.addBox(-8F, 2F, -10F, 16, 1, 16);
		Plate2.setRotationPoint(0F, 16F, 0F);
		Plate2.setTextureSize(64, 64);
		Plate2.mirror = true;
		setRotation(Plate2, 0.5235988F, 0F, 0F);
		plate3 = new ModelRenderer(this, 0, 30);
		plate3.addBox(-8F, 1F, -10F, 16, 1, 1);
		plate3.setRotationPoint(0F, 16F, 0F);
		plate3.setTextureSize(64, 64);
		plate3.mirror = true;
		setRotation(plate3, 0.5235988F, 0F, 0F);
		plate4 = new ModelRenderer(this, 0, 30);
		plate4.addBox(-8F, 1F, 5F, 16, 1, 1);
		plate4.setRotationPoint(0F, 16F, 0F);
		plate4.setTextureSize(64, 64);
		plate4.mirror = true;
		setRotation(plate4, 0.5235988F, 0F, 0F);
		Plate5 = new ModelRenderer(this, 0, 49);
		Plate5.addBox(-8F, 1F, -9F, 1, 1, 14);
		Plate5.setRotationPoint(0F, 16F, 0F);
		Plate5.setTextureSize(64, 64);
		Plate5.mirror = true;
		setRotation(Plate5, 0.5235988F, 0F, 0F);
		Plate6 = new ModelRenderer(this, 0, 49);
		Plate6.addBox(7F, 1F, -9F, 1, 1, 14);
		Plate6.setRotationPoint(0F, 16F, 0F);
		Plate6.setTextureSize(64, 64);
		Plate6.mirror = true;
		setRotation(Plate6, 0.5235988F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		back.render(f5);
		Plate1.render(f5);
		Plate2.render(f5);
		plate3.render(f5);
		plate4.render(f5);
		Plate5.render(f5);
		Plate6.render(f5);
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
