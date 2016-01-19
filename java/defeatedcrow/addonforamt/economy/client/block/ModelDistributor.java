package defeatedcrow.addonforamt.economy.client.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelDistributor extends ModelBase {
	// fields
	ModelRenderer back;
	ModelRenderer top;
	ModelRenderer Bottom;
	ModelRenderer Reft;
	ModelRenderer Left;
	ModelRenderer Door;
	ModelRenderer Bar1;
	ModelRenderer Bar2;
	ModelRenderer Bar3;
	ModelRenderer BreakerBase1;
	ModelRenderer BreakerBase2;
	ModelRenderer BreakerTop1;
	ModelRenderer BreakerTop2;
	ModelRenderer BreakerBase3;
	ModelRenderer BreakerBase4;
	ModelRenderer BreakerTop3;
	ModelRenderer BreakerTop4;
	ModelRenderer MG1;
	ModelRenderer MG2;
	ModelRenderer MG3;
	ModelRenderer MG4;
	ModelRenderer PLC;
	ModelRenderer Switch1;
	ModelRenderer Switch2;
	ModelRenderer Switch3;
	ModelRenderer Switch4;
	ModelRenderer Shape1;

	public ModelDistributor() {
		textureWidth = 64;
		textureHeight = 64;

		back = new ModelRenderer(this, 0, 0);
		back.addBox(-6F, -8F, 7F, 12, 16, 1);
		back.setRotationPoint(0F, 16F, 0F);
		back.setTextureSize(64, 64);
		back.mirror = true;
		setRotation(back, 0F, 0F, 0F);
		top = new ModelRenderer(this, 0, 19);
		top.addBox(-6F, -8F, 0F, 12, 1, 7);
		top.setRotationPoint(0F, 16F, 0F);
		top.setTextureSize(64, 64);
		top.mirror = true;
		setRotation(top, 0F, 0F, 0F);
		Bottom = new ModelRenderer(this, 0, 19);
		Bottom.addBox(-6F, 7F, 0F, 12, 1, 7);
		Bottom.setRotationPoint(0F, 16F, 0F);
		Bottom.setTextureSize(64, 64);
		Bottom.mirror = true;
		setRotation(Bottom, 0F, 0F, 0F);
		Reft = new ModelRenderer(this, 0, 30);
		Reft.addBox(-6F, -7F, 0F, 1, 14, 7);
		Reft.setRotationPoint(0F, 16F, 0F);
		Reft.setTextureSize(64, 64);
		Reft.mirror = true;
		setRotation(Reft, 0F, 0F, 0F);
		Left = new ModelRenderer(this, 0, 30);
		Left.addBox(5F, -7F, 0F, 1, 14, 7);
		Left.setRotationPoint(0F, 16F, 0F);
		Left.setTextureSize(64, 64);
		Left.mirror = true;
		setRotation(Left, 0F, 0F, 0F);
		Door = new ModelRenderer(this, 28, 0);
		Door.addBox(-10F, -7F, -0.5F, 10, 14, 1);
		Door.setRotationPoint(5F, 16F, 1F);
		Door.setTextureSize(64, 64);
		Door.mirror = true;
		setRotation(Door, 0F, -1.301251F, 0F);
		Bar1 = new ModelRenderer(this, 28, 16);
		Bar1.addBox(-4F, -4F, 6F, 8, 1, 1);
		Bar1.setRotationPoint(0F, 16F, 0F);
		Bar1.setTextureSize(64, 64);
		Bar1.mirror = true;
		setRotation(Bar1, 0F, 0F, 0F);
		Bar2 = new ModelRenderer(this, 28, 16);
		Bar2.addBox(-4F, 1F, 6F, 8, 1, 1);
		Bar2.setRotationPoint(0F, 16F, 0F);
		Bar2.setTextureSize(64, 64);
		Bar2.mirror = true;
		setRotation(Bar2, 0F, 0F, 0F);
		Bar3 = new ModelRenderer(this, 28, 16);
		Bar3.addBox(-4F, 4F, 6F, 8, 1, 1);
		Bar3.setRotationPoint(0F, 16F, 0F);
		Bar3.setTextureSize(64, 64);
		Bar3.mirror = true;
		setRotation(Bar3, 0F, 0F, 0F);
		BreakerBase1 = new ModelRenderer(this, 18, 30);
		BreakerBase1.addBox(-3.5F, -6F, 3F, 3, 4, 3);
		BreakerBase1.setRotationPoint(0F, 16F, 0F);
		BreakerBase1.setTextureSize(64, 64);
		BreakerBase1.mirror = true;
		setRotation(BreakerBase1, 0F, 0F, 0F);
		BreakerBase2 = new ModelRenderer(this, 18, 30);
		BreakerBase2.addBox(0.5F, -6F, 3F, 3, 4, 3);
		BreakerBase2.setRotationPoint(0F, 16F, 0F);
		BreakerBase2.setTextureSize(64, 64);
		BreakerBase2.mirror = true;
		setRotation(BreakerBase2, 0F, 0F, 0F);
		BreakerTop1 = new ModelRenderer(this, 18, 38);
		BreakerTop1.addBox(-3.5F, -5F, 2.5F, 3, 2, 1);
		BreakerTop1.setRotationPoint(0F, 16F, 0F);
		BreakerTop1.setTextureSize(64, 64);
		BreakerTop1.mirror = true;
		setRotation(BreakerTop1, 0F, 0F, 0F);
		BreakerTop2 = new ModelRenderer(this, 18, 38);
		BreakerTop2.addBox(0.5F, 0F, 2.5F, 3, 2, 1);
		BreakerTop2.setRotationPoint(0F, 11F, 0F);
		BreakerTop2.setTextureSize(64, 64);
		BreakerTop2.mirror = true;
		setRotation(BreakerTop2, 0F, 0F, 0F);
		BreakerBase3 = new ModelRenderer(this, 18, 30);
		BreakerBase3.addBox(-3.5F, -1F, 3F, 3, 4, 3);
		BreakerBase3.setRotationPoint(0F, 16F, 0F);
		BreakerBase3.setTextureSize(64, 64);
		BreakerBase3.mirror = true;
		setRotation(BreakerBase3, 0F, 0F, 0F);
		BreakerBase4 = new ModelRenderer(this, 18, 30);
		BreakerBase4.addBox(0.5F, -1F, 3F, 3, 4, 3);
		BreakerBase4.setRotationPoint(0F, 16F, 0F);
		BreakerBase4.setTextureSize(64, 64);
		BreakerBase4.mirror = true;
		setRotation(BreakerBase4, 0F, 0F, 0F);
		BreakerTop3 = new ModelRenderer(this, 18, 38);
		BreakerTop3.addBox(-3.5F, 0F, 2.5F, 3, 2, 1);
		BreakerTop3.setRotationPoint(0F, 16F, 0F);
		BreakerTop3.setTextureSize(64, 64);
		BreakerTop3.mirror = true;
		setRotation(BreakerTop3, 0F, 0F, 0F);
		BreakerTop4 = new ModelRenderer(this, 18, 38);
		BreakerTop4.addBox(0.5F, 0F, 2.5F, 3, 2, 1);
		BreakerTop4.setRotationPoint(0F, 16F, 0F);
		BreakerTop4.setTextureSize(64, 64);
		BreakerTop4.mirror = true;
		setRotation(BreakerTop4, 0F, 0F, 0F);
		MG1 = new ModelRenderer(this, 32, 30);
		MG1.addBox(-4F, 3.5F, 4F, 1, 2, 2);
		MG1.setRotationPoint(0F, 16F, 0F);
		MG1.setTextureSize(64, 64);
		MG1.mirror = true;
		setRotation(MG1, 0F, 0F, 0F);
		MG2 = new ModelRenderer(this, 32, 30);
		MG2.addBox(-2.8F, 3.5F, 4F, 1, 2, 2);
		MG2.setRotationPoint(0F, 16F, 0F);
		MG2.setTextureSize(64, 64);
		MG2.mirror = true;
		setRotation(MG2, 0F, 0F, 0F);
		MG3 = new ModelRenderer(this, 32, 30);
		MG3.addBox(-1.6F, 3.5F, 4F, 1, 2, 2);
		MG3.setRotationPoint(0F, 16F, 0F);
		MG3.setTextureSize(64, 64);
		MG3.mirror = true;
		setRotation(MG3, 0F, 0F, 0F);
		MG4 = new ModelRenderer(this, 32, 30);
		MG4.addBox(-0.4F, 3.5F, 4F, 1, 2, 2);
		MG4.setRotationPoint(0F, 16F, 0F);
		MG4.setTextureSize(64, 64);
		MG4.mirror = true;
		setRotation(MG4, 0F, 0F, 0F);
		PLC = new ModelRenderer(this, 32, 35);
		PLC.addBox(1F, 3.5F, 4F, 3, 2, 2);
		PLC.setRotationPoint(0F, 16F, 0F);
		PLC.setTextureSize(64, 64);
		PLC.mirror = true;
		setRotation(PLC, 0F, 0F, 0F);
		Switch1 = new ModelRenderer(this, 40, 30);
		Switch1.addBox(-0.5F, -0.5F, -2F, 1, 1, 2);
		Switch1.setRotationPoint(-2F, 12F, 3.5F);
		Switch1.setTextureSize(64, 64);
		Switch1.mirror = true;
		setRotation(Switch1, 0F, 0F, 0F);
		Switch2 = new ModelRenderer(this, 40, 30);
		Switch2.addBox(-0.5F, -0.5F, -2F, 1, 1, 2);
		Switch2.setRotationPoint(2F, 12F, 3.5F);
		Switch2.setTextureSize(64, 64);
		Switch2.mirror = true;
		setRotation(Switch2, 0F, 0F, 0F);
		Switch3 = new ModelRenderer(this, 40, 30);
		Switch3.addBox(-0.5F, -0.5F, -2F, 1, 1, 2);
		Switch3.setRotationPoint(-2F, 17F, 3.5F);
		Switch3.setTextureSize(64, 64);
		Switch3.mirror = true;
		setRotation(Switch3, 0F, 0F, 0F);
		Switch4 = new ModelRenderer(this, 40, 30);
		Switch4.addBox(-0.5F, -0.5F, -2F, 1, 1, 2);
		Switch4.setRotationPoint(2F, 17F, 3.5F);
		Switch4.setTextureSize(64, 64);
		Switch4.mirror = true;
		setRotation(Switch4, 0F, 0F, 0F);
		Shape1 = new ModelRenderer(this, 18, 42);
		Shape1.addBox(-5F, -7F, 6.3F, 9, 14, 0);
		Shape1.setRotationPoint(0F, 16F, 0F);
		Shape1.setTextureSize(64, 64);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, boolean door) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, door);
		back.render(f5);
		top.render(f5);
		Bottom.render(f5);
		Reft.render(f5);
		Left.render(f5);
		Door.render(f5);
		Bar1.render(f5);
		Bar2.render(f5);
		Bar3.render(f5);
		BreakerBase1.render(f5);
		BreakerBase2.render(f5);
		BreakerTop1.render(f5);
		BreakerTop2.render(f5);
		BreakerBase3.render(f5);
		BreakerBase4.render(f5);
		BreakerTop3.render(f5);
		BreakerTop4.render(f5);
		MG1.render(f5);
		MG2.render(f5);
		MG3.render(f5);
		MG4.render(f5);
		PLC.render(f5);
		Shape1.render(f5);
	}

	public void renderSwitch(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, boolean b1,
			boolean b2, boolean b3, boolean b4) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setSwitchRotationAngles(f, f1, f2, f3, f4, f5, b1, b2, b3, b4);
		Switch1.render(f5);
		Switch2.render(f5);
		Switch3.render(f5);
		Switch4.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, boolean door) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
		Door.rotateAngleY = door ? -1.301251F : 0F;
	}

	public void setSwitchRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, boolean b1,
			boolean b2, boolean b3, boolean b4) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
		Switch1.rotateAngleX = switchAngle(b1);
		Switch2.rotateAngleX = switchAngle(b2);
		Switch3.rotateAngleX = switchAngle(b3);
		Switch4.rotateAngleX = switchAngle(b4);
	}

	private float switchAngle(boolean b) {
		return b ? -0.55F : 0.55F;
	}

}
