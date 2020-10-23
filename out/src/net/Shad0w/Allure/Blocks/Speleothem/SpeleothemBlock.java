package net.Shad0w.Allure.Blocks.Speleothem;

import java.util.Locale;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class SpeleothemBlock extends Block implements Waterloggable {
   public static final EnumProperty SIZE = EnumProperty.method_11850("size", SpeleothemBlock.SpeleothemSize.class);
   public static final BooleanProperty WATERLOGGED;

   public SpeleothemBlock(Settings settings) {
      super(settings);
      this.method_9590((BlockState)((BlockState)((BlockState)this.field_10647.method_11664()).method_11657(SIZE, SpeleothemBlock.SpeleothemSize.BIG)).method_11657(WATERLOGGED, false));
   }

   public boolean method_9558(BlockState state, WorldView world, BlockPos pos) {
      return this.getBearing((World)world, pos) > 0;
   }

   public void method_9612(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
      int size = ((SpeleothemBlock.SpeleothemSize)state.method_11654(SIZE)).strength;
      if (this.getBearing(world, pos) < size + 1) {
         world.method_22352(pos, false);
      }

   }

   public BlockState method_9605(ItemPlacementContext ctx) {
      SpeleothemBlock.SpeleothemSize size = SpeleothemBlock.SpeleothemSize.values()[Math.max(0, this.getBearing(ctx.method_8045(), ctx.method_8037()) - 1)];
      return (BlockState)((BlockState)this.method_9564().method_11657(SIZE, size)).method_11657(WATERLOGGED, ctx.method_8045().method_8316(ctx.method_8037()).method_15772() == Fluids.field_15910);
   }

   private int getBearing(World world, BlockPos pos) {
      return Math.max(this.getStrength(world, pos.method_10074()), this.getStrength(world, pos.method_10084()));
   }

   private int getStrength(World world, BlockPos pos) {
      BlockState state = world.method_8320(pos);
      if (state.method_26212(world, pos)) {
         return 3;
      } else {
         return state.method_28498(SIZE) ? ((SpeleothemBlock.SpeleothemSize)state.method_11654(SIZE)).strength : 0;
      }
   }

   public VoxelShape method_9530(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return ((SpeleothemBlock.SpeleothemSize)state.method_11654(SIZE)).shape;
   }

   protected void method_9515(Builder builder) {
      builder.method_11667(new Property[]{SIZE, WATERLOGGED});
   }

   public FluidState method_9545(BlockState state) {
      return (Boolean)state.method_11654(WATERLOGGED) ? Fluids.field_15910.method_15729(false) : super.method_9545(state);
   }

   public boolean method_9516(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
      return type == NavigationType.field_48 && world.method_8316(pos).method_15767(FluidTags.field_15517);
   }

   static {
      WATERLOGGED = Properties.field_12508;
   }

   public static enum SpeleothemSize implements StringIdentifiable {
      SMALL(0, 2),
      MEDIUM(1, 4),
      BIG(2, 8);

      public final int strength;
      public final VoxelShape shape;

      private SpeleothemSize(int strength, int width) {
         this.strength = strength;
         int pad = (16 - width) / 2;
         this.shape = Block.method_9541((double)pad, 0.0D, (double)pad, (double)(16 - pad), 16.0D, (double)(16 - pad));
      }

      public String method_15434() {
         return this.name().toLowerCase(Locale.ROOT);
      }
   }
}
