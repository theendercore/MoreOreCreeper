package org.teamvoided.template.client

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry.getInstance as ParticlesRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import org.teamvoided.template.Template
import org.teamvoided.template.client.entity.ModdedOreCreeperRenderer
import org.teamvoided.template.client.particle.ColoredExplosionParticle
import org.teamvoided.template.init.MOCEntityTypes
import org.teamvoided.template.init.MOCParticleTypes

@Suppress("unused")
object TemplateClient {
    fun init() {
        Template.log.info("Hello from Client")
        ParticlesRegistry().register(MOCParticleTypes.COLORED_EXPLOSION, ColoredExplosionParticle::Factory)
//        ParticlesRegistry().register(MOCParticleTypes.DUAL_COLOR_EXPLOSION_EMITTER, DualExplosionParticle::Factory)
        EntityRendererRegistry.register(MOCEntityTypes.MODDED_ORE_CREEPER, ::ModdedOreCreeperRenderer)

    }
}