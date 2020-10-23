package net.Shad0w.Allure.Blocks.Crate;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import java.util.function.Predicate;
import net.Shad0w.Allure.Init.AllureContainer;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;

public class CrateBlockController extends SyncedGuiDescription {
   CrateBlockEntity gg = null;
   private static final Predicate DEFAULT_FILTER = (stack) -> {
      return !(Block.method_9503(stack.method_7909()) instanceof CrateBlock) && !(Block.method_9503(stack.method_7909()) instanceof ShulkerBoxBlock);
   };

   public CrateBlockController(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
      super(AllureContainer.CRATE_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(context, 9), getBlockPropertyDelegate(context));
      this.gg = (CrateBlockEntity)context.method_17395((world, pos) -> {
         return world.method_8321(pos);
      }).orElse((Object)null);
      WGridPanel rootPanel = (WGridPanel)this.getRootPanel();
      WPlainPanel panel = new WPlainPanel();
      rootPanel.add(panel, 0, 0);
      if (this.gg != null) {
         this.gg.invOpened(playerInventory.field_7546);
      }

      for(int int_7 = 0; int_7 < 9; ++int_7) {
         WItemSlot slot = WItemSlot.of(this.blockInventory, int_7);
         slot.setFilter(DEFAULT_FILTER);
         panel.add(slot, int_7 * 18, 8);
      }

      panel.add(this.createPlayerInventoryPanel(), 0, 39);
      rootPanel.validate(this);
   }

   public void method_7595(PlayerEntity playerEntity_1) {
      super.method_7595(playerEntity_1);
      if (this.gg != null) {
         this.gg.invClosed(playerEntity_1);
      }

   }
}
