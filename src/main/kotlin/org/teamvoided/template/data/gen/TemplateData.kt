package org.teamvoided.template.data.gen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistrySetBuilder
import org.teamvoided.template.Template.log
import org.teamvoided.template.data.gen.prov.OreCreeperVariantProv
import org.teamvoided.template.init.MOCRegistries
import java.util.concurrent.CompletableFuture

@Suppress("unused")
object TemplateData : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        log.info("Hello from DataGen")
        val pack = gen.createPack()

        pack.addProvider(::DynRegProvider)
    }

    override fun buildRegistry(gen: RegistrySetBuilder) {
        gen.add(MOCRegistries.ORE_CREEPER_VARIANT, OreCreeperVariantProv::bootstrap)
    }

    class DynRegProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
        FabricDynamicRegistryProvider(o, r) {
        override fun getName(): String = "more_ore_creepers"
        override fun configure(p: HolderLookup.Provider, e: Entries) {
            e.addAll(p.lookupOrThrow(MOCRegistries.ORE_CREEPER_VARIANT))
        }
    }
}
