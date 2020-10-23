package net.Shad0w.Allure.mixin;

import net.Shad0w.Allure.Allure;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({VillagerEntity.class})
public abstract class VillagerFollowMixin extends MerchantEntity {
   public VillagerFollowMixin(EntityType entityType_1, World world_1) {
      super(entityType_1, world_1);
   }

   @Inject(
      at = {@At("RETURN")},
      method = {"setVillagerData"}
   )
   private void setVillagerData(CallbackInfo info) {
      if (Allure.CONFIG.villagersFollow) {
         TargetPredicate TEMPTING_ENTITY_PREDICATE = (new TargetPredicate()).method_18418(10.0D).method_18417().method_18421().method_18423().method_18422();
         TemptGoal followPlayerTask = new TemptGoal(this, 0.5D, Ingredient.method_8091(new ItemConvertible[]{Items.field_8733}), false);
         this.field_6201.method_6277(4, followPlayerTask);
      }

   }
}
