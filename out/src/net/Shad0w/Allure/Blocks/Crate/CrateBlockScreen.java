package net.Shad0w.Allure.Blocks.Crate;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class CrateBlockScreen extends CottonInventoryScreen {
   public CrateBlockScreen(CrateBlockController gui, PlayerEntity player, Text title) {
      super(gui, player, title);
   }
}
