package net.Shad0w.Allure.mixin;

import net.Shad0w.Allure.Allure;
import net.Shad0w.Allure.Init.AllureBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ChorusFruitItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.Settings;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ChorusFruitItem.class})
public class ChorusFruitEatMixin extends Item {
   public ChorusFruitEatMixin(Settings item$Settings_1) {
      super(item$Settings_1);
   }

   @Inject(
      at = {@At("HEAD")},
      method = {"finishUsing"},
      cancellable = true
   )
   private void finishUsing(ItemStack itemStack_1, World world_1, LivingEntity livingEntity_1, CallbackInfoReturnable info) {
      if (Allure.CONFIG.chorusTPEnabled) {
         for(int x = -16; x <= 16; ++x) {
            for(int z = -16; z <= 16; ++z) {
               for(int y = -16; y <= 16; ++y) {
                  if (x != 0 || y != 0 || z != 0) {
                     BlockPos pos = livingEntity_1.method_24515().method_10069(x, y, z);
                     Block block = world_1.method_8320(pos).method_26204();
                     if (block.equals(AllureBlocks.ENDER_PEARL_BLOCK) && world_1.method_8320(pos.method_10069(0, 1, 0)).method_26204().equals(Blocks.field_10124) && world_1.method_8320(pos.method_10069(0, 2, 0)).method_26204().equals(Blocks.field_10124)) {
                        ItemStack itemStack_2 = super.method_7861(itemStack_1, world_1, livingEntity_1);
                        if (livingEntity_1.method_6082((double)pos.method_10263() + 0.5D, (double)(pos.method_10264() + 1), (double)pos.method_10260() + 0.5D, true)) {
                           world_1.method_8465((PlayerEntity)null, (double)pos.method_10263() + 0.5D, (double)(pos.method_10264() + 1), (double)pos.method_10260() + 0.5D, SoundEvents.field_14890, SoundCategory.field_15248, 1.0F, 1.0F);
                           livingEntity_1.method_5783(SoundEvents.field_14890, 1.0F, 1.0F);
                           if (livingEntity_1 instanceof PlayerEntity) {
                              ((PlayerEntity)livingEntity_1).method_7357().method_7906(this, 20);
                           }

                           info.setReturnValue(itemStack_2);
                        }
                     }
                  }
               }
            }
         }
      }

   }
}
