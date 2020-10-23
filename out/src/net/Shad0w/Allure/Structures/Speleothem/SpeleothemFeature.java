package net.Shad0w.Allure.Structures.Speleothem;

import java.util.Random;
import net.Shad0w.Allure.Blocks.Speleothem.SpeleothemBlock;
import net.Shad0w.Allure.Init.AllureBlocks;
import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class SpeleothemFeature extends Feature {
   public SpeleothemFeature() {
      super(DefaultFeatureConfig.field_24893);
   }

   public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random rand, BlockPos blockPos, DefaultFeatureConfig featureConfig) {
      int spread = 10;
      int tries = 60;
      int innerSpread = 6;
      int innerTries = 12;
      int upperBound = 55;
      int offset = 6;
      if (world.method_8410().method_27983() == World.field_25180) {
         upperBound = 128;
         offset = 0;
         tries = 4;
         innerTries = 12;
         if (rand.nextInt(10) != 1) {
            return false;
         }
      }

      if (upperBound > 0) {
         for(int i = 0; i < tries; ++i) {
            BlockPos target = blockPos.method_10069(rand.nextInt(spread), rand.nextInt(upperBound) + offset, rand.nextInt(spread));
            if (this.placeSpeleothemCluster(rand, world, target, innerSpread, innerTries)) {
               ++i;
            }
         }
      }

      return true;
   }

   private boolean placeSpeleothemCluster(Random random, StructureWorldAccess world, BlockPos pos, int spread, int tries) {
      if (!this.findAndPlaceSpeleothem(random, world, pos)) {
         return false;
      } else {
         for(int i = 0; i < tries; ++i) {
            BlockPos target = pos.method_10069(random.nextInt(spread * 2 + 1) - spread, random.nextInt(spread + 1) - spread, random.nextInt(spread * 2 + 1) - spread);
            this.findAndPlaceSpeleothem(random, world, target);
         }

         return true;
      }
   }

   private boolean findAndPlaceSpeleothem(Random random, StructureWorldAccess world, BlockPos pos) {
      if (world.method_22347(pos) && world.method_8320(pos).method_26204() instanceof AirBlock) {
         int off = world.method_8410().method_27983() == World.field_25180 ? -1000 : 0;
         boolean up = random.nextBoolean();
         Direction diff = up ? Direction.field_11036 : Direction.field_11033;
         boolean seeSky = true;

         for(int i = pos.method_10264(); i < world.method_8624(Type.field_13194, pos.method_10263(), pos.method_10260()); ++i) {
            if (!world.method_22347(new BlockPos(pos.method_10263(), i, pos.method_10260()))) {
               seeSky = false;
               i = world.method_8624(Type.field_13194, pos.method_10263(), pos.method_10260());
            }
         }

         if (seeSky) {
            return false;
         } else {
            BlockState stateAt;
            do {
               pos = pos.method_10093(diff);
               stateAt = world.method_8320(pos);
               ++off;
            } while(pos.method_10264() > 4 && pos.method_10264() < 200 && !stateAt.method_26212(world, pos) && off < 10);

            stateAt = world.method_8320(pos);
            if (stateAt.method_26215()) {
               return false;
            } else {
               this.placeSpeleothem(random, world, pos, !up);
               return true;
            }
         }
      } else {
         return false;
      }
   }

   private void placeSpeleothem(Random random, StructureWorldAccess world, BlockPos pos, boolean up) {
      Direction diff = up ? Direction.field_11036 : Direction.field_11033;
      int size = random.nextInt(3) == 0 ? 2 : 3;
      if (!up && random.nextInt(20) == 0) {
         size = 1;
      }

      Block type = null;
      BlockState stateAt = world.method_8320(pos);
      if (stateAt.method_26204() == Blocks.field_10474) {
         type = AllureBlocks.GRANITE_SPELEOTHEM;
      } else if (stateAt.method_26204() == Blocks.field_10115) {
         type = AllureBlocks.ANDESITE_SPELEOTHEM;
      } else if (stateAt.method_26204() == Blocks.field_10508) {
         type = AllureBlocks.DIORITE_SPELEOTHEM;
      } else if (stateAt.method_26204() == Blocks.field_10515) {
         type = AllureBlocks.NETHERRACK_SPELEOTHEM;
      } else if (stateAt.method_26204() == Blocks.field_10340) {
         type = AllureBlocks.STONE_SPELEOTHEM;
      } else if (stateAt.method_26204() == Blocks.field_22091) {
         type = AllureBlocks.BASALT_SPELEOTHEM;
      }

      if (type != null) {
         for(int i = 0; i < size; ++i) {
            pos = pos.method_10093(diff);
            if (!world.method_22347(pos)) {
               return;
            }

            SpeleothemBlock.SpeleothemSize sizeType = SpeleothemBlock.SpeleothemSize.values()[size - i - 1];
            BlockState targetBlock = (BlockState)type.method_9564().method_11657(SpeleothemBlock.SIZE, sizeType);
            world.method_8652(pos, targetBlock, 0);
         }

      }
   }
}
