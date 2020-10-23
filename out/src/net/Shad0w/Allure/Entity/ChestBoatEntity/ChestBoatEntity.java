package net.Shad0w.Allure.Entity.ChestBoatEntity;

import java.util.Iterator;
import net.Shad0w.Allure.Init.AllureEntities;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class ChestBoatEntity extends BoatEntity implements Inventory, NamedScreenHandlerFactory {
   public static final Identifier ID = new Identifier("allure", "chest_boat");
   private DefaultedList inventory;
   private boolean field_7733;

   public ChestBoatEntity(EntityType entityType, World world) {
      super(entityType, world);
      this.inventory = DefaultedList.method_10213(36, ItemStack.field_8037);
      this.field_7733 = true;
   }

   public ChestBoatEntity(World world, double x, double y, double z) {
      this(AllureEntities.CHEST_BOAT, world);
      this.method_5814(x, y, z);
      this.method_18799(Vec3d.field_1353);
      this.field_6014 = x;
      this.field_6036 = y;
      this.field_5969 = z;
   }

   public boolean method_5643(DamageSource source, float amount) {
      if (this.method_5679(source)) {
         return false;
      } else if (!this.field_6002.field_9236 && !this.field_5988) {
         this.method_7540(-this.method_7543());
         this.method_7553(10);
         this.method_7542(this.method_7554() + amount * 10.0F);
         this.method_5785();
         boolean bl = source.method_5529() instanceof PlayerEntity && ((PlayerEntity)source.method_5529()).field_7503.field_7477;
         if (bl || this.method_7554() > 40.0F) {
            if (bl && !this.method_16914()) {
               this.method_5650();
            } else {
               this.dropItems();
            }
         }

         return true;
      } else {
         return true;
      }
   }

   public void dropItems() {
      this.method_5650();
      if (this.field_6002.method_8450().method_8355(GameRules.field_19393)) {
         ItemStack itemStack = new ItemStack(this.method_7557());
         if (this.method_16914()) {
            itemStack.method_7977(this.method_5797());
         }

         this.method_5775(itemStack);
         this.method_5706(Blocks.field_10034);
         ItemScatterer.method_5452(this.field_6002, this, this);
      }

   }

   public boolean method_5442() {
      Iterator var1 = this.inventory.iterator();

      while(var1.hasNext()) {
         ItemStack itemStack = (ItemStack)var1.next();
         if (!itemStack.method_7960()) {
            return false;
         }
      }

      return true;
   }

   public ItemStack method_5438(int slot) {
      return (ItemStack)this.inventory.get(slot);
   }

   public ItemStack method_5434(int slot, int amount) {
      return Inventories.method_5430(this.inventory, slot, amount);
   }

   public ItemStack method_5441(int slot) {
      ItemStack itemStack = (ItemStack)this.inventory.get(slot);
      if (itemStack.method_7960()) {
         return ItemStack.field_8037;
      } else {
         this.inventory.set(slot, ItemStack.field_8037);
         return itemStack;
      }
   }

   public void method_5447(int slot, ItemStack stack) {
      this.inventory.set(slot, stack);
      if (!stack.method_7960() && stack.method_7947() > this.method_5444()) {
         stack.method_7939(this.method_5444());
      }

   }

   public boolean method_5758(int slot, ItemStack item) {
      if (slot >= 0 && slot < this.method_5439()) {
         this.method_5447(slot, item);
         return true;
      } else {
         return false;
      }
   }

   public void method_5431() {
   }

   public boolean method_5443(PlayerEntity player) {
      if (this.field_5988) {
         return false;
      } else {
         return player.method_5858(this) <= 64.0D;
      }
   }

   public Entity method_5731(ServerWorld destination) {
      this.field_7733 = false;
      return super.method_5731(destination);
   }

   public void method_5650() {
      if (!this.field_6002.field_9236 && this.field_7733) {
         ItemScatterer.method_5452(this.field_6002, this, this);
      }

      super.method_5650();
   }

   protected void method_5652(CompoundTag tag) {
      super.method_5652(tag);
      Inventories.method_5426(tag, this.inventory);
   }

   protected void method_5749(CompoundTag tag) {
      super.method_5749(tag);
      this.inventory = DefaultedList.method_10213(this.method_5439(), ItemStack.field_8037);
      Inventories.method_5429(tag, this.inventory);
   }

   public ActionResult method_5688(PlayerEntity player, Hand hand) {
      if (player.method_5715()) {
         player.method_17355(this);
         return ActionResult.method_29236(this.field_6002.field_9236);
      } else {
         return super.method_5688(player, hand);
      }
   }

   public ActionResult method_5664(PlayerEntity player, Vec3d hitPos, Hand hand) {
      return super.method_5664(player, hitPos, hand);
   }

   public void method_5448() {
      this.inventory.clear();
   }

   public ScreenHandler createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
      return this.getScreenHandler(i, playerInventory);
   }

   public int method_5439() {
      return 27;
   }

   public ScreenHandler getScreenHandler(int syncId, PlayerInventory playerInventory) {
      return GenericContainerScreenHandler.method_19245(syncId, playerInventory, this);
   }

   protected boolean method_5818(Entity passenger) {
      return passenger instanceof PlayerEntity && !this.method_5782();
   }
}
