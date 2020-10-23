package net.Shad0w.Allure.Blocks.Chute;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import net.Shad0w.Allure.Init.AllureContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;

public class ChuteBlockController extends SyncedGuiDescription {
   ChuteBlockEntity gg = null;

   public ChuteBlockController(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
      super(AllureContainer.CHUTE_HANDLER_TYPE, syncId, playerInventory, getBlockInventory(context, 5), getBlockPropertyDelegate(context));
      this.gg = (ChuteBlockEntity)context.method_17395((world, pos) -> {
         return world.method_8321(pos);
      }).orElse((Object)null);
      WGridPanel rootPanel = (WGridPanel)this.getRootPanel();
      WPlainPanel panel = new WPlainPanel();
      rootPanel.add(panel, 0, 0);

      for(int int_6 = 0; int_6 < 5; ++int_6) {
         panel.add(WItemSlot.of(this.blockInventory, int_6), 36 + int_6 * 18, 10);
      }

      panel.add(this.createPlayerInventoryPanel(), 0, 39);
      rootPanel.validate(this);
   }
}
