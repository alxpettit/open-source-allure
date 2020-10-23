package net.Shad0w.Allure.Init;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import net.Shad0w.Allure.Utils.AllureStatusEffect;
import net.Shad0w.Allure.mixin.BrewingRecipeRegistryMixin;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AllurePotions {
   private static final Collection RECIPES = new ArrayList();
   public static final StatusEffect RESILIENCE_EFFECT;
   public static final Potion RESISTANCE;
   public static final Potion LONG_RESISTANCE;
   public static final Potion STRONG_RESISTANCE;
   public static final Potion RESILIENCE;
   public static final Potion LONG_RESILIENCE;
   public static final Potion STRONG_RESILIENCE;

   public static void initialize() {
      Registry.method_10231(Registry.field_11159, 32, (new Identifier("allure", "resilience")).toString(), RESILIENCE_EFFECT);
      RESILIENCE_EFFECT.method_5566(EntityAttributes.field_23718, "2ddf3f0a-f386-47b6-aeb0-6bd32851f215", 0.5D, Operation.field_6328);
      RECIPES.add(new AllurePotions.RecipeToInit(Potions.field_8999, AllureItems.ROOT, RESISTANCE));
      Registry.method_10230(Registry.field_11143, new Identifier("allure", "resistance"), RESISTANCE);
      Registry.method_10230(Registry.field_11143, new Identifier("allure", "long_resistance"), LONG_RESISTANCE);
      Registry.method_10230(Registry.field_11143, new Identifier("allure", "strong_resistance"), STRONG_RESISTANCE);
      RECIPES.add(new AllurePotions.RecipeToInit(Potions.field_8999, AllureItems.CRAB_SHELL, RESILIENCE));
      Registry.method_10230(Registry.field_11143, new Identifier("allure", "resilience"), RESILIENCE);
      Registry.method_10230(Registry.field_11143, new Identifier("allure", "long_resilience"), LONG_RESILIENCE);
      Registry.method_10230(Registry.field_11143, new Identifier("allure", "strong_resilience"), STRONG_RESILIENCE);
      RECIPES.forEach(AllurePotions.RecipeToInit::init);
   }

   private static void mapPotions(Potion in, Item ingredient, Potion result) {
      Identifier potionInId = Registry.field_11143.method_10221(in);
      Identifier potionOutId = Registry.field_11143.method_10221(result);
      Optional inLong = Registry.field_11143.method_17966(new Identifier(potionInId.method_12836(), "long_" + potionInId.method_12832()));
      Optional inStrong = Registry.field_11143.method_17966(new Identifier(potionInId.method_12836(), "strong_" + potionInId.method_12832()));
      Optional outLong = Registry.field_11143.method_17966(new Identifier(potionOutId.method_12836(), "long_" + potionOutId.method_12832()));
      Optional outStrong = Registry.field_11143.method_17966(new Identifier(potionOutId.method_12836(), "strong_" + potionOutId.method_12832()));
      if (outLong.isPresent() && inLong.isPresent()) {
         BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe((Potion)inLong.get(), ingredient, (Potion)outLong.get());
      }

      if (outStrong.isPresent() && inStrong.isPresent()) {
         BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe((Potion)inStrong.get(), ingredient, (Potion)outStrong.get());
      }

      BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(in, ingredient, result);
   }

   private static void variantRecipes(Potion potion) {
      Identifier id = Registry.field_11143.method_10221(potion);
      Optional lengthy = Registry.field_11143.method_17966(new Identifier(id.method_12836(), "long_" + id.method_12832()));
      Optional strong = Registry.field_11143.method_17966(new Identifier(id.method_12836(), "strong_" + id.method_12832()));
      if (lengthy.isPresent()) {
         BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(potion, Items.field_8725, (Potion)lengthy.get());
      }

      if (strong.isPresent()) {
         BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(potion, Items.field_8601, (Potion)strong.get());
      }

   }

   static {
      RESILIENCE_EFFECT = new AllureStatusEffect(StatusEffectType.field_18271, 59704360);
      RESISTANCE = new Potion(new StatusEffectInstance[]{new StatusEffectInstance(StatusEffects.field_5907, 3600)});
      LONG_RESISTANCE = new Potion(new StatusEffectInstance[]{new StatusEffectInstance(StatusEffects.field_5907, 9600)});
      STRONG_RESISTANCE = new Potion(new StatusEffectInstance[]{new StatusEffectInstance(StatusEffects.field_5907, 1800, 1)});
      RESILIENCE = new Potion(new StatusEffectInstance[]{new StatusEffectInstance(RESILIENCE_EFFECT, 3600)});
      LONG_RESILIENCE = new Potion(new StatusEffectInstance[]{new StatusEffectInstance(RESILIENCE_EFFECT, 9600)});
      STRONG_RESILIENCE = new Potion(new StatusEffectInstance[]{new StatusEffectInstance(RESILIENCE_EFFECT, 1800, 1)});
   }

   private static class RecipeToInit {
      private final Potion in;
      private final Item ingredient;
      private final Potion result;

      private RecipeToInit(Potion in, Item ingredient, Potion result) {
         this.in = in;
         this.ingredient = ingredient;
         this.result = result;
      }

      public void init() {
         AllurePotions.mapPotions(this.in, this.ingredient, this.result);
         AllurePotions.variantRecipes(this.result);
      }

      // $FF: synthetic method
      RecipeToInit(Potion x0, Item x1, Potion x2, Object x3) {
         this(x0, x1, x2);
      }
   }
}
