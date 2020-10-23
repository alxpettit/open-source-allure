package net.Shad0w.Allure.Structures.Crate;

import java.util.Random;
import net.Shad0w.Allure.Allure;
import net.Shad0w.Allure.Blocks.Crate.CrateBlock;
import net.Shad0w.Allure.Blocks.Crate.CrateBlockEntity;
import net.Shad0w.Allure.Init.AllureBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class CrateSealedFeature extends Feature {
   public CrateSealedFeature() {
      super(DefaultFeatureConfig.field_24893);
   }

   public boolean generate(StructureWorldAccess iWorld_1, ChunkGenerator generator, Random random_1, BlockPos blockPos_1, DefaultFeatureConfig config) {
      if (iWorld_1.method_8410().method_27983() == World.field_25179 && blockPos_1.method_10264() <= 48 && blockPos_1.method_10264() >= 10 && iWorld_1.method_8320(blockPos_1).method_26215() && iWorld_1.method_8320(blockPos_1.method_10074()).method_26225() && Allure.CONFIG.crateEnabled) {
         float chance = random_1.nextFloat();
         if (chance > Allure.CONFIG.crateGenerateChance) {
            return false;
         } else {
            int rand = random_1.nextInt(6);
            BlockState blockState = (BlockState)AllureBlocks.CRATE_OAK_BLOCK.method_9564().method_11657(CrateBlock.SEALED, true);
            if (rand == 0) {
               blockState = (BlockState)AllureBlocks.CRATE_SPRUCE_BLOCK.method_9564().method_11657(CrateBlock.SEALED, true);
            } else if (rand == 1) {
               blockState = (BlockState)AllureBlocks.CRATE_DARK_OAK_BLOCK.method_9564().method_11657(CrateBlock.SEALED, true);
            } else if (rand == 2) {
               blockState = (BlockState)AllureBlocks.CRATE_ACACIA_BLOCK.method_9564().method_11657(CrateBlock.SEALED, true);
            } else if (rand == 3) {
               blockState = (BlockState)AllureBlocks.CRATE_JUNGLE_BLOCK.method_9564().method_11657(CrateBlock.SEALED, true);
            } else if (rand == 4) {
               blockState = (BlockState)AllureBlocks.CRATE_BIRCH_BLOCK.method_9564().method_11657(CrateBlock.SEALED, true);
            }

            iWorld_1.method_8652(blockPos_1, blockState, 2);
            if (iWorld_1.method_8321(blockPos_1) instanceof CrateBlockEntity) {
               ((CrateBlockEntity)iWorld_1.method_8321(blockPos_1)).abandoned = true;
            }

            return true;
         }
      } else {
         return false;
      }
   }
}
