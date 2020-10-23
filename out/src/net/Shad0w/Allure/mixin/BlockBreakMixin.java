package net.Shad0w.Allure.mixin;

import net.Shad0w.Allure.Allure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Block.class})
public abstract class BlockBreakMixin {
   @Inject(
      method = {"afterBreak"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void afterBreak(World world_1, PlayerEntity playerEntity_1, BlockPos blockPos_1, BlockState blockState_1, BlockEntity blockEntity_1, ItemStack itemStack_1, CallbackInfo ci) {
      if (playerEntity_1.method_6051().nextInt(5) == 2 && Allure.CONFIG.cursesEnabled && itemStack_1.method_7942()) {
         CompoundTag compoundTag_1 = new CompoundTag();
         compoundTag_1.method_10582("id", "allure:clumsiness_curse");
         compoundTag_1.method_10575("lvl", (short)1);
         CompoundTag compoundTag_2 = new CompoundTag();
         compoundTag_2.method_10582("id", "allure:haunting_curse");
         compoundTag_2.method_10575("lvl", (short)1);
         CompoundTag compoundTag_3 = new CompoundTag();
         compoundTag_3.method_10582("id", "allure:harming_curse");
         compoundTag_3.method_10575("lvl", (short)1);
         if (itemStack_1.method_7921().contains(compoundTag_2) && !world_1.field_9236) {
            int spawnRange = 12;
            double double_4 = (double)blockPos_1.method_10263() + (world_1.field_9229.nextDouble() - world_1.field_9229.nextDouble()) * (double)spawnRange + 0.5D;
            double double_5 = (double)(blockPos_1.method_10264() + world_1.field_9229.nextInt(3) - 1);
            double double_6 = (double)blockPos_1.method_10260() + (world_1.field_9229.nextDouble() - world_1.field_9229.nextDouble()) * (double)spawnRange + 0.5D;
            BlockPos blockPos = new BlockPos(double_4, double_5, double_6);
            if (world_1.method_8320(blockPos.method_10069(0, 1, 0)).method_26204().equals(Blocks.field_10124) && world_1.method_8320(blockPos.method_10069(0, 2, 0)).method_26204().equals(Blocks.field_10124)) {
               switch(playerEntity_1.method_6051().nextInt(4)) {
               case 1:
                  SkeletonEntity Entity_1 = (SkeletonEntity)EntityType.field_6137.method_5883(world_1);
                  Entity_1.method_5673(EquipmentSlot.field_6173, new ItemStack(Items.field_8102));
                  Entity_1.method_5641((double)blockPos.method_10263(), (double)blockPos.method_10264(), (double)blockPos.method_10260(), world_1.field_9229.nextFloat() * 360.0F, 0.0F);
                  world_1.method_8649(Entity_1);
                  break;
               case 2:
                  WitchEntity Entity_1 = (WitchEntity)EntityType.field_6145.method_5883(world_1);
                  Entity_1.method_5641((double)blockPos.method_10263(), (double)blockPos.method_10264(), (double)blockPos.method_10260(), world_1.field_9229.nextFloat() * 360.0F, 0.0F);
                  world_1.method_8649(Entity_1);
                  break;
               case 3:
                  EndermanEntity Entity_1 = (EndermanEntity)EntityType.field_6091.method_5883(world_1);
                  Entity_1.method_5641((double)blockPos.method_10263(), (double)blockPos.method_10264(), (double)blockPos.method_10260(), world_1.field_9229.nextFloat() * 360.0F, 0.0F);
                  world_1.method_8649(Entity_1);
                  break;
               default:
                  ZombieEntity Entity_1 = (ZombieEntity)EntityType.field_6051.method_5883(world_1);
                  Entity_1.method_5641((double)blockPos.method_10263(), (double)blockPos.method_10264(), (double)blockPos.method_10260(), world_1.field_9229.nextFloat() * 360.0F, 0.0F);
                  world_1.method_8649(Entity_1);
               }
            }
         }

         if (itemStack_1.method_7921().contains(compoundTag_3)) {
            playerEntity_1.method_5643(DamageSource.field_5869, 2.0F);
         }

         if (itemStack_1.method_7921().contains(compoundTag_1)) {
            ci.cancel();
         }
      }

   }
}
