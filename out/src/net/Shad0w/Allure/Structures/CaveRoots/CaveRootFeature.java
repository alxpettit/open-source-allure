package net.Shad0w.Allure.Structures.CaveRoots;

import java.util.Iterator;
import java.util.Random;
import net.Shad0w.Allure.Blocks.Root.RootBlock;
import net.Shad0w.Allure.Init.AllureBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Type;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class CaveRootFeature extends Feature {
   public CaveRootFeature() {
      super(DefaultFeatureConfig.field_24893);
   }

   public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random rand, BlockPos blockPos, DefaultFeatureConfig featureConfig) {
      for(int i = 0; i < 300; ++i) {
         int x = rand.nextInt(12) + 2;
         int z = rand.nextInt(12) + 2;
         int y = rand.nextInt(36) + 16;
         BlockPos pos = blockPos.method_10069(x, y, z);
         if (world.method_22347(pos)) {
            Iterator var11 = Type.field_11062.iterator();

            while(var11.hasNext()) {
               Direction facing = (Direction)var11.next();
               BlockPos target = pos.method_10093(facing);
               if (RootBlock.shouldConnectTo(world, target, facing.method_10153())) {
                  BlockState state = (BlockState)AllureBlocks.ROOT_BLOCK.method_9564().method_11657(RootBlock.getFacingProperty(facing), true);
                  world.method_8652(pos, state, 2);
                  RootBlock.growMany(state, world, pos, rand, 0.4F);
               }
            }
         }
      }

      return true;
   }
}
