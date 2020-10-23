package net.Shad0w.Allure.mixin;

import java.util.Iterator;
import java.util.List;
import net.Shad0w.Allure.Init.AllureBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Boxes;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({PistonBlockEntity.class})
public abstract class PistonBlockEntityMixin extends BlockEntity {
   @Shadow
   private BlockState field_12204;
   @Shadow
   private float field_12207;

   @Shadow
   public abstract Direction method_11506();

   @Shadow
   protected abstract BlockState method_11496();

   @Shadow
   protected abstract Box method_11500(Box var1);

   public PistonBlockEntityMixin(BlockEntityType type) {
      super(type);
   }

   @Inject(
      method = {"isPushingHoneyBlock"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void isPushingHoneyBlock(CallbackInfoReturnable ci) {
      if (this.field_12204.method_26204() == AllureBlocks.BLUE_SLIME_BLOCK || this.field_12204.method_26204() == AllureBlocks.RED_SLIME_BLOCK || this.field_12204.method_26204() == AllureBlocks.CYAN_SLIME_BLOCK || this.field_12204.method_26204() == AllureBlocks.MAGENTA_SLIME_BLOCK || this.field_12204.method_26204() == AllureBlocks.YELLOW_SLIME_BLOCK) {
         ci.setReturnValue(true);
      }

   }

   @Inject(
      method = {"pushEntities"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void pushEntities(float nextProgress, CallbackInfo ci) {
      Direction direction = this.method_11506();
      double d = (double)(nextProgress - this.field_12207);
      VoxelShape voxelShape = this.method_11496().method_26220(this.field_11863, this.method_11016());
      if (!voxelShape.method_1110()) {
         Box box = this.method_11500(voxelShape.method_1107());
         List list = this.field_11863.method_8335((Entity)null, Boxes.method_23362(box, direction, d).method_991(box));
         if (!list.isEmpty()) {
            List list2 = voxelShape.method_1090();
            boolean bl = this.field_12204.method_27852(AllureBlocks.YELLOW_SLIME_BLOCK) || this.field_12204.method_27852(AllureBlocks.MAGENTA_SLIME_BLOCK) || this.field_12204.method_27852(AllureBlocks.CYAN_SLIME_BLOCK) || this.field_12204.method_27852(AllureBlocks.BLUE_SLIME_BLOCK) || this.field_12204.method_27852(AllureBlocks.RED_SLIME_BLOCK);
            Iterator var10 = list.iterator();

            while(var10.hasNext()) {
               Entity entity = (Entity)var10.next();
               if (entity.method_5657() != PistonBehavior.field_15975 && bl) {
                  Vec3d vec3d = entity.method_18798();
                  double e = vec3d.field_1352;
                  double f = vec3d.field_1351;
                  double g = vec3d.field_1350;
                  switch(direction.method_10166()) {
                  case field_11048:
                     e = (double)direction.method_10148();
                     break;
                  case field_11052:
                     f = (double)direction.method_10164();
                     break;
                  case field_11051:
                     g = (double)direction.method_10165();
                  }

                  entity.method_18800(e, f, g);
                  if (entity instanceof ServerPlayerEntity) {
                     ((ServerPlayerEntity)entity).field_13987.method_14364(new EntityVelocityUpdateS2CPacket(entity));
                  }
               }
            }

            return;
         }
      }

   }
}
