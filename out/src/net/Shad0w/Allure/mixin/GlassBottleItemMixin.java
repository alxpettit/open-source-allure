package net.Shad0w.Allure.mixin;

import net.Shad0w.Allure.Allure;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.item.Item.Settings;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({GlassBottleItem.class})
public abstract class GlassBottleItemMixin extends Item {
   public GlassBottleItemMixin(Settings item$Settings_1) {
      super(item$Settings_1);
   }

   @Shadow
   protected abstract ItemStack method_7725(ItemStack var1, PlayerEntity var2, ItemStack var3);

   public ActionResult method_7884(ItemUsageContext itemUsageContext_1) {
      World world_1 = itemUsageContext_1.method_8045();
      BlockPos blockPos_1 = itemUsageContext_1.method_8037();
      BlockState blockState_1 = world_1.method_8320(blockPos_1);
      Block block_1 = blockState_1.method_26204();
      ItemStack itemStack_1 = itemUsageContext_1.method_8041();
      PlayerEntity playerEntity_1 = itemUsageContext_1.method_8036();
      if (block_1 == Blocks.field_10029 && Allure.CONFIG.cactusGiveWater) {
         world_1.method_8465(playerEntity_1, playerEntity_1.method_23317(), playerEntity_1.method_23318(), playerEntity_1.method_23321(), SoundEvents.field_14779, SoundCategory.field_15254, 1.0F, 1.0F);
         itemStack_1.method_7934(1);
         playerEntity_1.method_7259(Stats.field_15372.method_14956(this));
         if (!playerEntity_1.field_7514.method_7394(PotionUtil.method_8061(new ItemStack(Items.field_8574), Potions.field_8991))) {
            playerEntity_1.method_7328(PotionUtil.method_8061(new ItemStack(Items.field_8574), Potions.field_8991), false);
         }

         if (world_1.field_9229.nextFloat() < 0.2F && !world_1.method_8608()) {
            world_1.method_22352(blockPos_1, false);
         }

         return ActionResult.field_5812;
      } else {
         return ActionResult.field_5811;
      }
   }
}
