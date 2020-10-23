package net.Shad0w.Allure.mixin;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({BrewingRecipeRegistry.class})
public interface BrewingRecipeRegistryMixin {
   @Invoker
   static void invokeRegisterPotionRecipe(Potion in, Item ingredient, Potion result) {
   }
}
