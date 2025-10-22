package org.teamvoided.more_ore_creeper.data.gen.prov

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions
import net.mehvahdjukaar.randomium.Randomium
import net.minecraft.advancements.critereon.*
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.EnchantmentTags
import net.minecraft.world.item.Item
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import org.teamvoided.more_ore_creeper.MoreOreCreeper.MODID
import org.teamvoided.more_ore_creeper.data.OreCreeperVariants
import org.teamvoided.more_ore_creeper.entity.OreCreeperVariant
import org.teamvoided.more_ore_creeper.misc.customLootTable
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer

class EntityLootTableProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    SimpleFabricLootTableProvider(o, r, LootContextParamSets.CHEST) {
    val registries: HolderLookup.Provider = r.get()
    override fun generate(gen: BiConsumer<ResourceKey<LootTable>, LootTable.Builder>) = gen.niceGen()
    fun BiConsumer<ResourceKey<LootTable>, LootTable.Builder>.niceGen() {
        creeperDrops(
            OreCreeperVariants.RANDOMIUM,
            Randomium.RANDOMIUM_ORE.get().asItem(), 12, 4,
            Randomium.RANDOMIUM_ITEM.get(), 2, 0.2f
        )
    }

    fun BiConsumer<ResourceKey<LootTable>, LootTable.Builder>.creeperDrops(
        variant: ResourceKey<OreCreeperVariant>,
        item: Item, max: Int, lootMax: Int,
        block: Item, blockMax: Int, blockChance: Float,
    ) {
        getConditional(variant).accept(
            customLootTable(variant),
            LootTable.lootTable()
                .pool(
                    LootPool.lootPool().setRolls(ConstantValue.exactly(1f)).add(
                        LootItem.lootTableItem(item)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, max.toFloat())))
                            .apply(SmeltItemFunction.smelted().`when`(shouldSmeltLoot()))
                            .apply(
                                EnchantedCountIncreaseFunction.lootingMultiplier(
                                    registries, UniformGenerator.between(0f, lootMax.toFloat())
                                )
                            )
                    ).build()
                )
                .pool(
                    LootPool.lootPool().setRolls(ConstantValue.exactly(1f)).add(
                        LootItem.lootTableItem(block)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0f, blockMax.toFloat())))
                            .conditionally(LootItemRandomChanceCondition.randomChance(blockChance))
                    ).build()
                )
        )
    }


    fun shouldSmeltLoot(): AnyOfCondition.Builder {
        val enchant = registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentTags.SMELTS_LOOT)
        return AnyOfCondition.anyOf(
            LootItemEntityPropertyCondition.hasProperties(
                LootContext.EntityTarget.THIS,
                EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true))
            ),
            LootItemEntityPropertyCondition.hasProperties(
                LootContext.EntityTarget.DIRECT_ATTACKER, EntityPredicate.Builder.entity().equipment(
                    EntityEquipmentPredicate.Builder.equipment().mainhand(
                        ItemPredicate.Builder.item().withSubPredicate(
                            ItemSubPredicates.ENCHANTMENTS, ItemEnchantmentsPredicate.enchantments(
                                listOf(EnchantmentPredicate(enchant, MinMaxBounds.Ints.ANY))
                            )
                        )
                    )
                )
            )
        )
    }

    fun BiConsumer<ResourceKey<LootTable>, LootTable.Builder>.getConditional(variant: ResourceKey<OreCreeperVariant>): BiConsumer<ResourceKey<LootTable>, LootTable.Builder> {
        if (variant.location().namespace != MODID) {
            return withConditions(this, ResourceConditions.allModsLoaded(variant.location().namespace))
        }
        return this
    }

    fun <T : LootPoolEntryContainer.Builder<T>> LootPoolEntryContainer.Builder<T>.conditionally(builder: LootItemCondition.Builder): T =
        this.`when`(builder)
}


