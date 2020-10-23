package net.Shad0w.Allure.Init;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AllureSounds {
   public static final SoundEvent WOOD_OPEN = new SoundEvent(new Identifier("allure", "block.crate.wood_open"));
   public static final SoundEvent WOOD_CLOSE = new SoundEvent(new Identifier("allure", "block.crate.wood_close"));
   public static final SoundEvent WOOD_SMASH = new SoundEvent(new Identifier("allure", "block.crate.wood_smash"));
   public static final SoundEvent ENTITY_CRAB_DIE = new SoundEvent(new Identifier("allure", "entity.crab.die"));
   public static final SoundEvent ENTITY_CRAB_HURT = new SoundEvent(new Identifier("allure", "entity.crab.hurt"));
   public static final SoundEvent ENTITY_CRAB_IDLE = new SoundEvent(new Identifier("allure", "entity.crab.idle"));

   public static void initialize() {
      Registry.method_10230(Registry.field_11156, new Identifier("allure", "wood_open"), WOOD_OPEN);
      Registry.method_10230(Registry.field_11156, new Identifier("allure", "wood_close"), WOOD_CLOSE);
      Registry.method_10230(Registry.field_11156, new Identifier("allure", "wood_smash"), WOOD_SMASH);
   }
}
