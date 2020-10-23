package net.Shad0w.Allure.Init;

import net.Shad0w.Allure.Blocks.Chute.ChuteBlock;
import net.Shad0w.Allure.Blocks.Chute.ChuteBlockController;
import net.Shad0w.Allure.Blocks.Crate.CrateBlock;
import net.Shad0w.Allure.Blocks.Crate.CrateBlockController;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;

public class AllureContainer {
   public static ScreenHandlerType CRATE_HANDLER_TYPE;
   public static ScreenHandlerType CHUTE_HANDLER_TYPE;

   public static void initialize() {
      CRATE_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(CrateBlock.GUI, (syncId, inventory) -> {
         return new CrateBlockController(syncId, inventory, ScreenHandlerContext.field_17304);
      });
      CHUTE_HANDLER_TYPE = ScreenHandlerRegistry.registerSimple(ChuteBlock.GUI, (syncId, inventory) -> {
         return new ChuteBlockController(syncId, inventory, ScreenHandlerContext.field_17304);
      });
   }
}
