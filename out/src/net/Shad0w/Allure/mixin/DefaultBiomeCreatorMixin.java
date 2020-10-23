package net.Shad0w.Allure.mixin;

import net.Shad0w.Allure.Init.AllureEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.biome.DefaultBiomeCreator;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.Precipitation;
import net.minecraft.world.biome.BiomeEffects.GrassColorModifier;
import net.minecraft.world.biome.SpawnSettings.Builder;
import net.minecraft.world.biome.SpawnSettings.SpawnEntry;
import net.minecraft.world.gen.GenerationStep.Feature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilders;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({DefaultBiomeCreator.class})
public abstract class DefaultBiomeCreatorMixin {
   @Shadow
   protected static int method_30932(float temperature) {
      return 0;
   }

   @Inject(
      method = {"createBeach"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void createBeach(float depth, float scale, float temperature, float downfall, int waterColor, boolean snowy, boolean stony, CallbackInfoReturnable ci) {
      Builder builder = new Builder();
      if (!stony && !snowy) {
         builder.method_31011(SpawnGroup.field_6294, new SpawnEntry(EntityType.field_6113, 5, 2, 5));
      }

      DefaultBiomeFeatures.method_30581(builder);
      if (!snowy) {
         builder.method_31011(SpawnGroup.field_6294, new SpawnEntry(AllureEntities.CRAB, 15, 1, 3));
      }

      net.minecraft.world.biome.GenerationSettings.Builder builder2 = (new net.minecraft.world.biome.GenerationSettings.Builder()).method_30996(stony ? ConfiguredSurfaceBuilders.field_26337 : ConfiguredSurfaceBuilders.field_26321);
      if (stony) {
         DefaultBiomeFeatures.method_28440(builder2);
      } else {
         builder2.method_30995(ConfiguredStructureFeatures.field_26293);
         builder2.method_30995(ConfiguredStructureFeatures.field_26309);
         builder2.method_30995(ConfiguredStructureFeatures.field_26300);
      }

      builder2.method_30995(stony ? ConfiguredStructureFeatures.field_26289 : ConfiguredStructureFeatures.field_26316);
      DefaultBiomeFeatures.method_16983(builder2);
      DefaultBiomeFeatures.method_17002(builder2);
      DefaultBiomeFeatures.method_17004(builder2);
      DefaultBiomeFeatures.method_17005(builder2);
      DefaultBiomeFeatures.method_17006(builder2);
      DefaultBiomeFeatures.method_17010(builder2);
      DefaultBiomeFeatures.method_16977(builder2);
      DefaultBiomeFeatures.method_16979(builder2);
      DefaultBiomeFeatures.method_16982(builder2);
      DefaultBiomeFeatures.method_16984(builder2);
      DefaultBiomeFeatures.method_16996(builder2);
      DefaultBiomeFeatures.method_16999(builder2);
      ci.setReturnValue((new net.minecraft.world.biome.Biome.Builder()).method_8735(snowy ? Precipitation.field_9383 : Precipitation.field_9382).method_8738(stony ? Category.field_9371 : Category.field_9363).method_8740(depth).method_8743(scale).method_8747(temperature).method_8727(downfall).method_24379((new net.minecraft.world.biome.BiomeEffects.Builder()).method_24395(waterColor).method_24397(329011).method_24392(12638463).method_30820(method_30932(temperature)).method_24943(BiomeMoodSound.field_23146).method_24391()).method_30974(builder.method_31007()).method_30973(builder2.method_30987()).method_30972());
   }

   @Inject(
      method = {"createDarkForest"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void createDarkForest(float f, float g, boolean bl, CallbackInfoReturnable ci) {
      Builder builder = new Builder();
      DefaultBiomeFeatures.method_30580(builder);
      DefaultBiomeFeatures.method_30581(builder);
      builder.method_31011(SpawnGroup.field_6302, new SpawnEntry(EntityType.field_6065, 30, 1, 4));
      builder.method_31011(SpawnGroup.field_6302, new SpawnEntry(EntityType.field_6117, 30, 1, 4));
      net.minecraft.world.biome.GenerationSettings.Builder builder2 = (new net.minecraft.world.biome.GenerationSettings.Builder()).method_30996(ConfiguredSurfaceBuilders.field_26327);
      builder2.method_30995(ConfiguredStructureFeatures.field_26295);
      DefaultBiomeFeatures.method_28440(builder2);
      builder2.method_30995(ConfiguredStructureFeatures.field_26316);
      DefaultBiomeFeatures.method_16983(builder2);
      DefaultBiomeFeatures.method_17002(builder2);
      DefaultBiomeFeatures.method_17004(builder2);
      builder2.method_30992(Feature.field_13178, bl ? ConfiguredFeatures.field_26109 : ConfiguredFeatures.field_26108);
      DefaultBiomeFeatures.method_16970(builder2);
      DefaultBiomeFeatures.method_17005(builder2);
      DefaultBiomeFeatures.method_17006(builder2);
      DefaultBiomeFeatures.method_17010(builder2);
      DefaultBiomeFeatures.method_16977(builder2);
      DefaultBiomeFeatures.method_16971(builder2);
      DefaultBiomeFeatures.method_16982(builder2);
      DefaultBiomeFeatures.method_16984(builder2);
      DefaultBiomeFeatures.method_16996(builder2);
      DefaultBiomeFeatures.method_16999(builder2);
      ci.setReturnValue((new net.minecraft.world.biome.Biome.Builder()).method_8735(Precipitation.field_9382).method_8738(Category.field_9370).method_8740(f).method_8743(g).method_8747(0.7F).method_8727(0.8F).method_24379((new net.minecraft.world.biome.BiomeEffects.Builder()).method_24395(4159204).method_24397(329011).method_24392(12638463).method_30820(method_30932(0.7F)).method_30818(GrassColorModifier.field_26427).method_24943(BiomeMoodSound.field_23146).method_24391()).method_30974(builder.method_31007()).method_30973(builder2.method_30987()).method_30972());
   }
}
