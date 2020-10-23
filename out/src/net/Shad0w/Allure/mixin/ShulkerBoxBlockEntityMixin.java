package net.Shad0w.Allure.mixin;

import net.Shad0w.Allure.Blocks.Crate.CrateBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ShulkerBoxBlockEntity.class})
public abstract class ShulkerBoxBlockEntityMixin extends LootableContainerBlockEntity {
   protected ShulkerBoxBlockEntityMixin(BlockEntityType blockEntityType_1) {
      super(blockEntityType_1);
   }

   @Inject(
      method = {"canInsert"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void canInsert(int slot, ItemStack itemStack_1, Direction dir, CallbackInfoReturnable ci) {
      if (Block.method_9503(itemStack_1.method_7909()) instanceof CrateBlock) {
         ci.setReturnValue(false);
      }

   }
}
