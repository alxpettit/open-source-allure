package net.Shad0w.Allure.mixin;

import net.Shad0w.Allure.Init.AllureBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({PistonHandler.class})
public abstract class PistonHandlerMixin {
   @Inject(
      method = {"isBlockSticky"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void isBlockSticky(Block block, CallbackInfoReturnable ci) {
      if (block == AllureBlocks.RED_SLIME_BLOCK || block == AllureBlocks.BLUE_SLIME_BLOCK || block == AllureBlocks.CYAN_SLIME_BLOCK || block == AllureBlocks.MAGENTA_SLIME_BLOCK || block == AllureBlocks.YELLOW_SLIME_BLOCK) {
         ci.setReturnValue(true);
      }

   }

   @Inject(
      method = {"isAdjacentBlockStuck"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void isAdjacentBlockStuck(Block block, Block block2, CallbackInfoReturnable ci) {
      if (block != Blocks.field_21211 || block2 != AllureBlocks.RED_SLIME_BLOCK && block2 != AllureBlocks.BLUE_SLIME_BLOCK && block2 != AllureBlocks.CYAN_SLIME_BLOCK && block2 != AllureBlocks.YELLOW_SLIME_BLOCK && block2 != AllureBlocks.MAGENTA_SLIME_BLOCK) {
         if (block == Blocks.field_10030 && (block2 == AllureBlocks.RED_SLIME_BLOCK || block2 == AllureBlocks.BLUE_SLIME_BLOCK || block2 == AllureBlocks.MAGENTA_SLIME_BLOCK)) {
            ci.setReturnValue(false);
         } else if (block != AllureBlocks.RED_SLIME_BLOCK || block2 != Blocks.field_10030 && block2 != Blocks.field_21211 && block2 != AllureBlocks.BLUE_SLIME_BLOCK && block2 != AllureBlocks.CYAN_SLIME_BLOCK) {
            if (block != AllureBlocks.BLUE_SLIME_BLOCK || block2 != AllureBlocks.RED_SLIME_BLOCK && block2 != Blocks.field_10030 && block2 != Blocks.field_21211 && block2 != AllureBlocks.YELLOW_SLIME_BLOCK) {
               if (block == AllureBlocks.CYAN_SLIME_BLOCK && (block2 == AllureBlocks.RED_SLIME_BLOCK || block2 == Blocks.field_21211 || block2 == AllureBlocks.YELLOW_SLIME_BLOCK || block2 == AllureBlocks.MAGENTA_SLIME_BLOCK)) {
                  ci.setReturnValue(false);
               } else if (block != AllureBlocks.MAGENTA_SLIME_BLOCK || block2 != Blocks.field_10030 && block2 != Blocks.field_21211 && block2 != AllureBlocks.YELLOW_SLIME_BLOCK && block2 != AllureBlocks.CYAN_SLIME_BLOCK) {
                  if (block == AllureBlocks.YELLOW_SLIME_BLOCK && (block2 == AllureBlocks.BLUE_SLIME_BLOCK || block2 == Blocks.field_21211 || block2 == AllureBlocks.CYAN_SLIME_BLOCK || block2 == AllureBlocks.MAGENTA_SLIME_BLOCK)) {
                     ci.setReturnValue(false);
                  }
               } else {
                  ci.setReturnValue(false);
               }
            } else {
               ci.setReturnValue(false);
            }
         } else {
            ci.setReturnValue(false);
         }
      } else {
         ci.setReturnValue(false);
      }

   }
}
