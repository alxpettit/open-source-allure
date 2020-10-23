package net.Shad0w.Allure.mixin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.Shad0w.Allure.Utils.Chain.ChainHandler;
import net.Shad0w.Allure.Utils.Chain.ChainRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({MinecartEntity.class})
public abstract class MinecartEntityMixin extends AbstractMinecartEntity {
   public MinecartEntityMixin(EntityType type, World world) {
      super(type, world);
   }

   public void method_5773() {
      super.method_5773();
      if (ChainHandler.canBeLinkedTo(this)) {
         ChainHandler.adjustVehicle(this);
      }

      if (this.field_6002.method_8608()) {
         ChainRenderer.updateTick();
      }

   }

   public void method_7516(DamageSource damageSource) {
      super.method_7516(damageSource);
      if (ChainHandler.getLinked(this) != null) {
         this.method_5699(new ItemStack(Items.field_23983), 0.0F);
      }

   }

   protected void method_5652(CompoundTag tag) {
      super.method_5652(tag);
   }

   protected void method_5749(CompoundTag tag) {
      super.method_5749(tag);
   }

   @Inject(
      method = {"interact"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void interact(PlayerEntity player, Hand hand, CallbackInfoReturnable ci) {
      ItemStack stack = player.method_5998(hand);
      Entity entity = this;
      Entity link = ChainHandler.getLinked(this);
      boolean sneaking = player.method_5715();
      List linkedToPlayer = new ArrayList();
      Iterator var9 = super.field_6002.method_8335(this, player.method_5829().method_1014(8.0D)).iterator();

      Entity linked;
      while(var9.hasNext()) {
         linked = (Entity)var9.next();
         if (ChainHandler.getLinked(linked) == player) {
            linkedToPlayer.add(linked);
         }
      }

      if (ChainHandler.canBeLinked(this) && linkedToPlayer.isEmpty() && !stack.method_7960() && stack.method_7909() == Items.field_23983 && link == null) {
         if (!super.field_6002.method_8608()) {
            ChainHandler.setLink(this, player.method_5667());
            if (!player.method_7337()) {
               stack.method_7934(1);
            }
         }

         ci.setReturnValue(ActionResult.field_5812);
      } else if (link == player) {
         ci.setReturnValue(ActionResult.field_5812);
      } else if (ChainHandler.canBeLinked(this) && !linkedToPlayer.isEmpty()) {
         if (!super.field_6002.method_8608()) {
            var9 = linkedToPlayer.iterator();

            while(var9.hasNext()) {
               linked = (Entity)var9.next();
               ChainHandler.setLink(linked, entity.method_5667());
            }
         }

         ci.setReturnValue(ActionResult.field_5812);
      } else if (link != null && sneaking) {
         if (!super.field_6002.method_8608()) {
            if (!player.method_7337()) {
               this.method_5699(new ItemStack(Items.field_23983), 0.0F);
            }

            ChainHandler.setLink(this, (UUID)null);
         }

         ci.setReturnValue(ActionResult.field_5812);
      }

   }
}
