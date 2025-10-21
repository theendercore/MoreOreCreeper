package org.teamvoided.more_ore_creeper.data.gen.prov

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biomes
import org.teamvoided.more_ore_creeper.data.tags.MOCBiomeTags
import java.util.concurrent.CompletableFuture

class BiomeTagsProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider<Biome>(o, Registries.BIOME, r) {
    override fun addTags(arg: HolderLookup.Provider) {
        getOrCreateTagBuilder(MOCBiomeTags.VOID).add(Biomes.THE_VOID)
    }
}