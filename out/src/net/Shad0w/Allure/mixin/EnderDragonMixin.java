package net.Shad0w.Allure.mixin;

import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.DefaultAttributeContainer.Builder;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({EnderDragonEntity.class})
public class EnderDragonMixin {
   @Overwrite
   public static Builder method_26903() {
      return MobEntity.method_26828().method_26868(EntityAttributes.field_23716, 1.0D);
   }
}
