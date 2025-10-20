package org.teamvoided.more_ore_creeper.client

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry.getInstance as ParticlesRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import org.teamvoided.more_ore_creeper.MoreOreCreeper
import org.teamvoided.more_ore_creeper.client.entity.ModdedOreCreeperRenderer
import org.teamvoided.more_ore_creeper.client.particle.ColoredExplosionParticle
import org.teamvoided.more_ore_creeper.init.MOCEntityTypes
import org.teamvoided.more_ore_creeper.init.MOCParticleTypes

@Suppress("unused")
object MoreOreCreeperClient {
    fun init() {
        MoreOreCreeper.log.info("Hello from Client")
        ParticlesRegistry().register(MOCParticleTypes.COLORED_EXPLOSION, ColoredExplosionParticle::Factory)
//        ParticlesRegistry().register(MOCParticleTypes.DUAL_COLOR_EXPLOSION_EMITTER, DualExplosionParticle::Factory)
        EntityRendererRegistry.register(MOCEntityTypes.MODDED_ORE_CREEPER, ::ModdedOreCreeperRenderer)

    }
}