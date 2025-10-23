package org.teamvoided.more_ore_creeper.data

import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import org.teamvoided.more_ore_creeper.MoreOreCreeper.id
import org.teamvoided.more_ore_creeper.entity.variant.OreCreeperVariant
import org.teamvoided.more_ore_creeper.init.MOCRegistries.ORE_CREEPER_VARIANT

object OreCreeperVariants {
    val VARIANTS = mutableListOf<ResourceKey<OreCreeperVariant>>()
    val MISSING = create("missing")

    val RANDOMIUM = create("randomium", "randomium")

    fun create(path: String) = create(id(path))
    fun create(namespace: String, path: String) = create(id(namespace, path))
    fun create(id: ResourceLocation): ResourceKey<OreCreeperVariant> {
        val key = ResourceKey.create(ORE_CREEPER_VARIANT, id)
        VARIANTS.add(key)
        return key
    }
}