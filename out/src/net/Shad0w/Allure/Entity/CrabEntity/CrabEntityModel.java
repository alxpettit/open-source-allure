package net.Shad0w.Allure.Entity.CrabEntity;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.math.MathHelper;

public class CrabEntityModel extends EntityModel {
   private float wiggleX = 0.0F;
   private float wiggleY = 0.0F;
   private float crabSize = 0.0F;
   public ModelPart group;
   public ModelPart body;
   public ModelPart rightClaw;
   public ModelPart leftClaw;
   public ModelPart rightLeg1;
   public ModelPart rightLeg2;
   public ModelPart rightLeg3;
   public ModelPart rightLeg4;
   public ModelPart leftLeg1;
   public ModelPart leftLeg2;
   public ModelPart leftLeg3;
   public ModelPart leftLeg4;
   public ModelPart rightEye;
   public ModelPart leftEye;
   private final Set leftLegs;
   private final Set rightLegs;

   public CrabEntityModel() {
      this.field_17138 = 32;
      this.field_17139 = 32;
      this.group = new ModelPart(this);
      this.group.method_2851(0.0F, 0.0F, 0.0F);
      this.leftLeg4 = new ModelPart(this, 0, 19);
      this.leftLeg4.field_3666 = true;
      this.leftLeg4.method_2851(3.0F, 20.0F, -1.0F);
      this.leftLeg4.method_2856(0.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.leftLeg4, 0.0F, 0.43633232F, 0.7853982F);
      this.leftLeg3 = new ModelPart(this, 0, 19);
      this.leftLeg3.field_3666 = true;
      this.leftLeg3.method_2851(3.0F, 20.0F, 0.0F);
      this.leftLeg3.method_2856(0.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.leftLeg3, 0.0F, 0.21816616F, 0.7853982F);
      this.rightEye = new ModelPart(this, 0, 11);
      this.rightEye.method_2851(0.0F, 0.0F, 0.0F);
      this.rightEye.method_2856(-3.0F, -3.5F, -2.85F, 1.0F, 3.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.rightEye, -0.3926991F, 0.0F, 0.0F);
      this.rightLeg4 = new ModelPart(this, 0, 19);
      this.rightLeg4.method_2851(-3.0F, 20.0F, -1.0F);
      this.rightLeg4.method_2856(-6.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.rightLeg4, 0.0F, -0.43633232F, -0.7853982F);
      this.rightClaw = new ModelPart(this, 14, 11);
      this.rightClaw.method_2851(-3.0F, 20.0F, -4.0F);
      this.rightClaw.method_2856(-3.0F, -2.5F, -6.0F, 3.0F, 5.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.rightClaw, 0.0F, 0.3926991F, -0.3926991F);
      this.leftLeg1 = new ModelPart(this, 0, 19);
      this.leftLeg1.field_3666 = true;
      this.leftLeg1.method_2851(3.0F, 20.0F, 2.0F);
      this.leftLeg1.method_2856(0.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.leftLeg1, 0.0F, -0.43633232F, 0.7853982F);
      this.rightLeg2 = new ModelPart(this, 0, 19);
      this.rightLeg2.method_2851(-3.0F, 20.0F, 0.9F);
      this.rightLeg2.method_2856(-6.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.rightLeg2, 0.0F, 0.21816616F, -0.7853982F);
      this.leftClaw = new ModelPart(this, 14, 11);
      this.leftClaw.field_3666 = true;
      this.leftClaw.method_2851(3.0F, 20.0F, -4.0F);
      this.leftClaw.method_2856(0.0F, -2.5F, -6.0F, 3.0F, 5.0F, 6.0F, 0.0F);
      this.setRotateAngle(this.leftClaw, 0.0F, -0.3926991F, 0.3926991F);
      this.rightLeg1 = new ModelPart(this, 0, 19);
      this.rightLeg1.method_2851(-3.0F, 20.0F, 2.0F);
      this.rightLeg1.method_2856(-6.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.rightLeg1, 0.0F, 0.43633232F, -0.7853982F);
      this.body = new ModelPart(this, 0, 0);
      this.body.method_2851(0.0F, 20.0F, 0.0F);
      this.body.method_2856(-4.0F, -2.5F, -3.0F, 8.0F, 5.0F, 6.0F, 0.0F);
      this.leftEye = new ModelPart(this, 0, 11);
      this.leftEye.method_2851(0.0F, 0.0F, 0.0F);
      this.leftEye.method_2856(2.0F, -3.5F, -2.85F, 1.0F, 3.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.leftEye, -0.3926991F, 0.0F, 0.0F);
      this.leftLeg2 = new ModelPart(this, 0, 19);
      this.leftLeg2.field_3666 = true;
      this.leftLeg2.method_2851(3.0F, 20.0F, 0.9F);
      this.leftLeg2.method_2856(0.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.leftLeg2, 0.0F, -0.21816616F, 0.7853982F);
      this.rightLeg3 = new ModelPart(this, 0, 19);
      this.rightLeg3.method_2851(-3.0F, 20.0F, 0.0F);
      this.rightLeg3.method_2856(-6.0F, -0.5F, -0.5F, 6.0F, 1.0F, 1.0F, 0.0F);
      this.setRotateAngle(this.rightLeg3, 0.0F, -0.21816616F, -0.7853982F);
      this.body.method_2845(this.rightEye);
      this.body.method_2845(this.leftEye);
      this.group.method_2845(this.rightLeg1);
      this.group.method_2845(this.rightLeg2);
      this.group.method_2845(this.rightLeg3);
      this.group.method_2845(this.rightLeg4);
      this.group.method_2845(this.leftLeg1);
      this.group.method_2845(this.leftLeg2);
      this.group.method_2845(this.leftLeg3);
      this.group.method_2845(this.leftLeg4);
      this.group.method_2845(this.rightClaw);
      this.group.method_2845(this.leftClaw);
      this.group.method_2845(this.body);
      this.leftLegs = ImmutableSet.of(this.leftLeg1, this.leftLeg2, this.leftLeg3, this.leftLeg4);
      this.rightLegs = ImmutableSet.of(this.rightLeg1, this.rightLeg2, this.rightLeg3, this.rightLeg4);
   }

   public void setRotateAngle(ModelPart modelRenderer, float x, float y, float z) {
      modelRenderer.field_3654 = x;
      modelRenderer.field_3675 = y;
      modelRenderer.field_3674 = z;
   }

   public void setAngles(CrabEntity crab, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
      this.rightLeg1.field_3674 = -0.2618F + (-1.0F + MathHelper.method_15362(limbAngle * 0.6662F)) * 0.7F * limbDistance;
      this.rightLeg2.field_3674 = -0.5236F + (-1.0F + MathHelper.method_15362(limbAngle * 0.6662F + 3.1415927F)) * 0.7F * limbDistance;
      this.rightLeg3.field_3674 = -0.5236F + (-1.0F + MathHelper.method_15362(limbAngle * 0.6662F)) * 0.7F * limbDistance;
      this.rightLeg4.field_3674 = -0.2618F + (-1.0F + MathHelper.method_15362(limbAngle * 0.6662F + 3.1415927F)) * 0.7F * limbDistance;
      this.leftLeg1.field_3674 = 0.2618F + (1.0F + MathHelper.method_15362(limbAngle * 0.6662F + 3.1415927F)) * 0.7F * limbDistance;
      this.leftLeg2.field_3674 = 0.5236F + (1.0F + MathHelper.method_15362(limbAngle * 0.6662F)) * 0.7F * limbDistance;
      this.leftLeg3.field_3674 = 0.5236F + (1.0F + MathHelper.method_15362(limbAngle * 0.6662F + 3.1415927F)) * 0.7F * limbDistance;
      this.leftLeg4.field_3674 = 0.2618F + (1.0F + MathHelper.method_15362(limbAngle * 0.6662F)) * 0.7F * limbDistance;
      this.leftClaw.field_3654 = 0.0F;
      this.rightClaw.field_3654 = 0.0F;
      this.wiggleX = 0.0F;
      this.wiggleY = 0.0F;
      this.crabSize = crab.getSizeModifier();
      if (this.field_3448) {
         this.crabSize /= 2.0F;
      }

      if (crab.isRaving()) {
         float crabRaveBPM = 31.25F;
         float freq = 20.0F / crabRaveBPM;
         float tick = animationProgress * freq;
         float sin = (float)(Math.sin((double)tick) * 0.5D + 0.5D);
         float legRot = sin * 0.8F + 0.6F;
         this.leftLegs.forEach((l) -> {
            l.field_3674 = legRot;
         });
         this.rightLegs.forEach((l) -> {
            l.field_3674 = -legRot;
         });
         float maxHeight = -0.05F;
         float horizontalOff = 0.2F;
         this.wiggleX = (sin - 0.5F) * 2.0F * maxHeight + maxHeight / 2.0F;
         float slowSin = (float)Math.sin((double)(tick / 2.0F));
         this.wiggleY = slowSin * horizontalOff;
         float armRot = sin * 0.5F - 1.2F;
         this.leftClaw.field_3654 = armRot;
         this.rightClaw.field_3654 = armRot;
      }

   }

   public void method_2828(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
      matrices.method_22903();
      matrices.method_22904(0.0D, 1.5D - (double)this.crabSize * 1.5D, 0.0D);
      matrices.method_22905(this.crabSize, this.crabSize, this.crabSize);
      matrices.method_22907(Vector3f.field_20705.method_23214(90.0F));
      matrices.method_22904((double)this.wiggleX, (double)this.wiggleY, 0.0D);
      this.group.method_22699(matrices, vertices, light, overlay, red, green, blue, alpha);
      matrices.method_22909();
   }
}
