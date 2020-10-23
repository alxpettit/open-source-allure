package net.Shad0w.Allure.Utils.Blocks;

import java.util.Random;
import net.minecraft.block.OreBlock;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.util.math.MathHelper;

public class CustomOre extends OreBlock {
   private int xpMin = 0;
   private int xpMax = 0;

   public CustomOre(Settings settings, int xpMin, int xpMax) {
      super(settings);
      this.xpMax = xpMax;
      this.xpMin = xpMin;
   }

   protected int method_10398(Random random_1) {
      return MathHelper.method_15395(random_1, this.xpMin, this.xpMax);
   }
}
