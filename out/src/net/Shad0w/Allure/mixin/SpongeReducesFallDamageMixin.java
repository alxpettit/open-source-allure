package net.Shad0w.Allure.mixin;

import net.Shad0w.Allure.Allure;
import net.minecraft.block.Block;
import net.minecraft.block.SpongeBlock;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({SpongeBlock.class})
public class SpongeReducesFallDamageMixin extends Block {
   public SpongeReducesFallDamageMixin(Settings block$Settings_1) {
      super(block$Settings_1);
   }

   public void method_9554(World world_1, BlockPos blockPos_1, Entity entity_1, float float_1) {
      if (Allure.CONFIG.spongeReducement) {
         entity_1.method_5747(float_1, 0.5F);
      }

   }
}
