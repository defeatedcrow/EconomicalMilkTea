package defeatedcrow.addonforamt.economy.client.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelColdShop extends ModelBase {
	// fields
	ModelRenderer base;
	ModelRenderer sideF;
	ModelRenderer sideB;
	ModelRenderer sideL;
	ModelRenderer sideR;
	ModelRenderer sikiri;
	ModelRenderer top;
	ModelRenderer pack1;
	ModelRenderer pack2;
	ModelRenderer pack3;
	ModelRenderer pack4;
	ModelRenderer pack5;
	ModelRenderer pack6;
	ModelRenderer box;
	ModelRenderer box2;
	ModelRenderer bottle1;
	ModelRenderer bottle2;
	ModelRenderer bottle3;
	ModelRenderer cap1;
	ModelRenderer cap2;
	ModelRenderer cap3;

	public ModelColdShop() {
		textureWidth = 128;
		textureHeight = 64;

		base = new ModelRenderer(this, 0, 0);
		base.addBox(-8F, 7F, -8F, 16, 1, 16);
		base.setRotationPoint(0F, 16F, 0F);
		base.setTextureSize(128, 64);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		sideF = new ModelRenderer(this, 0, 18);
		sideF.addBox(-8F, -3F, -8F, 16, 10, 1);
		sideF.setRotationPoint(0F, 16F, 0F);
		sideF.setTextureSize(128, 64);
		sideF.mirror = true;
		setRotation(sideF, 0F, 0F, 0F);
		sideB = new ModelRenderer(this, 34, 18);
		sideB.addBox(-8F, -8F, 5F, 16, 15, 3);
		sideB.setRotationPoint(0F, 16F, 0F);
		sideB.setTextureSize(128, 64);
		sideB.mirror = true;
		setRotation(sideB, 0F, 0F, 0F);
		sideL = new ModelRenderer(this, 26, 38);
		sideL.addBox(7F, -3F, -7F, 1, 10, 12);
		sideL.setRotationPoint(0F, 16F, 0F);
		sideL.setTextureSize(128, 64);
		sideL.mirror = true;
		setRotation(sideL, 0F, 0F, 0F);
		sideR = new ModelRenderer(this, 0, 38);
		sideR.addBox(-8F, -3F, -7F, 1, 10, 12);
		sideR.setRotationPoint(0F, 16F, 0F);
		sideR.setTextureSize(128, 64);
		sideR.mirror = true;
		setRotation(sideR, 0F, 0F, 0F);
		sikiri = new ModelRenderer(this, 52, 38);
		sikiri.addBox(1F, -1F, -7F, 1, 8, 12);
		sikiri.setRotationPoint(0F, 16F, 0F);
		sikiri.setTextureSize(128, 64);
		sikiri.mirror = true;
		setRotation(sikiri, 0F, 0F, 0F);
		top = new ModelRenderer(this, 64, 0);
		top.addBox(-7F, -4F, -7F, 14, 2, 13);
		top.setRotationPoint(0F, 16F, 0F);
		top.setTextureSize(128, 64);
		top.mirror = true;
		setRotation(top, 0.1047198F, 0F, 0F);
		pack1 = new ModelRenderer(this, 80, 16);
		pack1.addBox(-6.5F, 1F, -6F, 3, 6, 3);
		pack1.setRotationPoint(0F, 16F, 0F);
		pack1.setTextureSize(128, 64);
		pack1.mirror = true;
		setRotation(pack1, 0F, 0F, 0F);
		pack2 = new ModelRenderer(this, 92, 16);
		pack2.addBox(-6.5F, -3.5F, -4.9F, 3, 2, 2);
		pack2.setRotationPoint(0F, 16F, 0F);
		pack2.setTextureSize(128, 64);
		pack2.mirror = true;
		setRotation(pack2, 0.7853982F, 0F, 0F);
		pack3 = new ModelRenderer(this, 102, 16);
		pack3.addBox(-6.5F, -1F, -5F, 3, 2, 1);
		pack3.setRotationPoint(0F, 16F, 0F);
		pack3.setTextureSize(128, 64);
		pack3.mirror = true;
		setRotation(pack3, 0F, 0F, 0F);
		pack4 = new ModelRenderer(this, 80, 16);
		pack4.addBox(-2.5F, 1F, -6F, 3, 6, 3);
		pack4.setRotationPoint(0F, 16F, 0F);
		pack4.setTextureSize(128, 64);
		pack4.mirror = true;
		setRotation(pack4, 0F, 0F, 0F);
		pack5 = new ModelRenderer(this, 92, 16);
		pack5.addBox(-2.5F, -3.5F, -4.9F, 3, 2, 2);
		pack5.setRotationPoint(0F, 16F, 0F);
		pack5.setTextureSize(128, 64);
		pack5.mirror = true;
		setRotation(pack5, 0.7853982F, 0F, 0F);
		pack6 = new ModelRenderer(this, 102, 16);
		pack6.addBox(-2.5F, -1F, -5F, 3, 2, 1);
		pack6.setRotationPoint(0F, 16F, 0F);
		pack6.setTextureSize(128, 64);
		pack6.mirror = true;
		setRotation(pack6, 0F, 0F, 0F);
		box = new ModelRenderer(this, 80, 26);
		box.addBox(-6F, 4F, -2F, 5, 2, 5);
		box.setRotationPoint(0F, 16F, 0F);
		box.setTextureSize(128, 64);
		box.mirror = true;
		setRotation(box, 0F, 0.2617994F, 0F);
		box2 = new ModelRenderer(this, 80, 26);
		box2.addBox(-6F, 2F, -1F, 5, 2, 5);
		box2.setRotationPoint(0F, 16F, 0F);
		box2.setTextureSize(128, 64);
		box2.mirror = true;
		setRotation(box2, 0F, -0.0698132F, 0F);
		bottle1 = new ModelRenderer(this, 80, 36);
		bottle1.addBox(3F, 1F, -6F, 3, 6, 3);
		bottle1.setRotationPoint(0F, 16F, 0F);
		bottle1.setTextureSize(128, 64);
		bottle1.mirror = true;
		setRotation(bottle1, 0F, 0.0174533F, 0F);
		bottle2 = new ModelRenderer(this, 80, 36);
		bottle2.addBox(3F, 1F, -2F, 3, 6, 3);
		bottle2.setRotationPoint(0F, 16F, 0F);
		bottle2.setTextureSize(128, 64);
		bottle2.mirror = true;
		setRotation(bottle2, 0F, 0.0872665F, 0F);
		bottle3 = new ModelRenderer(this, 80, 36);
		bottle3.addBox(3F, 1F, 2F, 3, 6, 3);
		bottle3.setRotationPoint(0F, 16F, 0F);
		bottle3.setTextureSize(128, 64);
		bottle3.mirror = true;
		setRotation(bottle3, 0F, 0.1047198F, 0F);
		cap1 = new ModelRenderer(this, 93, 36);
		cap1.addBox(4F, 0F, -5F, 1, 1, 1);
		cap1.setRotationPoint(0F, 16F, 0F);
		cap1.setTextureSize(128, 64);
		cap1.mirror = true;
		setRotation(cap1, 0F, 0.0174533F, 0F);
		cap2 = new ModelRenderer(this, 93, 36);
		cap2.addBox(4F, 0F, -1F, 1, 1, 1);
		cap2.setRotationPoint(0F, 16F, 0F);
		cap2.setTextureSize(128, 64);
		cap2.mirror = true;
		setRotation(cap2, 0F, 0.0872665F, 0F);
		cap3 = new ModelRenderer(this, 93, 36);
		cap3.addBox(4F, 0F, 3F, 1, 1, 1);
		cap3.setRotationPoint(0F, 16F, 0F);
		cap3.setTextureSize(128, 64);
		cap3.mirror = true;
		setRotation(cap3, 0F, 0.1047198F, 0F);
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
		sikiri.render(f5);
		pack1.render(f5);
		pack2.render(f5);
		pack3.render(f5);
		pack4.render(f5);
		pack5.render(f5);
		pack6.render(f5);
		box.render(f5);
		box2.render(f5);
		cap1.render(f5);
		cap2.render(f5);
		cap3.render(f5);
	}

	public void renderGlass(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		bottle1.render(f5);
		bottle2.render(f5);
		bottle3.render(f5);
		top.render(f5);
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
