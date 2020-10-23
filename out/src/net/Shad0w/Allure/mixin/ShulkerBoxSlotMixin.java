package net.Shad0w.Allure.mixin;

import net.Shad0w.Allure.Blocks.Crate.CrateBlock;
import net.minecraft.block.Block;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.ShulkerBoxSlot;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ShulkerBoxSlot.class})
public abstract class ShulkerBoxSlotMixin extends Slot {
   public ShulkerBoxSlotMixin(Inventory inventory, int index, int x, int y) {
      super(inventory, index, x, y);
   }

   @Inject(
      method = {"canInsert"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void canInsert(ItemStack itemStack_1, CallbackInfoReturnable ci) {
      if (Block.method_9503(itemStack_1.method_7909()) instanceof CrateBlock) {
         ci.setReturnValue(false);
      }

   }
}
