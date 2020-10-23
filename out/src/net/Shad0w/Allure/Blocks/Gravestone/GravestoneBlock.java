package net.Shad0w.Allure.Blocks.Gravestone;

import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.Shad0w.Allure.Allure;
import net.fabricmc.loader.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class GravestoneBlock extends BlockWithEntity {
   public static final Identifier ID = new Identifier("allure", "gravestone");
   public static final DirectionProperty FACING;
   private static final VoxelShape BASE_SHAPE;
   private static final VoxelShape NORTH_SHAPE;
   private static final VoxelShape SOUTH_SHAPE;
   private static final VoxelShape WEST_SHAPE;
   private static final VoxelShape EAST_SHAPE;

   public GravestoneBlock(Settings block$Settings_1) {
      super(block$Settings_1);
      this.method_9590((BlockState)((BlockState)this.field_10647.method_11664()).method_11657(FACING, Direction.field_11043));
   }

   public BlockEntity method_10123(BlockView blockView) {
      return new GravestoneBlockEntity();
   }

   public float method_9594(BlockState blockState_1, PlayerEntity playerEntity_1, BlockView blockView_1, BlockPos blockPos_1) {
      GravestoneBlockEntity gravestoneBlockEntity = (GravestoneBlockEntity)playerEntity_1.field_6002.method_8321(blockPos_1);
      if (playerEntity_1.method_5820().equals(gravestoneBlockEntity.playername) && Allure.CONFIG.lockGravestones) {
         return super.method_9594(blockState_1, playerEntity_1, blockView_1, blockPos_1);
      } else {
         return !Allure.CONFIG.lockGravestones ? super.method_9594(blockState_1, playerEntity_1, blockView_1, blockPos_1) : 0.0F;
      }
   }

   public void method_9576(World world_1, BlockPos blockPos_1, BlockState blockState_1, PlayerEntity playerEntity_1) {
      if (!world_1.field_9236) {
         GravestoneBlockEntity gravestoneBlockEntity = (GravestoneBlockEntity)world_1.method_8321(blockPos_1);
         if (playerEntity_1.field_7514.method_5442()) {
            for(int i = 0; i < gravestoneBlockEntity.playerInv.size(); ++i) {
               if (i < playerEntity_1.field_7514.method_5439()) {
                  if (!gravestoneBlockEntity.method_5438(i).method_7960()) {
                     playerEntity_1.field_7514.method_5447(i, gravestoneBlockEntity.method_5438(i));
                  }
               } else if (FabricLoader.INSTANCE.isModLoaded("trinkets")) {
                  TrinketComponent comp = TrinketsApi.getTrinketComponent(playerEntity_1);
                  if (comp.equip(gravestoneBlockEntity.method_5438(i))) {
                     System.out.println("YEP");
                  }
               }
            }
         } else {
            ItemScatterer.method_5451(world_1, blockPos_1, gravestoneBlockEntity);
            world_1.method_8455(blockPos_1, this);
         }
      }

      super.method_9576(world_1, blockPos_1, blockState_1, playerEntity_1);
   }

   public BlockRenderType method_9604(BlockState blockState_1) {
      return BlockRenderType.field_11458;
   }

   public boolean method_9526(BlockState blockState_1) {
      return true;
   }

   public VoxelShape method_9530(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, ShapeContext entityContext_1) {
      Direction direction_1 = (Direction)blockState_1.method_11654(FACING);
      switch(direction_1) {
      case field_11043:
         return NORTH_SHAPE;
      case field_11035:
         return SOUTH_SHAPE;
      case field_11039:
         return WEST_SHAPE;
      default:
         return EAST_SHAPE;
      }
   }

   public BlockState method_9598(BlockState blockState_1, BlockRotation blockRotation_1) {
      return (BlockState)blockState_1.method_11657(FACING, blockRotation_1.method_10503((Direction)blockState_1.method_11654(FACING)));
   }

   public BlockState method_9569(BlockState blockState_1, BlockMirror blockMirror_1) {
      return blockState_1.method_26186(blockMirror_1.method_10345((Direction)blockState_1.method_11654(FACING)));
   }

   protected void method_9515(Builder stateFactory$Builder_1) {
      stateFactory$Builder_1.method_11667(new Property[]{FACING});
   }

   static {
      FACING = HorizontalFacingBlock.field_11177;
      BASE_SHAPE = Block.method_9541(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
      NORTH_SHAPE = VoxelShapes.method_1084(BASE_SHAPE, Block.method_9541(0.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D));
      SOUTH_SHAPE = VoxelShapes.method_1084(BASE_SHAPE, Block.method_9541(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D));
      WEST_SHAPE = VoxelShapes.method_1084(BASE_SHAPE, Block.method_9541(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D));
      EAST_SHAPE = VoxelShapes.method_1084(BASE_SHAPE, Block.method_9541(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 16.0D));
   }
}
