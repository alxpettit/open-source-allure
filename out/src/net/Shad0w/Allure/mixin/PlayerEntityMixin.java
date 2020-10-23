package net.Shad0w.Allure.mixin;

import net.Shad0w.Allure.Allure;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({PlayerEntity.class})
public abstract class PlayerEntityMixin extends LivingEntity {
   @Shadow
   @Final
   public PlayerInventory field_7514;

   protected PlayerEntityMixin(EntityType entityType_1, World world_1) {
      super(entityType_1, world_1);
   }

   @Shadow
   protected abstract void method_7293();

   @Shadow
   public abstract Text method_5477();

   @Inject(
      at = {@At("HEAD")},
      method = {"dropInventory"},
      cancellable = true
   )
   private void dropInventory(CallbackInfo info) {
      if (!this.field_6002.method_8450().method_8355(GameRules.field_19389) && !this.field_7514.method_5442() && Allure.CONFIG.gravestonesEnabled) {
         this.method_7293();
         info.cancel();
      }

   }
}
