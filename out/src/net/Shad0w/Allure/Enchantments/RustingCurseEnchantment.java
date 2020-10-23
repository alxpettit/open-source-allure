package net.Shad0w.Allure.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.entity.EquipmentSlot;

public class RustingCurseEnchantment extends Enchantment {
   public RustingCurseEnchantment(Rarity enchantment$Weight_1, EquipmentSlot[] equipmentSlots_1) {
      super(enchantment$Weight_1, EnchantmentTarget.field_9082, equipmentSlots_1);
   }

   public int method_8182(int int_1) {
      return 25;
   }

   public int method_20742(int int_1) {
      return 50;
   }

   public int method_8183() {
      return 1;
   }

   public boolean method_8193() {
      return true;
   }

   public boolean method_8195() {
      return true;
   }
}
