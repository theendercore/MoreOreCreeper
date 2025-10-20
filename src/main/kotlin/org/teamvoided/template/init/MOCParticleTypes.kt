package org.teamvoided.template.init

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.core.Registry
import net.minecraft.core.particles.ColorParticleOption
import net.minecraft.core.registries.BuiltInRegistries
import org.teamvoided.template.Template.id

object MOCParticleTypes {
    val COLORED_EXPLOSION =
        FabricParticleTypes.complex(ColorParticleOption::codec, ColorParticleOption::streamCodec)
//    val DUAL_COLOR_EXPLOSION_EMITTER =
//        FabricParticleTypes.complex(TwoColorParticleOption::codec, TwoColorParticleOption::streamCodec)


    fun init() {
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, id("colored_explosion"), COLORED_EXPLOSION)
//        Registry.register(BuiltInRegistries.PARTICLE_TYPE, id("dual_color_explosion_emitter"), DUAL_COLOR_EXPLOSION_EMITTER)
    }
}
