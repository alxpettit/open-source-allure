package net.Shad0w.Allure.Init;

import net.Shad0w.Allure.Utils.MusicDiscDispenserBehaviour;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.BlockPlacementDispenserBehavior;
import net.minecraft.item.Items;

public class AllureDispenserBehaviour {
   public static void initialize() {
      DispenserBlock.method_10009(Items.field_8317, new BlockPlacementDispenserBehavior());
      DispenserBlock.method_10009(Items.field_8309, new BlockPlacementDispenserBehavior());
      DispenserBlock.method_10009(Items.field_8188, new BlockPlacementDispenserBehavior());
      DispenserBlock.method_10009(Items.field_8706, new BlockPlacementDispenserBehavior());
      DispenserBlock.method_10009(Items.field_8179, new BlockPlacementDispenserBehavior());
      DispenserBlock.method_10009(Items.field_8567, new BlockPlacementDispenserBehavior());
      DispenserBlock.method_10009(Items.field_8116, new BlockPlacementDispenserBehavior());
      DispenserBlock.method_10009(Items.field_8710, new BlockPlacementDispenserBehavior());
      DispenserBlock.method_10009(Items.field_8731, new MusicDiscDispenserBehaviour());
      DispenserBlock.method_10009(Items.field_8144, new MusicDiscDispenserBehaviour());
      DispenserBlock.method_10009(Items.field_8075, new MusicDiscDispenserBehaviour());
      DispenserBlock.method_10009(Items.field_8425, new MusicDiscDispenserBehaviour());
      DispenserBlock.method_10009(Items.field_8623, new MusicDiscDispenserBehaviour());
      DispenserBlock.method_10009(Items.field_8502, new MusicDiscDispenserBehaviour());
      DispenserBlock.method_10009(Items.field_8534, new MusicDiscDispenserBehaviour());
      DispenserBlock.method_10009(Items.field_8344, new MusicDiscDispenserBehaviour());
      DispenserBlock.method_10009(Items.field_23984, new MusicDiscDispenserBehaviour());
      DispenserBlock.method_10009(Items.field_8834, new MusicDiscDispenserBehaviour());
      DispenserBlock.method_10009(Items.field_8065, new MusicDiscDispenserBehaviour());
      DispenserBlock.method_10009(Items.field_8806, new MusicDiscDispenserBehaviour());
      DispenserBlock.method_10009(Items.field_8355, new MusicDiscDispenserBehaviour());
   }
}
