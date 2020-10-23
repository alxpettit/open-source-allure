package net.Shad0w.Allure.Utils.Chain;

import java.util.Iterator;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;

public class ChainHandler {
   public static final String LINKED_TO = "Quark:VehicleLink";
   public static final double DRAG = 0.95D;
   public static final float CHAIN_SLACK = 2.0F;
   public static final float MAX_DISTANCE = 8.0F;
   private static final float STIFFNESS = 0.4F;
   private static final float MAX_FORCE = 6.0F;
   private static CompoundTag leashTag;
   public static UUID attachedUUID = new UUID(555555555L, 555555555L);

   protected static void writeCustomDataToTag(CompoundTag tag) {
   }

   protected static void readCustomDataFromTag(CompoundTag tag) {
      if (tag.method_10573("Leash", 10)) {
         leashTag = tag.method_10562("Leash");
      }

   }

   private static void adjustVelocity(Entity master, Entity follower) {
      if (master != follower && !master.field_6002.method_8608()) {
         double dist = (double)master.method_5739(follower);
         Vec3d masterPosition = master.method_19538();
         Vec3d followerPosition = follower.method_19538();
         Vec3d masterMotion = master.method_18798();
         Vec3d followerMotion = follower.method_18798();
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
            follower.method_18799(followerMotion);
         }

      }
   }

   public static UUID getLink(Entity vehicle) {
      if (!canBeLinked(vehicle)) {
         return null;
      } else {
         return !vehicle.method_5667().equals("Quark:VehicleLink") ? null : vehicle.method_5667();
      }
   }

   public static boolean canBeLinkedTo(Entity entity) {
      return entity instanceof BoatEntity || entity instanceof AbstractMinecartEntity || entity instanceof PlayerEntity;
   }

   public static boolean canBeLinked(Entity entity) {
      return entity instanceof BoatEntity || entity instanceof AbstractMinecartEntity;
   }

   public static Entity getLinked(Entity vehicle) {
      UUID uuid = getLink(vehicle);
      if (uuid != null && !uuid.equals((Object)null)) {
         Iterator var2 = vehicle.field_6002.method_8390(Entity.class, vehicle.method_5829().method_1014(8.0D), ChainHandler::canBeLinkedTo).iterator();

         Entity entity;
         do {
            if (!var2.hasNext()) {
               return null;
            }

            entity = (Entity)var2.next();
         } while(!entity.method_5667().equals(uuid));

         return entity;
      } else {
         return null;
      }
   }

   public static void adjustVehicle(Entity cart) {
      Entity other = getLinked(cart);
      if (other == null) {
         if (getLink(cart) != null) {
            breakChain(cart);
         }

      } else {
         if (!(other instanceof PlayerEntity)) {
            adjustVelocity(other, cart);
         }

         cart.method_18799(cart.method_18798().method_18805(0.95D, 1.0D, 0.95D));
      }
   }

   private static void breakChain(Entity cart) {
      setLink(cart, (UUID)null);
      if (!cart.field_6002.method_8608() && cart.field_6002.method_8450().method_8355(GameRules.field_19393)) {
         cart.method_5699(new ItemStack(Items.field_23983), 0.0F);
      }

   }

   public static void setLink(Entity entity, UUID uuid) {
      if (canBeLinked(entity)) {
         if (entity.method_5667().equals(uuid)) {
            return;
         }

         if (uuid != null && !uuid.equals((Object)null)) {
            entity.method_5826(attachedUUID);
         } else {
            entity.method_5826((UUID)null);
         }
      }

   }
}
