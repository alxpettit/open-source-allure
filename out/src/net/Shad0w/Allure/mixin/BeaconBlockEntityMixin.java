package net.Shad0w.Allure.mixin;

import java.util.Iterator;
import java.util.List;
import net.Shad0w.Allure.Allure;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({BeaconBlockEntity.class})
public class BeaconBlockEntityMixin extends BlockEntity {
   @Shadow
   private StatusEffect field_11795;
   @Shadow
   private int field_11803;
   @Shadow
   private StatusEffect field_11799;

   public BeaconBlockEntityMixin(BlockEntityType blockEntityType_1) {
      super(blockEntityType_1);
   }

   @Inject(
      method = {"applyPlayerEffects"},
      at = {@At("HEAD")}
   )
   private void applyPlayerEffects(CallbackInfo ci) {
      if (!this.field_11863.field_9236 && this.field_11795 != null && Allure.CONFIG.beaconsHealPets) {
         double d = (double)(this.field_11803 * 10 + 10);
         int i = false;
         if (this.field_11803 >= 4 && this.field_11795 == this.field_11799) {
            i = true;
         }

         int j = (9 + this.field_11803 * 2) * 20;
         Box box = (new Box(this.field_11867)).method_1014(d).method_1012(0.0D, (double)this.field_11863.method_8322(), 0.0D);
         List list_2;
         Iterator var8;
         if (this.field_11799 == StatusEffects.field_5924) {
            list_2 = this.field_11863.method_18467(TameableEntity.class, box);
            var8 = list_2.iterator();

            while(var8.hasNext()) {
               TameableEntity tameableEntity = (TameableEntity)var8.next();
               tameableEntity.method_6092(new StatusEffectInstance(this.field_11799, j, 0, true, true));
            }

            List list_3 = this.field_11863.method_18467(PassiveEntity.class, box);
            Iterator var9 = list_3.iterator();

            while(var9.hasNext()) {
               PassiveEntity passiveEntity = (PassiveEntity)var9.next();
               passiveEntity.method_6092(new StatusEffectInstance(this.field_11799, j, 0, true, true));
            }
         }

         if (this.field_11795 == StatusEffects.field_5910) {
            list_2 = this.field_11863.method_18467(HostileEntity.class, box);
            var8 = list_2.iterator();

            while(var8.hasNext()) {
               HostileEntity hostileEntity = (HostileEntity)var8.next();
               hostileEntity.method_6092(new StatusEffectInstance(StatusEffects.field_5911, j, 0, true, true));
            }
         }
      }

   }
}
