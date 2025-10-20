package org.teamvoided.template.entity

import com.cozary.ore_creeper.entities.AbstractOreCreeperEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.VariantHolder
import net.minecraft.world.level.Level
import org.teamvoided.template.Template.log
import org.teamvoided.template.data.OreCreeperVariants
import org.teamvoided.template.init.MOCAttachmentTypes.ORE_CREEPER_VARIANT
import org.teamvoided.template.init.MOCRegistries

//import com.cozary.ore_creeper.entities.CopperCreeperEntity
@Suppress("UnstableApiUsage")
class ModdedOreCreeper(type: EntityType<out AbstractOreCreeperEntity>, level: Level) :
    AbstractOreCreeperEntity(type, level), VariantHolder<Holder<OreCreeperVariant>> {
    override fun setVariant(variant: Holder<OreCreeperVariant>) {
        setAttached(ORE_CREEPER_VARIANT, variant.unwrapKey().get())
    }

    override fun getVariant(): Holder<OreCreeperVariant> {
        val key = getAttachedOrElse(ORE_CREEPER_VARIANT, OreCreeperVariants.DEFAULT)
        return level().registryAccess().lookupOrThrow(MOCRegistries.ORE_CREEPER_VARIANT).getOrThrow(key)
    }

    override fun explodeCreeper() {
        val variant = getVariant().value()
        if (level().isClientSide) {
            if (variant.particles.isEmpty()) return

            val maxSpeed = 0.5
            for (i in 0..500) {
                val particle = variant.particles.random()
                try {
                    level().addParticle(
                        particle,
                        false,
                        (x + 0.5) + (random.nextGaussian() * random.nextGaussian() * 0.02),
                        (y) + (random.nextGaussian() * random.nextGaussian() * 0.02),
                        (z + 0.5) + (random.nextGaussian() * random.nextGaussian() * 0.02),
                        random.nextGaussian() * maxSpeed,
                        random.nextGaussian() * maxSpeed,
                        random.nextGaussian() * maxSpeed
                    )
                } catch (e: Exception) {
                    log.warn("Could not spawn particle effect {}", particle)
                }
            }

        } else {
            dead = true
            val r = (variant.radius * if (isPowered) 1.5f else 1f).toInt()
            // Explode
            for (x in -r..r) {
                for (y in -r..r) {
                    for (z in -r..r) {
                        val pos = BlockPos(x, y, z)
                        for (ore in variant.orePlacements) {
                            if (ore.tryPlace(level(), pos)) {
                                break
                            }
                        }
                    }
                }
            }
            discard()
            triggerOnDeathMobEffects(RemovalReason.KILLED)
            spawnLingeringCloud()
        }
    }
}
