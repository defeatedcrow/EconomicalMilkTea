package defeatedcrow.addonforamt.economy.client.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelTransactionBox extends ModelBase {
	// fields
	ModelRenderer chest;
	ModelRenderer body;
	ModelRenderer base;
	ModelRenderer back;
	ModelRenderer panel;
	ModelRenderer lever1;
	ModelRenderer lever2;
	ModelRenderer lever3;
	ModelRenderer lever4;
	ModelRenderer leverbase;

	public ModelTransactionBox() {
		textureWidth = 64;
		textureHeight = 64;

		chest = new ModelRenderer(this, 0, 42);
		chest.addBox(-8F, 2F, -8F, 16, 6, 16);
		chest.setRotationPoint(0F, 16F, 0F);
		chest.setTextureSize(64, 64);
		chest.mirror = true;
		setRotation(chest, 0F, 0F, 0F);
		body = new ModelRenderer(this, 0, 10);
		body.addBox(-6F, -4F, -5F, 12, 5, 10);
		body.setRotationPoint(0F, 16F, 0F);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.5235988F, 0F, 0F);
		base = new ModelRenderer(this, 0, 25);
		base.addBox(-7F, -1F, -7F, 14, 3, 14);
		base.setRotationPoint(0F, 16F, 0F);
		base.setTextureSize(64, 64);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		back = new ModelRenderer(this, 0, 12);
		back.addBox(-6F, -9F, 2F, 12, 8, 5);
		back.setRotationPoint(0F, 16F, 0F);
		back.setTextureSize(64, 64);
		back.mirror = true;
		setRotation(back, 0F, 0F, 0F);
		panel = new ModelRenderer(this, 0, 0);
		panel.addBox(-2F, -5F, -4F, 7, 2, 7);
		panel.setRotationPoint(0F, 16F, 0F);
		panel.setTextureSize(64, 64);
		panel.mirror = true;
		setRotation(panel, 0.5235988F, 0F, 0F);
		lever1 = new ModelRenderer(this, 30, 0);
		lever1.addBox(-4.5F, -7F, -2F, 1, 4, 1);
		lever1.setRotationPoint(0F, 16F, 0F);
		lever1.setTextureSize(64, 64);
		lever1.mirror = true;
		setRotation(lever1, 0.7853982F, 0F, 0F);
		lever2 = new ModelRenderer(this, 34, 0);
		lever2.addBox(6F, -7F, 4F, 1, 1, 1);
		lever2.setRotationPoint(0F, 16F, 0F);
		lever2.setTextureSize(64, 64);
		lever2.mirror = true;
		setRotation(lever2, 0F, 0F, 0F);
		lever3 = new ModelRenderer(this, 38, 0);
		lever3.addBox(7F, -7F, 4F, 1, 4, 1);
		lever3.setRotationPoint(0F, 16F, 0F);
		lever3.setTextureSize(64, 64);
		lever3.mirror = true;
		setRotation(lever3, 0F, 0F, 0F);
		lever4 = new ModelRenderer(this, 42, 0);
		lever4.addBox(8F, -4.5F, 3.5F, 2, 2, 2);
		lever4.setRotationPoint(0F, 16F, 0F);
		lever4.setTextureSize(64, 64);
		lever4.mirror = true;
		setRotation(lever4, 0F, 0F, 0F);
		leverbase = new ModelRenderer(this, 44, 10);
		leverbase.addBox(-5.5F, -4.5F, -4F, 3, 1, 6);
		leverbase.setRotationPoint(0F, 16F, 0F);
		leverbase.setTextureSize(64, 64);
		leverbase.mirror = true;
		setRotation(leverbase, 0.5235988F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		chest.render(f5);
		body.render(f5);
		base.render(f5);
		back.render(f5);
		panel.render(f5);
		lever1.render(f5);
		lever2.render(f5);
		lever3.render(f5);
		lever4.render(f5);
		leverbase.render(f5);
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
