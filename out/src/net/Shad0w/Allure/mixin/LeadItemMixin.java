package net.Shad0w.Allure.mixin;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.decoration.LeashKnotEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.LeadItem;
import net.minecraft.item.Item.Settings;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({LeadItem.class})
public abstract class LeadItemMixin extends Item {
   public LeadItemMixin(Settings settings) {
      super(settings);
   }

   @Overwrite
   public ActionResult method_7884(ItemUsageContext context) {
      World world = context.method_8045();
      BlockPos blockPos = context.method_8037();
      Block block = world.method_8320(blockPos).method_26204();
      if (block.method_9525(BlockTags.field_16584)) {
         PlayerEntity playerEntity = context.method_8036();
         if (!world.field_9236 && playerEntity != null) {
            method_7994(playerEntity, world, blockPos);
         }

         return ActionResult.method_29236(world.field_9236);
      } else {
         return ActionResult.field_5811;
      }
   }

   @Overwrite
   public static ActionResult method_7994(PlayerEntity playerEntity, World world, BlockPos blockPos) {
      LeashKnotEntity leashKnotEntity = null;
      boolean bl = false;
      double d = 7.0D;
      int i = blockPos.method_10263();
      int j = blockPos.method_10264();
      int k = blockPos.method_10260();
      List list = world.method_18467(MobEntity.class, new Box((double)i - 7.0D, (double)j - 7.0D, (double)k - 7.0D, (double)i + 7.0D, (double)j + 7.0D, (double)k + 7.0D));
      Iterator var11 = list.iterator();
      if (leashKnotEntity == null) {
         leashKnotEntity = LeashKnotEntity.method_6932(world, blockPos);
      }

      bl = true;

      while(var11.hasNext()) {
         MobEntity mobEntity = (MobEntity)var11.next();
         if (mobEntity.method_5933() == playerEntity) {
            mobEntity.method_5954(leashKnotEntity, true);
         }
      }

      return bl ? ActionResult.field_5812 : ActionResult.field_5811;
   }
}
