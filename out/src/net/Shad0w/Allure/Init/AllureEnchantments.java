package net.Shad0w.Allure.Init;

import net.Shad0w.Allure.Allure;
import net.Shad0w.Allure.Enchantments.ClumsinessCurseEnchantment;
import net.Shad0w.Allure.Enchantments.HarmingCurseEnchantment;
import net.Shad0w.Allure.Enchantments.HauntingCurseEnchantment;
import net.Shad0w.Allure.Enchantments.RustingCurseEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AllureEnchantments {
   private static final Enchantment RUSTING_CURSE;
   private static final Enchantment CLUMSINESS_CURSE;
   private static final Enchantment HAUNTING_CURSE;
   private static final Enchantment HARMING_CURSE;

   public static void initialize() {
      if (Allure.CONFIG.cursesEnabled) {
         Registry.method_10230(Registry.field_11160, new Identifier("allure", "rusting_curse"), RUSTING_CURSE);
         Registry.method_10230(Registry.field_11160, new Identifier("allure", "clumsiness_curse"), CLUMSINESS_CURSE);
         Registry.method_10230(Registry.field_11160, new Identifier("allure", "haunting_curse"), HAUNTING_CURSE);
         Registry.method_10230(Registry.field_11160, new Identifier("allure", "harming_curse"), HARMING_CURSE);
      }

   }

   static {
      RUSTING_CURSE = new RustingCurseEnchantment(Rarity.field_9091, EquipmentSlot.values());
      CLUMSINESS_CURSE = new ClumsinessCurseEnchantment(Rarity.field_9091, new EquipmentSlot[]{EquipmentSlot.field_6173});
      HAUNTING_CURSE = new HauntingCurseEnchantment(Rarity.field_9091, new EquipmentSlot[]{EquipmentSlot.field_6173});
      HARMING_CURSE = new HarmingCurseEnchantment(Rarity.field_9091, new EquipmentSlot[]{EquipmentSlot.field_6173});
   }
}
