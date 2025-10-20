package org.teamvoided.more_ore_creeper.init

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.SpawnEggItem
import org.teamvoided.more_ore_creeper.MoreOreCreeper.id
import org.teamvoided.more_ore_creeper.init.MOCEntityTypes.MODDED_ORE_CREEPER

object MOCItems {
    val MODDED_ORE_CREEPER_SPAWN_EGG = Registry.register(
        BuiltInRegistries.ITEM, id("modded_ore_creeper_spawn_egg"),
        SpawnEggItem(MODDED_ORE_CREEPER, 0, 0, Item.Properties())
    )

    fun init() {
        ItemGroupEvents.modifyEntriesEvent(KEY).register {
//            it.context.holders.lookup()
            it.accept(MODDED_ORE_CREEPER_SPAWN_EGG.defaultInstance)
        }
    }

    val KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB, id("ore_creeper", "ore_creeper"))
}
