package net.Shad0w.Allure.Items;

import java.util.Iterator;
import java.util.List;
import net.Shad0w.Allure.Entity.ChestBoatEntity.ChestBoatEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity.Type;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.Settings;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.RaycastContext.FluidHandling;

public class ChestBoatItem extends Item {
   private final Type type;

   public ChestBoatItem(Type type, Settings settings) {
      super(settings);
      this.type = type;
   }

   public TypedActionResult method_7836(World world, PlayerEntity user, Hand hand) {
      ItemStack itemStack = user.method_5998(hand);
      HitResult hitResult = method_7872(world, user, FluidHandling.field_1347);
      if (hitResult.method_17783() == net.minecraft.util.hit.HitResult.Type.field_1333) {
         return TypedActionResult.method_22430(itemStack);
      } else {
         Vec3d vec3d = user.method_5828(1.0F);
         double d = 5.0D;
         List list = world.method_8333(user, user.method_5829().method_18804(vec3d.method_1021(5.0D)).method_1014(1.0D), EntityPredicates.field_6155.and(Entity::method_5863));
         if (!list.isEmpty()) {
            System.out.println("Whats");
            Vec3d vec3d2 = user.method_5836(1.0F);
            Iterator var11 = list.iterator();

            while(var11.hasNext()) {
               Entity entity = (Entity)var11.next();
               Box box = entity.method_5829().method_1014((double)entity.method_5871());
               if (box.method_1006(vec3d2)) {
                  return TypedActionResult.method_22430(itemStack);
               }
            }
         }

         if (hitResult.method_17783() == net.minecraft.util.hit.HitResult.Type.field_1332) {
            ChestBoatEntity boatEntity = new ChestBoatEntity(world, hitResult.method_17784().field_1352, hitResult.method_17784().field_1351, hitResult.method_17784().field_1350);
            boatEntity.method_7541(this.type);
            boatEntity.field_6031 = user.field_6031;
            if (!world.method_8587(boatEntity, boatEntity.method_5829().method_1014(-0.1D))) {
               return TypedActionResult.method_22431(itemStack);
            } else {
               if (!world.field_9236) {
                  world.method_8649(boatEntity);
                  if (!user.field_7503.field_7477) {
                     itemStack.method_7934(1);
                  }
               }

               user.method_7259(Stats.field_15372.method_14956(this));
               return TypedActionResult.method_29237(itemStack, world.method_8608());
            }
         } else {
            return TypedActionResult.method_22430(itemStack);
         }
      }
   }
}
