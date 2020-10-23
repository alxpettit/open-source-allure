package net.Shad0w.Allure.mixin;

import java.util.UUID;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.LeashKnotEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.packet.s2c.play.EntityAttachS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({MinecartEntity.class})
public abstract class AbstractMinecartEntityMixin extends AbstractMinecartEntity {
   private Entity holdingEntity;
   private int holdingEntityId;
   private CompoundTag leashTag;
   private static final float CHAIN_SLACK = 2.0F;
   private static final float STIFFNESS = 0.4F;
   private static final float MAX_FORCE = 6.0F;

   public AbstractMinecartEntityMixin(EntityType type, World world) {
      super(type, world);
   }

   public void method_5773() {
      super.method_5773();
      if (!this.field_6002.field_9236) {
         this.updateLeash();
      }

   }

   protected void method_5652(CompoundTag tag) {
      super.method_5652(tag);
      if (this.holdingEntity != null) {
         CompoundTag compoundTag3 = new CompoundTag();
         if (this.holdingEntity instanceof LivingEntity) {
            UUID uUID = this.holdingEntity.method_5667();
            compoundTag3.method_25927("UUID", uUID);
         } else if (this.holdingEntity instanceof AbstractDecorationEntity) {
            BlockPos blockPos = ((AbstractDecorationEntity)this.holdingEntity).method_6896();
            compoundTag3.method_10569("X", blockPos.method_10263());
            compoundTag3.method_10569("Y", blockPos.method_10264());
            compoundTag3.method_10569("Z", blockPos.method_10260());
         }

         tag.method_10566("Leash", compoundTag3);
      } else if (this.leashTag != null) {
         tag.method_10566("Leash", this.leashTag.method_10553());
      }

   }

   protected void method_5749(CompoundTag tag) {
      super.method_5749(tag);
      if (tag.method_10573("Leash", 10)) {
         this.leashTag = tag.method_10562("Leash");
      }

   }

   @Inject(
      method = {"interact"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void interact(PlayerEntity player, Hand hand, CallbackInfoReturnable ci) {
      ItemStack itemStack = player.method_5998(hand);
      if (this.getHoldingEntity() == player) {
         this.detachLeash(true, !player.field_7503.field_7477);
         ci.setReturnValue(ActionResult.method_29236(this.field_6002.field_9236));
      } else if (itemStack.method_7909() == Items.field_8719 && this.canBeLeashedBy(player)) {
         this.attachLeash(player, true);
         itemStack.method_7934(1);
         ci.setReturnValue(ActionResult.method_29236(this.field_6002.field_9236));
      }

   }

   protected void updateLeash() {
      if (this.leashTag != null) {
         this.deserializeLeashTag();
      }

      if (this.holdingEntity != null) {
         if (!this.method_5805() || !this.holdingEntity.method_5805()) {
            this.detachLeash(true, true);
         }

         Entity entity = this.getHoldingEntity();
         if (entity != null && entity.field_6002 == this.field_6002) {
            Entity master = this.holdingEntity;
            if (master == this || !master.field_6002.method_8608()) {
               return;
            }

            double dist = (double)master.method_5739(this);
            Vec3d masterPosition = master.method_19538();
            Vec3d followerPosition = this.method_19538();
            Vec3d masterMotion = master.method_18798();
            Vec3d followerMotion = this.method_18798();
            Vec3d direction = followerPosition.method_1020(masterPosition);
            direction = direction.method_1023(0.0D, direction.field_1351, 0.0D).method_1029();
            double base = masterMotion.method_1033() + followerMotion.method_1033();
            if (base != 0.0D) {
               double masterRatio = 1.0D + masterMotion.method_1033() / base;
               double followerRatio = 1.0D + followerMotion.method_1033() / base;
               double stretch = dist - 2.0D;
               double springX = 0.4000000059604645D * stretch * direction.field_1352;
               double springZ = 0.4000000059604645D * stretch * direction.field_1350;
               springX = MathHelper.method_15350(springX, -6.0D, 6.0D);
               springZ = MathHelper.method_15350(springZ, -6.0D, 6.0D);
               masterMotion = masterMotion.method_1031(springX * followerRatio, 0.0D, springZ * followerRatio);
               followerMotion = followerMotion.method_1023(springX * masterRatio, 0.0D, springZ * masterRatio);
               master.method_18799(masterMotion);
               this.method_18799(followerMotion);
            }
         }

         System.out.println("jeff");
      }

   }

   public void detachLeash(boolean sendPacket, boolean dropItem) {
      if (this.holdingEntity != null) {
         this.field_5983 = false;
         if (!(this.holdingEntity instanceof PlayerEntity)) {
            this.holdingEntity.field_5983 = false;
         }

         this.holdingEntity = null;
         this.leashTag = null;
         if (!this.field_6002.field_9236 && dropItem) {
            this.method_5706(Items.field_8719);
         }

         if (!this.field_6002.field_9236 && sendPacket && this.field_6002 instanceof ServerWorld) {
            ((ServerWorld)this.field_6002).method_14178().method_18754(this, new EntityAttachS2CPacket(this, (Entity)null));
         }
      }

   }

   public boolean canBeLeashedBy(PlayerEntity player) {
      return !this.isLeashed() && !(this instanceof Monster);
   }

   public boolean isLeashed() {
      return this.holdingEntity != null;
   }

   public Entity getHoldingEntity() {
      if (this.holdingEntity == null && this.holdingEntityId != 0 && this.field_6002.field_9236) {
         this.holdingEntity = this.field_6002.method_8469(this.holdingEntityId);
      }

      return this.holdingEntity;
   }

   public void attachLeash(Entity entity, boolean sendPacket) {
      this.holdingEntity = entity;
      this.leashTag = null;
      this.field_5983 = true;
      if (!(this.holdingEntity instanceof PlayerEntity)) {
         this.holdingEntity.field_5983 = true;
      }

      if (!this.field_6002.field_9236 && sendPacket && this.field_6002 instanceof ServerWorld) {
         ((ServerWorld)this.field_6002).method_14178().method_18754(this, new EntityAttachS2CPacket(this, this.holdingEntity));
      }

      if (this.method_5765()) {
         this.method_5848();
      }

   }

   @Environment(EnvType.CLIENT)
   public void setHoldingEntityId(int id) {
      this.holdingEntityId = id;
      this.detachLeash(false, false);
   }

   private void deserializeLeashTag() {
      if (this.leashTag != null && this.field_6002 instanceof ServerWorld) {
         if (this.leashTag.method_25928("UUID")) {
            UUID uUID = this.leashTag.method_25926("UUID");
            Entity entity = ((ServerWorld)this.field_6002).method_14190(uUID);
            if (entity != null) {
               this.attachLeash(entity, true);
               return;
            }
         } else if (this.leashTag.method_10573("X", 99) && this.leashTag.method_10573("Y", 99) && this.leashTag.method_10573("Z", 99)) {
            BlockPos blockPos = new BlockPos(this.leashTag.method_10550("X"), this.leashTag.method_10550("Y"), this.leashTag.method_10550("Z"));
            this.attachLeash(LeashKnotEntity.method_6932(this.field_6002, blockPos), true);
            return;
         }

         if (this.field_6012 > 100) {
            this.method_5706(Items.field_8719);
            this.leashTag = null;
         }
      }

   }

   protected void method_30076() {
      super.method_30076();
      this.detachLeash(true, false);
   }
}
