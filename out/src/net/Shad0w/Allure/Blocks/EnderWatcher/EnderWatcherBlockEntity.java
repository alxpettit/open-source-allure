package net.Shad0w.Allure.Blocks.EnderWatcher;

import java.util.Iterator;
import java.util.List;
import net.Shad0w.Allure.Init.AllureBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.Tickable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;

public class EnderWatcherBlockEntity extends BlockEntity implements Tickable {
   public EnderWatcherBlockEntity() {
      super(AllureBlocks.ENDER_WATCHER_BLOCK_ENTITY);
   }

   public void method_16896() {
      BlockState state = this.method_11010();
      boolean wasLooking = (Boolean)state.method_11654(EnderWatcherBlock.WATCHED);
      int currWatch = (Integer)state.method_11654(EnderWatcherBlock.POWER);
      int range = 80;
      int newWatch = 0;
      List players = this.field_11863.method_18467(PlayerEntity.class, new Box(this.field_11867.method_10069(-range, -range, -range), this.field_11867.method_10069(range, range, range)));
      boolean looking = false;
      Iterator var8 = players.iterator();

      while(true) {
         PlayerEntity player;
         ItemStack helm;
         do {
            if (!var8.hasNext()) {
               if (!this.field_11863.method_8608() && (looking != wasLooking || currWatch != newWatch)) {
                  this.field_11863.method_8652(this.field_11867, (BlockState)((BlockState)this.field_11863.method_8320(this.field_11867).method_11657(EnderWatcherBlock.WATCHED, looking)).method_11657(EnderWatcherBlock.POWER, newWatch), 3);
               }

               if (looking) {
                  double x = (double)this.field_11867.method_10263() - 0.1D + Math.random() * 1.2D;
                  double y = (double)this.field_11867.method_10264() - 0.1D + Math.random() * 1.2D;
                  double z = (double)this.field_11867.method_10260() - 0.1D + Math.random() * 1.2D;
                  this.field_11863.method_8406(new DustParticleEffect(1.0F, 0.0F, 0.0F, 1.0F), x, y, z, 0.0D, 0.0D, 0.0D);
               }

               return;
            }

            player = (PlayerEntity)var8.next();
            helm = player.method_6118(EquipmentSlot.field_6169);
         } while(!helm.method_7960() && helm.method_7909() == Items.field_17518);

         HitResult result = player.method_5745(64.0D, 1.0F, false);
         if (result != null && result instanceof BlockHitResult && ((BlockHitResult)result).method_17777().equals(this.field_11867)) {
            looking = true;
            Direction dir = ((BlockHitResult)result).method_17780();
            double x = Math.abs(player.method_19538().field_1352 - (double)this.field_11867.method_10263() - 0.5D);
            double y = Math.abs(player.method_19538().field_1351 - (double)this.field_11867.method_10264() - 0.5D);
            double z = Math.abs(player.method_19538().field_1350 - (double)this.field_11867.method_10260() - 0.5D);
            float fract = (float)(65L - Math.round(Math.sqrt(x * x + y * y + z * z)));
            newWatch = (int)Math.ceil((double)(fract / 64.0F * 15.0F));
         }
      }
   }
}
