package org.teamvoided.more_ore_creeper.data.gen.prov


import net.minecraft.core.Holder
import net.minecraft.core.particles.ColorParticleOption
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BiomeTags
import net.minecraft.tags.BlockTags
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import org.teamvoided.more_ore_creeper.MoreOreCreeper.id
import org.teamvoided.more_ore_creeper.data.OreCreeperVariants
import org.teamvoided.more_ore_creeper.entity.OreCreeperVariant
import org.teamvoided.more_ore_creeper.entity.OrePlacement
import org.teamvoided.more_ore_creeper.init.MOCParticleTypes

object OreCreeperVariantProv {
    val MISSING = texture("mising")
    val tempTexture = id("ore_creeper", "entity/coal_ore_creeper")
    const val RADIUS = 3.75f

    fun bootstrap(c: BootstrapContext<OreCreeperVariant>) {
        c.registerDefault(OreCreeperVariants.DEFAULT)
        c.register(
            OreCreeperVariants.EVIL,
            BiomeTags.IS_OVERWORLD,
            MISSING,
            listOf(color(.23f, 1f, 0f)),
            RADIUS,
            listOf(c.placement(BlockTags.DIRT, Blocks.END_STONE))
        )
    }

    fun BootstrapContext<OreCreeperVariant>.registerDefault(registryKey: ResourceKey<OreCreeperVariant>): Holder.Reference<OreCreeperVariant> {
        return this.register(
            registryKey,
            BiomeTags.IS_OVERWORLD,
            tempTexture,
            listOf(color(.5f, .5f, .5f)),
            RADIUS,
            listOf(placement(BlockTags.STONE_ORE_REPLACEABLES, Blocks.DIAMOND_BLOCK))
        )
    }

    private fun BootstrapContext<OreCreeperVariant>.register(
        key: ResourceKey<OreCreeperVariant>,
        biomes: TagKey<Biome>,
        texture: ResourceLocation,
        particles: List<ColorParticleOption>,
        radius: Float,
        orePlacements: List<OrePlacement>,
    ): Holder.Reference<OreCreeperVariant> {
        return this.register(
            key,
            OreCreeperVariant(lookup(Registries.BIOME).getOrThrow(biomes), texture, particles, radius, orePlacements)
        )
    }

    fun BootstrapContext<OreCreeperVariant>.placement(tag: TagKey<Block>, block: Block) =
        OrePlacement(lookup(Registries.BLOCK).getOrThrow(tag), BlockStateProvider.simple(block))

    fun color(r: Float, g: Float, b: Float): ColorParticleOption =
        ColorParticleOption.create(MOCParticleTypes.COLORED_EXPLOSION, r, g, b)

    fun texture(name: String): ResourceLocation = id("entity/ore_creeper/$name")

}