package org.teamvoided.more_ore_creeper.init

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.core.Registry
import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.nbt.CompoundTag
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.SpawnEggItem
import net.minecraft.world.item.component.CustomData
import org.teamvoided.more_ore_creeper.MoreOreCreeper.id
import org.teamvoided.more_ore_creeper.init.MOCEntityTypes.MODDED_ORE_CREEPER
import org.teamvoided.more_ore_creeper.init.MOCRegistries.ORE_CREEPER_VARIANT

object MOCItems {
    val MODDED_ORE_CREEPER_SPAWN_EGG = Registry.register(
        BuiltInRegistries.ITEM, id("modded_ore_creeper_spawn_egg"),
        SpawnEggItem(MODDED_ORE_CREEPER, 0, 0, Item.Properties())
    )

    fun init() {
        ItemGroupEvents.modifyEntriesEvent(KEY).register {
            it.context.holders.lookup(ORE_CREEPER_VARIANT).ifPresent { lookup ->
                for (key in lookup.listElementIds()) {
                    val stack = MODDED_ORE_CREEPER_SPAWN_EGG.defaultInstance
                    val attachments = CompoundTag()
                    attachments.putString("more_ore_creeper:ore_creeper_variant", key.location().toString())
                    val tag = CompoundTag()
                    tag.put("fabric:attachments", attachments)
                    tag.putString("id", "more_ore_creeper:modded_ore_creeper")

                    stack.set(DataComponents.ENTITY_DATA, CustomData.of(tag))
                    it.accept(stack)
                }
            }
        }
    }

    val KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB, id("ore_creeper", "ore_creeper"))
}
