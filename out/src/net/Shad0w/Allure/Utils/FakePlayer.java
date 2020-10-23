package net.Shad0w.Allure.Utils;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FakePlayer extends PlayerEntity {
   public FakePlayer(World world, BlockPos pos, float yaw, GameProfile profile) {
      super(world, pos, 0.0F, profile);
   }

   public boolean method_7325() {
      return false;
   }

   public boolean method_7337() {
      return false;
   }

   public float method_18394(EntityPose pose, EntityDimensions dimensions) {
      return -0.5F;
   }
}
