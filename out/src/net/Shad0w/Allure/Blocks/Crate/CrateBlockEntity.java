package net.Shad0w.Allure.Blocks.Crate;

import java.util.stream.IntStream;
import net.Shad0w.Allure.Init.AllureBlocks;
import net.Shad0w.Allure.Init.AllureSounds;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;

public class CrateBlockEntity extends LootableContainerBlockEntity implements SidedInventory, Tickable, BlockEntityClientSerializable {
   private DefaultedList inventory;
   private int viewerCount;
   private static final int[] AVAILABLE_SLOTS = IntStream.range(0, 9).toArray();
   private boolean crateOpen;
   public boolean abandoned = false;

   public CrateBlockEntity() {
      super(AllureBlocks.CRATE_BLOCK_ENTITY);
      this.inventory = DefaultedList.method_10213(9, ItemStack.field_8037);
      this.crateOpen = false;
   }

   public void method_11014(BlockState state, CompoundTag compoundTag_1) {
      super.method_11014(state, compoundTag_1);
      this.abandoned = compoundTag_1.method_10577("abandoned");
      this.deserializeInventory(compoundTag_1);
   }

   public CompoundTag method_11007(CompoundTag compoundTag_1) {
      super.method_11007(compoundTag_1);
      compoundTag_1.method_10556("abandoned", this.abandoned);
      return this.serializeInventory(compoundTag_1);
   }

   public ScreenHandler createMenu(int syncId, PlayerInventory inventory, PlayerEntity player) {
      if (this.method_17489(player) && !this.abandoned) {
         this.method_11289(inventory.field_7546);
         return new CrateBlockController(syncId, inventory, ScreenHandlerContext.method_17392(this.field_11863, this.field_11867));
      } else {
         return null;
      }
   }

   public void deserializeInventory(CompoundTag compoundTag_1) {
      this.inventory = DefaultedList.method_10213(this.method_5439(), ItemStack.field_8037);
      if (!this.method_11283(compoundTag_1) && compoundTag_1.method_10573("Items", 9)) {
         Inventories.method_5429(compoundTag_1, this.inventory);
      }

   }

   public CompoundTag serializeInventory(CompoundTag compoundTag_1) {
      if (!this.method_11286(compoundTag_1)) {
         Inventories.method_5427(compoundTag_1, this.inventory, false);
      }

      return compoundTag_1;
   }

   public TranslatableText getGoodName(BlockState blockState) {
      if (blockState.method_26204() == AllureBlocks.CRATE_OAK_BLOCK) {
         return new TranslatableText("block.allure.crate_oak");
      } else if (blockState.method_26204() == AllureBlocks.CRATE_SPRUCE_BLOCK) {
         return new TranslatableText("block.allure.crate_spruce");
      } else if (blockState.method_26204() == AllureBlocks.CRATE_BIRCH_BLOCK) {
         return new TranslatableText("block.allure.crate_birch");
      } else if (blockState.method_26204() == AllureBlocks.CRATE_JUNGLE_BLOCK) {
         return new TranslatableText("block.allure.crate_jungle");
      } else {
         return blockState.method_26204() == AllureBlocks.CRATE_ACACIA_BLOCK ? new TranslatableText("block.allure.crate_acacia") : new TranslatableText("block.allure.crate_dark_oak");
      }
   }

   protected Text method_17823() {
      if (this.method_11010().method_26204() == AllureBlocks.CRATE_OAK_BLOCK) {
         return new TranslatableText("block.allure.crate_oak");
      } else if (this.method_11010().method_26204() == AllureBlocks.CRATE_SPRUCE_BLOCK) {
         return new TranslatableText("block.allure.crate_spruce");
      } else if (this.method_11010().method_26204() == AllureBlocks.CRATE_BIRCH_BLOCK) {
         return new TranslatableText("block.allure.crate_birch");
      } else if (this.method_11010().method_26204() == AllureBlocks.CRATE_JUNGLE_BLOCK) {
         return new TranslatableText("block.allure.crate_jungle");
      } else {
         return this.method_11010().method_26204() == AllureBlocks.CRATE_ACACIA_BLOCK ? new TranslatableText("block.allure.crate_acacia") : new TranslatableText("block.allure.crate_dark_oak");
      }
   }

   protected ScreenHandler method_5465(int syncId, PlayerInventory playerInventory) {
      return null;
   }

   public int[] method_5494(Direction direction) {
      return AVAILABLE_SLOTS;
   }

   public boolean method_5492(int i, ItemStack itemStack, Direction direction) {
      return !(Block.method_9503(itemStack.method_7909()) instanceof CrateBlock) && !(Block.method_9503(itemStack.method_7909()) instanceof ShulkerBoxBlock);
   }

   public boolean method_5493(int i, ItemStack itemStack, Direction direction) {
      return true;
   }

   public int method_5439() {
      return this.inventory.size();
   }

   public void method_5448() {
      this.inventory.clear();
   }

   protected DefaultedList method_11282() {
      return this.inventory;
   }

   protected void method_11281(DefaultedList defaultedList_1) {
      this.inventory = defaultedList_1;
   }

   public void method_5435(PlayerEntity player) {
      System.out.println("CRATE OPEN");
   }

   public void invOpened(PlayerEntity playerEntity_1) {
      if (!playerEntity_1.method_7325()) {
         if (this.viewerCount < 0) {
            this.viewerCount = 0;
         }

         ++this.viewerCount;
         BlockState blockState_1 = this.method_11010();
         if (!this.crateOpen) {
            this.playSound(blockState_1, AllureSounds.WOOD_OPEN);
            this.crateOpen = true;
         }

         this.scheduleUpdate();
      }

   }

   private void scheduleUpdate() {
      this.field_11863.method_8397().method_8676(this.method_11016(), this.method_11010().method_26204(), 5);
   }

   private void playSound(BlockState blockState_1, SoundEvent soundEvent_1) {
      double double_1 = (double)this.field_11867.method_10263() + 0.25D;
      double double_2 = (double)this.field_11867.method_10264() + 0.25D;
      double double_3 = (double)this.field_11867.method_10260() + 0.25D;
      this.field_11863.method_8465((PlayerEntity)null, double_1, double_2, double_3, soundEvent_1, SoundCategory.field_15245, 0.5F, this.field_11863.field_9229.nextFloat() * 0.1F + 0.9F);
   }

   public void method_16896() {
      if (!(Boolean)this.field_11863.method_8320(this.field_11867).method_11654(CrateBlock.SEALED)) {
         int int_1 = this.field_11867.method_10263();
         int int_2 = this.field_11867.method_10264();
         int int_3 = this.field_11867.method_10260();
         if (this.viewerCount > 0) {
            this.scheduleUpdate();
         } else {
            BlockState blockState_1 = this.method_11010();
            if (blockState_1 != this.field_11863.method_8320(this.field_11867)) {
               return;
            }

            if (this.crateOpen) {
               this.playSound(blockState_1, AllureSounds.WOOD_CLOSE);
               this.crateOpen = false;
            }
         }

      }
   }

   public void invClosed(PlayerEntity playerEntity_1) {
      if (!playerEntity_1.method_7325()) {
         --this.viewerCount;
      }

   }

   public void method_5432(PlayerEntity playerEntity_1) {
      if (!playerEntity_1.method_7325()) {
         --this.viewerCount;
      }

      this.playSound(this.method_11010(), AllureSounds.WOOD_CLOSE);
   }

   public void fromClientTag(CompoundTag compoundTag) {
      if (compoundTag.method_10573("CustomName", 8)) {
         this.method_17488(new TranslatableText(compoundTag.method_10558("CustomName")));
      }

   }

   public CompoundTag toClientTag(CompoundTag compoundTag) {
      if (this.method_16914()) {
         compoundTag.method_10582("CustomName", this.method_5797().method_10851());
      }

      return compoundTag;
   }
}
