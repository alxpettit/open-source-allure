package net.Shad0w.Allure.mixin;

import net.Shad0w.Allure.Entity.ChestBoatEntity.ChestBoatEntity;
import net.Shad0w.Allure.Init.AllureEntities;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.sound.MovingMinecartSoundInstance;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ClientPlayNetworkHandler.class})
public abstract class ClientPlayNetworkHandlerMixin {
   @Shadow
   private ClientWorld field_3699;
   @Shadow
   private MinecraftClient field_3690;

   @Inject(
      method = {"onEntitySpawn"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void onEntitySpawn(EntitySpawnS2CPacket packet, CallbackInfo ci) {
      NetworkThreadUtils.method_11074(packet, (ClientPlayNetworkHandler)this, this.field_3690);
      double d = packet.method_11175();
      double e = packet.method_11174();
      double f = packet.method_11176();
      EntityType entityType = packet.method_11169();
      Object entity15 = null;
      if (entityType == AllureEntities.CHEST_BOAT) {
         entity15 = new ChestBoatEntity(this.field_3699, d, e, f);
      }

      if (entity15 != null) {
         int i = packet.method_11167();
         ((Entity)entity15).method_18003(d, e, f);
         ((Entity)entity15).method_24203(d, e, f);
         ((Entity)entity15).field_5965 = (float)(packet.method_11171() * 360) / 256.0F;
         ((Entity)entity15).field_6031 = (float)(packet.method_11168() * 360) / 256.0F;
         ((Entity)entity15).method_5838(i);
         ((Entity)entity15).method_5826(packet.method_11164());
         this.field_3699.method_2942(i, (Entity)entity15);
         if (entity15 instanceof AbstractMinecartEntity) {
            this.field_3690.method_1483().method_4873(new MovingMinecartSoundInstance((AbstractMinecartEntity)entity15));
         }

         ci.cancel();
      }

   }
}
