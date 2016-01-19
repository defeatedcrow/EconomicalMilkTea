package defeatedcrow.addonforamt.economy.client.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelSafetyBox extends ModelBase {
	// fields
	ModelRenderer base;
	ModelRenderer sideF;
	ModelRenderer sideB;
	ModelRenderer sideL;
	ModelRenderer sideR;
	ModelRenderer top;
	ModelRenderer hinge;
	ModelRenderer dial;
	ModelRenderer button1;
	ModelRenderer buttonbase1;
	ModelRenderer button2;
	ModelRenderer buttonbase2;
	ModelRenderer button3;
	ModelRenderer handle2;
	ModelRenderer handle3;
	ModelRenderer handle1;

	public ModelSafetyBox() {
		textureWidth = 64;
		textureHeight = 64;

		base = new ModelRenderer(this, 0, 49);
		base.addBox(-8F, 7F, -6F, 16, 1, 14);
		base.setRotationPoint(0F, 16F, 0F);
		base.setTextureSize(64, 64);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		sideF = new ModelRenderer(this, 0, 32);
		sideF.addBox(-8F, 0F, -6F, 16, 7, 1);
		sideF.setRotationPoint(0F, 16F, 0F);
		sideF.setTextureSize(64, 64);
		sideF.mirror = true;
		setRotation(sideF, 0F, 0F, 0F);
		sideB = new ModelRenderer(this, 0, 40);
		sideB.addBox(-8F, 0F, 7F, 16, 7, 1);
		sideB.setRotationPoint(0F, 16F, 0F);
		sideB.setTextureSize(64, 64);
		sideB.mirror = true;
		setRotation(sideB, 0F, 0F, 0F);
		sideL = new ModelRenderer(this, 36, 29);
		sideL.addBox(7F, 0F, -5F, 1, 7, 12);
		sideL.setRotationPoint(0F, 16F, 0F);
		sideL.setTextureSize(64, 64);
		sideL.mirror = true;
		setRotation(sideL, 0F, 0F, 0F);
		sideR = new ModelRenderer(this, 36, 29);
		sideR.addBox(-8F, 0F, -5F, 1, 7, 12);
		sideR.setRotationPoint(0F, 16F, 0F);
		sideR.setTextureSize(64, 64);
		sideR.mirror = true;
		setRotation(sideR, 0F, 0F, 0F);
		top = new ModelRenderer(this, 0, 12);
		top.addBox(-8F, -2.1F, -6F, 16, 2, 14);
		top.setRotationPoint(0F, 16F, 0F);
		top.setTextureSize(64, 64);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
		hinge = new ModelRenderer(this, 0, 29);
		hinge.addBox(-7F, -0.5F, 7.2F, 14, 1, 1);
		hinge.setRotationPoint(0F, 16F, 0F);
		hinge.setTextureSize(64, 64);
		hinge.mirror = true;
		setRotation(hinge, 0F, 0F, 0F);
		dial = new ModelRenderer(this, 0, 7);
		dial.addBox(-1.5F, 2F, -7.5F, 3, 3, 1);
		dial.setRotationPoint(0F, 16F, 0F);
		dial.setTextureSize(64, 64);
		dial.mirror = true;
		setRotation(dial, 0F, 0F, 0F);
		button1 = new ModelRenderer(this, 0, 4);
		button1.addBox(-0.5F, 3F, -7F, 1, 1, 1);
		button1.setRotationPoint(0F, 16F, 0F);
		button1.setTextureSize(64, 64);
		button1.mirror = true;
		setRotation(button1, 0F, 0F, 0F);
		buttonbase1 = new ModelRenderer(this, 0, 0);
		buttonbase1.addBox(-6F, 2.5F, -6.5F, 2, 2, 1);
		buttonbase1.setRotationPoint(0F, 16F, 0F);
		buttonbase1.setTextureSize(64, 64);
		buttonbase1.mirror = true;
		setRotation(buttonbase1, 0F, 0F, 0F);
		button2 = new ModelRenderer(this, 0, 4);
		button2.addBox(-5.5F, 3F, -7F, 1, 1, 1);
		button2.setRotationPoint(0F, 16F, 0F);
		button2.setTextureSize(64, 64);
		button2.mirror = true;
		setRotation(button2, 0F, 0F, 0F);
		buttonbase2 = new ModelRenderer(this, 0, 0);
		buttonbase2.addBox(4F, 2.5F, -6.5F, 2, 2, 1);
		buttonbase2.setRotationPoint(0F, 16F, 0F);
		buttonbase2.setTextureSize(64, 64);
		buttonbase2.mirror = true;
		setRotation(buttonbase2, 0F, 0F, 0F);
		button3 = new ModelRenderer(this, 0, 4);
		button3.addBox(4.5F, 3F, -7F, 1, 1, 1);
		button3.setRotationPoint(0F, 16F, 0F);
		button3.setTextureSize(64, 64);
		button3.mirror = true;
		setRotation(button3, 0F, 0F, 0F);
		handle2 = new ModelRenderer(this, 10, 0);
		handle2.addBox(-5F, -3F, 0F, 2, 1, 2);
		handle2.setRotationPoint(0F, 16F, 0F);
		handle2.setTextureSize(64, 64);
		handle2.mirror = true;
		setRotation(handle2, 0F, 0F, 0F);
		handle3 = new ModelRenderer(this, 10, 0);
		handle3.addBox(3F, -3F, 0F, 2, 1, 2);
		handle3.setRotationPoint(0F, 16F, 0F);
		handle3.setTextureSize(64, 64);
		handle3.mirror = true;
		setRotation(handle3, 0F, 0F, 0F);
		handle1 = new ModelRenderer(this, 10, 4);
		handle1.addBox(-4.5F, -4F, 0.5F, 9, 1, 1);
		handle1.setRotationPoint(0F, 16F, 0F);
		handle1.setTextureSize(64, 64);
		handle1.mirror = true;
		setRotation(handle1, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		base.render(f5);
		sideF.render(f5);
		sideB.render(f5);
		sideL.render(f5);
		sideR.render(f5);
		top.render(f5);
		hinge.render(f5);
		dial.render(f5);
		button1.render(f5);
		buttonbase1.render(f5);
		button2.render(f5);
		buttonbase2.render(f5);
		button3.render(f5);
		handle2.render(f5);
		handle3.render(f5);
		handle1.render(f5);
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
