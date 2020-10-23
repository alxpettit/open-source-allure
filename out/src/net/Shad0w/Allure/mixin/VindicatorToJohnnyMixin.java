package net.Shad0w.Allure.mixin;

import java.util.Objects;
import java.util.Optional;
import net.Shad0w.Allure.Allure;
import net.Shad0w.Allure.Init.AllureBiome;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.BiomeKeys;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({VindicatorEntity.class})
public abstract class VindicatorToJohnnyMixin extends IllagerEntity {
   protected VindicatorToJohnnyMixin(EntityType entityType, World world) {
      super(entityType, world);
   }

   public boolean method_5979(WorldAccess world, SpawnReason spawnReason) {
      if (Objects.equals(world.method_31081(this.method_24515()), Optional.of(BiomeKeys.field_9475)) && Allure.CONFIG.johnnyChance != 0) {
         ++AllureBiome.johnnyCounter;
         if (AllureBiome.johnnyCounter >= Allure.CONFIG.johnnyChance) {
            System.out.println(this.method_24515());
            this.method_5665(new TranslatableText("Johnny"));
            AllureBiome.johnnyCounter = 0;
         }
      }

      return super.method_5979(world, spawnReason);
   }
}
