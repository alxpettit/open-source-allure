package net.Shad0w.Allure.Blocks.Root;

import com.google.common.collect.ImmutableMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.Shad0w.Allure.Init.AllureBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ConnectingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.util.math.Direction.Type;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class RootBlock extends Block {
   public static final BooleanProperty UP;
   public static final BooleanProperty NORTH;
   public static final BooleanProperty EAST;
   public static final BooleanProperty SOUTH;
   public static final BooleanProperty WEST;
   public static final Map FACING_PROPERTIES;
   private static final VoxelShape UP_SHAPE;
   private static final VoxelShape EAST_SHAPE;
   private static final VoxelShape WEST_SHAPE;
   private static final VoxelShape SOUTH_SHAPE;
   private static final VoxelShape NORTH_SHAPE;
   private final Map field_26659;

   public RootBlock(Settings settings) {
      super(settings);
      this.method_9590((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.field_10647.method_11664()).method_11657(UP, false)).method_11657(NORTH, false)).method_11657(EAST, false)).method_11657(SOUTH, false)).method_11657(WEST, false));
      this.field_26659 = ImmutableMap.copyOf((Map)this.field_10647.method_11662().stream().collect(Collectors.toMap(Function.identity(), RootBlock::method_31018)));
   }

   private static VoxelShape method_31018(BlockState blockState) {
      VoxelShape voxelShape = VoxelShapes.method_1073();
      if ((Boolean)blockState.method_11654(UP)) {
         voxelShape = UP_SHAPE;
      }

      if ((Boolean)blockState.method_11654(NORTH)) {
         voxelShape = VoxelShapes.method_1084(voxelShape, SOUTH_SHAPE);
      }

      if ((Boolean)blockState.method_11654(SOUTH)) {
         voxelShape = VoxelShapes.method_1084(voxelShape, NORTH_SHAPE);
      }

      if ((Boolean)blockState.method_11654(EAST)) {
         voxelShape = VoxelShapes.method_1084(voxelShape, WEST_SHAPE);
      }

      if ((Boolean)blockState.method_11654(WEST)) {
         voxelShape = VoxelShapes.method_1084(voxelShape, EAST_SHAPE);
      }

      return voxelShape;
   }

   public VoxelShape method_9530(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
      return (VoxelShape)this.field_26659.get(state);
   }

   public boolean method_9558(BlockState state, WorldView world, BlockPos pos) {
      return this.hasAdjacentBlocks(this.getPlacementShape(state, world, pos));
   }

   private boolean hasAdjacentBlocks(BlockState state) {
      return this.getAdjacentBlockCount(state) > 0;
   }

   private int getAdjacentBlockCount(BlockState state) {
      int i = 0;
      Iterator var3 = FACING_PROPERTIES.values().iterator();

      while(var3.hasNext()) {
         BooleanProperty booleanProperty = (BooleanProperty)var3.next();
         if ((Boolean)state.method_11654(booleanProperty)) {
            ++i;
         }
      }

      return i;
   }

   private static boolean shouldHaveSide(BlockView world, BlockPos pos, Direction side) {
      if (side == Direction.field_11033) {
         return false;
      } else {
         BlockPos blockPos = pos.method_10093(side);
         if (shouldConnectTo(world, blockPos, side)) {
            return true;
         } else if (side.method_10166() == Axis.field_11052) {
            return false;
         } else {
            BooleanProperty booleanProperty = (BooleanProperty)FACING_PROPERTIES.get(side);
            BlockState blockState = world.method_8320(pos.method_10084());
            return blockState.method_27852(AllureBlocks.ROOT_BLOCK) && (Boolean)blockState.method_11654(booleanProperty);
         }
      }
   }

   public static boolean shouldConnectTo(BlockView world, BlockPos pos, Direction direction) {
      BlockState blockState = world.method_8320(pos);
      return Block.method_9501(blockState.method_26220(world, pos), direction.method_10153());
   }

   private BlockState getPlacementShape(BlockState state, BlockView world, BlockPos pos) {
      BlockPos blockPos = pos.method_10084();
      if ((Boolean)state.method_11654(UP)) {
         state = (BlockState)state.method_11657(UP, shouldConnectTo(world, blockPos, Direction.field_11033));
      }

      BlockState blockState = null;
      Iterator var6 = Type.field_11062.iterator();

      while(true) {
         Direction direction;
         BooleanProperty booleanProperty;
         do {
            if (!var6.hasNext()) {
               return state;
            }

            direction = (Direction)var6.next();
            booleanProperty = getFacingProperty(direction);
         } while(!(Boolean)state.method_11654(booleanProperty));

         boolean bl = shouldHaveSide(world, pos, direction);
         if (!bl) {
            if (blockState == null) {
               blockState = world.method_8320(blockPos);
            }

            bl = blockState.method_27852(this) && (Boolean)blockState.method_11654(booleanProperty);
         }

         state = (BlockState)state.method_11657(booleanProperty, bl);
      }
   }

   public BlockState method_9559(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
      if (direction == Direction.field_11033) {
         return super.method_9559(state, direction, newState, world, pos, posFrom);
      } else {
         BlockState blockState = this.getPlacementShape(state, world, pos);
         return !this.hasAdjacentBlocks(blockState) ? Blocks.field_10124.method_9564() : blockState;
      }
   }

   public static void growMany(BlockState state, StructureWorldAccess world, BlockPos pos, Random random, float chance) {
      while(random.nextFloat() < chance) {
         Direction direction = Direction.method_10162(random);
         BlockPos blockPos = pos.method_10084();
         BlockPos blockPos2;
         BlockState blockState3;
         Direction direction5;
         if (direction.method_10166().method_10179() && !(Boolean)state.method_11654(getFacingProperty(direction))) {
            if (canGrowAt(world, pos)) {
               blockPos2 = pos.method_10093(direction);
               blockState3 = world.method_8320(blockPos2);
               if (blockState3.method_26215()) {
                  direction5 = direction.method_10170();
                  Direction direction3 = direction.method_10160();
                  boolean bl = (Boolean)state.method_11654(getFacingProperty(direction5));
                  boolean bl2 = (Boolean)state.method_11654(getFacingProperty(direction3));
                  BlockPos blockPos3 = blockPos2.method_10093(direction5);
                  BlockPos blockPos4 = blockPos2.method_10093(direction3);
                  if (bl && shouldConnectTo(world, blockPos3, direction5)) {
                     world.method_8652(blockPos2, (BlockState)AllureBlocks.ROOT_BLOCK.method_9564().method_11657(getFacingProperty(direction5), true), 2);
                  } else if (bl2 && shouldConnectTo(world, blockPos4, direction3)) {
                     world.method_8652(blockPos2, (BlockState)AllureBlocks.ROOT_BLOCK.method_9564().method_11657(getFacingProperty(direction3), true), 2);
                  } else {
                     Direction direction4 = direction.method_10153();
                     if (bl && world.method_22347(blockPos3) && shouldConnectTo(world, pos.method_10093(direction5), direction4)) {
                        world.method_8652(blockPos3, (BlockState)AllureBlocks.ROOT_BLOCK.method_9564().method_11657(getFacingProperty(direction4), true), 2);
                     } else if (bl2 && world.method_22347(blockPos4) && shouldConnectTo(world, pos.method_10093(direction3), direction4)) {
                        world.method_8652(blockPos4, (BlockState)AllureBlocks.ROOT_BLOCK.method_9564().method_11657(getFacingProperty(direction4), true), 2);
                     } else if ((double)random.nextFloat() < 0.05D && shouldConnectTo(world, blockPos2.method_10084(), Direction.field_11036)) {
                        world.method_8652(blockPos2, (BlockState)AllureBlocks.ROOT_BLOCK.method_9564().method_11657(UP, true), 2);
                     }
                  }
               } else if (shouldConnectTo(world, blockPos2, direction)) {
                  world.method_8652(pos, (BlockState)state.method_11657(getFacingProperty(direction), true), 2);
               }
            }
         } else {
            BlockState blockState2;
            if (direction == Direction.field_11036 && pos.method_10264() < 255) {
               if (shouldHaveSide(world, pos, direction)) {
                  world.method_8652(pos, (BlockState)state.method_11657(UP, true), 2);
                  return;
               }

               if (world.method_22347(blockPos)) {
                  if (!canGrowAt(world, pos)) {
                     return;
                  }

                  blockState2 = state;
                  Iterator var17 = Type.field_11062.iterator();

                  while(true) {
                     do {
                        if (!var17.hasNext()) {
                           if (hasHorizontalSide(blockState2)) {
                              world.method_8652(blockPos, blockState2, 2);
                           }

                           return;
                        }

                        direction5 = (Direction)var17.next();
                     } while(!random.nextBoolean() && shouldConnectTo(world, blockPos.method_10093(direction5), Direction.field_11036));

                     blockState2 = (BlockState)blockState2.method_11657(getFacingProperty(direction5), false);
                  }
               }
            }

            if (pos.method_10264() > 0) {
               blockPos2 = pos.method_10074();
               blockState3 = world.method_8320(blockPos2);
               if (blockState3.method_26215() || blockState3.method_27852(AllureBlocks.ROOT_BLOCK)) {
                  blockState2 = blockState3.method_26215() ? AllureBlocks.ROOT_BLOCK.method_9564() : blockState3;
                  BlockState blockState5 = getGrownState(state, blockState2, random);
                  if (blockState2 != blockState5 && hasHorizontalSide(blockState5)) {
                     world.method_8652(blockPos2, blockState5, 2);
                  }
               }
            }
         }
      }

   }

   public void method_9514(BlockState state, ServerWorld world, BlockPos pos, Random random) {
      if (world.field_9229.nextInt(4) == 0) {
         Direction direction = Direction.method_10162(random);
         BlockPos blockPos = pos.method_10084();
         BlockPos blockPos2;
         BlockState blockState3;
         Direction direction5;
         if (direction.method_10166().method_10179() && !(Boolean)state.method_11654(getFacingProperty(direction))) {
            if (canGrowAt(world, pos)) {
               blockPos2 = pos.method_10093(direction);
               blockState3 = world.method_8320(blockPos2);
               if (blockState3.method_26215()) {
                  direction5 = direction.method_10170();
                  Direction direction3 = direction.method_10160();
                  boolean bl = (Boolean)state.method_11654(getFacingProperty(direction5));
                  boolean bl2 = (Boolean)state.method_11654(getFacingProperty(direction3));
                  BlockPos blockPos3 = blockPos2.method_10093(direction5);
                  BlockPos blockPos4 = blockPos2.method_10093(direction3);
                  if (bl && shouldConnectTo(world, blockPos3, direction5)) {
                     world.method_8652(blockPos2, (BlockState)this.method_9564().method_11657(getFacingProperty(direction5), true), 2);
                  } else if (bl2 && shouldConnectTo(world, blockPos4, direction3)) {
                     world.method_8652(blockPos2, (BlockState)this.method_9564().method_11657(getFacingProperty(direction3), true), 2);
                  } else {
                     Direction direction4 = direction.method_10153();
                     if (bl && world.method_22347(blockPos3) && shouldConnectTo(world, pos.method_10093(direction5), direction4)) {
                        world.method_8652(blockPos3, (BlockState)this.method_9564().method_11657(getFacingProperty(direction4), true), 2);
                     } else if (bl2 && world.method_22347(blockPos4) && shouldConnectTo(world, pos.method_10093(direction3), direction4)) {
                        world.method_8652(blockPos4, (BlockState)this.method_9564().method_11657(getFacingProperty(direction4), true), 2);
                     } else if ((double)world.field_9229.nextFloat() < 0.05D && shouldConnectTo(world, blockPos2.method_10084(), Direction.field_11036)) {
                        world.method_8652(blockPos2, (BlockState)this.method_9564().method_11657(UP, true), 2);
                     }
                  }
               } else if (shouldConnectTo(world, blockPos2, direction)) {
                  world.method_8652(pos, (BlockState)state.method_11657(getFacingProperty(direction), true), 2);
               }
            }
         } else {
            BlockState blockState2;
            if (direction == Direction.field_11036 && pos.method_10264() < 255) {
               if (shouldHaveSide(world, pos, direction)) {
                  world.method_8652(pos, (BlockState)state.method_11657(UP, true), 2);
                  return;
               }

               if (world.method_22347(blockPos)) {
                  if (!canGrowAt(world, pos)) {
                     return;
                  }

                  blockState2 = state;
                  Iterator var17 = Type.field_11062.iterator();

                  while(true) {
                     do {
                        if (!var17.hasNext()) {
                           if (hasHorizontalSide(blockState2)) {
                              world.method_8652(blockPos, blockState2, 2);
                           }

                           return;
                        }

                        direction5 = (Direction)var17.next();
                     } while(!random.nextBoolean() && shouldConnectTo(world, blockPos.method_10093(direction5), Direction.field_11036));

                     blockState2 = (BlockState)blockState2.method_11657(getFacingProperty(direction5), false);
                  }
               }
            }

            if (pos.method_10264() > 0) {
               blockPos2 = pos.method_10074();
               blockState3 = world.method_8320(blockPos2);
               if (blockState3.method_26215() || blockState3.method_27852(this)) {
                  blockState2 = blockState3.method_26215() ? this.method_9564() : blockState3;
                  BlockState blockState5 = getGrownState(state, blockState2, random);
                  if (blockState2 != blockState5 && hasHorizontalSide(blockState5)) {
                     world.method_8652(blockPos2, blockState5, 2);
                  }
               }
            }
         }
      }

   }

   private static BlockState getGrownState(BlockState above, BlockState state, Random random) {
      Iterator var4 = Type.field_11062.iterator();

      while(var4.hasNext()) {
         Direction direction = (Direction)var4.next();
         if (random.nextBoolean()) {
            BooleanProperty booleanProperty = getFacingProperty(direction);
            if ((Boolean)above.method_11654(booleanProperty)) {
               state = (BlockState)state.method_11657(booleanProperty, true);
            }
         }
      }

      return state;
   }

   private static boolean hasHorizontalSide(BlockState state) {
      return (Boolean)state.method_11654(NORTH) || (Boolean)state.method_11654(EAST) || (Boolean)state.method_11654(SOUTH) || (Boolean)state.method_11654(WEST);
   }

   private static boolean canGrowAt(BlockView world, BlockPos pos) {
      Iterable iterable = BlockPos.method_10094(pos.method_10263() - 4, pos.method_10264() - 1, pos.method_10260() - 4, pos.method_10263() + 4, pos.method_10264() + 1, pos.method_10260() + 4);
      int j = 5;
      Iterator var6 = iterable.iterator();

      while(var6.hasNext()) {
         BlockPos blockPos = (BlockPos)var6.next();
         if (world.method_8320(blockPos).method_27852(AllureBlocks.ROOT_BLOCK)) {
            --j;
            if (j <= 0) {
               return false;
            }
         }
      }

      return true;
   }

   public boolean method_9616(BlockState state, ItemPlacementContext context) {
      BlockState blockState = context.method_8045().method_8320(context.method_8037());
      if (blockState.method_27852(this)) {
         return this.getAdjacentBlockCount(blockState) < FACING_PROPERTIES.size();
      } else {
         return super.method_9616(state, context);
      }
   }

   public BlockState method_9605(ItemPlacementContext ctx) {
      BlockState blockState = ctx.method_8045().method_8320(ctx.method_8037());
      boolean bl = blockState.method_27852(this);
      BlockState blockState2 = bl ? blockState : this.method_9564();
      Direction[] var5 = ctx.method_7718();
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         Direction direction = var5[var7];
         if (direction != Direction.field_11033) {
            BooleanProperty booleanProperty = getFacingProperty(direction);
            boolean bl2 = bl && (Boolean)blockState.method_11654(booleanProperty);
            if (!bl2 && shouldHaveSide(ctx.method_8045(), ctx.method_8037(), direction)) {
               return (BlockState)blockState2.method_11657(booleanProperty, true);
            }
         }
      }

      return bl ? blockState2 : null;
   }

   protected void method_9515(Builder builder) {
      builder.method_11667(new Property[]{UP, NORTH, EAST, SOUTH, WEST});
   }

   public BlockState method_9598(BlockState state, BlockRotation rotation) {
      switch(rotation) {
      case field_11464:
         return (BlockState)((BlockState)((BlockState)((BlockState)state.method_11657(NORTH, state.method_11654(SOUTH))).method_11657(EAST, state.method_11654(WEST))).method_11657(SOUTH, state.method_11654(NORTH))).method_11657(WEST, state.method_11654(EAST));
      case field_11465:
         return (BlockState)((BlockState)((BlockState)((BlockState)state.method_11657(NORTH, state.method_11654(EAST))).method_11657(EAST, state.method_11654(SOUTH))).method_11657(SOUTH, state.method_11654(WEST))).method_11657(WEST, state.method_11654(NORTH));
      case field_11463:
         return (BlockState)((BlockState)((BlockState)((BlockState)state.method_11657(NORTH, state.method_11654(WEST))).method_11657(EAST, state.method_11654(NORTH))).method_11657(SOUTH, state.method_11654(EAST))).method_11657(WEST, state.method_11654(SOUTH));
      default:
         return state;
      }
   }

   public BlockState method_9569(BlockState state, BlockMirror mirror) {
      switch(mirror) {
      case field_11300:
         return (BlockState)((BlockState)state.method_11657(NORTH, state.method_11654(SOUTH))).method_11657(SOUTH, state.method_11654(NORTH));
      case field_11301:
         return (BlockState)((BlockState)state.method_11657(EAST, state.method_11654(WEST))).method_11657(WEST, state.method_11654(EAST));
      default:
         return super.method_9569(state, mirror);
      }
   }

   public static BooleanProperty getFacingProperty(Direction direction) {
      return (BooleanProperty)FACING_PROPERTIES.get(direction);
   }

   static {
      UP = ConnectingBlock.field_11327;
      NORTH = ConnectingBlock.field_11332;
      EAST = ConnectingBlock.field_11335;
      SOUTH = ConnectingBlock.field_11331;
      WEST = ConnectingBlock.field_11328;
      FACING_PROPERTIES = (Map)ConnectingBlock.field_11329.entrySet().stream().filter((entry) -> {
         return entry.getKey() != Direction.field_11033;
      }).collect(Util.method_664());
      UP_SHAPE = Block.method_9541(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
      EAST_SHAPE = Block.method_9541(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
      WEST_SHAPE = Block.method_9541(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
      SOUTH_SHAPE = Block.method_9541(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
      NORTH_SHAPE = Block.method_9541(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
   }
}
