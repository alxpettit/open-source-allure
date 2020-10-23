package net.Shad0w.Allure.Init;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;

public class AllureLootTables {
   private static final Identifier ILLUSIONER_LOOT_TABLE_ID = new Identifier("minecraft", "entities/illusioner");
   private static final Identifier ABANDONED_MINESHAFT_LOOT_TABLE_ID = new Identifier("minecraft", "chests/abandoned_mineshaft");
   private static final Identifier NETHER_BRIDGE_LOOT_TABLE_ID = new Identifier("minecraft", "chests/nether_bridge");
   private static final Identifier WOODLAND_MANSION_LOOT_TABLE_ID = new Identifier("minecraft", "chests/woodland_mansion");

   public static void initialize() {
      LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
         if (ILLUSIONER_LOOT_TABLE_ID.equals(id) || ABANDONED_MINESHAFT_LOOT_TABLE_ID.equals(id) || NETHER_BRIDGE_LOOT_TABLE_ID.equals(id) || WOODLAND_MANSION_LOOT_TABLE_ID.equals(id)) {
            FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder().rolls(ConstantLootTableRange.method_289(1)).withCondition(RandomChanceLootCondition.method_932(0.5F).build()).withEntry(ItemEntry.method_411(AllureItems.TOTEM_OF_RETURNING).method_419());
            supplier.withPool(poolBuilder.method_355());
         }

      });
   }
}
