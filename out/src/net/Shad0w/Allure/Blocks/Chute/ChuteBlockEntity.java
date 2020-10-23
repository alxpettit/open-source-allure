package net.Shad0w.Allure.Blocks.Chute;

import java.util.Iterator;
import java.util.Random;
import net.Shad0w.Allure.Init.AllureBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.HopperBlock;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPointerImpl;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class ChuteBlockEntity extends LootableContainerBlockEntity implements Tickable {
   private static final Random RANDOM = new Random();
   private DefaultedList inventory;
   private int transferCooldown;

   public ChuteBlockEntity() {
      super(AllureBlocks.CHUTE_BLOCK_ENTITY);
      this.inventory = DefaultedList.method_10213(5, ItemStack.field_8037);
      this.transferCooldown = -1;
   }

   protected Text method_17823() {
      return new TranslatableText("block.allure.chute");
   }

   public ScreenHandler createMenu(int syncId, PlayerInventory inventory, PlayerEntity player) {
      return new ChuteBlockController(syncId, inventory, ScreenHandlerContext.method_17392(this.field_11863, this.field_11867));
   }

   protected ScreenHandler method_5465(int syncId, PlayerInventory playerInventory) {
      return null;
   }

   public int method_5439() {
      return 5;
   }

   public boolean isInvEmpty() {
      Iterator var1 = this.inventory.iterator();

      while(var1.hasNext()) {
         ItemStack itemStack_1 = (ItemStack)var1.next();
         if (!itemStack_1.method_7960()) {
            return false;
         }
      }

      return true;
   }

   public void method_11014(BlockState state, CompoundTag compoundTag_1) {
      super.method_11014(state, compoundTag_1);
      this.inventory = DefaultedList.method_10213(this.method_5439(), ItemStack.field_8037);
      if (!this.method_11283(compoundTag_1)) {
         Inventories.method_5429(compoundTag_1, this.inventory);
      }

      this.transferCooldown = compoundTag_1.method_10550("TransferCooldown");
   }

   public CompoundTag method_11007(CompoundTag compoundTag_1) {
      super.method_11007(compoundTag_1);
      if (!this.method_11286(compoundTag_1)) {
         Inventories.method_5426(compoundTag_1, this.inventory);
      }

      compoundTag_1.method_10569("TransferCooldown", this.transferCooldown);
      return compoundTag_1;
   }

   protected DefaultedList method_11282() {
      return this.inventory;
   }

   protected void method_11281(DefaultedList defaultedList_1) {
      this.inventory = defaultedList_1;
   }

   public int chooseNonEmptySlot() {
      this.method_11289((PlayerEntity)null);
      int int_1 = -1;
      int int_2 = 1;

      for(int int_3 = 0; int_3 < this.inventory.size(); ++int_3) {
         if (!((ItemStack)this.inventory.get(int_3)).method_7960() && RANDOM.nextInt(int_2++) == 0) {
            int_1 = int_3;
         }
      }

      return int_1;
   }

   public void method_16896() {
      if (this.field_11863 != null && !this.field_11863.field_9236) {
         --this.transferCooldown;
         if (!this.needsCooldown() && (Boolean)this.method_11010().method_11654(HopperBlock.field_11126) && !this.isInvEmpty()) {
            this.setCooldown(8);
            this.dispense((ServerWorld)this.field_11863, this.field_11867);
            this.method_5431();
         }
      }

   }

   protected void dispense(ServerWorld world_1, BlockPos blockPos_1) {
      BlockPointerImpl blockPointerImpl_1 = new BlockPointerImpl(world_1, blockPos_1);
      ChuteBlockEntity chuteBlockEntity = (ChuteBlockEntity)blockPointerImpl_1.method_10121();
      int int_1 = chuteBlockEntity.chooseNonEmptySlot();
      if (int_1 < 0) {
         world_1.method_20290(1001, blockPos_1, 0);
      } else {
         ItemStack outItem = chuteBlockEntity.method_5438(int_1);
         if (!outItem.method_7960()) {
            Direction direction_1 = Direction.field_11033;
            ItemStack itemStack_2 = outItem.method_7971(1);
            double double_1 = blockPointerImpl_1.method_10216();
            double double_2 = blockPointerImpl_1.method_10214() - 1.0D;
            double double_3 = blockPointerImpl_1.method_10215();
            ItemEntity itemEntity_1 = new ItemEntity(world_1, double_1, double_2, double_3, itemStack_2);
            itemEntity_1.method_18800(0.0D, world_1.field_9229.nextGaussian() * 0.007499999832361937D * (double)int_1 - 0.20000000298023224D, 0.0D);
            world_1.method_8649(itemEntity_1);
            blockPointerImpl_1.method_10207().method_20290(1000, blockPointerImpl_1.method_10122(), 0);
            blockPointerImpl_1.method_10207().method_20290(2000, blockPointerImpl_1.method_10122(), direction_1.method_10146());
            chuteBlockEntity.method_5447(int_1, outItem);
         }
      }

   }

   private boolean needsCooldown() {
      return this.transferCooldown > 0;
   }

   private void setCooldown(int int_1) {
      this.transferCooldown = int_1;
   }
}
