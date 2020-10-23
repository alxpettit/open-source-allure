package net.Shad0w.Allure.Init;

import net.Shad0w.Allure.Entity.ChestBoatEntity.ChestBoatEntity;
import net.Shad0w.Allure.Entity.CrabEntity.CrabEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.Registry;

public class AllureEntities {
   public static final EntityType CHEST_BOAT;
   public static final EntityType CRAB;

   public static void initialize() {
      Registry.method_10230(Registry.field_11145, ChestBoatEntity.ID, CHEST_BOAT);
      Registry.method_10230(Registry.field_11145, CrabEntity.ID, CRAB);
      FabricDefaultAttributeRegistry.register(CRAB, CrabEntity.createCrabAttributes());
   }

   static {
      CHEST_BOAT = FabricEntityTypeBuilder.create(SpawnGroup.field_17715, ChestBoatEntity::new).dimensions(EntityDimensions.method_18385(1.375F, 0.5625F)).build();
      CRAB = FabricEntityTypeBuilder.create(SpawnGroup.field_6294, CrabEntity::new).dimensions(EntityDimensions.method_18385(0.9F, 0.5F)).trackable(80, 3, true).build();
   }
}
