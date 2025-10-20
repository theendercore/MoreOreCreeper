package org.teamvoided.more_ore_creeper.data

import net.minecraft.resources.ResourceKey
import org.teamvoided.more_ore_creeper.MoreOreCreeper.id
import org.teamvoided.more_ore_creeper.entity.OreCreeperVariant
import org.teamvoided.more_ore_creeper.init.MOCRegistries.ORE_CREEPER_VARIANT

object OreCreeperVariants {
    val DEFAULT = create("default")


    val EVIL = create("evil")

    fun create(path: String): ResourceKey<OreCreeperVariant> = ResourceKey.create(ORE_CREEPER_VARIANT, id(path))
}