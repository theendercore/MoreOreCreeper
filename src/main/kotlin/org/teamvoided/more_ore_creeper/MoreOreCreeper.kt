package org.teamvoided.more_ore_creeper

import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import net.minecraft.resources.ResourceLocation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.more_ore_creeper.config.MOCConfig
import org.teamvoided.more_ore_creeper.init.*

@Suppress("unused")
object MoreOreCreeper {
    const val MODID = "more_ore_creeper"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(MoreOreCreeper::class.simpleName)

    @JvmField
    var config = ConfigApi.registerAndLoadConfig(::MOCConfig)

    fun init() {
        MOCParticleTypes.init()
        MOCRegistries.init()
        MOCAttachmentTypes.init()
        MOCDataComponents.init()
        MOCEntityTypes.init()
        MOCItems.init()

    }

    fun id(namespace: String, path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(namespace, path)
    fun id(path: String) = id(MODID, path)
}
