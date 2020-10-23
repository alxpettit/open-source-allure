package net.Shad0w.Allure.mixin;

import net.Shad0w.Allure.Allure;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.EquipmentSlot.Type;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({PlayerInventory.class})
public abstract class PlayerInventoryMixin {
   @Shadow
   @Final
   public DefaultedList field_7548;
   @Shadow
   @Final
   public PlayerEntity field_7546;

   @Inject(
      method = {"damageArmor"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void damageArmor(DamageSource damageSource, float float_1, CallbackInfo ci) {
      if (float_1 > 0.0F) {
         float_1 /= 4.0F;
         if (float_1 < 1.0F) {
            float_1 = 1.0F;
         }

         for(int int_1 = 0; int_1 < this.field_7548.size(); ++int_1) {
            ItemStack itemStack_1 = (ItemStack)this.field_7548.get(int_1);
            if (itemStack_1.method_7909() instanceof ArmorItem) {
               CompoundTag compoundTag_1 = new CompoundTag();
               compoundTag_1.method_10582("id", "allure:rusting_curse");
               compoundTag_1.method_10575("lvl", (short)1);
               if (itemStack_1.method_7921().contains(compoundTag_1) && Allure.CONFIG.cursesEnabled) {
                  itemStack_1.method_7956((int)float_1 * 8, this.field_7546, (playerEntity_1) -> {
                     playerEntity_1.method_20235(EquipmentSlot.method_20234(Type.field_6178, int_1));
                  });
               } else {
                  itemStack_1.method_7956((int)float_1, this.field_7546, (playerEntity_1) -> {
                     playerEntity_1.method_20235(EquipmentSlot.method_20234(Type.field_6178, int_1));
                  });
               }
            }
         }
      }

      ci.cancel();
   }
}
