package net.Shad0w.Allure.Blocks.FeedingTrough;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class FeedingTroughBlock extends BlockWithEntity {
   public static final Identifier GUI = new Identifier("allure", "feeding_trough");
   public static final BooleanProperty FULL = BooleanProperty.method_11825("full");
   private static final VoxelShape FULL_SHAPE = Block.method_9541(2.0D, 6.0D, 2.0D, 14.0D, 8.0D, 14.0D);
   private static final VoxelShape EMPTY_SHAPE = Block.method_9541(2.0D, 2.0D, 2.0D, 14.0D, 8.0D, 14.0D);
   private static final VoxelShape OUT_SHAPE = Block.method_9541(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);

   public FeedingTroughBlock(Settings block$Settings_1) {
      super(block$Settings_1);
      this.method_9590((BlockState)((BlockState)this.field_10647.method_11664()).method_11657(FULL, false));
   }

   public BlockEntity method_10123(BlockView world) {
      return new FeedingTroughBlockEntity();
   }

   public BlockRenderType method_9604(BlockState blockState_1) {
      return BlockRenderType.field_11458;
   }

   public boolean method_9526(BlockState blockState_1) {
      return true;
   }

   public VoxelShape method_9530(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, ShapeContext entityContext_1) {
      return VoxelShapes.method_1072(OUT_SHAPE, (Boolean)blockState_1.method_11654(FULL) ? FULL_SHAPE : EMPTY_SHAPE, BooleanBiFunction.field_16886);
   }

   public VoxelShape getRayTraceShape(BlockState blockState_1, BlockView world, BlockPos pos) {
      return (Boolean)blockState_1.method_11654(FULL) ? FULL_SHAPE : EMPTY_SHAPE;
   }

   public BlockSoundGroup method_9573(BlockState state) {
      return (Boolean)state.method_11654(FULL) ? new BlockSoundGroup(1.0F, 1.0F, SoundEvents.field_15215, SoundEvents.field_14573, SoundEvents.field_14718, SoundEvents.field_14808, SoundEvents.field_14607) : super.method_9573(state);
   }

   public void method_9554(World world, BlockPos pos, Entity entity, float distance) {
      if ((Boolean)world.method_8320(pos).method_11654(FULL)) {
         entity.method_5747(distance, 0.2F);
      } else {
         super.method_9554(world, pos, entity, distance);
      }

   }

   public ActionResult method_9534(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
      if (world.field_9236) {
         return ActionResult.field_5812;
      } else {
         BlockEntity blockEntity = world.method_8321(pos);
         if (blockEntity instanceof FeedingTroughBlockEntity) {
            player.method_17355((FeedingTroughBlockEntity)blockEntity);
         }

         return ActionResult.field_21466;
      }
   }

   public void method_9536(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
      if (!state.method_27852(newState.method_26204())) {
         BlockEntity blockEntity = world.method_8321(pos);
         if (blockEntity instanceof FeedingTroughBlockEntity) {
            ItemScatterer.method_5451(world, pos, (FeedingTroughBlockEntity)blockEntity);
            world.method_8455(pos, this);
         }

         super.method_9536(state, world, pos, newState, moved);
      }

   }

   public boolean method_9498(BlockState state) {
      return true;
   }

   public int method_9572(BlockState state, World world, BlockPos pos) {
      return ScreenHandler.method_7608(world.method_8321(pos));
   }

   protected void method_9515(Builder stateFactory$Builder_1) {
      stateFactory$Builder_1.method_11667(new Property[]{FULL});
   }

   public PistonBehavior method_9527(BlockState blockState_1) {
      return PistonBehavior.field_15971;
   }
}
