package net.Shad0w.Allure.Utils.Chain;

import io.netty.util.collection.IntObjectHashMap;
import io.netty.util.collection.IntObjectMap;
import java.util.Iterator;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.world.LightType;

public class ChainRenderer {
   private static final IntObjectMap RENDER_MAP = new IntObjectHashMap();

   private static void renderLeash(Entity cart, float yaw2, float partialTicks, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, Entity holder) {
      if (holder != null && holder != null) {
         boolean player = holder instanceof PlayerEntity;
         double yaw = (double)MathHelper.method_16439(partialTicks * 0.5F, holder.field_5982, holder.field_6031) * 3.141592653589793D / 180.0D;
         double pitch = (double)MathHelper.method_16439(partialTicks * 0.5F, holder.field_6004, holder.field_5965) * 3.141592653589793D / 180.0D;
         double rotX = Math.cos(yaw);
         double rotZ = Math.sin(yaw);
         double rotY = Math.sin(pitch);
         float xLocus = (float)MathHelper.method_16436((double)partialTicks, prevX(holder), holder.method_19538().method_10216());
         float yLocus = (float)MathHelper.method_16436((double)partialTicks, prevY(holder), holder.method_19538().method_10214());
         float zLocus = (float)MathHelper.method_16436((double)partialTicks, prevZ(holder), holder.method_19538().method_10215());
         if (player) {
            xLocus = (float)((double)xLocus + rotX);
            zLocus = (float)((double)zLocus + rotZ);
            yLocus = (float)((double)yLocus + 1.3D);
         }

         float targetX = (float)MathHelper.method_16436((double)partialTicks, prevX(cart), cart.method_19538().method_10216());
         float targetY = (float)MathHelper.method_16436((double)partialTicks, prevY(cart), cart.method_19538().method_10214());
         float targetZ = (float)MathHelper.method_16436((double)partialTicks, prevZ(cart), cart.method_19538().method_10215());
         if (player) {
            xLocus = (float)((double)xLocus - rotX);
            zLocus = (float)((double)zLocus - rotZ);
         }

         float offsetX = xLocus - targetX;
         float offsetY = yLocus - targetY;
         float offsetZ = zLocus - targetZ;
         VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.method_23587());
         int lightAtEntity = getBlockLight(holder, partialTicks);
         int lightAtOther = getBlockLight(holder, partialTicks);
         int skyLightAtEntity = holder.field_6002.method_8314(LightType.field_9284, new BlockPos(holder.method_5836(partialTicks)));
         int skyLightAtOther = holder.field_6002.method_8314(LightType.field_9284, new BlockPos(holder.method_5836(partialTicks)));
         float mag = MathHelper.method_22858(offsetX * offsetX + offsetZ * offsetZ) * 0.025F / 2.0F;
         float zMag = offsetZ * mag;
         float xMag = offsetX * mag;
         matrices.method_22903();
         matrices.method_22904(0.0D, 0.10000000149011612D, 0.0D);
         Matrix4f matrix4f = matrices.method_23760().method_23761();
         renderSide(vertexConsumer, matrix4f, offsetX, offsetY, offsetZ, lightAtEntity, lightAtOther, skyLightAtEntity, skyLightAtOther, 0.025F, 0.025F, zMag, xMag);
         renderSide(vertexConsumer, matrix4f, offsetX, offsetY, offsetZ, lightAtEntity, lightAtOther, skyLightAtEntity, skyLightAtOther, 0.025F, 0.0F, zMag, xMag);
         matrices.method_22909();
      }

   }

   private static int getBlockLight(Entity entityIn, float partialTicks) {
      return entityIn.method_5809() ? 15 : entityIn.field_6002.method_8314(LightType.field_9282, new BlockPos(entityIn.method_5836(partialTicks)));
   }

   public static void renderSide(VertexConsumer vertexBuilder, Matrix4f matrix, float dX, float dY, float dZ, int lightAtEntity, int lightAtOther, int skyLightAtEntity, int skyLightAtOther, float width, float rotation, float xMag, float zMag) {
      for(int stepIdx = 0; stepIdx < 24; ++stepIdx) {
         float step = (float)stepIdx / 23.0F;
         int brightness = (int)MathHelper.method_16439(step, (float)lightAtEntity, (float)lightAtOther);
         int skyBrightness = (int)MathHelper.method_16439(step, (float)skyLightAtEntity, (float)skyLightAtOther);
         int light = LightmapTextureManager.method_23687(brightness, skyBrightness);
         addVertexPair(vertexBuilder, matrix, light, dX, dY, dZ, width, rotation, 24, stepIdx, false, xMag, zMag);
         addVertexPair(vertexBuilder, matrix, light, dX, dY, dZ, width, rotation, 24, stepIdx + 1, true, xMag, zMag);
      }

   }

   public static void addVertexPair(VertexConsumer vertexBuilder, Matrix4f matrix, int light, float dX, float dY, float dZ, float width, float rotation, int steps, int stepIdx, boolean leading, float xMag, float zMag) {
      float r = 0.3F;
      float g = 0.3F;
      float b = 0.3F;
      if (stepIdx % 2 == 0) {
         r *= 0.7F;
         g *= 0.7F;
         b *= 0.7F;
      }

      float step = (float)stepIdx / (float)steps;
      float x = dX * step;
      float y = dY * (step * step + step) * 0.5F;
      float z = dZ * step;
      if (!leading) {
         vertexBuilder.method_22918(matrix, x + xMag, y + width - rotation, z - zMag).method_22915(r, g, b, 1.0F).method_22916(light).method_1344();
      }

      vertexBuilder.method_22918(matrix, x - xMag, y + rotation, z + zMag).method_22915(r, g, b, 1.0F).method_22916(light).method_1344();
      if (leading) {
         vertexBuilder.method_22918(matrix, x + xMag, y + width - rotation, z - zMag).method_22915(r, g, b, 1.0F).method_22916(light).method_1344();
      }

   }

   public static void renderChain(Entity entity, MatrixStack matrixStack, VertexConsumerProvider renderBuffer, float partTicks) {
      if (ChainHandler.canBeLinked(entity)) {
         Entity holder = (Entity)RENDER_MAP.get(entity.method_5628());
         if (holder != null) {
            System.out.println("sdklffudigyfdu");
            renderLeash(entity, 0.0F, partTicks, matrixStack, renderBuffer, 0, holder);
         }
      }

   }

   private static double prevX(Entity entity) {
      return entity instanceof AbstractMinecartEntity ? entity.field_6038 : entity.field_6014;
   }

   private static double prevY(Entity entity) {
      return entity instanceof AbstractMinecartEntity ? entity.field_5971 : entity.field_6036;
   }

   private static double prevZ(Entity entity) {
      return entity instanceof AbstractMinecartEntity ? entity.field_5989 : entity.field_5969;
   }

   public static void updateTick() {
      RENDER_MAP.clear();
      ClientWorld world = MinecraftClient.method_1551().field_1687;
      if (world != null) {
         Iterator var1 = world.method_18112().iterator();

         while(var1.hasNext()) {
            Entity entity = (Entity)var1.next();
            if (ChainHandler.canBeLinked(entity)) {
               Entity other = ChainHandler.getLinked(entity);
               if (other != null) {
                  System.out.println("LOL !");
                  RENDER_MAP.put(entity.method_5628(), other);
               }
            }
         }

      }
   }
}
