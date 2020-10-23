package net.Shad0w.Allure.Entity.CrabEntity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class CrabEntityRenderer extends MobEntityRenderer {
   private static final Identifier[] TEXTURES = new Identifier[]{new Identifier("allure", "textures/entity/crab/red.png"), new Identifier("allure", "textures/entity/crab/blue.png"), new Identifier("allure", "textures/entity/crab/green.png")};

   public CrabEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
      super(entityRenderDispatcher, new CrabEntityModel(), 0.4F);
   }

   public Identifier getTexture(CrabEntity entity) {
      return TEXTURES[Math.min(TEXTURES.length - 1, entity.getVariant())];
   }
}
