package net.Shad0w.Allure.mixin;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({ItemStack.class})
public abstract class NBTSHOWER {
   @Shadow
   private CompoundTag field_8040;
   @Shadow
   @Final
   public static DecimalFormat field_8029;

   @Shadow
   public abstract Text method_7964();

   @Shadow
   public abstract Rarity method_7932();

   @Shadow
   public abstract boolean method_7938();

   @Shadow
   public abstract Item method_7909();

   @Shadow
   public abstract boolean method_7985();

   @Shadow
   public static void method_17870(List tooltip, ListTag enchantments) {
   }

   @Shadow
   public abstract ListTag method_7921();

   @Shadow
   public abstract Multimap method_7926(EquipmentSlot var1);

   @Shadow
   public abstract CompoundTag method_7969();

   @Shadow
   private static Collection method_7937(String tag) {
      return null;
   }

   @Shadow
   public abstract boolean method_7986();

   @Shadow
   public abstract int method_7936();

   @Shadow
   public abstract int method_7919();

   @Overwrite
   @Environment(EnvType.CLIENT)
   public List method_7950(PlayerEntity player, TooltipContext context) {
      List list = Lists.newArrayList();
      MutableText text = (new LiteralText("")).method_10852(this.method_7964()).method_27692(this.method_7932().field_8908);
      if (this.method_7938()) {
         text.method_27692(Formatting.field_1056);
      }

      list.add(text);
      if (context.method_8035()) {
         if (this.method_7986()) {
            list.add(new TranslatableText("item.durability", new Object[]{this.method_7936() - this.method_7919(), this.method_7936()}));
         }

         list.add((new LiteralText(Registry.field_11142.method_10221(this.method_7909()).toString())).method_27692(Formatting.field_1063));
         if (this.method_7985()) {
            System.out.println(this.method_7969().method_10541());
            System.out.println(this.method_7969());
            list.add((new TranslatableText("item.nbt_tags", new Object[]{this.method_7969().method_10541().size()})).method_27692(Formatting.field_1063));
         }
      }

      return list;
   }
}
