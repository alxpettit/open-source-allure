package net.Shad0w.Allure.mixin;

import net.Shad0w.Allure.Allure;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({MiningToolItem.class})
public class MiningToolMixin {
   @Inject(
      at = {@At("HEAD")},
      method = {"postHit"},
      cancellable = true
   )
   private void postHit(ItemStack itemStack_1, LivingEntity livingEntity_1, LivingEntity livingEntity_2, CallbackInfoReturnable info) {
      CompoundTag compoundTag_1 = new CompoundTag();
      compoundTag_1.method_10582("id", "allure:rusting_curse");
      compoundTag_1.method_10575("lvl", (short)1);
      if (itemStack_1.method_7921().contains(compoundTag_1) && Allure.CONFIG.cursesEnabled) {
         itemStack_1.method_7956(8, livingEntity_2, (livingEntity_1x) -> {
            livingEntity_1x.method_20235(EquipmentSlot.field_6173);
         });
         info.setReturnValue(true);
      }

   }

   @Inject(
      at = {@At("HEAD")},
      method = {"postMine"},
      cancellable = true
   )
   private void postMine(ItemStack itemStack_1, World world_1, BlockState blockState_1, BlockPos blockPos_1, LivingEntity livingEntity_1, CallbackInfoReturnable info) {
      CompoundTag compoundTag_1 = new CompoundTag();
      compoundTag_1.method_10582("id", "allure:rusting_curse");
      compoundTag_1.method_10575("lvl", (short)1);
      if (itemStack_1.method_7921().contains(compoundTag_1) && Allure.CONFIG.cursesEnabled) {
         if (!world_1.field_9236 && blockState_1.method_26214(world_1, blockPos_1) != 0.0F) {
            itemStack_1.method_7956(16, livingEntity_1, (livingEntity_1x) -> {
               livingEntity_1x.method_20235(EquipmentSlot.field_6173);
            });
         }

         info.setReturnValue(true);
      }

   }
}
