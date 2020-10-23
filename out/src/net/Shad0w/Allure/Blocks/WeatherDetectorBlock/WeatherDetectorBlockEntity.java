package net.Shad0w.Allure.Blocks.WeatherDetectorBlock;

import net.Shad0w.Allure.Init.AllureBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Tickable;

public class WeatherDetectorBlockEntity extends BlockEntity implements Tickable {
   public WeatherDetectorBlockEntity() {
      super(AllureBlocks.WEATHER_DETECTOR_BLOCK_ENTITY);
   }

   public void method_16896() {
      if (this.field_11863 != null && !this.field_11863.field_9236) {
         BlockState blockState = this.method_11010();
         Block block = blockState.method_26204();
         if (block instanceof WeatherDetectorBlock) {
            WeatherDetectorBlock.updateState(blockState, this.field_11863, this.field_11867);
         }
      }

   }
}
