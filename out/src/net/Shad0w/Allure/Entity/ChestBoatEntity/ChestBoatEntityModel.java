package net.Shad0w.Allure.Entity.ChestBoatEntity;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import java.util.Arrays;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.util.math.MathHelper;

public class ChestBoatEntityModel extends CompositeEntityModel {
   private final ModelPart[] paddles = new ModelPart[2];
   private final ModelPart bottom;
   private final ImmutableList parts;

   public ChestBoatEntityModel() {
      ModelPart[] modelParts = new ModelPart[]{(new ModelPart(this, 0, 0)).method_2853(128, 64), (new ModelPart(this, 0, 19)).method_2853(128, 64), (new ModelPart(this, 0, 27)).method_2853(128, 64), (new ModelPart(this, 0, 35)).method_2853(128, 64), (new ModelPart(this, 0, 43)).method_2853(128, 64)};
      modelParts[0].method_2856(-14.0F, -9.0F, -3.0F, 28.0F, 16.0F, 3.0F, 0.0F);
      modelParts[0].method_2851(0.0F, 3.0F, 1.0F);
      modelParts[1].method_2856(-13.0F, -7.0F, -1.0F, 18.0F, 6.0F, 2.0F, 0.0F);
      modelParts[1].method_2851(-15.0F, 4.0F, 4.0F);
      modelParts[2].method_2856(-8.0F, -7.0F, -1.0F, 16.0F, 6.0F, 2.0F, 0.0F);
      modelParts[2].method_2851(15.0F, 4.0F, 0.0F);
      modelParts[3].method_2856(-14.0F, -7.0F, -1.0F, 28.0F, 6.0F, 2.0F, 0.0F);
      modelParts[3].method_2851(0.0F, 4.0F, -9.0F);
      modelParts[4].method_2856(-14.0F, -7.0F, -1.0F, 28.0F, 6.0F, 2.0F, 0.0F);
      modelParts[4].method_2851(0.0F, 4.0F, 9.0F);
      modelParts[0].field_3654 = 1.5707964F;
      modelParts[1].field_3675 = 4.712389F;
      modelParts[2].field_3675 = 1.5707964F;
      modelParts[3].field_3675 = 3.1415927F;
      this.paddles[0] = this.makePaddle(true);
      this.paddles[0].method_2851(3.0F, -5.0F, 9.0F);
      this.paddles[1] = this.makePaddle(false);
      this.paddles[1].method_2851(3.0F, -5.0F, -9.0F);
      this.paddles[1].field_3675 = 3.1415927F;
      this.paddles[0].field_3674 = 0.19634955F;
      this.paddles[1].field_3674 = 0.19634955F;
      this.bottom = (new ModelPart(this, 0, 0)).method_2853(128, 64);
      this.bottom.method_2856(-14.0F, -9.0F, -3.0F, 28.0F, 16.0F, 3.0F, 0.0F);
      this.bottom.method_2851(0.0F, -3.0F, 1.0F);
      this.bottom.field_3654 = 1.5707964F;
      Builder builder = ImmutableList.builder();
      builder.addAll(Arrays.asList(modelParts));
      builder.addAll(Arrays.asList(this.paddles));
      this.parts = builder.build();
   }

   public void setAngles(ChestBoatEntity boatEntity, float f, float g, float h, float i, float j) {
      this.setPaddleAngle(boatEntity, 0, f);
      this.setPaddleAngle(boatEntity, 1, f);
   }

   public ImmutableList getParts() {
      return this.parts;
   }

   public ModelPart getBottom() {
      return this.bottom;
   }

   protected ModelPart makePaddle(boolean isLeft) {
      ModelPart modelPart = (new ModelPart(this, 62, isLeft ? 0 : 20)).method_2853(128, 64);
      float f = -5.0F;
      modelPart.method_2844(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F);
      modelPart.method_2844(isLeft ? -1.001F : 0.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F);
      return modelPart;
   }

   protected void setPaddleAngle(ChestBoatEntity boat, int paddle, float angle) {
      float f = boat.method_7551(paddle, angle);
      ModelPart modelPart = this.paddles[paddle];
      modelPart.field_3654 = (float)MathHelper.method_15390(-1.0471975803375244D, -0.2617993950843811D, (double)((MathHelper.method_15374(-f) + 1.0F) / 2.0F));
      modelPart.field_3675 = (float)MathHelper.method_15390(-0.7853981852531433D, 0.7853981852531433D, (double)((MathHelper.method_15374(-f + 1.0F) + 1.0F) / 2.0F));
      if (paddle == 1) {
         modelPart.field_3675 = 3.1415927F - modelPart.field_3675;
      }

   }
}
