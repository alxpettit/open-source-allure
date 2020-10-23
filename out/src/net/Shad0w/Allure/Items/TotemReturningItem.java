package net.Shad0w.Allure.Items;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Item.Settings;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TotemReturningItem extends Item {
   public static final Identifier ID = new Identifier("allure", "totem_of_returning");

   public TotemReturningItem(Settings item$Settings_1) {
      super(item$Settings_1);
   }

   private static boolean isBoundToBlock(ItemStack itemStack_1) {
      CompoundTag compoundTag_1 = itemStack_1.method_7969();
      return compoundTag_1 != null && compoundTag_1.method_10577("boundToBlock");
   }

   private static void setBoundToBlock(ItemStack itemStack_1, boolean boolean_1) {
      CompoundTag compoundTag_1 = itemStack_1.method_7948();
      compoundTag_1.method_10556("boundToBlock", boolean_1);
   }

   private static BlockPos getBoundBlock(ItemStack itemStack_1) {
      CompoundTag compoundTag_1 = itemStack_1.method_7969();
      return new BlockPos((double)compoundTag_1.method_10583("boundBlockX"), (double)compoundTag_1.method_10583("boundBlockY"), (double)compoundTag_1.method_10583("boundBlockZ"));
   }

   private static void setBoundBlock(ItemStack itemStack_1, BlockPos blockPos) {
      CompoundTag compoundTag_1 = itemStack_1.method_7948();
      compoundTag_1.method_10548("boundBlockX", (float)blockPos.method_10263());
      compoundTag_1.method_10548("boundBlockY", (float)blockPos.method_10264());
      compoundTag_1.method_10548("boundBlockZ", (float)blockPos.method_10260());
   }

   private static void setDimension(ItemStack itemStack_1, String dimension) {
      CompoundTag compoundTag_1 = itemStack_1.method_7948();
      compoundTag_1.method_10582("dimension", dimension);
   }

   private static String getDimension(ItemStack itemStack_1) {
      CompoundTag compoundTag_1 = itemStack_1.method_7969();
      return compoundTag_1.method_10558("dimension");
   }

   public TypedActionResult method_7836(World world_1, PlayerEntity playerEntity_1, Hand hand_1) {
      ItemStack itemStack_1 = playerEntity_1.method_5998(hand_1);
      if (isBoundToBlock(itemStack_1)) {
         if (!getDimension(itemStack_1).equals(world_1.method_27983().method_29177().toString()) && world_1 instanceof ServerWorld) {
            if (getDimension(itemStack_1).equals(World.field_25180.method_29177().toString())) {
               playerEntity_1.method_5731(world_1.method_8503().method_3847(World.field_25180));
            } else if (getDimension(itemStack_1).equals(World.field_25181.method_29177().toString())) {
               playerEntity_1.method_5731(world_1.method_8503().method_3847(World.field_25181));
            } else {
               if (!getDimension(itemStack_1).equals(World.field_25179.method_29177().toString())) {
                  return new TypedActionResult(ActionResult.field_5814, itemStack_1);
               }

               playerEntity_1.method_5731(world_1.method_8503().method_3847(World.field_25179));
            }
         }

         world_1.method_8421(playerEntity_1, (byte)55);
         playerEntity_1.method_20620((double)((float)getBoundBlock(itemStack_1).method_10263() + 0.5F), (double)((float)getBoundBlock(itemStack_1).method_10264() + 1.0F), (double)((float)getBoundBlock(itemStack_1).method_10260() + 0.5F));
         world_1.method_8396(playerEntity_1, getBoundBlock(itemStack_1), SoundEvents.field_14931, SoundCategory.field_15245, 0.8F, 1.0F);
         setBoundToBlock(itemStack_1, false);
         itemStack_1.method_7934(1);
         return new TypedActionResult(ActionResult.field_5812, itemStack_1);
      } else {
         return new TypedActionResult(ActionResult.field_5811, itemStack_1);
      }
   }

   public ActionResult method_7884(ItemUsageContext itemUsageContext_1) {
      World world_1 = itemUsageContext_1.method_8045();
      BlockPos blockPos_1 = itemUsageContext_1.method_8037();
      BlockState blockState_1 = world_1.method_8320(blockPos_1);
      Block block_1 = blockState_1.method_26204();
      ItemStack itemStack_1 = itemUsageContext_1.method_8041();
      PlayerEntity playerEntity_1 = itemUsageContext_1.method_8036();
      if (block_1 == null || isBoundToBlock(itemStack_1) && !playerEntity_1.method_5715()) {
         return ActionResult.field_5811;
      } else {
         world_1.method_8396(playerEntity_1, blockPos_1, SoundEvents.field_15119, SoundCategory.field_15245, 1.0F, 0.8F);
         playerEntity_1.method_5783(SoundEvents.field_15119, 1.0F, 0.8F);
         setDimension(itemStack_1, world_1.method_27983().method_29177().toString());
         setBoundBlock(itemStack_1, blockPos_1);
         setBoundToBlock(itemUsageContext_1.method_8041(), true);
         return ActionResult.field_5812;
      }
   }

   @Environment(EnvType.CLIENT)
   public void method_7851(ItemStack itemStack_1, World world_1, List list_1, TooltipContext tooltipContext_1) {
      if (isBoundToBlock(itemStack_1)) {
         list_1.add((new TranslatableText("X=" + getBoundBlock(itemStack_1).method_10263() + " Y=" + getBoundBlock(itemStack_1).method_10264() + " Z=" + getBoundBlock(itemStack_1).method_10260() + " " + getDimension(itemStack_1))).method_27692(Formatting.field_1078));
      }

   }
}
