package net.Shad0w.Allure.mixin;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.Shad0w.Allure.Blocks.FeedingTrough.FeedingTroughBlock;
import net.Shad0w.Allure.Blocks.FeedingTrough.FeedingTroughBlockEntity;
import net.Shad0w.Allure.Init.AllureBlocks;
import net.Shad0w.Allure.Utils.FakePlayer;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({TemptGoal.class})
public abstract class TemptGoalMixin {
   @Shadow
   private int field_6612;
   @Shadow
   @Final
   protected PathAwareEntity field_6616;
   @Shadow
   protected PlayerEntity field_6617;

   @Shadow
   protected abstract boolean method_6312(ItemStack var1);

   @Inject(
      method = {"canStart"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void canStart(CallbackInfoReturnable ci) {
      if (this.field_6612 <= 0 && this.field_6616 instanceof AnimalEntity && ((AnimalEntity)this.field_6616).method_6482()) {
         int spawnRange = 5;
         BlockPos blockPos_1 = this.field_6616.method_24515();
         World world_1 = this.field_6616.field_6002;
         FeedingTroughBlockEntity block = null;
         double dist = 100.0D;

         int x;
         for(x = -spawnRange; x < spawnRange + 1; ++x) {
            for(int z = -spawnRange; z < spawnRange + 1; ++z) {
               for(int y = -1; y < 2; ++y) {
                  if (world_1.method_8320(new BlockPos(blockPos_1.method_10263() + x, blockPos_1.method_10264() + y, blockPos_1.method_10260() + z)).method_26204().equals(AllureBlocks.FEEDING_TROUGH_BLOCK) && (Boolean)world_1.method_8320(new BlockPos(blockPos_1.method_10263() + x, blockPos_1.method_10264() + y, blockPos_1.method_10260() + z)).method_11654(FeedingTroughBlock.FULL) && this.field_6616.method_5649((double)(blockPos_1.method_10263() + x), (double)(blockPos_1.method_10264() + y), (double)(blockPos_1.method_10260() + z)) < dist) {
                     this.field_6617 = new FakePlayer(world_1, new BlockPos(blockPos_1.method_10263() + x, blockPos_1.method_10264() + y, blockPos_1.method_10260() + z), 0.0F, new GameProfile(UUID.randomUUID(), "[FeedingTrough]"));
                     dist = this.field_6616.method_5649((double)(blockPos_1.method_10263() + x), (double)(blockPos_1.method_10264() + y), (double)(blockPos_1.method_10260() + z));
                     block = (FeedingTroughBlockEntity)world_1.method_8321(new BlockPos(blockPos_1.method_10263() + x, blockPos_1.method_10264() + y, blockPos_1.method_10260() + z));
                  }
               }
            }
         }

         if (block != null) {
            for(x = 8; x >= 0; --x) {
               if (!block.method_5438(x).method_7960() && this.method_6312(block.method_5438(x))) {
                  ci.setReturnValue(true);
               }
            }
         }
      }

   }
}
