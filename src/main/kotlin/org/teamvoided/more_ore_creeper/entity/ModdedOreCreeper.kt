package org.teamvoided.more_ore_creeper.entity

import com.cozary.ore_creeper.entities.AbstractOreCreeperEntity
import net.minecraft.Util
import net.minecraft.core.BlockPos
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.VariantHolder
import net.minecraft.world.level.Level
import net.minecraft.world.level.Level.ExplosionInteraction
import net.minecraft.world.level.storage.loot.LootTable
import org.teamvoided.more_ore_creeper.MoreOreCreeper.MODID
import org.teamvoided.more_ore_creeper.MoreOreCreeper.config
import org.teamvoided.more_ore_creeper.MoreOreCreeper.log
import org.teamvoided.more_ore_creeper.data.OreCreeperVariants
import org.teamvoided.more_ore_creeper.entity.variant.OreCreeperVariant
import org.teamvoided.more_ore_creeper.init.MOCAttachmentTypes.ORE_CREEPER_VARIANT
import org.teamvoided.more_ore_creeper.init.MOCEntityTypes
import org.teamvoided.more_ore_creeper.init.MOCRegistries
import org.teamvoided.more_ore_creeper.misc.customLootTable
import kotlin.jvm.optionals.getOrElse
import kotlin.math.ceil
import kotlin.math.pow
import kotlin.math.sqrt

@Suppress("UnstableApiUsage")
class ModdedOreCreeper(type: EntityType<out AbstractOreCreeperEntity>, level: Level) :
    AbstractOreCreeperEntity(type, level), VariantHolder<Holder<OreCreeperVariant>> {
    constructor(level: Level) : this(MOCEntityTypes.MODDED_ORE_CREEPER, level)

    override fun getTypeName(): Component {
        if (!isMissing()) {
            return Component.translatable(Util.makeDescriptionId("entity.$MODID", variant.unwrapKey().get().location()))
        }
        return super.getTypeName()
    }

    init {
        variant = lookup().getOrThrow(OreCreeperVariants.MISSING)
    }

    override fun setVariant(variant: Holder<OreCreeperVariant>) {
        setAttached(ORE_CREEPER_VARIANT, variant.unwrapKey().get())
    }

    override fun getVariant(): Holder<OreCreeperVariant> {
        return lookup().get(getAttachedOrElse(ORE_CREEPER_VARIANT, OreCreeperVariants.MISSING))
            .getOrElse { lookup().getOrThrow(OreCreeperVariants.MISSING) }
    }

    fun lookup(): HolderLookup.RegistryLookup<OreCreeperVariant> =
        level().registryAccess().lookupOrThrow(MOCRegistries.ORE_CREEPER_VARIANT)

    fun isMissing() = variant.`is`(OreCreeperVariants.MISSING)

    override fun getDefaultLootTable(): ResourceKey<LootTable> {
        return if (!isMissing()) customLootTable(variant.unwrapKey().get()) else super.getDefaultLootTable()
    }

    override fun explodeCreeper() {
        if (level().isClientSide) return
        dead = true
        val variant = variant.value()
        val radius = variant.radius * if (isPowered) 1.5f else 1f
        explodeEffects(variant, radius)
        level().broadcastEntityEvent(this, 14)
        discard()
        triggerOnDeathMobEffects(RemovalReason.KILLED)
        spawnLingeringCloud()
    }

    fun explodeEffects(variant: OreCreeperVariant, radius: Float) {
        if (isMissing()) return

        val r = ceil(radius).toInt()
        val optional = variant.explodeEffect
        if (config.customExplodeEffects && optional.isPresent) {
            val effect = optional.get()
            if (random.nextFloat() <= effect.chance) {
                level().explode(this, x, y, z, effect.power, effect.type)
            }
        } else {
            level().explode(
                this, x, y, z,
                if (config.isBlowUp()) radius else 0f,
                if (config.isBlowUp()) ExplosionInteraction.MOB else ExplosionInteraction.NONE
            )
        }

        if (variant.orePlacements.isEmpty()) return
        for (x in -r..r) {
            for (y in -r..r) {
                for (z in -r..r) {
                    val pos = BlockPos((x + this.x).toInt(), (y + this.y).toInt(), (z + this.z).toInt())
                    if (sqrt(x.toDouble().pow(2.0) + y.toDouble().pow(2.0) + z.toDouble().pow(2.0)) <= radius) {
                        for (ore in variant.orePlacements) {
                            if (ore.tryPlace(level(), pos)) {
                                break
                            }
                        }
                    }
                }
            }
        }
    }

    override fun handleEntityEvent(b: Byte) {
        if (b.toInt() == 14) {
            if (variant.value().particles.isEmpty()) return

            val maxSpeed = 0.5
            for (i in 0..config.creeperParticleAmount) {
                val particle = variant.value().particles.random()
                try {
                    level().addParticle(
                        particle,
                        false,
                        x + (random.nextGaussian() * random.nextGaussian() * 0.02),
                        (y + 0.5) + (random.nextGaussian() * random.nextGaussian() * 0.02),
                        z + (random.nextGaussian() * random.nextGaussian() * 0.02),
                        random.nextGaussian() * maxSpeed,
                        random.nextGaussian() * maxSpeed,
                        random.nextGaussian() * maxSpeed
                    )
                } catch (_: Exception) {
                    log.warn("Could not spawn particle effect {}", particle)
                    break
                }
            }
        } else {
            super.handleEntityEvent(b)
        }
    }
}
