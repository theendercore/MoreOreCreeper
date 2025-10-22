package org.teamvoided.more_ore_creeper.data.gen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions
import net.minecraft.core.HolderLookup
import net.minecraft.core.HolderLookup.RegistryLookup
import net.minecraft.core.RegistrySetBuilder
import org.teamvoided.more_ore_creeper.MoreOreCreeper.MODID
import org.teamvoided.more_ore_creeper.MoreOreCreeper.log
import org.teamvoided.more_ore_creeper.data.gen.prov.BiomeTagsProvider
import org.teamvoided.more_ore_creeper.data.gen.prov.BlockTagsProvider
import org.teamvoided.more_ore_creeper.data.gen.prov.EnglishTranslationProvider
import org.teamvoided.more_ore_creeper.data.gen.prov.OreCreeperVariantProv
import org.teamvoided.more_ore_creeper.init.MOCRegistries
import java.util.concurrent.CompletableFuture

@Suppress("unused")
object MoreOreCreeperData : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        val pack = gen.createPack()

        pack.addProvider(::BlockTagsProvider)
        pack.addProvider(::BiomeTagsProvider)
        pack.addProvider(::DynRegProvider)

        pack.addProvider(::EnglishTranslationProvider)
    }

    override fun buildRegistry(gen: RegistrySetBuilder) {
        gen.add(MOCRegistries.ORE_CREEPER_VARIANT, OreCreeperVariantProv::bootstrap)
    }

    class DynRegProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
        FabricDynamicRegistryProvider(o, r) {
        override fun getName(): String = "more_ore_creepers"
        override fun configure(p: HolderLookup.Provider, e: Entries) {
            e.addAllConditional(p.lookupOrThrow(MOCRegistries.ORE_CREEPER_VARIANT))
        }

        fun <T> Entries.addAllConditional(registry: RegistryLookup<T>) {
            for (key in registry.listElementIds()) {
                if (key.location().namespace == MODID) add(registry, key)
                else add(registry, key, ResourceConditions.allModsLoaded(key.location().namespace))
            }
        }
    }
}
