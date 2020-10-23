package net.Shad0w.Allure.Blocks.Crate;

import java.util.Iterator;
import java.util.List;
import net.Shad0w.Allure.Allure;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.entity.mob.StrayEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CrateBlock extends BlockWithEntity {
   public static final Identifier GUI = new Identifier("allure", "crate");
   private static final Identifier CONTENTS = new Identifier("contents");
   public static final BooleanProperty SEALED = BooleanProperty.method_11825("sealed");

   public CrateBlock() {
      super(FabricBlockSettings.of(Material.field_15932).strength(2.0F, 10.0F).sounds(BlockSoundGroup.field_11547).build());
      this.method_9590((BlockState)((BlockState)this.field_10647.method_11664()).method_11657(SEALED, false));
   }

   public BlockEntity method_10123(BlockView blockView) {
      return new CrateBlockEntity();
   }

   public ActionResult method_9534(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
      if ((Boolean)state.method_11654(SEALED)) {
         return ActionResult.field_5811;
      } else {
         player.method_17355(state.method_26196(world, pos));
         return ActionResult.field_5812;
      }
   }

   public void method_9536(BlockState blockState_1, World world_1, BlockPos blockPos_1, BlockState blockState_2, boolean boolean_1) {
      if (blockState_1.method_26204() != blockState_2.method_26204()) {
         BlockEntity blockEntity_1 = world_1.method_8321(blockPos_1);
         if (blockEntity_1 instanceof CrateBlockEntity) {
            if ((Boolean)blockState_1.method_11654(SEALED)) {
               if (((CrateBlockEntity)blockEntity_1).abandoned) {
                  float f = world_1.field_9229.nextFloat();
                  Identifier rarity = new Identifier("allure:chests/common");
                  if (f <= Allure.CONFIG.crateRareChance) {
                     rarity = new Identifier("allure:chests/rare");
                  } else if (f <= Allure.CONFIG.crateRareChance * 2.0F) {
                     rarity = new Identifier("allure:chests/explosive");
                  } else if (f <= Allure.CONFIG.crateValuableChance / 2.0F) {
                     rarity = new Identifier("allure:chests/dangerous");
                  } else if (f <= Allure.CONFIG.crateValuableChance) {
                     rarity = new Identifier("allure:chests/valuable");
                  } else if (f <= Allure.CONFIG.crateUncommonChance) {
                     rarity = new Identifier("allure:chests/uncommon");
                  }

                  LootableContainerBlockEntity.method_11287(world_1, world_1.method_8409(), blockPos_1, rarity);

                  for(int i = 0; i < ((Inventory)blockEntity_1).method_5439(); ++i) {
                     if (!((Inventory)blockEntity_1).method_5438(i).method_7960()) {
                        int spawnRange = 12;
                        double double_4 = (double)blockPos_1.method_10263() + (world_1.field_9229.nextDouble() - world_1.field_9229.nextDouble()) * (double)spawnRange + 0.5D;
                        double double_5 = (double)(blockPos_1.method_10264() + world_1.field_9229.nextInt(3));
                        double double_6 = (double)blockPos_1.method_10260() + (world_1.field_9229.nextDouble() - world_1.field_9229.nextDouble()) * (double)spawnRange + 0.5D;
                        BlockPos blockPos = new BlockPos(double_4, double_5, double_6);
                        boolean isVaild = world_1.method_8320(blockPos).method_26215() && (world_1.method_8320(blockPos.method_10074()).method_26215() || world_1.method_8320(blockPos.method_10084()).method_26215());
                        if (((Inventory)blockEntity_1).method_5438(i).method_7909() == Items.field_8564) {
                           if (isVaild) {
                              SilverfishEntity Entity_1 = (SilverfishEntity)EntityType.field_6125.method_5883(world_1);
                              Entity_1.method_5641((double)blockPos.method_10263(), (double)blockPos.method_10264(), (double)blockPos.method_10260(), world_1.field_9229.nextFloat() * 360.0F, 0.0F);
                              world_1.method_8649(Entity_1);
                           }

                           ((Inventory)blockEntity_1).method_5441(i);
                        } else if (((Inventory)blockEntity_1).method_5438(i).method_7909() == Items.field_8514) {
                           if (isVaild) {
                              StrayEntity Entity_1 = (StrayEntity)EntityType.field_6098.method_5883(world_1);
                              Entity_1.method_5641((double)blockPos.method_10263(), (double)blockPos.method_10264(), (double)blockPos.method_10260(), world_1.field_9229.nextFloat() * 360.0F, 0.0F);
                              Entity_1.method_5673(EquipmentSlot.field_6173, new ItemStack(Items.field_8102));
                              world_1.method_8649(Entity_1);
                           }

                           ((Inventory)blockEntity_1).method_5441(i);
                        } else if (((Inventory)blockEntity_1).method_5438(i).method_7909() == Items.field_8132) {
                           if (isVaild) {
                              OcelotEntity Entity_1 = (OcelotEntity)EntityType.field_6081.method_5883(world_1);
                              Entity_1.method_5641((double)blockPos.method_10263(), (double)blockPos.method_10264(), (double)blockPos.method_10260(), world_1.field_9229.nextFloat() * 360.0F, 0.0F);
                              world_1.method_8649(Entity_1);
                           }

                           ((Inventory)blockEntity_1).method_5441(i);
                        } else if (((Inventory)blockEntity_1).method_5438(i).method_7909() == Items.field_8832) {
                           if (isVaild) {
                              WitherSkeletonEntity Entity_1 = (WitherSkeletonEntity)EntityType.field_6076.method_5883(world_1);
                              Entity_1.method_5641((double)blockPos.method_10263(), (double)blockPos.method_10264(), (double)blockPos.method_10260(), world_1.field_9229.nextFloat() * 360.0F, 0.0F);
                              Entity_1.method_5673(EquipmentSlot.field_6173, new ItemStack(Items.field_8528));
                              world_1.method_8649(Entity_1);
                           }

                           ((Inventory)blockEntity_1).method_5441(i);
                        } else if (((Inventory)blockEntity_1).method_5438(i).method_7909() == Items.field_8235) {
                           if (isVaild) {
                              VexEntity Entity_1 = (VexEntity)EntityType.field_6059.method_5883(world_1);
                              Entity_1.method_5641((double)blockPos.method_10263(), (double)blockPos.method_10264(), (double)blockPos.method_10260(), world_1.field_9229.nextFloat() * 360.0F, 0.0F);
                              Entity_1.method_5673(EquipmentSlot.field_6173, new ItemStack(Items.field_8371));
                              world_1.method_8649(Entity_1);
                           }

                           ((Inventory)blockEntity_1).method_5441(i);
                        } else if (((Inventory)blockEntity_1).method_5438(i).method_7909() == Items.field_8441) {
                           if (isVaild) {
                              ZombieEntity Entity_1 = (ZombieEntity)EntityType.field_6051.method_5883(world_1);
                              Entity_1.method_5641((double)blockPos.method_10263(), (double)blockPos.method_10264(), (double)blockPos.method_10260(), world_1.field_9229.nextFloat() * 360.0F, 0.0F);
                              Entity_1.method_5673(EquipmentSlot.field_6173, new ItemStack(Items.field_8475));
                              world_1.method_8649(Entity_1);
                           }

                           ((Inventory)blockEntity_1).method_5441(i);
                        } else if (((Inventory)blockEntity_1).method_5438(i).method_7909() == Items.field_8503) {
                           if (isVaild) {
                              CreeperEntity Entity_1 = (CreeperEntity)EntityType.field_6046.method_5883(world_1);
                              Entity_1.method_5641((double)blockPos.method_10263(), (double)blockPos.method_10264(), (double)blockPos.method_10260(), world_1.field_9229.nextFloat() * 360.0F, 0.0F);
                              world_1.method_8649(Entity_1);
                           }

                           ((Inventory)blockEntity_1).method_5441(i);
                        } else if (((Inventory)blockEntity_1).method_5438(i).method_7909() == Items.field_8149) {
                           if (isVaild) {
                              VindicatorEntity Entity_1 = (VindicatorEntity)EntityType.field_6117.method_5883(world_1);
                              Entity_1.method_5641((double)blockPos.method_10263(), (double)blockPos.method_10264(), (double)blockPos.method_10260(), world_1.field_9229.nextFloat() * 360.0F, 0.0F);
                              Entity_1.method_5673(EquipmentSlot.field_6173, new ItemStack(Items.field_8475));
                              world_1.method_8649(Entity_1);
                           }

                           ((Inventory)blockEntity_1).method_5441(i);
                        }

                        if (((Inventory)blockEntity_1).method_5438(i).method_7909() == Items.field_8626) {
                           if (isVaild) {
                              TntEntity tntEntity_1 = new TntEntity(world_1, (double)((float)blockPos_1.method_10263() + 0.5F), (double)blockPos_1.method_10264(), (double)((float)blockPos_1.method_10260() + 0.5F), (LivingEntity)null);
                              world_1.method_8649(tntEntity_1);
                              world_1.method_8465((PlayerEntity)null, tntEntity_1.method_23317(), tntEntity_1.method_23318(), tntEntity_1.method_23321(), SoundEvents.field_15079, SoundCategory.field_15245, 1.0F, 1.0F);
                           }

                           ((Inventory)blockEntity_1).method_5441(i);
                        }
                     }
                  }
               }

               ItemScatterer.method_5451(world_1, blockPos_1, (Inventory)blockEntity_1);
            }

            world_1.method_8455(blockPos_1, blockState_1.method_26204());
         }

         super.method_9536(blockState_1, world_1, blockPos_1, blockState_2, boolean_1);
      }

   }

   public void method_9556(World world_1, PlayerEntity playerEntity_1, BlockPos blockPos_1, BlockState blockState_1, BlockEntity blockEntity_1, ItemStack itemStack_1) {
      playerEntity_1.method_7259(Stats.field_15427.method_14956(this));
      playerEntity_1.method_7322(0.005F);
      if (!(Boolean)blockState_1.method_11654(SEALED)) {
         method_9511(blockState_1, world_1, blockPos_1, blockEntity_1, playerEntity_1, itemStack_1);
      }

   }

   public BlockRenderType method_9604(BlockState blockState_1) {
      return BlockRenderType.field_11458;
   }

   public void method_9567(World world_1, BlockPos blockPos_1, BlockState blockState_1, LivingEntity livingEntity_1, ItemStack itemStack_1) {
      BlockEntity blockEntity_1 = world_1.method_8321(blockPos_1);
      if (blockEntity_1 instanceof CrateBlockEntity) {
         if (itemStack_1.method_7985()) {
            world_1.method_8501(blockPos_1, (BlockState)this.method_9564().method_11657(SEALED, itemStack_1.method_7969().method_10577("sealed")));
         }

         if (itemStack_1.method_7938()) {
            ((CrateBlockEntity)blockEntity_1).method_17488(itemStack_1.method_7964());
         }
      }

   }

   public boolean method_9498(BlockState blockState_1) {
      return true;
   }

   public int method_9572(BlockState blockState_1, World world_1, BlockPos blockPos_1) {
      return ScreenHandler.method_7608(world_1.method_8321(blockPos_1));
   }

   protected void method_9515(Builder stateFactory$Builder_1) {
      stateFactory$Builder_1.method_11667(new Property[]{SEALED});
   }

   public PistonBehavior method_9527(BlockState blockState_1) {
      return PistonBehavior.field_15971;
   }

   public void method_9576(World world_1, BlockPos blockPos_1, BlockState blockState_1, PlayerEntity playerEntity_1) {
      BlockEntity blockEntity_1 = world_1.method_8321(blockPos_1);
      if (blockEntity_1 instanceof CrateBlockEntity && !(Boolean)blockState_1.method_11654(SEALED)) {
         CrateBlockEntity crateBlockEntity_1 = (CrateBlockEntity)blockEntity_1;
         if (!world_1.field_9236 && playerEntity_1.method_7337() && !crateBlockEntity_1.method_5442()) {
            ItemStack itemStack_1 = new ItemStack(this.method_8389());
            CompoundTag compoundTag_1 = crateBlockEntity_1.serializeInventory(new CompoundTag());
            if (!compoundTag_1.isEmpty()) {
               itemStack_1.method_7959("BlockEntityTag", compoundTag_1);
            }

            if (crateBlockEntity_1.method_16914()) {
               itemStack_1.method_7977(crateBlockEntity_1.method_5797());
            }

            ItemEntity itemEntity_1 = new ItemEntity(world_1, (double)blockPos_1.method_10263(), (double)blockPos_1.method_10264(), (double)blockPos_1.method_10260(), itemStack_1);
            itemEntity_1.method_6988();
            world_1.method_8649(itemEntity_1);
         } else {
            crateBlockEntity_1.method_11289(playerEntity_1);
         }

         super.method_9576(world_1, blockPos_1, blockState_1, playerEntity_1);
      } else {
         world_1.method_8444(playerEntity_1, 2010, blockPos_1, method_9507(blockState_1));
      }

   }

   public List method_9560(BlockState blockState_1, net.minecraft.loot.context.LootContext.Builder lootContext$Builder_1) {
      BlockEntity blockEntity_1 = (BlockEntity)lootContext$Builder_1.method_305(LootContextParameters.field_1228);
      if (blockEntity_1 instanceof CrateBlockEntity && !(Boolean)blockState_1.method_11654(SEALED)) {
         CrateBlockEntity crateBlockEntity_1 = (CrateBlockEntity)blockEntity_1;
         lootContext$Builder_1 = lootContext$Builder_1.method_307(CONTENTS, (lootContext_1, consumer_1) -> {
            for(int int_1 = 0; int_1 < crateBlockEntity_1.method_5439(); ++int_1) {
               consumer_1.accept(crateBlockEntity_1.method_5438(int_1));
            }

         });
      }

      return super.method_9560(blockState_1, lootContext$Builder_1);
   }

   @Environment(EnvType.CLIENT)
   public void method_9568(ItemStack itemStack_1, BlockView blockView_1, List list_1, TooltipContext tooltipContext_1) {
      super.method_9568(itemStack_1, blockView_1, list_1, tooltipContext_1);
      CompoundTag compoundTag_1 = itemStack_1.method_7941("BlockEntityTag");
      if (compoundTag_1 != null) {
         if (compoundTag_1.method_10573("LootTable", 8)) {
            list_1.add(new LiteralText("???????"));
         }

         if (compoundTag_1.method_10573("Items", 9)) {
            DefaultedList defaultedList_1 = DefaultedList.method_10213(9, ItemStack.field_8037);
            Inventories.method_5429(compoundTag_1, defaultedList_1);
            int int_1 = 0;
            int int_2 = 0;
            Iterator var9 = defaultedList_1.iterator();

            while(var9.hasNext()) {
               ItemStack itemStack_2 = (ItemStack)var9.next();
               if (!itemStack_2.method_7960()) {
                  ++int_2;
                  if (int_1 <= 4) {
                     ++int_1;
                     MutableText text_1 = itemStack_2.method_7964().method_27661();
                     text_1.method_27693(" x").method_27693(String.valueOf(itemStack_2.method_7947()));
                     list_1.add(text_1);
                  }
               }
            }

            if (int_2 - int_1 > 0) {
               list_1.add((new TranslatableText("container.shulkerBox.more", new Object[]{int_2 - int_1})).method_27692(Formatting.field_1056));
            }
         }
      }

   }
}
