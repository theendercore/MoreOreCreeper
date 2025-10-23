package org.teamvoided.more_ore_creeper.init

import net.fabricmc.fabric.api.event.registry.DynamicRegistries
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import org.teamvoided.more_ore_creeper.MoreOreCreeper.id
import org.teamvoided.more_ore_creeper.entity.OreCreeperVariant

object MOCRegistries {
    val ORE_CREEPER_VARIANT = createRegistryKey<OreCreeperVariant>("ore_creeper_variant")

    fun init() {
        DynamicRegistries.registerSynced(ORE_CREEPER_VARIANT, OreCreeperVariant.CODEC)
    }

    @Suppress("SameParameterValue")
    private fun <T> createRegistryKey(id: String): ResourceKey<Registry<T>> = ResourceKey.createRegistryKey<T>(id(id))
}
