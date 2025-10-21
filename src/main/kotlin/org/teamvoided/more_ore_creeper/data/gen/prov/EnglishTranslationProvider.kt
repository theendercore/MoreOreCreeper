package org.teamvoided.more_ore_creeper.data.gen.prov

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceLocation
import org.teamvoided.more_ore_creeper.MoreOreCreeper.MODID
import org.teamvoided.more_ore_creeper.data.OreCreeperVariants
import org.teamvoided.more_ore_creeper.init.MOCEntityTypes
import org.teamvoided.more_ore_creeper.init.MOCItems.EGG
import java.util.concurrent.CompletableFuture

@Suppress("MemberVisibilityCanBePrivate")
class EnglishTranslationProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricLanguageProvider(o, r) {

    override fun generateTranslations(lookup: HolderLookup.Provider, gen: TranslationBuilder) {
        for (key in OreCreeperVariants.VARIANTS) {
            val id = key.location()
            val lang = id.toString().replace(":", ".")

            gen.add("item.$MODID.$EGG.$lang", genLang(id) + " Creper Spawn Egg")
            gen.add("entity.$MODID.$lang", genLang(id) + " Creper")
        }
        gen.add(MOCEntityTypes.MODDED_ORE_CREEPER, genLang(MOCEntityTypes.ID))
    }

    private fun genLang(id: ResourceLocation): String =
        id.path.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }
}