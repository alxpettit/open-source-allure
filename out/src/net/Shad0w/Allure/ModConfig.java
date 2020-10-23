package net.Shad0w.Allure;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry.BoundedDiscrete;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry.Category;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(
   name = "allure"
)
public class ModConfig implements ConfigData {
   @Category("automation")
   public boolean babiesEatTroughEnabled = true;
   @Category("crafting")
   @Comment("WARNING! Will break any existing crates")
   public boolean crateEnabled = true;
   @Category("crafting")
   @BoundedDiscrete(
      max = 1L
   )
   public float crateGenerateChance = 0.15F;
   @Category("crafting")
   @BoundedDiscrete(
      max = 1L
   )
   public float crateRareChance = 0.001F;
   @Category("crafting")
   @BoundedDiscrete(
      max = 1L
   )
   public float crateValuableChance = 0.02F;
   @Category("crafting")
   @BoundedDiscrete(
      max = 1L
   )
   public float crateUncommonChance = 0.2F;
   @Category("crafting")
   public boolean chorusTPEnabled = true;
   @Category("enchanting")
   public boolean cursesEnabled = true;
   @Category("tweaks")
   public boolean villagersFollow = true;
   @Category("tweaks")
   public boolean spongeReducement = true;
   @Category("tweaks")
   public boolean skeletonStrafe = true;
   @Category("tweaks")
   public boolean featherFallingFarm = true;
   @Category("tweaks")
   public boolean beaconsHealPets = true;
   @Category("world")
   public boolean extraDarkForest = true;
   @Category("world")
   @BoundedDiscrete(
      max = 300L
   )
   public int extraDarkForestSpawn = 30;
   @Category("world")
   @BoundedDiscrete(
      max = 10L
   )
   public int johnnyChance = 3;
   @Category("world")
   public boolean gravestonesEnabled = true;
   @Category("world")
   public boolean lockGravestones = true;
   @Category("world")
   public boolean renderSkulls = true;
   @Category("world")
   public boolean renderCrateName = true;
   @Category("world")
   public boolean cactusGiveWater = true;
}
