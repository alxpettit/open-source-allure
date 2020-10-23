package net.Shad0w.Allure.Blocks.Chute;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ChuteBlock extends BlockWithEntity {
   public static final Identifier GUI = new Identifier("allure", "chute");
   public static final BooleanProperty ENABLED;
   private static final VoxelShape TOP_SHAPE;
   private static final VoxelShape MIDDLE_SHAPE;
   private static final VoxelShape OUTSIDE_SHAPE;
   private static final VoxelShape DOWN_SHAPE;
   private static final DispenserBehavior BEHAVIOR;

   public ChuteBlock(Settings block$Settings_1) {
      super(block$Settings_1);
      this.method_9590((BlockState)((BlockState)this.field_10647.method_11664()).method_11657(ENABLED, true));
   }

   public VoxelShape method_9530(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return DOWN_SHAPE;
   }

   public BlockEntity method_10123(BlockView blockView) {
      return new ChuteBlockEntity();
   }

   public void method_9567(World world_1, BlockPos blockPos_1, BlockState blockState_1, LivingEntity livingEntity_1, ItemStack itemStack_1) {
      this.updateEnabled(world_1, blockPos_1, blockState_1);
      if (itemStack_1.method_7938()) {
         BlockEntity blockEntity_1 = world_1.method_8321(blockPos_1);
         if (blockEntity_1 instanceof ChuteBlockEntity) {
            ((ChuteBlockEntity)blockEntity_1).method_17488(itemStack_1.method_7964());
         }
      }

   }

   public ActionResult method_9534(BlockState state, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand, BlockHitResult hit) {
      playerEntity_1.method_17355(state.method_26196(world_1, blockPos_1));
      return ActionResult.field_5812;
   }

   public void method_9536(BlockState blockState_1, World world_1, BlockPos blockPos_1, BlockState blockState_2, boolean boolean_1) {
      if (blockState_1.method_26204() != blockState_2.method_26204()) {
         BlockEntity blockEntity_1 = world_1.method_8321(blockPos_1);
         if (blockEntity_1 instanceof ChuteBlockEntity) {
            ItemScatterer.method_5451(world_1, blockPos_1, (ChuteBlockEntity)blockEntity_1);
            world_1.method_8455(blockPos_1, this);
         }

         super.method_9536(blockState_1, world_1, blockPos_1, blockState_2, boolean_1);
      }

   }

   public BlockRenderType method_9604(BlockState blockState_1) {
      return BlockRenderType.field_11458;
   }

   public boolean method_9498(BlockState blockState_1) {
      return true;
   }

   public int method_9572(BlockState blockState_1, World world_1, BlockPos blockPos_1) {
      return ScreenHandler.method_7608(world_1.method_8321(blockPos_1));
   }

   protected void method_9515(Builder stateFactory$Builder_1) {
      stateFactory$Builder_1.method_11667(new Property[]{ENABLED});
   }

   public void method_9612(BlockState blockState_1, World world_1, BlockPos blockPos_1, Block block_1, BlockPos blockPos_2, boolean boolean_1) {
      this.updateEnabled(world_1, blockPos_1, blockState_1);
   }

   private void updateEnabled(World world_1, BlockPos blockPos_1, BlockState blockState_1) {
      boolean boolean_1 = !world_1.method_8479(blockPos_1);
      boolean boolean_2 = world_1.method_8320(blockPos_1.method_10074()).method_26215();
      if (boolean_2) {
         if (boolean_1 != (Boolean)blockState_1.method_11654(ENABLED)) {
            world_1.method_8652(blockPos_1, (BlockState)blockState_1.method_11657(ENABLED, boolean_1), 4);
         }
      } else {
         world_1.method_8652(blockPos_1, (BlockState)blockState_1.method_11657(ENABLED, false), 4);
      }

   }

   static {
      ENABLED = Properties.field_12515;
      TOP_SHAPE = Block.method_9541(0.0D, 10.0D, 0.0D, 16.0D, 16.0D, 16.0D);
      MIDDLE_SHAPE = Block.method_9541(4.0D, 4.0D, 4.0D, 12.0D, 10.0D, 12.0D);
      OUTSIDE_SHAPE = VoxelShapes.method_1084(MIDDLE_SHAPE, TOP_SHAPE);
      DOWN_SHAPE = VoxelShapes.method_1084(OUTSIDE_SHAPE, Block.method_9541(6.0D, 0.0D, 6.0D, 10.0D, 4.0D, 10.0D));
      BEHAVIOR = new ItemDispenserBehavior();
   }
}
