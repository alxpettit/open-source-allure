package net.Shad0w.Allure.Entity.CrabEntity;

import java.util.EnumSet;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.Goal.Control;

public class RaveGoal extends Goal {
   private final CrabEntity crab;

   public RaveGoal(CrabEntity crab) {
      this.crab = crab;
      this.method_6265(EnumSet.of(Control.field_18405, Control.field_18407));
   }

   public boolean method_6264() {
      return this.crab.isRaving();
   }

   public boolean method_6266() {
      return this.crab.isRaving();
   }

   public void method_6269() {
      this.crab.method_5942().method_6340();
   }
}
