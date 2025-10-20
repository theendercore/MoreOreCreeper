package org.teamvoided.template

import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import net.minecraft.resources.ResourceLocation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.template.config.TemplateConfig
import org.teamvoided.template.init.MOCEntityTypes
import org.teamvoided.template.init.MOCParticleTypes
import org.teamvoided.template.init.MOCRegistries

@Suppress("unused")
object Template {
    const val MODID = "more_ore_creeper"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(Template::class.simpleName)

    @JvmField
    var config = ConfigApi.registerAndLoadConfig(::TemplateConfig)

    fun init() {
        log.info("Hello from Common")
        MOCParticleTypes.init()
        MOCEntityTypes.init()
        MOCRegistries.init()
    }

    fun id(namespace: String, path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(namespace, path)
    fun id(path: String) = id(MODID, path)
}
