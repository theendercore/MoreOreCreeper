package org.teamvoided.template.data

import net.minecraft.resources.ResourceKey
import org.teamvoided.template.Template.id
import org.teamvoided.template.entity.OreCreeperVariant
import org.teamvoided.template.init.MOCRegistries.ORE_CREEPER_VARIANT

object OreCreeperVariants {
    val DEFAULT = create("default")


    val EVIL = create("evil")

    fun create(path: String): ResourceKey<OreCreeperVariant> = ResourceKey.create(ORE_CREEPER_VARIANT, id(path))
}