package net.Shad0w.Allure.Structures.DesertTomb;

import java.util.Iterator;
import java.util.Set;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructureFeature.StructureStartFactory;

public class DesertTombFeature extends StructureFeature {
   public DesertTombFeature() {
      super(DefaultFeatureConfig.field_24893);
   }

   public StructureStartFactory method_14016() {
      return DesertTombFeature.Start::new;
   }

   protected boolean shouldStartAt(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long l, ChunkRandom random, int i, int j, Biome biome, ChunkPos chunkPos, DefaultFeatureConfig defaultFeatureConfig) {
      Set set = biomeSource.method_8763(i * 16 + 9, chunkGenerator.method_16398(), j * 16 + 9, 32);
      Iterator var12 = set.iterator();
      Biome biome2;
      if (biome.method_8688() == Category.field_9368) {
         do {
            if (!var12.hasNext()) {
               return true;
            }

            biome2 = (Biome)var12.next();
         } while(biome2.method_30970().method_30980(this));
      }

      return false;
   }

   public static class Start extends StructureStart {
      public Start(StructureFeature structureFeature, int i, int j, BlockBox blockBox, int k, long l) {
         super(structureFeature, i, j, blockBox, k, l);
      }

      public void init(DynamicRegistryManager registryManager, ChunkGenerator chunkGenerator_1, StructureManager structureManager_1, int int_1, int int_2, Biome biome, DefaultFeatureConfig defaultFeatureConfig) {
         BlockRotation blockRotation_1 = BlockRotation.values()[this.field_16715.nextInt(BlockRotation.values().length)];
         BlockPos blockPos_1 = new BlockPos(int_1 * 16, chunkGenerator_1.method_20402(int_1 * 16, int_2 * 16, Type.field_13194), int_2 * 16);
         DesertTombGenerator.addParts(structureManager_1, blockPos_1, blockRotation_1, this.field_15325, this.field_16715, chunkGenerator_1);
         this.method_14969();
      }
   }
}
