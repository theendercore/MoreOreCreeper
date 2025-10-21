package org.teamvoided.more_ore_creeper.init

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item
import org.teamvoided.more_ore_creeper.MoreOreCreeper.id
import org.teamvoided.more_ore_creeper.init.MOCEntityTypes.MODDED_ORE_CREEPER
import org.teamvoided.more_ore_creeper.init.MOCRegistries.ORE_CREEPER_VARIANT
import org.teamvoided.more_ore_creeper.item.CustomSpawnEggItem

object MOCItems {
    val MODDED_ORE_CREEPER_SPAWN_EGG = Registry.register(
        BuiltInRegistries.ITEM, id("modded_ore_creeper_spawn_egg"),
        CustomSpawnEggItem(MODDED_ORE_CREEPER, 0, -1, Item.Properties())
    )

    fun init() {
        ItemGroupEvents.modifyEntriesEvent(KEY).register {
            it.context.holders.lookup(ORE_CREEPER_VARIANT).ifPresent { lookup ->
                lookup.listElements().map(CustomSpawnEggItem::createEgg).forEach(it::accept)
            }
        }
    }

    val KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB, id("ore_creeper", "ore_creeper"))
}
