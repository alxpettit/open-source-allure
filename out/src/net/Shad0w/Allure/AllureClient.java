package net.Shad0w.Allure;

import net.Shad0w.Allure.Blocks.Chute.ChuteBlockScreen;
import net.Shad0w.Allure.Blocks.Crate.CrateBlockEntityRenderer;
import net.Shad0w.Allure.Blocks.Crate.CrateBlockScreen;
import net.Shad0w.Allure.Blocks.Gravestone.GravestoneBlockEntityRenderer;
import net.Shad0w.Allure.Entity.ChestBoatEntity.ChestBoatEntityRenderer;
import net.Shad0w.Allure.Entity.CrabEntity.CrabEntityRenderer;
import net.Shad0w.Allure.Init.AllureBlocks;
import net.Shad0w.Allure.Init.AllureContainer;
import net.Shad0w.Allure.Init.AllureEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

public class AllureClient implements ClientModInitializer {
   public void onInitializeClient() {
      ScreenRegistry.register(AllureContainer.CRATE_HANDLER_TYPE, (gui, player, title) -> {
         return new CrateBlockScreen(gui, player.field_7546, title);
      });
      ScreenRegistry.register(AllureContainer.CHUTE_HANDLER_TYPE, (gui, player, title) -> {
         return new ChuteBlockScreen(gui, player.field_7546, title);
      });
      BlockEntityRendererRegistry.INSTANCE.register(AllureBlocks.GRAVESTONE_BLOCK_ENTITY, GravestoneBlockEntityRenderer::new);
      BlockEntityRendererRegistry.INSTANCE.register(AllureBlocks.CRATE_BLOCK_ENTITY, CrateBlockEntityRenderer::new);
      BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.method_23583(), new Block[]{AllureBlocks.BLUE_SLIME_BLOCK, AllureBlocks.CYAN_SLIME_BLOCK, AllureBlocks.MAGENTA_SLIME_BLOCK, AllureBlocks.RED_SLIME_BLOCK, AllureBlocks.YELLOW_SLIME_BLOCK});
      BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.method_23579(), new Block[]{AllureBlocks.FRAMED_GLASS, AllureBlocks.FRAMED_GLASS_PANE, AllureBlocks.GOLD_BARS});
      BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.method_23581(), new Block[]{AllureBlocks.ROOT_BLOCK});
      EntityRendererRegistry.INSTANCE.register(AllureEntities.CHEST_BOAT, (dispatcher, context) -> {
         return new ChestBoatEntityRenderer(dispatcher);
      });
      EntityRendererRegistry.INSTANCE.register(AllureEntities.CRAB, (dispatcher, context) -> {
         return new CrabEntityRenderer(dispatcher);
      });
   }
}
