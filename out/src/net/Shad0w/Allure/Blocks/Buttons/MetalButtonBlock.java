package net.Shad0w.Allure.Blocks.Buttons;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.WallMountedBlock;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class MetalButtonBlock extends WallMountedBlock {
   public static final BooleanProperty POWERED;
   protected static final VoxelShape CEILING_X_SHAPE;
   protected static final VoxelShape CEILING_Z_SHAPE;
   protected static final VoxelShape FLOOR_X_SHAPE;
   protected static final VoxelShape FLOOR_Z_SHAPE;
   protected static final VoxelShape NORTH_SHAPE;
   protected static final VoxelShape SOUTH_SHAPE;
   protected static final VoxelShape WEST_SHAPE;
   protected static final VoxelShape EAST_SHAPE;
   protected static final VoxelShape CEILING_X_PRESSED_SHAPE;
   protected static final VoxelShape CEILING_Z_PRESSED_SHAPE;
   protected static final VoxelShape FLOOR_X_PRESSED_SHAPE;
   protected static final VoxelShape FLOOR_Z_PRESSED_SHAPE;
   protected static final VoxelShape NORTH_PRESSED_SHAPE;
   protected static final VoxelShape SOUTH_PRESSED_SHAPE;
   protected static final VoxelShape WEST_PRESSED_SHAPE;
   protected static final VoxelShape EAST_PRESSED_SHAPE;
   private final boolean golden;

   public MetalButtonBlock(boolean golden, Settings settings) {
      super(settings);
      this.method_9590((BlockState)((BlockState)((BlockState)((BlockState)this.field_10647.method_11664()).method_11657(field_11177, Direction.field_11043)).method_11657(POWERED, false)).method_11657(field_11007, WallMountLocation.field_12471));
      this.golden = golden;
   }

   private int getPressTicks() {
      return this.golden ? 2 : 50;
   }

   public VoxelShape method_9530(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      Direction direction = (Direction)state.method_11654(field_11177);
      boolean bl = (Boolean)state.method_11654(POWERED);
      switch((WallMountLocation)state.method_11654(field_11007)) {
      case field_12475:
         if (direction.method_10166() == Axis.field_11048) {
            return bl ? FLOOR_X_PRESSED_SHAPE : FLOOR_X_SHAPE;
         }

         return bl ? FLOOR_Z_PRESSED_SHAPE : FLOOR_Z_SHAPE;
      case field_12471:
         switch(direction) {
         case field_11034:
            return bl ? EAST_PRESSED_SHAPE : EAST_SHAPE;
         case field_11039:
            return bl ? WEST_PRESSED_SHAPE : WEST_SHAPE;
         case field_11035:
            return bl ? SOUTH_PRESSED_SHAPE : SOUTH_SHAPE;
         case field_11043:
         default:
            return bl ? NORTH_PRESSED_SHAPE : NORTH_SHAPE;
         }
      case field_12473:
      default:
         if (direction.method_10166() == Axis.field_11048) {
            return bl ? CEILING_X_PRESSED_SHAPE : CEILING_X_SHAPE;
         } else {
            return bl ? CEILING_Z_PRESSED_SHAPE : CEILING_Z_SHAPE;
         }
      }
   }

   public ActionResult method_9534(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
      if ((Boolean)state.method_11654(POWERED)) {
         return ActionResult.field_21466;
      } else {
         this.powerOn(state, world, pos);
         this.playClickSound(player, world, pos, true);
         return ActionResult.method_29236(world.field_9236);
      }
   }

   public void powerOn(BlockState state, World world, BlockPos pos) {
      world.method_8652(pos, (BlockState)state.method_11657(POWERED, true), 3);
      this.updateNeighbors(state, world, pos);
      world.method_8397().method_8676(pos, this, this.getPressTicks());
   }

   protected void playClickSound(PlayerEntity player, WorldAccess world, BlockPos pos, boolean powered) {
      world.method_8396(powered ? player : null, pos, this.getClickSound(powered), SoundCategory.field_15245, 0.3F, powered ? 0.6F : 0.5F);
   }

   protected SoundEvent getClickSound(boolean powered) {
      return powered ? SoundEvents.field_14791 : SoundEvents.field_14954;
   }

   public void method_9536(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
      if (!moved && !state.method_27852(newState.method_26204())) {
         if ((Boolean)state.method_11654(POWERED)) {
            this.updateNeighbors(state, world, pos);
         }

         super.method_9536(state, world, pos, newState, moved);
      }

   }

   public int method_9524(BlockState state, BlockView world, BlockPos pos, Direction direction) {
      return (Boolean)state.method_11654(POWERED) ? 15 : 0;
   }

   public int method_9603(BlockState state, BlockView world, BlockPos pos, Direction direction) {
      return (Boolean)state.method_11654(POWERED) && method_10119(state) == direction ? 15 : 0;
   }

   public boolean method_9506(BlockState state) {
      return true;
   }

   public void method_9588(BlockState state, ServerWorld world, BlockPos pos, Random random) {
      if ((Boolean)state.method_11654(POWERED)) {
         world.method_8652(pos, (BlockState)state.method_11657(POWERED, false), 3);
         this.updateNeighbors(state, world, pos);
         this.playClickSound((PlayerEntity)null, world, pos, false);
      }

   }

   private void updateNeighbors(BlockState state, World world, BlockPos pos) {
      world.method_8452(pos, this);
      world.method_8452(pos.method_10093(method_10119(state).method_10153()), this);
   }

   protected void method_9515(Builder builder) {
      builder.method_11667(new Property[]{field_11177, POWERED, field_11007});
   }

   static {
      POWERED = Properties.field_12484;
      CEILING_X_SHAPE = Block.method_9541(6.0D, 14.0D, 5.0D, 10.0D, 16.0D, 11.0D);
      CEILING_Z_SHAPE = Block.method_9541(5.0D, 14.0D, 6.0D, 11.0D, 16.0D, 10.0D);
      FLOOR_X_SHAPE = Block.method_9541(6.0D, 0.0D, 5.0D, 10.0D, 2.0D, 11.0D);
      FLOOR_Z_SHAPE = Block.method_9541(5.0D, 0.0D, 6.0D, 11.0D, 2.0D, 10.0D);
      NORTH_SHAPE = Block.method_9541(5.0D, 6.0D, 14.0D, 11.0D, 10.0D, 16.0D);
      SOUTH_SHAPE = Block.method_9541(5.0D, 6.0D, 0.0D, 11.0D, 10.0D, 2.0D);
      WEST_SHAPE = Block.method_9541(14.0D, 6.0D, 5.0D, 16.0D, 10.0D, 11.0D);
      EAST_SHAPE = Block.method_9541(0.0D, 6.0D, 5.0D, 2.0D, 10.0D, 11.0D);
      CEILING_X_PRESSED_SHAPE = Block.method_9541(6.0D, 15.0D, 5.0D, 10.0D, 16.0D, 11.0D);
      CEILING_Z_PRESSED_SHAPE = Block.method_9541(5.0D, 15.0D, 6.0D, 11.0D, 16.0D, 10.0D);
      FLOOR_X_PRESSED_SHAPE = Block.method_9541(6.0D, 0.0D, 5.0D, 10.0D, 1.0D, 11.0D);
      FLOOR_Z_PRESSED_SHAPE = Block.method_9541(5.0D, 0.0D, 6.0D, 11.0D, 1.0D, 10.0D);
      NORTH_PRESSED_SHAPE = Block.method_9541(5.0D, 6.0D, 15.0D, 11.0D, 10.0D, 16.0D);
      SOUTH_PRESSED_SHAPE = Block.method_9541(5.0D, 6.0D, 0.0D, 11.0D, 10.0D, 1.0D);
      WEST_PRESSED_SHAPE = Block.method_9541(15.0D, 6.0D, 5.0D, 16.0D, 10.0D, 11.0D);
      EAST_PRESSED_SHAPE = Block.method_9541(0.0D, 6.0D, 5.0D, 1.0D, 10.0D, 11.0D);
   }
}
