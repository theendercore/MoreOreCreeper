package org.teamvoided.more_ore_creeper.data.tags

import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome
import org.teamvoided.more_ore_creeper.MoreOreCreeper.id


object MOCBiomeTags {
    val VOID = create("void")

    fun create(id: String): TagKey<Biome> = TagKey.create(Registries.BIOME, id(id))

}