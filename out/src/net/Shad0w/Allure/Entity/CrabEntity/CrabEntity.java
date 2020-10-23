package net.Shad0w.Allure.Entity.CrabEntity;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.Shad0w.Allure.Init.AllureEntities;
import net.Shad0w.Allure.Init.AllureSounds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.TargetFinder;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.DefaultAttributeContainer.Builder;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class CrabEntity extends AnimalEntity {
   public static final Identifier ID = new Identifier("allure", "crab");
   private static final TrackedData SIZE_MODIFIER;
   private static final TrackedData VARIANT;
   private boolean crabRave;
   private BlockPos jukeboxPosition;

   protected void method_5959() {
      this.field_6201.method_6277(0, new RaveGoal(this));
      this.field_6201.method_6277(1, new EscapeDangerGoal(this, 2.0D));
      this.field_6201.method_6277(2, new AnimalMateGoal(this, 1.0D));
      this.field_6201.method_6277(3, new TemptGoal(this, 1.25D, this.getTemptationItems(), false));
      this.field_6201.method_6277(4, new FollowParentGoal(this, 1.25D));
      this.field_6201.method_6277(5, new CrabEntity.WanderAroundFarGoal(this, 1.0D));
      this.field_6201.method_6277(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
      this.field_6201.method_6277(7, new LookAroundGoal(this));
   }

   public static Builder createCrabAttributes() {
      return MobEntity.method_26828().method_26868(EntityAttributes.field_23716, 20.0D).method_26868(EntityAttributes.field_23719, 0.25D).method_26868(EntityAttributes.field_23724, 3.0D).method_26868(EntityAttributes.field_23725, 2.0D).method_26868(EntityAttributes.field_23718, 0.5D);
   }

   public CrabEntity(EntityType entityType, World world) {
      this(entityType, world, world.field_9229.nextFloat() + world.field_9229.nextFloat() + 0.25F);
   }

   public CrabEntity(EntityType type, World worldIn, float sizeModifier) {
      super(type, worldIn);
      this.method_5941(PathNodeType.field_14, -1.0F);
      this.method_5941(PathNodeType.field_18, 0.0F);
      if (sizeModifier != 1.0F) {
         this.field_6011.method_12778(SIZE_MODIFIER, sizeModifier);
      }

   }

   public void method_6091(Vec3d movementInput) {
      if (this.method_6034() && this.method_5799()) {
         this.method_5724(this.method_6029(), movementInput);
         this.method_5784(MovementType.field_6308, this.method_18798());
         this.method_18799(this.method_18798().method_1021(0.9D));
         if (this.method_5968() == null) {
            this.method_18799(this.method_18798().method_1031(0.0D, -0.005D, 0.0D));
         }
      } else {
         super.method_6091(movementInput);
      }

   }

   public static void rave(World world, BlockPos pos, boolean raving) {
      Iterator var3 = world.method_18467(CrabEntity.class, (new Box(pos)).method_1014(3.0D)).iterator();

      while(var3.hasNext()) {
         CrabEntity crab = (CrabEntity)var3.next();
         crab.party(pos, raving);
      }

   }

   public float method_6144(BlockPos pos, WorldView world) {
      return world.method_8320(pos.method_10074()).method_26204() == Blocks.field_10102 ? 10.0F : world.method_22349(pos) - 0.5F;
   }

   public EntityGroup method_6046() {
      return EntityGroup.field_6293;
   }

   protected SoundEvent method_5994() {
      return AllureSounds.ENTITY_CRAB_IDLE;
   }

   protected SoundEvent method_6002() {
      return AllureSounds.ENTITY_CRAB_DIE;
   }

   protected SoundEvent method_6011(DamageSource source) {
      return AllureSounds.ENTITY_CRAB_HURT;
   }

   public float getSizeModifier() {
      return (Float)this.field_6011.method_12789(SIZE_MODIFIER);
   }

   public void method_5773() {
      super.method_5773();
      if (!this.field_6002.method_8608() && (Integer)this.field_6011.method_12789(VARIANT) == -1) {
         int variant = 0;
         if (this.field_5974.nextBoolean()) {
            variant += this.field_5974.nextInt(2) + 1;
         }

         this.field_6011.method_12778(VARIANT, variant);
      }

      if (this.method_5869()) {
         this.field_6013 = 1.0F;
      } else {
         this.field_6013 = 0.6F;
      }

      Vec3d pos = this.method_19538();
      if (this.isRaving() && (this.jukeboxPosition == null || this.jukeboxPosition.method_10268(pos.field_1352, pos.field_1351, pos.field_1350, true) > 24.0D || this.field_6002.method_8320(this.jukeboxPosition).method_26204() != Blocks.field_10223)) {
         this.party((BlockPos)null, false);
      }

      if (this.isRaving() && this.field_6002.method_8608() && this.field_6012 % 10 == 0) {
         BlockPos below = this.method_24515().method_10074();
         BlockState belowState = this.field_6002.method_8320(below);
         if (belowState.method_26207() == Material.field_15916) {
            this.field_6002.method_8474(2001, below, Block.method_9507(belowState));
         }
      }

   }

   public EntityDimensions method_18377(EntityPose pose) {
      return super.method_18377(pose).method_18383(this.getSizeModifier());
   }

   public boolean method_6094() {
      return true;
   }

   public void method_5800(ServerWorld serverWorld, LightningEntity lightningEntity) {
      super.method_5800(serverWorld, lightningEntity);
   }

   public void method_5697(Entity entity) {
      if (this.getSizeModifier() <= 1.0F) {
         super.method_5697(entity);
      }

      if (this.field_6002.method_8407() != Difficulty.field_5801 && entity instanceof LivingEntity && !(entity instanceof CrabEntity)) {
         entity.method_5643(DamageSource.field_5848, 1.0F);
      }

   }

   private Ingredient getTemptationItems() {
      Stream lol = Stream.of(new ItemStack(Items.field_8861), new ItemStack(Items.field_8726));
      List list = (List)lol.collect(Collectors.toList());
      Iterator var2 = ItemTags.field_15527.method_15138().iterator();

      while(var2.hasNext()) {
         Item item = (Item)var2.next();
         list.add(new ItemStack(item));
      }

      return Ingredient.method_26964(list.stream());
   }

   public boolean method_6481(ItemStack stack) {
      return this.getTemptationItems().method_8093(stack);
   }

   protected float method_18394(EntityPose pose, EntityDimensions dimensions) {
      return 0.2F * dimensions.field_18068;
   }

   public PassiveEntity method_5613(ServerWorld serverWorld, PassiveEntity passiveEntity) {
      return (PassiveEntity)AllureEntities.CRAB.method_5883(this.field_6002);
   }

   public int getVariant() {
      return Math.max(0, (Integer)this.field_6011.method_12789(VARIANT));
   }

   public void party(BlockPos pos, boolean isPartying) {
      this.jukeboxPosition = pos;
      this.crabRave = isPartying;
   }

   @Environment(EnvType.CLIENT)
   public void setPartying(BlockPos pos, boolean isPartying) {
      this.party(pos, isPartying);
   }

   public boolean isRaving() {
      return this.crabRave;
   }

   protected void method_5693() {
      super.method_5693();
      this.field_6011.method_12784(SIZE_MODIFIER, 1.0F);
      this.field_6011.method_12784(VARIANT, -1);
   }

   public void method_5674(TrackedData data) {
      if (data.equals(SIZE_MODIFIER)) {
         this.method_18382();
      }

      super.method_5674(data);
   }

   public void method_5652(CompoundTag tag) {
      super.method_5652(tag);
      tag.method_10548("EnemyCrabRating", this.getSizeModifier());
      tag.method_10569("Variant", (Integer)this.field_6011.method_12789(VARIANT));
   }

   public void method_5749(CompoundTag tag) {
      super.method_5749(tag);
      if (tag.method_10545("EnemyCrabRating")) {
         float sizeModifier = tag.method_10583("EnemyCrabRating");
         this.field_6011.method_12778(SIZE_MODIFIER, sizeModifier);
      }

      if (tag.method_10545("Variant")) {
         this.field_6011.method_12778(VARIANT, tag.method_10550("Variant"));
      }

   }

   static {
      SIZE_MODIFIER = DataTracker.method_12791(CrabEntity.class, TrackedDataHandlerRegistry.field_13320);
      VARIANT = DataTracker.method_12791(CrabEntity.class, TrackedDataHandlerRegistry.field_13327);
   }

   class WanderAroundFarGoal extends WanderAroundGoal {
      protected final float probability;

      public WanderAroundFarGoal(PathAwareEntity pathAwareEntity, double d) {
         this(pathAwareEntity, d, 0.001F);
      }

      public WanderAroundFarGoal(PathAwareEntity mob, double speed, float probability) {
         super(mob, speed);
         this.probability = probability;
      }

      protected Vec3d method_6302() {
         return this.field_6566.method_6051().nextFloat() >= this.probability ? TargetFinder.method_6375(this.field_6566, 10, 7) : super.method_6302();
      }
   }
}
