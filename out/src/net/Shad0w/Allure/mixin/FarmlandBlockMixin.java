package net.Shad0w.Allure.mixin;

import net.Shad0w.Allure.Allure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({FarmlandBlock.class})
public abstract class FarmlandBlockMixin extends Block {
   public FarmlandBlockMixin(Settings block$Settings_1) {
      super(block$Settings_1);
   }

   @Shadow
   public static void method_10125(BlockState blockState_1, World world_1, BlockPos blockPos_1) {
   }

   @Inject(
      method = {"onLandedUpon"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void onLandedUpon(World world_1, BlockPos blockPos_1, Entity entity_1, float float_1, CallbackInfo ci) {
      if (!world_1.field_9236 && world_1.field_9229.nextFloat() < float_1 - 0.5F && entity_1 instanceof LivingEntity && (entity_1 instanceof PlayerEntity || world_1.method_8450().method_8355(GameRules.field_19388)) && entity_1.method_17681() * entity_1.method_17681() * entity_1.method_17682() > 0.512F && entity_1 instanceof PlayerEntity && Allure.CONFIG.featherFallingFarm) {
         PlayerEntity player = (PlayerEntity)entity_1;
         CompoundTag compoundTag_1 = new CompoundTag();
         compoundTag_1.method_10582("id", "minecraft:feather_falling");
         compoundTag_1.method_10575("lvl", (short)4);
         if (((ItemStack)player.field_7514.field_7548.get(0)).method_7921().contains(compoundTag_1)) {
            super.method_9554(world_1, blockPos_1, entity_1, float_1);
            ci.cancel();
         } else {
            compoundTag_1.method_10575("lvl", (short)3);
            if (((ItemStack)player.field_7514.field_7548.get(0)).method_7921().contains(compoundTag_1) && (world_1.field_9229.nextInt(3) == 0 || world_1.field_9229.nextInt(3) == 1 || world_1.field_9229.nextInt(3) == 2)) {
               super.method_9554(world_1, blockPos_1, entity_1, float_1);
               ci.cancel();
            } else {
               compoundTag_1.method_10575("lvl", (short)2);
               if (!((ItemStack)player.field_7514.field_7548.get(0)).method_7921().contains(compoundTag_1) || world_1.field_9229.nextInt(3) != 0 && world_1.field_9229.nextInt(3) != 1) {
                  compoundTag_1.method_10575("lvl", (short)1);
                  if (((ItemStack)player.field_7514.field_7548.get(0)).method_7921().contains(compoundTag_1) && world_1.field_9229.nextInt(3) == 0) {
                     super.method_9554(world_1, blockPos_1, entity_1, float_1);
                     ci.cancel();
                  }
               } else {
                  super.method_9554(world_1, blockPos_1, entity_1, float_1);
                  ci.cancel();
               }
            }
         }
      }

   }
}
