package net.Shad0w.Allure.mixin;

import net.Shad0w.Allure.Utils.Chain.ChainRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.MinecartEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({MinecartEntityRenderer.class})
public abstract class MinecartEntityRendererMixin extends EntityRenderer {
   protected MinecartEntityRendererMixin(EntityRenderDispatcher dispatcher) {
      super(dispatcher);
   }

   @Inject(
      at = {@At("HEAD")},
      method = {"render"}
   )
   public void render(AbstractMinecartEntity abstractMinecartEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci) {
      ChainRenderer.renderChain(abstractMinecartEntity, matrixStack, vertexConsumerProvider, f);
   }
}
