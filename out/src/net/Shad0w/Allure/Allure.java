package net.Shad0w.Allure;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.Shad0w.Allure.Init.AllureBiome;
import net.Shad0w.Allure.Init.AllureBlocks;
import net.Shad0w.Allure.Init.AllureContainer;
import net.Shad0w.Allure.Init.AllureDispenserBehaviour;
import net.Shad0w.Allure.Init.AllureEnchantments;
import net.Shad0w.Allure.Init.AllureEntities;
import net.Shad0w.Allure.Init.AllureItems;
import net.Shad0w.Allure.Init.AllureLootTables;
import net.Shad0w.Allure.Init.AllurePotions;
import net.Shad0w.Allure.Init.AllureSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.Settings;
import net.minecraft.util.Identifier;

public class Allure implements ModInitializer {
   public static final String MODID = "allure";
   public static ModConfig CONFIG;
   public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier("allure", "item_group"), () -> {
      return new ItemStack(AllureItems.TOTEM_OF_RETURNING);
   });
   public static final Settings ITEM_SETTINGS;

   public void onInitialize() {
      AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
      CONFIG = (ModConfig)AutoConfig.getConfigHolder(ModConfig.class).getConfig();
      AllureLootTables.initialize();
      AllureBlocks.initialize();
      AllureBiome.initialize();
      AllureItems.initialize();
      AllureDispenserBehaviour.initialize();
      AllurePotions.initialize();
      AllureContainer.initialize();
      AllureEnchantments.initialize();
      AllureEntities.initialize();
      AllureSounds.initialize();
   }

   static {
      ITEM_SETTINGS = (new Settings()).method_7892(ITEM_GROUP);
   }
}
