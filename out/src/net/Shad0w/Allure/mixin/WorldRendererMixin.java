package net.Shad0w.Allure.mixin;

import net.Shad0w.Allure.Entity.CrabEntity.CrabEntity;
import net.Shad0w.Allure.Init.AllureSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({WorldRenderer.class})
public abstract class WorldRendererMixin {
   @Shadow
   private ClientWorld field_4085;
   @Shadow
   @Final
   private MinecraftClient field_4088;

   @Inject(
      at = {@At("HEAD")},
      method = {"processWorldEvent"},
      cancellable = true
   )
   private void processWorldEvent(PlayerEntity playerEntity_1, int int_1, BlockPos blockPos_1, int int_2, CallbackInfo info) {
      if (int_1 == 1010) {
         CrabEntity.rave(this.field_4085, blockPos_1, Item.method_7875(int_2) instanceof MusicDiscItem);
      }

      if (int_1 == 2010) {
         BlockState blockState_1 = Block.method_9531(int_2);
         if (!blockState_1.method_26215()) {
            BlockSoundGroup blockSoundGroup_1 = blockState_1.method_26231();
            this.field_4085.method_2947(blockPos_1, AllureSounds.WOOD_SMASH, SoundCategory.field_15245, (blockSoundGroup_1.method_10597() + 1.0F) / 4.0F, blockSoundGroup_1.method_10599() * 0.8F, false);
         }

         this.field_4088.field_1713.method_3046(blockPos_1, blockState_1);
         info.cancel();
      }

   }
}
