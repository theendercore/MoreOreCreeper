package org.teamvoided.more_ore_creeper.entity

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.HolderSet
import net.minecraft.core.RegistryCodecs
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.biome.Biome
import org.teamvoided.more_ore_creeper.entity.variant.ExplodeEffect
import java.util.*

data class OreCreeperVariant(
    val biomes: HolderSet<Biome>,
    val texture: ResourceLocation,
    val particles: List<ParticleOptions>,
    val radius: Float,
    val orePlacements: List<OrePlacement>,
    val spawnEggColor1: Int,
    val spawnEggColor2: Int,
    val maxSpawnYLevel: Int,
    val explodeEffect: Optional<ExplodeEffect>,
) {
    fun getTextureLoc() = getFullTextureId(texture)

    companion object {
        val CODEC: Codec<OreCreeperVariant> = RecordCodecBuilder.create {
            it.group(
                RegistryCodecs.homogeneousList(Registries.BIOME).fieldOf("biomes").forGetter(OreCreeperVariant::biomes),
                ResourceLocation.CODEC.fieldOf("texture").forGetter(OreCreeperVariant::texture),
                ParticleTypes.CODEC.listOf().fieldOf("particles").forGetter(OreCreeperVariant::particles),
                Codec.floatRange(0f, 256f).fieldOf("explosion_radius").forGetter(OreCreeperVariant::radius),
                OrePlacement.CODEC.codec().listOf().fieldOf("ore_placements")
                    .forGetter(OreCreeperVariant::orePlacements),
                Codec.INT.fieldOf("spawn_egg_color_1").forGetter(OreCreeperVariant::spawnEggColor1),
                Codec.INT.fieldOf("spawn_egg_color_2").forGetter(OreCreeperVariant::spawnEggColor2),
                Codec.INT.fieldOf("max_spawn_y_level").forGetter(OreCreeperVariant::maxSpawnYLevel),
                ExplodeEffect.CODEC.codec().optionalFieldOf("explode_effect")
                    .forGetter(OreCreeperVariant::explodeEffect)
            ).apply(it, ::OreCreeperVariant)
        }

        private fun getFullTextureId(texture: ResourceLocation): ResourceLocation =
            texture.withPath { "textures/$it.png" }
    }
}