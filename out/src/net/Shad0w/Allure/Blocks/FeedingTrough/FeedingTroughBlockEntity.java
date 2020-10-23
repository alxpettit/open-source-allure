package net.Shad0w.Allure.Blocks.FeedingTrough;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.Shad0w.Allure.Allure;
import net.Shad0w.Allure.Init.AllureBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.client.util.math.Vector3d;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.Generic3x3ContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class FeedingTroughBlockEntity extends LootableContainerBlockEntity implements Tickable {
   private DefaultedList inventory;
   private int cooldown = 0;
   private long internalRng = 0L;

   public FeedingTroughBlockEntity() {
      super(AllureBlocks.FEEDING_TROUGH_BLOCK_ENTITY);
      this.inventory = DefaultedList.method_10213(9, ItemStack.field_8037);
   }

   protected DefaultedList method_11282() {
      return this.inventory;
   }

   protected void method_11281(DefaultedList list) {
      this.inventory = list;
   }

   protected Text method_17823() {
      return new TranslatableText("block.allure.feeding_trough");
   }

   public void method_11014(BlockState state, CompoundTag tag) {
      super.method_11014(state, tag);
      this.inventory = DefaultedList.method_10213(this.method_5439(), ItemStack.field_8037);
      if (!this.method_11283(tag)) {
         Inventories.method_5429(tag, this.inventory);
      }

   }

   public CompoundTag method_11007(CompoundTag tag) {
      super.method_11007(tag);
      if (!this.method_11286(tag)) {
         Inventories.method_5426(tag, this.inventory);
      }

      return tag;
   }

   protected ScreenHandler method_5465(int syncId, PlayerInventory playerInventory) {
      return new Generic3x3ContainerScreenHandler(syncId, playerInventory, this);
   }

   public int method_5439() {
      return this.inventory.size();
   }

   public void method_16896() {
      if (this.cooldown > 0 && !this.field_11863.method_8608()) {
         --this.cooldown;
      } else {
         this.cooldown = 30;
         List animals = this.field_11863.method_18467(AnimalEntity.class, (new Box(this.field_11867)).method_1009(1.5D, 0.0D, 1.5D).method_1002(0.0D, 0.75D, 0.0D));
         Iterator var2 = animals.iterator();

         while(var2.hasNext()) {
            AnimalEntity creature = (AnimalEntity)var2.next();
            ItemStack stack = null;

            for(int i = 8; i >= 0; --i) {
               if (!this.method_5438(i).method_7960() && creature.method_6481(this.method_5438(i))) {
                  stack = this.method_5438(i);
               }
            }

            if (stack == null) {
               return;
            }

            if (this.field_11863.method_8608()) {
               creature.method_5783(creature.method_18869(stack), 0.5F + 0.5F * (float)this.field_11863.field_9229.nextInt(2), (this.field_11863.field_9229.nextFloat() - this.field_11863.field_9229.nextFloat()) * 0.2F + 1.0F);
               this.addItemParticles(creature, stack, 16);
            }

            if (creature.method_6109() && Allure.CONFIG.babiesEatTroughEnabled) {
               creature.method_5620((int)((float)(-creature.method_5618() / 20) * 0.1F), true);
               stack.method_7934(1);
               this.method_5431();
            }

            if (this.getSpecialRand().nextDouble() < 0.333333333D && creature.method_5618() == 0) {
               List animalsAround = this.field_11863.method_18467(AnimalEntity.class, (new Box(this.field_11867)).method_1014(10.0D));
               if (animalsAround.size() <= 32) {
                  creature.method_6476(600);
               }

               stack.method_7934(1);
               this.method_5431();
            }
         }
      }

   }

   public void method_5431() {
      super.method_5431();
      BlockState state = this.method_11010();
      if (this.field_11863 != null && state.method_26204() instanceof FeedingTroughBlock) {
         boolean full = (Boolean)state.method_11654(FeedingTroughBlock.FULL);
         boolean shouldBeFull = !this.method_5442();
         if (full != shouldBeFull) {
            this.field_11863.method_8652(this.field_11867, (BlockState)state.method_11657(FeedingTroughBlock.FULL, shouldBeFull), 2);
         }
      }

   }

   private Random getSpecialRand() {
      Random specialRand = new Random(this.internalRng);
      this.internalRng = specialRand.nextLong();
      return specialRand;
   }

   @Environment(EnvType.CLIENT)
   private void addItemParticles(AnimalEntity entity, ItemStack stack, int count) {
      for(int i = 0; i < count; ++i) {
         new Vector3d(((double)entity.field_6002.field_9229.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
         double yVelocity = (double)(-entity.field_6002.field_9229.nextFloat()) * 0.6D - 0.3D;
         new Vector3d(((double)entity.field_6002.field_9229.nextFloat() - 0.5D) * 0.3D, yVelocity, 0.6D);
         Vec3d var9 = entity.method_19538();
      }

   }
}
