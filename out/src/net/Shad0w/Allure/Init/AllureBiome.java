package net.Shad0w.Allure.Init;

import java.util.Locale;
import net.Shad0w.Allure.Structures.CaveRoots.CaveRootFeature;
import net.Shad0w.Allure.Structures.Crate.CrateSealedFeature;
import net.Shad0w.Allure.Structures.DesertTomb.DesertTombFeature;
import net.Shad0w.Allure.Structures.DesertTomb.DesertTombGenerator;
import net.Shad0w.Allure.Structures.Speleothem.SpeleothemFeature;
import net.earthcomputer.libstructure.LibStructure;
import net.minecraft.block.Blocks;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.GenerationStep.Feature;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.decorator.ConfiguredDecorator;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig.Rules;

public class AllureBiome {
   public static int johnnyCounter = 0;
   public static final StructurePieceType DESERT_TOMB_STRUCTUREPIECE;
   public static final DesertTombFeature DESERT_TOMB_FEATURE;
   public static final ConfiguredStructureFeature DESERT_TOMB_STRUCTURE;
   public static final CrateSealedFeature CRATE_FEATURE;
   public static final ConfiguredFeature CRATE;
   public static final SpeleothemFeature SPELEOTHEM_FEATURE;
   public static final ConfiguredFeature SPELEOTHEM;
   public static final CaveRootFeature CAVE_ROOT_FEATURE;
   public static final ConfiguredFeature CAVE_ROOT;
   public static final ConfiguredFeature ORE_CLAY;

   public static void initialize() {
      Registry.method_10230(BuiltinRegistries.field_25930, new Identifier("allure", "desert_tomb"), DESERT_TOMB_STRUCTURE);
      LibStructure.registerSurfaceAdjustingStructure(new Identifier("allure", "desert_tomb"), DESERT_TOMB_FEATURE, Feature.field_13173, new StructureConfig(32, 16, 6574543), DESERT_TOMB_STRUCTURE);
      Registry.method_10230(Registry.field_11138, new Identifier("allure", "crate_sealed"), CRATE_FEATURE);
      Registry.method_10230(BuiltinRegistries.field_25929, new Identifier("allure", "crate_sealed"), CRATE);
      Registry.method_10230(Registry.field_11138, new Identifier("allure", "speleothem"), SPELEOTHEM_FEATURE);
      Registry.method_10230(BuiltinRegistries.field_25929, new Identifier("allure", "speleothem"), SPELEOTHEM);
      Registry.method_10230(Registry.field_11138, new Identifier("allure", "cave_root"), CAVE_ROOT_FEATURE);
      Registry.method_10230(BuiltinRegistries.field_25929, new Identifier("allure", "cave_root"), CAVE_ROOT);
      Registry.method_10230(BuiltinRegistries.field_25929, new Identifier("allure", "ore_clay"), ORE_CLAY);
   }

   private static net.minecraft.world.gen.feature.Feature registerFeature(String string_1, net.minecraft.world.gen.feature.Feature feature_1) {
      return (net.minecraft.world.gen.feature.Feature)Registry.method_10226(Registry.field_11138, string_1, feature_1);
   }

   static StructurePieceType registerStructurePiece(StructurePieceType structurePieceType_1, String string_1) {
      return (StructurePieceType)Registry.method_10226(Registry.field_16645, string_1.toLowerCase(Locale.ROOT), structurePieceType_1);
   }

   private static StructureFeature registerStructure(String string_1, StructureFeature structureFeature_1) {
      return (StructureFeature)Registry.method_10226(Registry.field_16644, string_1.toLowerCase(Locale.ROOT), structureFeature_1);
   }

   static {
      DESERT_TOMB_STRUCTUREPIECE = (StructurePieceType)Registry.method_10230(Registry.field_16645, new Identifier("allure", "desert_tomb"), DesertTombGenerator.Piece::new);
      DESERT_TOMB_FEATURE = new DesertTombFeature();
      DESERT_TOMB_STRUCTURE = DESERT_TOMB_FEATURE.method_28659(DefaultFeatureConfig.field_24894);
      CRATE_FEATURE = new CrateSealedFeature();
      CRATE = CRATE_FEATURE.method_23397(FeatureConfig.field_13603).method_23388((ConfiguredDecorator)Decorator.field_25860.method_23475(new CountConfig(1)).method_30371());
      SPELEOTHEM_FEATURE = new SpeleothemFeature();
      SPELEOTHEM = SPELEOTHEM_FEATURE.method_23397(FeatureConfig.field_13603).method_23388((ConfiguredDecorator)Decorator.field_25862.method_23475(new CountConfig(1)).method_30371());
      CAVE_ROOT_FEATURE = new CaveRootFeature();
      CAVE_ROOT = (ConfiguredFeature)CAVE_ROOT_FEATURE.method_23397(FeatureConfig.field_13603).method_30371();
      ORE_CLAY = (ConfiguredFeature)((ConfiguredFeature)((ConfiguredFeature)net.minecraft.world.gen.feature.Feature.field_13517.method_23397(new OreFeatureConfig(Rules.field_25845, Blocks.field_10460.method_9564(), 17)).method_30377(55)).method_30371()).method_30375(3);
   }
}
