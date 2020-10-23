package net.Shad0w.Allure.mixin;

import net.Shad0w.Allure.Entity.ChestBoatEntity.ChestBoatEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.BoatEntity.Type;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({BoatEntity.class})
public abstract class BoatEntityMixin extends Entity {
   @Shadow
   public abstract Type method_7536();

   public BoatEntityMixin(EntityType type, World world) {
      super(type, world);
   }

   @Inject(
      method = {"interact"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void interact(PlayerEntity player, Hand hand, CallbackInfoReturnable ci) {
      if (player.method_5998(hand).method_7909().equals(Items.field_8106)) {
         player.method_5998(hand).method_7934(1);
         ChestBoatEntity chestBoatEntity = new ChestBoatEntity(this.field_6002, this.method_23317(), this.method_23318(), this.method_23321());
         chestBoatEntity.method_5719(this);
         chestBoatEntity.method_18799(this.method_18798());
         chestBoatEntity.method_5665(this.method_5797());
         chestBoatEntity.method_7541(this.method_7536());
         this.method_5650();
         this.field_6002.method_8649(chestBoatEntity);
         this.method_5783(SoundEvents.field_14598, 1.0F, (this.field_5974.nextFloat() - this.field_5974.nextFloat()) * 0.2F + 1.0F);
         ci.setReturnValue(ActionResult.field_5812);
      }

   }
}
