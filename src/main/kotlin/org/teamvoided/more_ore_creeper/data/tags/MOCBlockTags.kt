package org.teamvoided.more_ore_creeper.data.tags

import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block
import org.teamvoided.more_ore_creeper.MoreOreCreeper.id


object MOCBlockTags {
    // Overworld
    val OVERWORLD_ORE_REPLACEABLE = create("overworld_ore_replaceable")
    val STONE_ORE_REPLACEABLE = create("stone_ore_replaceable")
    val DEEPSLATE_ORE_REPLACEABLE = create("deepslate_ore_replaceable")

    // Nether
    val NETHER_ORE_REPLACEABLE = create("nether_ore_replaceable")
    val NETHERRACK_ORE_REPLACEABLE = create("netherrack_ore_replaceable")
    val BLACKSTONE_ORE_REPLACEABLE = create("blackstone_ore_replaceable")

    // End
    val END_ORE_REPLACEABLE = create("end_ore_replaceable")
    val ENDSTONE_ORE_REPLACEABLE = create("endstone_ore_replaceable")


    fun create(id: String): TagKey<Block> = TagKey.create(Registries.BLOCK, id(id))
}