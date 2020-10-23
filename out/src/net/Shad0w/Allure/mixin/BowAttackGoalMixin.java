package net.Shad0w.Allure.mixin;

import net.Shad0w.Allure.Allure;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.BowAttackGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.BowItem;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({BowAttackGoal.class})
public abstract class BowAttackGoalMixin extends Goal {
   @Shadow
   private int field_6572;
   @Shadow
   @Final
   private float field_6570;
   @Shadow
   private int field_6568;
   @Shadow
   @Final
   private double field_6569;
   @Shadow
   private boolean field_6573;
   @Shadow
   private boolean field_6571;
   @Shadow
   private int field_6574;
   @Shadow
   private int field_6575;
   @Shadow
   @Final
   private HostileEntity field_6576;

   @Overwrite
   public void method_6268() {
      LivingEntity livingEntity = this.field_6576.method_5968();
      if (livingEntity != null) {
         double d = this.field_6576.method_5649(livingEntity.method_23317(), livingEntity.method_23318(), livingEntity.method_23321());
         boolean bl = this.field_6576.method_5985().method_6369(livingEntity);
         boolean bl2 = this.field_6572 > 0;
         if (bl != bl2) {
            this.field_6572 = 0;
         }

         if (bl) {
            ++this.field_6572;
         } else {
            --this.field_6572;
         }

         if (d <= (double)this.field_6570 && this.field_6572 >= 20) {
            this.field_6576.method_5942().method_6340();
            ++this.field_6568;
         } else {
            this.field_6576.method_5942().method_6335(livingEntity, this.field_6569);
            this.field_6568 = -1;
         }

         if (this.field_6568 >= 20) {
            if ((double)this.field_6576.method_6051().nextFloat() < 0.3D) {
               this.field_6573 = !this.field_6573;
            }

            if ((double)this.field_6576.method_6051().nextFloat() < 0.3D) {
               this.field_6571 = !this.field_6571;
            }

            this.field_6568 = 0;
         }

         if (this.field_6568 > -1) {
            if (d > (double)(this.field_6570 * 0.75F)) {
               this.field_6571 = false;
            } else if (d < (double)(this.field_6570 * 0.25F)) {
               this.field_6571 = true;
            }

            if (Allure.CONFIG.skeletonStrafe) {
               this.field_6576.method_5962().method_6243(this.field_6571 ? -0.5F : 0.5F, this.field_6573 ? 0.5F : -0.5F);
            }

            this.field_6576.method_5988().method_6226(livingEntity, 30.0F, 30.0F);
            this.field_6576.method_5951(livingEntity, 30.0F, 30.0F);
         } else {
            this.field_6576.method_5988().method_6226(livingEntity, 30.0F, 30.0F);
            this.field_6576.method_5951(livingEntity, 30.0F, 30.0F);
         }

         if (this.field_6576.method_6115()) {
            if (!bl && this.field_6572 < -60) {
               this.field_6576.method_6021();
            } else if (bl) {
               int i = this.field_6576.method_6048();
               if (i >= 20) {
                  this.field_6576.method_6021();
                  ((RangedAttackMob)this.field_6576).method_7105(livingEntity, BowItem.method_7722(i));
                  this.field_6574 = this.field_6575;
               }
            }
         } else if (--this.field_6574 <= 0 && this.field_6572 >= -60) {
            this.field_6576.method_6019(ProjectileUtil.method_18812(this.field_6576, Items.field_8102));
         }
      }

   }
}
