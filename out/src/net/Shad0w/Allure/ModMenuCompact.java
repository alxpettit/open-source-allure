package net.Shad0w.Allure;

import io.github.prospector.modmenu.api.ModMenuApi;
import java.util.function.Function;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;

@Environment(EnvType.CLIENT)
public class ModMenuCompact implements ModMenuApi {
   public String getModId() {
      return "allure";
   }

   public Function getConfigScreenFactory() {
      return (screen) -> {
         return (Screen)AutoConfig.getConfigScreen(ModConfig.class, screen).get();
      };
   }
}
