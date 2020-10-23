package net.Shad0w.Allure.Blocks.Crate;

import net.Shad0w.Allure.Allure;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.Matrix4f;

public class CrateBlockEntityRenderer extends BlockEntityRenderer {
   protected final BlockEntityRenderDispatcher renderManager;

   public CrateBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
      super(dispatcher);
      this.renderManager = dispatcher;
   }

   public void render(CrateBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
      if (blockEntity.method_16914() || (Boolean)blockEntity.method_11010().method_11654(CrateBlock.SEALED)) {
         Object string;
         if (blockEntity.method_16914()) {
            string = blockEntity.method_5476();
         } else {
            string = new LiteralText((new TranslatableText("block.allure.abandoned")).getString() + " " + blockEntity.getGoodName(blockEntity.method_11010()).getString());
         }

         double d = this.renderManager.field_4344.method_19326().method_1028((double)blockEntity.method_11016().method_10263(), (double)blockEntity.method_11016().method_10264(), (double)blockEntity.method_11016().method_10260());
         if (d <= 4096.0D) {
            matrices.method_22903();
            matrices.method_22904(0.5D, 1.5D, 0.5D);
            matrices.method_22907(this.renderManager.field_4344.method_23767());
            matrices.method_22905(-0.025F, -0.025F, 0.025F);
            Matrix4f matrix4f = matrices.method_23760().method_23761();
            float g = MinecraftClient.method_1551().field_1690.method_19343(0.25F);
            int k = (int)(g * 255.0F) << 24;
            TextRenderer textRenderer = this.renderManager.method_3556();
            float h = (float)(-textRenderer.method_27525((StringVisitable)string) / 2);
            int lightAbove = WorldRenderer.method_23794(blockEntity.method_10997(), blockEntity.method_11016().method_10084());
            if (d <= 1024.0D) {
               textRenderer.method_30882((Text)string, h, 0.0F, 553648127, false, matrix4f, vertexConsumers, Allure.CONFIG.renderCrateName, k, lightAbove);
            }

            textRenderer.method_30882((Text)string, h, 0.0F, -1, false, matrix4f, vertexConsumers, false, 0, lightAbove);
            matrices.method_22909();
         }
      }

   }
}
