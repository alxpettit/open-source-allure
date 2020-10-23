package net.Shad0w.Allure.mixin;

import net.Shad0w.Allure.Init.AllureItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ClientPlayNetworkHandler.class})
public abstract class CustomTotemAnimationMixin implements ClientPlayPacketListener {
   @Shadow
   private MinecraftClient field_3690;
   @Shadow
   private ClientWorld field_3699;

   @Inject(
      method = {"onEntityStatus"},
      at = {@At("HEAD")}
   )
   public void onEntityStatus(EntityStatusS2CPacket entityStatusS2CPacket_1, CallbackInfo ci) {
      NetworkThreadUtils.method_11074(entityStatusS2CPacket_1, this, this.field_3690);
      Entity entity_1 = entityStatusS2CPacket_1.method_11469(this.field_3699);
      if (entity_1 != null && entityStatusS2CPacket_1.method_11470() == 55) {
         this.field_3690.field_1713.method_3051(entity_1, ParticleTypes.field_11220, 30);
         if (entity_1 == this.field_3690.field_1724) {
            this.field_3690.field_1773.method_3189(grabtotem(this.field_3690.field_1724));
         }
      }

   }

   private static ItemStack grabtotem(PlayerEntity playerEntity_1) {
      Hand[] var1 = Hand.values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         Hand hand_1 = var1[var3];
         ItemStack itemStack_1 = playerEntity_1.method_5998(hand_1);
         if (itemStack_1.method_7909() == AllureItems.TOTEM_OF_RETURNING) {
            return itemStack_1;
         }
      }

      return new ItemStack(AllureItems.TOTEM_OF_RETURNING);
   }
}
