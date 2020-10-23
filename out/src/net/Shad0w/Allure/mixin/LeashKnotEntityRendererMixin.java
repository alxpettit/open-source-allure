package net.Shad0w.Allure.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LeashKnotEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.LeashKnotEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LightType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LeashKnotEntityRenderer.class})
public class LeashKnotEntityRendererMixin {
   @Inject(
      at = {@At("RETURN")},
      method = {"render"},
      cancellable = true
   )
   public void render(LeashKnotEntity leashKnotEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
      Entity entity = MinecraftClient.method_1551().field_1724;
      if (entity != null) {
         this.method_4073(leashKnotEntity, g, matrixStack, vertexConsumerProvider, entity);
      }

   }

   private void method_4073(LeashKnotEntity mobEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, Entity entity) {
      matrixStack.method_22903();
      Vec3d vec3d = entity.method_30951(f);
      double d = (double)(MathHelper.method_16439(f, 0.0F, 0.0F) * 0.017453292F) + 1.5707963267948966D;
      Vec3d vec3d2 = mobEntity.method_29919();
      double e = Math.cos(d) * vec3d2.field_1350 + Math.sin(d) * vec3d2.field_1352;
      double g = Math.sin(d) * vec3d2.field_1350 - Math.cos(d) * vec3d2.field_1352;
      double h = MathHelper.method_16436((double)f, mobEntity.field_6014, mobEntity.method_23317()) + e;
      double i = MathHelper.method_16436((double)f, mobEntity.field_6036, mobEntity.method_23318()) + vec3d2.field_1351;
      double j = MathHelper.method_16436((double)f, mobEntity.field_5969, mobEntity.method_23321()) + g;
      matrixStack.method_22904(e, vec3d2.field_1351, g);
      float k = (float)(vec3d.field_1352 - h);
      float l = (float)(vec3d.field_1351 - i);
      float m = (float)(vec3d.field_1350 - j);
      float n = 0.025F;
      VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.method_23587());
      Matrix4f matrix4f = matrixStack.method_23760().method_23761();
      float o = MathHelper.method_22858(k * k + m * m) * 0.025F / 2.0F;
      float p = m * o;
      float q = k * o;
      BlockPos blockPos = new BlockPos(mobEntity.method_5836(f));
      BlockPos blockPos2 = new BlockPos(entity.method_5836(f));
      int r = 0;
      int s = 0;
      int t = mobEntity.field_6002.method_8314(LightType.field_9284, blockPos);
      int u = mobEntity.field_6002.method_8314(LightType.field_9284, blockPos2);
      this.method_23186(vertexConsumer, matrix4f, k, l, m, r, s, t, u, 0.025F, 0.025F, p, q);
      this.method_23186(vertexConsumer, matrix4f, k, l, m, r, s, t, u, 0.025F, 0.0F, p, q);
      matrixStack.method_22909();
   }

   public void method_23186(VertexConsumer vertexConsumer, Matrix4f matrix4f, float f, float g, float h, int i, int j, int k, int l, float m, float n, float o, float p) {
      for(int r = 0; r < 24; ++r) {
         float s = (float)r / 23.0F;
         int t = (int)MathHelper.method_16439(s, (float)i, (float)j);
         int u = (int)MathHelper.method_16439(s, (float)k, (float)l);
         int v = LightmapTextureManager.method_23687(t, u);
         this.method_23187(vertexConsumer, matrix4f, v, f, g, h, m, n, 24, r, false, o, p);
         this.method_23187(vertexConsumer, matrix4f, v, f, g, h, m, n, 24, r + 1, true, o, p);
      }

   }

   public void method_23187(VertexConsumer vertexConsumer, Matrix4f matrix4f, int i, float f, float g, float h, float j, float k, int l, int m, boolean bl, float n, float o) {
      float p = 0.5F;
      float q = 0.4F;
      float r = 0.3F;
      if (m % 2 == 0) {
         p *= 0.7F;
         q *= 0.7F;
         r *= 0.7F;
      }

      float s = (float)m / (float)l;
      float t = f * s;
      float u = g > 0.0F ? g * s * s : g - g * (1.0F - s) * (1.0F - s);
      float v = h * s;
      if (!bl) {
         vertexConsumer.method_22918(matrix4f, t + n, u + j - k, v - o).method_22915(p, q, r, 1.0F).method_22916(i).method_1344();
      }

      vertexConsumer.method_22918(matrix4f, t - n, u + k, v + o).method_22915(p, q, r, 1.0F).method_22916(i).method_1344();
      if (bl) {
         vertexConsumer.method_22918(matrix4f, t + n, u + j - k, v - o).method_22915(p, q, r, 1.0F).method_22916(i).method_1344();
      }

   }
}
