package net.Shad0w.Allure.mixin;

import java.util.BitSet;
import java.util.Random;
import java.util.UUID;
import net.Shad0w.Allure.Init.AllureBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.World;
import net.minecraft.world.Heightmap.Type;
import net.minecraft.world.gen.feature.EndPortalFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EnderDragonFight.class})
public abstract class EnderDragonFightMixin {
   @Shadow
   private UUID field_13116;
   @Shadow
   @Final
   private ServerWorld field_13108;

   @Inject(
      method = {"dragonKilled"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void dragonKilled(EnderDragonEntity dragon, CallbackInfo ci) {
      if (dragon.method_5667().equals(this.field_13116)) {
         BlockPos blockPos = this.field_13108.method_8598(Type.field_13202, EndPortalFeature.field_13600);
         float f = 140.0F;

         for(int i = 0; f > 0.5F; --i) {
            for(int j = MathHelper.method_15375(-f); j <= MathHelper.method_15386(f); ++j) {
               for(int k = MathHelper.method_15375(-f); k <= MathHelper.method_15386(f); ++k) {
                  if ((float)(j * j + k * k) <= (f + 1.0F) * (f + 1.0F)) {
                     BlockPos jeff = blockPos.method_10069(j, i, k);
                     if (this.field_13108.method_8320(jeff).method_26204().equals(Blocks.field_10471) && this.field_13108.field_9229.nextInt(225) == 1) {
                        this.generate(this.field_13108, this.field_13108.field_9229, jeff, this.field_13108.field_9229.nextInt(6) + 1);
                     }
                  }
               }
            }

            f = (float)((double)f - ((double)this.field_13108.field_9229.nextInt(2) + 0.5D));
         }
      }

   }

   public void generate(World world, Random random, BlockPos blockPos, int oreSize) {
      float f = random.nextFloat() * 3.1415927F;
      float g = (float)oreSize / 8.0F;
      int i = MathHelper.method_15386(((float)oreSize / 16.0F * 2.0F + 1.0F) / 2.0F);
      double d = (double)((float)blockPos.method_10263() + MathHelper.method_15374(f) * g);
      double e = (double)((float)blockPos.method_10263() - MathHelper.method_15374(f) * g);
      double h = (double)((float)blockPos.method_10260() + MathHelper.method_15362(f) * g);
      double j = (double)((float)blockPos.method_10260() - MathHelper.method_15362(f) * g);
      double l = (double)(blockPos.method_10264() + random.nextInt(3) - 2);
      double m = (double)(blockPos.method_10264() + random.nextInt(3) - 2);
      int n = blockPos.method_10263() - MathHelper.method_15386(g) - i;
      int o = blockPos.method_10264() - 2 - i;
      int p = blockPos.method_10260() - MathHelper.method_15386(g) - i;
      int q = 2 * (MathHelper.method_15386(g) + i);
      int r = 2 * (2 + i);

      for(int s = n; s <= n + q; ++s) {
         for(int t = p; t <= p + q; ++t) {
            this.generateVeinPart(world, random, d, e, h, j, l, m, n, o, p, q, r, oreSize);
         }
      }

   }

   protected void generateVeinPart(World world, Random random, double startX, double endX, double startZ, double endZ, double startY, double endY, int x, int y, int z, int size, int i, int oreSize) {
      int j = 0;
      BitSet bitSet = new BitSet(size * i * size);
      Mutable mutable = new Mutable();
      double[] ds = new double[oreSize * 4];

      int m;
      double o;
      double p;
      double q;
      double r;
      for(m = 0; m < oreSize; ++m) {
         float f = (float)m / (float)oreSize;
         o = MathHelper.method_16436((double)f, startX, endX);
         p = MathHelper.method_16436((double)f, startY, endY);
         q = MathHelper.method_16436((double)f, startZ, endZ);
         r = random.nextDouble() * (double)oreSize / 16.0D;
         double l = ((double)(MathHelper.method_15374(3.1415927F * f) + 1.0F) * r + 1.0D) / 2.0D;
         ds[m * 4 + 0] = o;
         ds[m * 4 + 1] = p;
         ds[m * 4 + 2] = q;
         ds[m * 4 + 3] = l;
      }

      for(m = 0; m < oreSize - 1; ++m) {
         if (ds[m * 4 + 3] > 0.0D) {
            for(int n = m + 1; n < oreSize; ++n) {
               if (ds[n * 4 + 3] > 0.0D) {
                  o = ds[m * 4 + 0] - ds[n * 4 + 0];
                  p = ds[m * 4 + 1] - ds[n * 4 + 1];
                  q = ds[m * 4 + 2] - ds[n * 4 + 2];
                  r = ds[m * 4 + 3] - ds[n * 4 + 3];
                  if (r * r > o * o + p * p + q * q) {
                     if (r > 0.0D) {
                        ds[n * 4 + 3] = -1.0D;
                     } else {
                        ds[m * 4 + 3] = -1.0D;
                     }
                  }
               }
            }
         }
      }

      for(m = 0; m < oreSize; ++m) {
         double t = ds[m * 4 + 3];
         if (t >= 0.0D) {
            double u = ds[m * 4 + 0];
            double v = ds[m * 4 + 1];
            double w = ds[m * 4 + 2];
            int aa = Math.max(MathHelper.method_15357(u - t), x);
            int ab = Math.max(MathHelper.method_15357(v - t), y);
            int ac = Math.max(MathHelper.method_15357(w - t), z);
            int ad = Math.max(MathHelper.method_15357(u + t), aa);
            int ae = Math.max(MathHelper.method_15357(v + t), ab);
            int af = Math.max(MathHelper.method_15357(w + t), ac);

            for(int ag = aa; ag <= ad; ++ag) {
               double ah = ((double)ag + 0.5D - u) / t;
               if (ah * ah < 1.0D) {
                  for(int ai = ab; ai <= ae; ++ai) {
                     double aj = ((double)ai + 0.5D - v) / t;
                     if (ah * ah + aj * aj < 1.0D) {
                        for(int ak = ac; ak <= af; ++ak) {
                           double al = ((double)ak + 0.5D - w) / t;
                           if (ah * ah + aj * aj + al * al < 1.0D) {
                              int am = ag - x + (ai - y) * size + (ak - z) * size * i;
                              if (!bitSet.get(am)) {
                                 bitSet.set(am);
                                 mutable.method_10103(ag, ai, ak);
                                 if (world.method_8320(mutable).method_26204().equals(Blocks.field_10471)) {
                                    world.method_8501(mutable, AllureBlocks.BIOTITE_ORE.method_9564());
                                    ++j;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

   }
}
