package org.teamvoided.more_ore_creeper.init

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.core.Registry
import net.minecraft.core.particles.ColorParticleOption
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.registries.BuiltInRegistries
import org.teamvoided.more_ore_creeper.MoreOreCreeper.id

object MOCParticleTypes {
    val COLORED_EXPLOSION: ParticleType<ColorParticleOption> =
        FabricParticleTypes.complex(ColorParticleOption::codec, ColorParticleOption::streamCodec)


    fun init() {
        Registry.register(BuiltInRegistries.PARTICLE_TYPE, id("colored_explosion"), COLORED_EXPLOSION)
    }
}
