package net.Shad0w.Allure.Blocks.WeatherDetectorBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class WeatherDetectorBlock extends BlockWithEntity {
   public static final IntProperty POWER;
   public static final BooleanProperty INVERTED;
   protected static final VoxelShape SHAPE;
   public static final Identifier ID;

   public WeatherDetectorBlock(Settings settings) {
      super(settings);
      this.method_9590((BlockState)((BlockState)((BlockState)this.field_10647.method_11664()).method_11657(POWER, 0)).method_11657(INVERTED, false));
   }

   public VoxelShape method_9530(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return SHAPE;
   }

   public boolean method_9526(BlockState state) {
      return true;
   }

   public int method_9524(BlockState state, BlockView world, BlockPos pos, Direction direction) {
      return (Integer)state.method_11654(POWER);
   }

   public static void updateState(BlockState state, World world, BlockPos pos) {
      if (world.method_8597().method_12491()) {
         int i = world.method_8419() ? 15 : 0;
         if ((Boolean)state.method_11654(INVERTED)) {
            i = world.method_8419() ? 0 : 15;
         }

         if ((Integer)state.method_11654(POWER) != i) {
            world.method_8652(pos, (BlockState)state.method_11657(POWER, i), 3);
         }
      }

   }

   public ActionResult method_9534(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
      if (player.method_7294()) {
         if (world.field_9236) {
            return ActionResult.field_5812;
         } else {
            BlockState blockState = (BlockState)state.method_28493(INVERTED);
            world.method_8652(pos, blockState, 4);
            updateState(blockState, world, pos);
            return ActionResult.field_21466;
         }
      } else {
         return super.method_9534(state, world, pos, player, hand, hit);
      }
   }

   public BlockRenderType method_9604(BlockState state) {
      return BlockRenderType.field_11458;
   }

   public boolean method_9506(BlockState state) {
      return true;
   }

   protected void method_9515(Builder builder) {
      builder.method_11667(new Property[]{POWER, INVERTED});
   }

   public BlockEntity method_10123(BlockView world) {
      return new WeatherDetectorBlockEntity();
   }

   static {
      POWER = Properties.field_12511;
      INVERTED = Properties.field_12501;
      SHAPE = Block.method_9541(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);
      ID = new Identifier("allure", "weather_detector");
   }
}
