package net.Shad0w.Allure.Blocks.Chute;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class ChuteBlockScreen extends CottonInventoryScreen {
   public ChuteBlockScreen(ChuteBlockController gui, PlayerEntity player, Text title) {
      super(gui, player, title);
   }
}
