package org.teamvoided.more_ore_creeper.misc

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.storage.loot.LootTable
import org.teamvoided.more_ore_creeper.MoreOreCreeper.MODID
import org.teamvoided.more_ore_creeper.MoreOreCreeper.id
import org.teamvoided.more_ore_creeper.entity.OreCreeperVariant

fun customLootTable(variant: ResourceKey<OreCreeperVariant>): ResourceKey<LootTable> {
    return ResourceKey.create(
        Registries.LOOT_TABLE,
        id(variant.location().toString().replace(":", "/")).withPrefix("entity/$MODID/")
    )
}