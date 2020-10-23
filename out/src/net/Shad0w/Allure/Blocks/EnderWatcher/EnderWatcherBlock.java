package net.Shad0w.Allure.Blocks.EnderWatcher;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class EnderWatcherBlock extends BlockWithEntity {
   public static final BooleanProperty WATCHED = BooleanProperty.method_11825("watched");
   public static final IntProperty POWER;
   public static final Identifier ID;

   public EnderWatcherBlock(Settings settings) {
      super(settings);
      this.method_9590((BlockState)((BlockState)((BlockState)this.field_10647.method_11664()).method_11657(WATCHED, false)).method_11657(POWER, 1));
   }

   public boolean method_9506(BlockState state) {
      return true;
   }

   public int method_9603(BlockState state, BlockView world, BlockPos pos, Direction direction) {
      return state.method_26195(world, pos, direction);
   }

   public int method_9524(BlockState state, BlockView world, BlockPos pos, Direction direction) {
      return (Integer)state.method_11654(POWER);
   }

   public int method_9572(BlockState state, World world, BlockPos pos) {
      return (Integer)state.method_11654(POWER);
   }

   protected void method_9515(Builder builder) {
      builder.method_11667(new Property[]{POWER, WATCHED});
   }

   public BlockRenderType method_9604(BlockState state) {
      return BlockRenderType.field_11458;
   }

   public BlockEntity method_10123(BlockView world) {
      return new EnderWatcherBlockEntity();
   }

   static {
      POWER = Properties.field_12538;
      ID = new Identifier("allure", "ender_watcher");
   }
}
