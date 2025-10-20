package org.teamvoided.template.entity

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.HolderSet
import net.minecraft.core.RegistryCodecs
import net.minecraft.core.particles.ColorParticleOption
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.biome.Biome
import org.teamvoided.template.init.MOCParticleTypes

data class OreCreeperVariant(
    val biomes: HolderSet<Biome>,
    val texture: ResourceLocation,
    val particles: List<ColorParticleOption>,
    val radius: Float,
    val orePlacements: List<OrePlacement>
) {
    fun getTextureLoc() = getFullTextureId(texture)

    companion object {
        val CODEC: Codec<OreCreeperVariant> = RecordCodecBuilder.create {
            it.group(
                RegistryCodecs.homogeneousList(Registries.BIOME).fieldOf("biomes").forGetter(OreCreeperVariant::biomes),
                ResourceLocation.CODEC.fieldOf("texture").forGetter(OreCreeperVariant::texture),
                ColorParticleOption.codec(MOCParticleTypes.COLORED_EXPLOSION).codec().listOf().fieldOf("particles").forGetter(OreCreeperVariant::particles),
                Codec.FLOAT.fieldOf("explosion_radius").forGetter(OreCreeperVariant::radius),
                OrePlacement.CODEC.codec().listOf().fieldOf("ore_placements").forGetter(OreCreeperVariant::orePlacements),
            ).apply(it, ::OreCreeperVariant)
        }

        private fun getFullTextureId(texture: ResourceLocation): ResourceLocation =
            texture.withPath { "textures/$it.png" }
    }
}