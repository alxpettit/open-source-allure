package net.Shad0w.Allure.Blocks.Gravestone;

import net.Shad0w.Allure.Allure;
import net.Shad0w.Allure.Utils.AllureStringTag;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation.Mode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Matrix4f;

public class GravestoneBlockEntityRenderer extends BlockEntityRenderer {
   float skullYaw = 0.0F;
   float skullPitch = 0.0F;
   private boolean gotRotation = false;
   Direction gravestoneDir;

   public GravestoneBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
      super(dispatcher);
      this.gravestoneDir = Direction.field_11043;
   }

   public void render(GravestoneBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
      float h;
      if (blockEntity.method_10997().method_8320(blockEntity.method_11016()).method_28498(GravestoneBlock.FACING)) {
         ItemStack skull = FabricLoader.getInstance().isDevelopmentEnvironment() && !Allure.CONFIG.renderSkulls ? new ItemStack(Items.field_8398) : new ItemStack(Items.field_8575);
         skull.method_7980(new CompoundTag());
         if (!FabricLoader.getInstance().isDevelopmentEnvironment()) {
            skull.method_7969().method_10566("SkullOwner", new AllureStringTag(blockEntity.playername));
         }

         matrices.method_22903();
         double offset = Math.sin((double)(((float)blockEntity.method_10997().method_8510() + tickDelta) / 16.0F)) / 32.0D;
         matrices.method_22904(0.5D, 0.42D + offset, 0.5D);

         for(h = blockEntity.field_11964 - blockEntity.field_11963; h >= 3.1415927F; h -= 6.2831855F) {
         }

         while(h < -3.1415927F) {
            h += 6.2831855F;
         }

         float direction;
         if (blockEntity.field_11962 != 0.0F && blockEntity.field_11965 != 0.0F) {
            direction = blockEntity.field_11963 + h * tickDelta;
            matrices.method_22907(Vector3f.field_20705.method_23626(-direction));
            matrices.method_22907(Vector3f.field_20705.method_23626(-1.58F));
            matrices.method_22907(Vector3f.field_20703.method_23626(blockEntity.field_11965));
         } else {
            this.gravestoneDir = (Direction)blockEntity.method_10997().method_8320(blockEntity.method_11016()).method_11654(GravestoneBlock.FACING);
            direction = 0.0F;
            if (this.gravestoneDir == Direction.field_11035) {
               direction = 180.0F;
               matrices.method_22907(Vector3f.field_20705.method_23626(-3.14F));
            } else if (this.gravestoneDir == Direction.field_11034) {
               direction = 270.0F;
               matrices.method_22907(Vector3f.field_20705.method_23626(-1.57F));
            } else if (this.gravestoneDir == Direction.field_11039) {
               direction = 90.0F;
               matrices.method_22907(Vector3f.field_20705.method_23626(1.57F));
            } else if (this.gravestoneDir == Direction.field_11043) {
               matrices.method_22907(Vector3f.field_20705.method_23626(0.0F));
            }

            double offset2 = Math.sin((double)(((float)blockEntity.method_10997().method_8510() + tickDelta) / 32.0F));
            matrices.method_22907(Vector3f.field_20704.method_23626((float)offset2 + direction));
         }

         matrices.method_22905(0.75F, 0.75F, 0.75F);
         MinecraftClient.method_1551().method_1480().method_23178(skull, Mode.field_4319, light, OverlayTexture.field_21444, matrices, vertexConsumers);
         matrices.method_22909();
      }

      double d = this.field_20989.field_4344.method_19326().method_1028((double)blockEntity.method_11016().method_10263(), (double)blockEntity.method_11016().method_10264(), (double)blockEntity.method_11016().method_10260());
      if (d <= 4096.0D) {
         matrices.method_22903();
         matrices.method_22904(0.5D, 1.5D, 0.5D);
         matrices.method_22907(this.field_20989.field_4344.method_23767());
         matrices.method_22905(-0.025F, -0.025F, 0.025F);
         Matrix4f matrix4f = matrices.method_23760().method_23761();
         h = MinecraftClient.method_1551().field_1690.method_19343(0.25F);
         int k = (int)(h * 255.0F) << 24;
         TextRenderer textRenderer = this.field_20989.method_3556();
         float h = (float)(-textRenderer.method_1727(blockEntity.playername) / 2);
         int lightAbove = WorldRenderer.method_23794(blockEntity.method_10997(), blockEntity.method_11016().method_10084());
         textRenderer.method_27521(blockEntity.playername, h, 0.0F, 553648127, false, matrix4f, vertexConsumers, true, k, lightAbove);
         textRenderer.method_27521(blockEntity.playername, h, 0.0F, -1, false, matrix4f, vertexConsumers, false, 0, lightAbove);
         matrices.method_22909();
      }

   }

   private float changeAngle(float float_1, float float_2, float float_3) {
      float float_4 = MathHelper.method_15381(float_1, float_2);
      float float_5 = MathHelper.method_15363(float_4, -float_3, float_3);
      return float_1 == 0.0F ? float_1 + float_4 : float_1 + float_5;
   }

   private float getTargetYaw(double skullX, double skullZ) {
      double double_1 = MinecraftClient.method_1551().field_1724.method_23317() - skullX;
      double double_2 = MinecraftClient.method_1551().field_1724.method_23321() - skullZ;
      return (float)(MathHelper.method_15349(double_2, double_1) * 57.2957763671875D) - 90.0F;
   }

   private float getTargetPitch(double skullX, double skullY, double skullZ) {
      double double_1 = MinecraftClient.method_1551().field_1724.method_23317() - skullX;
      double double_2 = MinecraftClient.method_1551().field_1724.method_23318() + (double)MinecraftClient.method_1551().field_1724.method_5751() - skullY;
      double double_3 = MinecraftClient.method_1551().field_1724.method_23321() - skullZ;
      double double_4 = (double)MathHelper.method_15368(double_1 * double_1 + double_3 * double_3);
      return (float)(-(MathHelper.method_15349(double_2, double_4) * 57.2957763671875D));
   }
}
