package net.Shad0w.Allure.mixin;

import net.Shad0w.Allure.Allure;
import net.Shad0w.Allure.Blocks.Gravestone.GravestoneBlock;
import net.Shad0w.Allure.Blocks.Gravestone.GravestoneBlockEntity;
import net.Shad0w.Allure.Init.AllureBlocks;
import net.fabricmc.loader.FabricLoader;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({LivingEntity.class})
public abstract class LivingEntityMixin extends Entity {
   @Shadow
   public int field_6213;
   private boolean once = true;

   public LivingEntityMixin(EntityType entityType_1, World world_1) {
      super(entityType_1, world_1);
   }

   @Inject(
      at = {@At("HEAD")},
      method = {"updatePostDeath"}
   )
   private void updatePostDeath(CallbackInfo info) {
      if (this.field_6002.method_18470(this.field_6021) != null && this.once && Allure.CONFIG.gravestonesEnabled && !this.field_6002.method_8450().method_8355(GameRules.field_19389)) {
         PlayerEntity playerEntity = this.field_6002.method_18470(this.field_6021);
         if (!playerEntity.field_7514.method_5442()) {
            BlockPos deathPos;
            for(deathPos = this.method_24515(); this.field_6002.method_8320(deathPos.method_10074()).method_26215(); deathPos = deathPos.method_10074()) {
            }

            if (deathPos.method_10264() < 0) {
               deathPos = new BlockPos(deathPos.method_10263(), 2, deathPos.method_10260());
            }

            while(playerEntity.field_6002.method_8320(deathPos).method_26204() instanceof FluidBlock) {
               deathPos = deathPos.method_10084();
            }

            if (this.field_6002.method_8320(deathPos.method_10074()).method_26204() instanceof FluidBlock || this.field_6002.method_8320(deathPos.method_10074()).method_26204().equals(Blocks.field_10566) || this.field_6002.method_8320(deathPos.method_10074()).method_26204().equals(Blocks.field_10219)) {
               this.field_6002.method_8501(deathPos.method_10074(), Blocks.field_10253.method_9564());
            }

            Direction facing = playerEntity.method_5735().method_10153();
            if (playerEntity.field_6002.method_8320(deathPos.method_10078()).method_26225()) {
               facing = Direction.field_11039;
            } else if (playerEntity.field_6002.method_8320(deathPos.method_10067()).method_26225()) {
               facing = Direction.field_11034;
            } else if (playerEntity.field_6002.method_8320(deathPos.method_10095()).method_26225()) {
               facing = Direction.field_11035;
            } else if (playerEntity.field_6002.method_8320(deathPos.method_10072()).method_26225()) {
               facing = Direction.field_11043;
            }

            playerEntity.field_6002.method_8501(deathPos, (BlockState)AllureBlocks.GRAVESTONE_BLOCK.method_9564().method_11657(GravestoneBlock.FACING, facing));
            GravestoneBlockEntity gravestoneBlockEntity = (GravestoneBlockEntity)playerEntity.field_6002.method_8321(deathPos);
            if (FabricLoader.INSTANCE.isModLoaded("trinkets")) {
               gravestoneBlockEntity.playerInv = DefaultedList.method_10213(playerEntity.field_7514.method_5439() + gravestoneBlockEntity.getTrinketInv(this).method_5439(), ItemStack.field_8037);
            } else {
               gravestoneBlockEntity.playerInv = DefaultedList.method_10213(playerEntity.field_7514.method_5439(), ItemStack.field_8037);
            }

            gravestoneBlockEntity.playername = playerEntity.method_5820();
            if (!this.field_6002.field_9236) {
               for(int i = 0; i < playerEntity.field_7514.method_5439(); ++i) {
                  if (!playerEntity.field_7514.method_5438(i).method_7960()) {
                     gravestoneBlockEntity.method_5447(i, playerEntity.field_7514.method_5438(i));
                     playerEntity.field_7514.method_5441(i);
                  }
               }

               if (FabricLoader.INSTANCE.isModLoaded("trinkets")) {
                  Inventory inv = gravestoneBlockEntity.getTrinketInv(this);

                  for(int i = 0; i < inv.method_5439(); ++i) {
                     ItemStack stack = inv.method_5438(i);
                     if (!stack.method_7960()) {
                        gravestoneBlockEntity.method_5447(i + playerEntity.field_7514.method_5439(), stack);
                     }
                  }

                  inv.method_5448();
               }
            }

            gravestoneBlockEntity.method_5431();
            this.once = false;
         }
      }

   }

   @Inject(
      at = {@At("HEAD")},
      method = {"damage"},
      cancellable = true
   )
   private void damage(DamageSource damageSource_1, float float_1, CallbackInfoReturnable info) {
      if (damageSource_1.method_5529() instanceof PlayerEntity && Allure.CONFIG.cursesEnabled) {
         PlayerEntity entity_2 = (PlayerEntity)damageSource_1.method_5529();
         boolean EnchantActive = entity_2.method_6051().nextInt(5) == 2;
         CompoundTag compoundTag_1 = new CompoundTag();
         compoundTag_1.method_10582("id", "allure:clumsiness_curse");
         compoundTag_1.method_10575("lvl", (short)1);
         CompoundTag compoundTag_2 = new CompoundTag();
         compoundTag_2.method_10582("id", "allure:haunting_curse");
         compoundTag_2.method_10575("lvl", (short)1);
         CompoundTag compoundTag_3 = new CompoundTag();
         compoundTag_3.method_10582("id", "allure:harming_curse");
         compoundTag_3.method_10575("lvl", (short)1);
         ItemStack itemStack_1 = entity_2.field_7514.method_7391();
         if (EnchantActive) {
            if (itemStack_1.method_7921().contains(compoundTag_2) && !entity_2.field_6002.field_9236) {
               int spawnRange = 12;
               double double_4 = (double)entity_2.method_24515().method_10263() + (entity_2.field_6002.field_9229.nextDouble() - entity_2.field_6002.field_9229.nextDouble()) * (double)spawnRange + 0.5D;
               double double_5 = (double)(entity_2.method_24515().method_10264() + entity_2.field_6002.field_9229.nextInt(3));
               double double_6 = (double)entity_2.method_24515().method_10260() + (entity_2.field_6002.field_9229.nextDouble() - entity_2.field_6002.field_9229.nextDouble()) * (double)spawnRange + 0.5D;
               BlockPos blockPos = new BlockPos(double_4, double_5, double_6);
               if (entity_2.field_6002.method_8320(blockPos.method_10069(0, 1, 0)).method_26204().equals(Blocks.field_10124) && entity_2.field_6002.method_8320(blockPos.method_10069(0, 2, 0)).method_26204().equals(Blocks.field_10124)) {
                  switch(entity_2.method_6051().nextInt(4)) {
                  case 1:
                     SkeletonEntity Entity_1 = (SkeletonEntity)EntityType.field_6137.method_5883(entity_2.field_6002);
                     Entity_1.method_5673(EquipmentSlot.field_6173, new ItemStack(Items.field_8102));
                     Entity_1.method_5641((double)blockPos.method_10263(), (double)blockPos.method_10264(), (double)blockPos.method_10260(), entity_2.field_6002.field_9229.nextFloat() * 360.0F, 0.0F);
                     entity_2.field_6002.method_8649(Entity_1);
                     break;
                  case 2:
                     WitchEntity Entity_1 = (WitchEntity)EntityType.field_6145.method_5883(entity_2.field_6002);
                     Entity_1.method_5641((double)blockPos.method_10263(), (double)blockPos.method_10264(), (double)blockPos.method_10260(), entity_2.field_6002.field_9229.nextFloat() * 360.0F, 0.0F);
                     entity_2.field_6002.method_8649(Entity_1);
                     break;
                  case 3:
                     EndermanEntity Entity_1 = (EndermanEntity)EntityType.field_6091.method_5883(entity_2.field_6002);
                     Entity_1.method_5641((double)blockPos.method_10263(), (double)blockPos.method_10264(), (double)blockPos.method_10260(), entity_2.field_6002.field_9229.nextFloat() * 360.0F, 0.0F);
                     entity_2.field_6002.method_8649(Entity_1);
                     break;
                  default:
                     ZombieEntity Entity_1 = (ZombieEntity)EntityType.field_6051.method_5883(entity_2.field_6002);
                     Entity_1.method_5641((double)blockPos.method_10263(), (double)blockPos.method_10264(), (double)blockPos.method_10260(), entity_2.field_6002.field_9229.nextFloat() * 360.0F, 0.0F);
                     entity_2.field_6002.method_8649(Entity_1);
                  }
               }
            }

            if (itemStack_1.method_7921().contains(compoundTag_3)) {
               entity_2.method_5643(DamageSource.field_5846, 2.0F);
            }

            if (itemStack_1.method_7921().contains(compoundTag_1)) {
               info.setReturnValue(false);
            }
         }
      }

   }
}
