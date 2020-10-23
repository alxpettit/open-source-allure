package net.Shad0w.Allure.Structures.DesertTomb;

import java.util.List;
import java.util.Random;
import net.Shad0w.Allure.Init.AllureBiome;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.block.enums.StairShape;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.SimpleStructurePiece;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class DesertTombGenerator {
   private static final Identifier[] REGULAR_TEMPLATES = new Identifier[]{new Identifier("allure:desert_temple/part_1"), new Identifier("allure:desert_temple/part_2"), new Identifier("allure:desert_temple/part_3"), new Identifier("allure:desert_temple/part_4")};
   private static BlockPos field_14536 = new BlockPos(0, 0, 0);

   public static void addParts(StructureManager structureManager_1, BlockPos blockPos_1, BlockRotation blockRotation_1, List list_1, Random random_1, ChunkGenerator chunkGenerator_1) {
      Identifier[] var6 = REGULAR_TEMPLATES;
      int var7 = var6.length;

      for(int var8 = 0; var8 < var7; ++var8) {
         Identifier regularTemplate = var6[var8];
         list_1.add(new DesertTombGenerator.Piece(structureManager_1, regularTemplate, blockPos_1, blockRotation_1));
      }

   }

   public static class Piece extends SimpleStructurePiece {
      private final BlockRotation rotation;
      private final Identifier template;

      public Piece(StructureManager structureManager_1, Identifier identifier_1, BlockPos blockPos_1, BlockRotation blockRotation_1) {
         super(AllureBiome.DESERT_TOMB_STRUCTUREPIECE, 0);
         this.rotation = blockRotation_1;
         this.template = identifier_1;
         if (this.rotation == BlockRotation.field_11467) {
            if (this.template == DesertTombGenerator.REGULAR_TEMPLATES[1]) {
               DesertTombGenerator.field_14536 = new BlockPos(0, 0, 31);
            } else if (this.template == DesertTombGenerator.REGULAR_TEMPLATES[2]) {
               DesertTombGenerator.field_14536 = new BlockPos(32, 0, 0);
            } else if (this.template == DesertTombGenerator.REGULAR_TEMPLATES[3]) {
               DesertTombGenerator.field_14536 = new BlockPos(32, 0, 31);
            } else {
               DesertTombGenerator.field_14536 = new BlockPos(0, 0, 0);
            }
         } else if (this.rotation == BlockRotation.field_11465) {
            if (this.template == DesertTombGenerator.REGULAR_TEMPLATES[1]) {
               DesertTombGenerator.field_14536 = new BlockPos(31, 0, 0);
            } else if (this.template == DesertTombGenerator.REGULAR_TEMPLATES[2]) {
               DesertTombGenerator.field_14536 = new BlockPos(0, 0, -32);
            } else if (this.template == DesertTombGenerator.REGULAR_TEMPLATES[3]) {
               DesertTombGenerator.field_14536 = new BlockPos(31, 0, -32);
            } else {
               DesertTombGenerator.field_14536 = new BlockPos(0, 0, 0);
            }
         } else if (this.rotation == BlockRotation.field_11463) {
            if (this.template == DesertTombGenerator.REGULAR_TEMPLATES[1]) {
               DesertTombGenerator.field_14536 = new BlockPos(-31, 0, 0);
            } else if (this.template == DesertTombGenerator.REGULAR_TEMPLATES[2]) {
               DesertTombGenerator.field_14536 = new BlockPos(0, 0, 32);
            } else if (this.template == DesertTombGenerator.REGULAR_TEMPLATES[3]) {
               DesertTombGenerator.field_14536 = new BlockPos(-31, 0, 32);
            } else {
               DesertTombGenerator.field_14536 = new BlockPos(0, 0, 0);
            }
         } else if (this.rotation == BlockRotation.field_11464) {
            if (this.template == DesertTombGenerator.REGULAR_TEMPLATES[1]) {
               DesertTombGenerator.field_14536 = new BlockPos(0, 0, -31);
            } else if (this.template == DesertTombGenerator.REGULAR_TEMPLATES[2]) {
               DesertTombGenerator.field_14536 = new BlockPos(-32, 0, 0);
            } else if (this.template == DesertTombGenerator.REGULAR_TEMPLATES[3]) {
               DesertTombGenerator.field_14536 = new BlockPos(-32, 0, -31);
            } else {
               DesertTombGenerator.field_14536 = new BlockPos(0, 0, 0);
            }
         }

         this.field_15432 = blockPos_1.method_10081(DesertTombGenerator.field_14536);
         this.initializeStructureData(structureManager_1);
      }

      public Piece(StructureManager structureManager_1, CompoundTag compoundTag_1) {
         super(AllureBiome.DESERT_TOMB_STRUCTUREPIECE, compoundTag_1);
         this.template = new Identifier(compoundTag_1.method_10558("Template"));
         this.rotation = BlockRotation.valueOf(compoundTag_1.method_10558("Rot"));
         this.initializeStructureData(structureManager_1);
      }

      protected void method_14943(CompoundTag compoundTag_1) {
         super.method_14943(compoundTag_1);
         compoundTag_1.method_10582("Template", this.template.toString());
         compoundTag_1.method_10582("Rot", this.rotation.name());
      }

      private void initializeStructureData(StructureManager structureManager_1) {
         Structure structure_1 = structureManager_1.method_15091(this.template);
         StructurePlacementData structurePlacementData_1 = (new StructurePlacementData()).method_15123(this.rotation).method_15125(BlockMirror.field_11302);
         this.method_15027(structure_1, this.field_15432, structurePlacementData_1);
      }

      protected void method_15026(String string_1, BlockPos blockPos_1, ServerWorldAccess iWorld_1, Random random_1, BlockBox boundingBox) {
         if ("map_chest".equals(string_1)) {
            LootableContainerBlockEntity.method_11287(iWorld_1, random_1, blockPos_1.method_10074(), LootTables.field_274);
         } else if ("treasure_chest".equals(string_1)) {
            LootableContainerBlockEntity.method_11287(iWorld_1, random_1, blockPos_1.method_10074(), LootTables.field_274);
            iWorld_1.method_8652(blockPos_1, Blocks.field_10124.method_9564(), 3);
         } else if ("supply_chest".equals(string_1)) {
            LootableContainerBlockEntity.method_11287(iWorld_1, random_1, blockPos_1.method_10074(), LootTables.field_274);
         }

      }

      public boolean method_14931(StructureWorldAccess iWorld_1, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random_1, BlockBox blockBox, ChunkPos chunkPos_1, BlockPos blockPos) {
         int int_4 = chunkGenerator.method_20402(blockPos.method_10263(), blockPos.method_10260(), Type.field_13194) - 5;
         this.field_15432 = new BlockPos(this.field_15432.method_10263(), int_4, this.field_15432.method_10260());
         boolean success = super.method_14931(iWorld_1, structureAccessor, chunkGenerator, random_1, blockBox, chunkPos_1, blockPos);
         if (this.template == DesertTombGenerator.REGULAR_TEMPLATES[0]) {
            int i;
            if (this.rotation == BlockRotation.field_11467) {
               iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() + 6, int_4, this.field_15432.method_10260() - 1), (BlockState)((BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11034)).method_11657(StairsBlock.field_11565, StairShape.field_12709), 0);

               for(i = 0; i < 3; ++i) {
                  iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() + 7 + i, int_4, this.field_15432.method_10260() - 1), (BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11035), 0);
               }

               iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() + 10, int_4, this.field_15432.method_10260() - 1), (BlockState)((BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11039)).method_11657(StairsBlock.field_11565, StairShape.field_12708), 0);

               for(i = 0; i < 7; ++i) {
                  iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() - 1, int_4, this.field_15432.method_10260() + 28 + i), Blocks.field_10262.method_9564(), 0);
               }

               iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() + 6, int_4, this.field_15432.method_10260() + 63), (BlockState)((BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11034)).method_11657(StairsBlock.field_11565, StairShape.field_12708), 0);

               for(i = 0; i < 3; ++i) {
                  iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() + 7 + i, int_4, this.field_15432.method_10260() + 63), (BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11043), 0);
               }

               iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() + 10, int_4, this.field_15432.method_10260() + 63), (BlockState)((BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11039)).method_11657(StairsBlock.field_11565, StairShape.field_12709), 0);
            } else if (this.rotation == BlockRotation.field_11465) {
               iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() - 1, int_4, this.field_15432.method_10260() - 6), (BlockState)((BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11043)).method_11657(StairsBlock.field_11565, StairShape.field_12709), 0);

               for(i = 0; i < 3; ++i) {
                  iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() - 1, int_4, this.field_15432.method_10260() - 7 - i), (BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11034), 0);
               }

               iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() - 1, int_4, this.field_15432.method_10260() - 10), (BlockState)((BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11035)).method_11657(StairsBlock.field_11565, StairShape.field_12708), 0);

               for(i = 0; i < 7; ++i) {
                  iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() + 28 + i, int_4, this.field_15432.method_10260() + 1), Blocks.field_10262.method_9564(), 0);
               }

               iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() + 63, int_4, this.field_15432.method_10260() - 6), (BlockState)((BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11043)).method_11657(StairsBlock.field_11565, StairShape.field_12708), 0);

               for(i = 0; i < 3; ++i) {
                  iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() + 63, int_4, this.field_15432.method_10260() - 7 - i), (BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11039), 0);
               }

               iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() + 63, int_4, this.field_15432.method_10260() - 10), (BlockState)((BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11035)).method_11657(StairsBlock.field_11565, StairShape.field_12709), 0);
            } else if (this.rotation == BlockRotation.field_11463) {
               iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() + 1, int_4, this.field_15432.method_10260() + 6), (BlockState)((BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11035)).method_11657(StairsBlock.field_11565, StairShape.field_12709), 0);

               for(i = 0; i < 3; ++i) {
                  iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() + 1, int_4, this.field_15432.method_10260() + 7 + i), (BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11039), 0);
               }

               iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() + 1, int_4, this.field_15432.method_10260() + 10), (BlockState)((BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11043)).method_11657(StairsBlock.field_11565, StairShape.field_12708), 0);

               for(i = 0; i < 7; ++i) {
                  iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() - 28 - i, int_4, this.field_15432.method_10260() - 1), Blocks.field_10262.method_9564(), 0);
               }

               iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() - 63, int_4, this.field_15432.method_10260() + 6), (BlockState)((BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11035)).method_11657(StairsBlock.field_11565, StairShape.field_12708), 0);

               for(i = 0; i < 3; ++i) {
                  iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() - 63, int_4, this.field_15432.method_10260() + 7 + i), (BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11034), 0);
               }

               iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() - 63, int_4, this.field_15432.method_10260() + 10), (BlockState)((BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11043)).method_11657(StairsBlock.field_11565, StairShape.field_12709), 0);
            }

            if (this.rotation == BlockRotation.field_11464) {
               iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() - 6, int_4, this.field_15432.method_10260() + 1), (BlockState)((BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11039)).method_11657(StairsBlock.field_11565, StairShape.field_12709), 0);

               for(i = 0; i < 3; ++i) {
                  iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() - 7 - i, int_4, this.field_15432.method_10260() + 1), (BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11043), 0);
               }

               iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() - 10, int_4, this.field_15432.method_10260() + 1), (BlockState)((BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11034)).method_11657(StairsBlock.field_11565, StairShape.field_12708), 0);

               for(i = 0; i < 7; ++i) {
                  iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() + 1, int_4, this.field_15432.method_10260() - 28 - i), Blocks.field_10262.method_9564(), 0);
               }

               iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() - 6, int_4, this.field_15432.method_10260() - 63), (BlockState)((BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11039)).method_11657(StairsBlock.field_11565, StairShape.field_12708), 0);

               for(i = 0; i < 3; ++i) {
                  iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() - 7 - i, int_4, this.field_15432.method_10260() - 63), (BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11035), 0);
               }

               iWorld_1.method_8652(new BlockPos(this.field_15432.method_10263() - 10, int_4, this.field_15432.method_10260() - 63), (BlockState)((BlockState)Blocks.field_10142.method_9564().method_11657(HorizontalFacingBlock.field_11177, Direction.field_11034)).method_11657(StairsBlock.field_11565, StairShape.field_12709), 0);
            }
         }

         return success;
      }
   }
}
