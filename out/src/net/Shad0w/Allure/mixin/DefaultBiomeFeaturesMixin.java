package net.Shad0w.Allure.mixin;

import net.Shad0w.Allure.Init.AllureBiome;
import net.minecraft.world.biome.GenerationSettings.Builder;
import net.minecraft.world.gen.GenerationStep.Feature;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({DefaultBiomeFeatures.class})
public class DefaultBiomeFeaturesMixin {
   @Inject(
      method = {"addDungeons"},
      at = {@At("HEAD")}
   )
   private static void addDungeons(Builder builder, CallbackInfo ci) {
      builder.method_30992(Feature.field_13172, AllureBiome.CRATE);
      builder.method_30992(Feature.field_13172, AllureBiome.CAVE_ROOT);
   }

   @Inject(
      method = {"addDefaultDisks"},
      at = {@At("HEAD")}
   )
   private static void addDefaultDisks(Builder builder, CallbackInfo ci) {
      builder.method_30992(Feature.field_13176, AllureBiome.ORE_CLAY);
      builder.method_30992(Feature.field_13179, AllureBiome.SPELEOTHEM);
   }

   @Inject(
      method = {"addDesertFeatures"},
      at = {@At("HEAD")}
   )
   private static void addDesertFeatures(Builder builder, CallbackInfo ci) {
      builder.method_30995(AllureBiome.DESERT_TOMB_STRUCTURE);
   }

   @Inject(
      method = {"addNetherMineables"},
      at = {@At("HEAD")}
   )
   private static void addNetherMineables(Builder builder, CallbackInfo ci) {
      builder.method_30992(Feature.field_13177, AllureBiome.SPELEOTHEM);
   }

   @Inject(
      method = {"addAncientDebris"},
      at = {@At("HEAD")}
   )
   private static void addAncientDebris(Builder builder, CallbackInfo ci) {
      builder.method_30992(Feature.field_13177, AllureBiome.SPELEOTHEM);
   }
}
