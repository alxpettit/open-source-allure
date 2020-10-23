package net.Shad0w.Allure.Blocks.Gravestone;

import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import java.util.Iterator;
import net.Shad0w.Allure.Init.AllureBlocks;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.MathHelper;

public class GravestoneBlockEntity extends BlockEntity implements Inventory, BlockEntityClientSerializable, Tickable {
   public String playername = "";
   public DefaultedList playerInv;
   public float field_11964;
   public float field_11963;
   public float field_11962;
   public float field_11965;
   public int ticks;

   public GravestoneBlockEntity() {
      super(AllureBlocks.GRAVESTONE_BLOCK_ENTITY);
      this.playerInv = DefaultedList.method_10213(41, ItemStack.field_8037);
   }

   public Inventory getTrinketInv(Entity entity) {
      return ((TrinketComponent)TrinketsApi.TRINKETS.get(entity)).getInventory();
   }

   public CompoundTag method_11007(CompoundTag compoundTag) {
      super.method_11007(compoundTag);
      compoundTag.method_10582("playername", this.playername);
      return this.serializeInventory(compoundTag);
   }

   public void method_11014(BlockState state, CompoundTag compoundTag) {
      super.method_11014(state, compoundTag);
      this.playername = compoundTag.method_10558("playername");
      this.deserializeInventory(compoundTag);
   }

   public void fromClientTag(CompoundTag compoundTag) {
      this.playername = compoundTag.method_10558("playername");
   }

   public CompoundTag toClientTag(CompoundTag compoundTag) {
      compoundTag.method_10582("playername", this.playername);
      return compoundTag;
   }

   private void deserializeInventory(CompoundTag compoundTag_1) {
      this.playerInv = DefaultedList.method_10213(this.method_5439(), ItemStack.field_8037);
      Inventories.method_5429(compoundTag_1, this.playerInv);
   }

   private CompoundTag serializeInventory(CompoundTag compoundTag_1) {
      Inventories.method_5427(compoundTag_1, this.playerInv, true);
      return compoundTag_1;
   }

   public void method_16896() {
      this.field_11963 = this.field_11964;
      PlayerEntity playerEntity = this.field_11863.method_18459((double)((float)this.field_11867.method_10263() + 0.5F), (double)((float)this.field_11867.method_10264() + 0.5F), (double)((float)this.field_11867.method_10260() + 0.5F), 10.0D, false);
      if (playerEntity != null && playerEntity.method_5477().getString().equals(this.playername)) {
         double d = playerEntity.method_23317() - ((double)this.field_11867.method_10263() + 0.5D);
         double e = playerEntity.method_23321() - ((double)this.field_11867.method_10260() + 0.5D);
         double o = playerEntity.method_23318() + (double)playerEntity.method_5751() - (double)this.field_11867.method_10264() - 0.5D;
         this.field_11962 = (float)MathHelper.method_15349(e, d);
         this.field_11965 = (float)MathHelper.method_15349(o, (double)MathHelper.method_15368(d * d + e * e));
      } else {
         this.field_11962 = 0.0F;
         this.field_11965 = 0.0F;
      }

      while(this.field_11964 >= 3.1415927F) {
         this.field_11964 -= 6.2831855F;
      }

      while(this.field_11964 < -3.1415927F) {
         this.field_11964 += 6.2831855F;
      }

      while(this.field_11962 >= 3.1415927F) {
         this.field_11962 -= 6.2831855F;
      }

      while(this.field_11962 < -3.1415927F) {
         this.field_11962 += 6.2831855F;
      }

      float g;
      for(g = this.field_11962 - this.field_11964; g >= 3.1415927F; g -= 6.2831855F) {
      }

      while(g < -3.1415927F) {
         g += 6.2831855F;
      }

      this.field_11964 += g * 0.4F;
      ++this.ticks;
   }

   public int method_5439() {
      return this.playerInv.size();
   }

   public boolean method_5442() {
      Iterator var1 = this.playerInv.iterator();

      while(var1.hasNext()) {
         ItemStack itemStack_1 = (ItemStack)var1.next();
         if (!itemStack_1.method_7960()) {
            return false;
         }
      }

      return true;
   }

   public ItemStack method_5438(int i) {
      return (ItemStack)this.playerInv.get(i);
   }

   public ItemStack method_5434(int i, int amount) {
      return Inventories.method_5430(this.playerInv, i, amount);
   }

   public ItemStack method_5441(int i) {
      return Inventories.method_5428(this.playerInv, i);
   }

   public void method_5447(int i, ItemStack itemStack) {
      this.playerInv.set(i, itemStack);
      if (itemStack.method_7947() > this.method_5444()) {
         itemStack.method_7939(this.method_5444());
      }

   }

   public boolean method_5443(PlayerEntity player) {
      return false;
   }

   public void method_5448() {
      this.playerInv.clear();
   }
}
