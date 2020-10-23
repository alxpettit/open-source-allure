package net.Shad0w.Allure.Entity.ChestBoatEntity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;

@Environment(EnvType.CLIENT)
public class ChestBoatEntityRenderer extends EntityRenderer {
   private static final Identifier[] TEXTURES = new Identifier[]{new Identifier("textures/entity/boat/oak.png"), new Identifier("textures/entity/boat/spruce.png"), new Identifier("textures/entity/boat/birch.png"), new Identifier("textures/entity/boat/jungle.png"), new Identifier("textures/entity/boat/acacia.png"), new Identifier("textures/entity/boat/dark_oak.png")};
   protected final ChestBoatEntityModel model = new ChestBoatEntityModel();

   public ChestBoatEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
      super(entityRenderDispatcher);
      this.field_4673 = 0.8F;
   }

   public void render(ChestBoatEntity boatEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
      matrixStack.method_22903();
      matrixStack.method_22904(0.0D, 0.375D, 0.0D);
      matrixStack.method_22907(Vector3f.field_20705.method_23214(180.0F - f));
      float h = (float)boatEntity.method_7533() - g;
      float j = boatEntity.method_7554() - g;
      if (j < 0.0F) {
         j = 0.0F;
      }

      if (h > 0.0F) {
         matrixStack.method_22907(Vector3f.field_20703.method_23214(MathHelper.method_15374(h) * h * j / 10.0F * (float)boatEntity.method_7543()));
      }

      float k = boatEntity.method_7547(g);
      if (!MathHelper.method_15347(k, 0.0F)) {
         matrixStack.method_22907(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), boatEntity.method_7547(g), true));
      }

      if (Blocks.field_10034.method_9564().method_26217() != BlockRenderType.field_11455) {
         matrixStack.method_22903();
         matrixStack.method_22905(0.75F, 0.75F, 0.75F);
         matrixStack.method_22904(-0.5D, -0.25D, 0.1D);
         matrixStack.method_22907(Vector3f.field_20705.method_23214(0.0F));
         this.renderBlock(boatEntity, g, Blocks.field_10034.method_9564(), matrixStack, vertexConsumerProvider, i);
         matrixStack.method_22909();
      }

      matrixStack.method_22905(-1.0F, -1.0F, 1.0F);
      matrixStack.method_22907(Vector3f.field_20705.method_23214(90.0F));
      this.model.setAngles(boatEntity, g, 0.0F, -0.1F, 0.0F, 0.0F);
      VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.method_23500(this.getTexture(boatEntity)));
      this.model.method_2828(matrixStack, vertexConsumer, i, OverlayTexture.field_21444, 1.0F, 1.0F, 1.0F, 1.0F);
      if (!boatEntity.method_5869()) {
         VertexConsumer vertexConsumer2 = vertexConsumerProvider.getBuffer(RenderLayer.method_23589());
         this.model.getBottom().method_22698(matrixStack, vertexConsumer2, i, OverlayTexture.field_21444);
      }

      matrixStack.method_22909();
      super.method_3936(boatEntity, f, g, matrixStack, vertexConsumerProvider, i);
   }

   public Identifier getTexture(ChestBoatEntity boatEntity) {
      return TEXTURES[boatEntity.method_7536().ordinal()];
   }

   protected void renderBlock(ChestBoatEntity abstractMinecartEntity, float delta, BlockState state, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
      MinecraftClient.method_1551().method_1541().method_3353(state, matrixStack, vertexConsumerProvider, i, OverlayTexture.field_21444);
   }
}
