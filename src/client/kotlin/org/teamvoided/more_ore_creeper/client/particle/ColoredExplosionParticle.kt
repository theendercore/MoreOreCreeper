package org.teamvoided.more_ore_creeper.client.particle

import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.*
import net.minecraft.core.particles.ColorParticleOption

class ColoredExplosionParticle(
    level: ClientLevel,
    x: Double, y: Double, z: Double,
    xSpeed: Double, ySpeed: Double, zSpeed: Double,
    val sprites: SpriteSet,
) : TextureSheetParticle(level, x, y, z) {

    init {
        gravity = -0.1f
        friction = 0.9f
        xd = xSpeed + (Math.random() * 2.0 - 1.0) * 0.05
        yd = ySpeed + (Math.random() * 2.0 - 1.0) * 0.05
        zd = zSpeed + (Math.random() * 2.0 - 1.0) * 0.05
        val f = random.nextFloat() * 0.3f + 0.7f
        rCol = f
        gCol = f
        bCol = f
        quadSize = 0.1f * (random.nextFloat() * random.nextFloat() * 6.0f + 1.0f)
        lifetime = (16.0 / (random.nextFloat().toDouble() * 0.8 + 0.2)).toInt() + 2
        setSpriteFromAge(sprites)
    }

    override fun getRenderType(): ParticleRenderType = ParticleRenderType.PARTICLE_SHEET_OPAQUE
    override fun tick() {
        super.tick()
        setSpriteFromAge(sprites)
    }

    class Factory(val sprites: SpriteSet) : ParticleProvider<ColorParticleOption> {
        override fun createParticle(
            options: ColorParticleOption, level: ClientLevel,
            x: Double, y: Double, z: Double,
            xSpeed: Double, ySpeed: Double, zSpeed: Double,
        ): Particle {
            val particle = ColoredExplosionParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, sprites)
            particle.setColor(
                options.red + (Math.random().toFloat() * 2f - 1f) * 0.05f,
                options.green + (Math.random().toFloat() * 2f - 1f) * 0.05f,
                options.blue + (Math.random().toFloat() * 2f - 1f) * 0.05f,
            )
            return particle
        }

    }
}
