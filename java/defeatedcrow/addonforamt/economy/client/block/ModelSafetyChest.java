package defeatedcrow.addonforamt.economy.client.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSafetyChest extends ModelBase {
	// fields
	ModelRenderer bottom;
	ModelRenderer top;
	ModelRenderer back;
	ModelRenderer sideR;
	ModelRenderer sideL;
	ModelRenderer door;
	ModelRenderer base;
	ModelRenderer dial;
	ModelRenderer handle2;
	ModelRenderer handle1;

	public ModelSafetyChest() {
		textureWidth = 64;
		textureHeight = 64;

		bottom = new ModelRenderer(this, 0, 0);
		bottom.addBox(-8F, 7F, -8F, 16, 1, 16);
		bottom.setRotationPoint(0F, 16F, 0F);
		bottom.setTextureSize(64, 64);
		bottom.mirror = true;
		setRotation(bottom, 0F, 0F, 0F);
		top = new ModelRenderer(this, 0, 0);
		top.addBox(-8F, -8F, -8F, 16, 1, 16);
		top.setRotationPoint(0F, 16F, 0F);
		top.setTextureSize(64, 64);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
		back = new ModelRenderer(this, 0, 18);
		back.addBox(-8F, -7F, 7F, 16, 14, 1);
		back.setRotationPoint(0F, 16F, 0F);
		back.setTextureSize(64, 64);
		back.mirror = true;
		setRotation(back, 0F, 0F, 0F);
		sideR = new ModelRenderer(this, 32, 20);
		sideR.addBox(-8F, -7F, -8F, 1, 14, 15);
		sideR.setRotationPoint(0F, 16F, 0F);
		sideR.setTextureSize(64, 64);
		sideR.mirror = true;
		setRotation(sideR, 0F, 0F, 0F);
		sideL = new ModelRenderer(this, 32, 20);
		sideL.addBox(7F, -7F, -8F, 1, 14, 15);
		sideL.setRotationPoint(0F, 16F, 0F);
		sideL.setTextureSize(64, 64);
		sideL.mirror = true;
		setRotation(sideL, 0F, 0F, 0F);
		door = new ModelRenderer(this, 0, 34);
		door.addBox(-14F, -7F, 0F, 14, 14, 1);
		door.setRotationPoint(7F, 16F, -7F);
		door.setTextureSize(64, 64);
		door.mirror = true;
		setRotation(door, 0F, 0F, 0F);
		base = new ModelRenderer(this, 0, 50);
		base.addBox(-13F, -3F, -0.5F, 9, 6, 1);
		base.setRotationPoint(7F, 16F, -7F);
		base.setTextureSize(64, 64);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		dial = new ModelRenderer(this, 22, 50);
		dial.addBox(-9F, -2F, -1F, 3, 3, 1);
		dial.setRotationPoint(7F, 16F, -7F);
		dial.setTextureSize(64, 64);
		dial.mirror = true;
		setRotation(dial, 0F, 0F, 0F);
		handle2 = new ModelRenderer(this, 28, 56);
		handle2.addBox(-12F, -2F, -1F, 1, 1, 1);
		handle2.setRotationPoint(7F, 16F, -7F);
		handle2.setTextureSize(64, 64);
		handle2.mirror = true;
		setRotation(handle2, 0F, 0F, 0F);
		handle1 = new ModelRenderer(this, 22, 56);
		handle1.addBox(-12F, -2F, -2F, 1, 4, 1);
		handle1.setRotationPoint(7F, 16F, -7F);
		handle1.setTextureSize(64, 64);
		handle1.mirror = true;
		setRotation(handle1, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, boolean b) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, b);
		bottom.render(f5);
		top.render(f5);
		back.render(f5);
		sideR.render(f5);
		sideL.render(f5);
		door.render(f5);
		base.render(f5);
		dial.render(f5);
		handle2.render(f5);
		handle1.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, boolean b) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
		door.rotateAngleY = b ? -1.301251F : 0F;
		base.rotateAngleY = b ? -1.301251F : 0F;
		dial.rotateAngleY = b ? -1.301251F : 0F;
		handle1.rotateAngleY = b ? -1.301251F : 0F;
		handle2.rotateAngleY = b ? -1.301251F : 0F;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	}

}
