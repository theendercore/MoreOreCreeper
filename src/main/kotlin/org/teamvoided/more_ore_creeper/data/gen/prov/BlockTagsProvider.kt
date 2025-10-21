package org.teamvoided.more_ore_creeper.data.gen.prov

import net.bunten.enderscape.registry.tag.EnderscapeBlockTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.BlockTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.Blocks
import org.teamvoided.more_ore_creeper.data.tags.MOCBlockTags
import org.teamvoided.more_ore_creeper.data.tags.MOCBlockTags.OVERWORLD_ORE_REPLACEABLE
import java.util.concurrent.CompletableFuture

class BlockTagsProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) : BlockTagProvider(o, r) {
    override fun addTags(arg: HolderLookup.Provider) {
        // Overworld
        getOrCreateTagBuilder(OVERWORLD_ORE_REPLACEABLE)
            .addTag(MOCBlockTags.STONE_ORE_REPLACEABLE)
            .addTag(MOCBlockTags.DEEPSLATE_ORE_REPLACEABLE)

        getOrCreateTagBuilder(MOCBlockTags.STONE_ORE_REPLACEABLE)
            .forceAddTag(BlockTags.STONE_ORE_REPLACEABLES)
            .add(Blocks.GRAVEL, Blocks.CLAY, Blocks.DRIPSTONE_BLOCK, Blocks.CALCITE)
        getOrCreateTagBuilder(MOCBlockTags.DEEPSLATE_ORE_REPLACEABLE)
            .forceAddTag(BlockTags.DEEPSLATE_ORE_REPLACEABLES)

        // Nether
        getOrCreateTagBuilder(MOCBlockTags.NETHER_ORE_REPLACEABLE)
            .addTag(MOCBlockTags.NETHERRACK_ORE_REPLACEABLE)
            .addTag(MOCBlockTags.BLACKSTONE_ORE_REPLACEABLE)
        getOrCreateTagBuilder(MOCBlockTags.NETHERRACK_ORE_REPLACEABLE)
            .add(Blocks.NETHERRACK)
        getOrCreateTagBuilder(MOCBlockTags.BLACKSTONE_ORE_REPLACEABLE)
            .add(Blocks.BLACKSTONE)

        // End
        getOrCreateTagBuilder(MOCBlockTags.END_ORE_REPLACEABLE)
            .addTag(MOCBlockTags.ENDSTONE_ORE_REPLACEABLE)
        getOrCreateTagBuilder(MOCBlockTags.ENDSTONE_ORE_REPLACEABLE)
            .add(Blocks.END_STONE)
    }
}