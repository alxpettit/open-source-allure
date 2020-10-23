package net.Shad0w.Allure.Utils;

import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.JukeboxBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class MusicDiscDispenserBehaviour extends FallibleItemDispenserBehavior {
   protected ItemStack method_10135(BlockPointer pointer, ItemStack stack) {
      this.method_27955(false);
      Item item = stack.method_7909();
      if (item instanceof MusicDiscItem) {
         Direction direction = (Direction)pointer.method_10120().method_11654(DispenserBlock.field_10918);
         BlockPos blockPos = pointer.method_10122().method_10093(direction);
         if (pointer.method_10207().method_8320(blockPos).method_26204() == Blocks.field_10223 && !(Boolean)pointer.method_10207().method_8320(blockPos).method_11654(JukeboxBlock.field_11180)) {
            ((JukeboxBlock)Blocks.field_10223).method_10276(pointer.method_10207(), blockPos, pointer.method_10207().method_8320(blockPos), stack);
            pointer.method_10207().method_8444((PlayerEntity)null, 1010, blockPos, Item.method_7880(item));
            stack.method_7934(1);
            this.method_27955(true);
         }
      }

      return stack;
   }
}
