package org.teamvoided.more_ore_creeper.data.gen.prov


import net.mehvahdjukaar.randomium.Randomium
import net.minecraft.core.Holder
import net.minecraft.core.particles.ColorParticleOption
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BiomeTags
import net.minecraft.tags.TagKey
import net.minecraft.util.random.SimpleWeightedRandomList
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider
import org.teamvoided.more_ore_creeper.MoreOreCreeper.id
import org.teamvoided.more_ore_creeper.data.OreCreeperVariants
import org.teamvoided.more_ore_creeper.data.tags.MOCBiomeTags
import org.teamvoided.more_ore_creeper.data.tags.MOCBlockTags
import org.teamvoided.more_ore_creeper.entity.OreCreeperVariant
import org.teamvoided.more_ore_creeper.entity.OrePlacement
import org.teamvoided.more_ore_creeper.entity.variant.ExplodeEffect
import org.teamvoided.more_ore_creeper.init.MOCParticleTypes
import java.util.*

object OreCreeperVariantProv {
    val MISSING = texture("missing")
    val tempTexture = id("ore_creeper", "entity/coal_creeper")
    const val RADIUS = 3.75f
    const val MAX_Y = 320
    const val STONE_COLOR = 0xff_808080
    const val NETHERRACK_COLOR = 0xff_8B0000
    const val ENDSTONE_COLOR = -1

    fun bootstrap(c: BootstrapContext<OreCreeperVariant>) = c.create()
    fun BootstrapContext<OreCreeperVariant>.create() {
        registerDefault(OreCreeperVariants.MISSING)
        register(
            OreCreeperVariants.RANDOMIUM,
            BiomeTags.IS_OVERWORLD, tempTexture, listOf(color(0xff_76428a)), RADIUS,
            listOf(
                placement(
                    MOCBlockTags.STONE_ORE_REPLACEABLE,
                    Randomium.RANDOMIUM_ORE.get() to 6, Blocks.AIR to 4,
                ),
                placement(
                    MOCBlockTags.DEEPSLATE_ORE_REPLACEABLE,
                    Randomium.RANDOMIUM_ORE_DEEP.get() to 6, Blocks.AIR to 4,
                ),
                placement(
                    MOCBlockTags.END_ORE_REPLACEABLE,
                    Randomium.RANDOMIUM_ORE_END.get() to 6, Blocks.AIR to 4,
                )
            ),
            STONE_COLOR to 0xff_76428a, MAX_Y
        )
    }

    fun BootstrapContext<OreCreeperVariant>.registerDefault(registryKey: ResourceKey<OreCreeperVariant>): Holder.Reference<OreCreeperVariant> {
        return this.register(
            registryKey, MOCBiomeTags.VOID, MISSING,
            listOf(color(0x0), color(0xff_fa00ff)),
            RADIUS, listOf(), 0xff_00_00_00 to 0xff_fa00ff, MAX_Y
        )
    }

    fun BootstrapContext<OreCreeperVariant>.register(
        key: ResourceKey<OreCreeperVariant>,
        biomes: TagKey<Biome>,
        texture: ResourceLocation,
        particles: List<ParticleOptions>,
        radius: Float,
        orePlacements: List<OrePlacement>,
        spawnEggColors: Pair<Number, Number>,
        maxSpawnYLevel: Int,
    ): Holder.Reference<OreCreeperVariant> {
        return register(key, biomes, texture, particles, radius, orePlacements, spawnEggColors, maxSpawnYLevel, null)
    }

    fun BootstrapContext<OreCreeperVariant>.register(
        key: ResourceKey<OreCreeperVariant>,
        biomes: TagKey<Biome>,
        texture: ResourceLocation,
        particles: List<ParticleOptions>,
        radius: Float,
        orePlacements: List<OrePlacement>,
        spawnEggColors: Pair<Number, Number>,
        maxSpawnYLevel: Int,
        explodeEffect: ExplodeEffect?,
    ): Holder.Reference<OreCreeperVariant> {
        return this.register(
            key,
            OreCreeperVariant(
                lookup(Registries.BIOME).getOrThrow(biomes),
                texture,
                particles,
                radius,
                orePlacements,
                spawnEggColors.first.toInt(), spawnEggColors.second.toInt(),
                maxSpawnYLevel,
                Optional.ofNullable(explodeEffect)
            )
        )
    }


    fun BootstrapContext<OreCreeperVariant>.placement(tag: TagKey<Block>, vararg blocks: Pair<Block, Int>) =
        statePlacement(tag, *blocks.map { it.first.defaultBlockState() to it.second }.toTypedArray())

    fun BootstrapContext<OreCreeperVariant>.statePlacement(
        tag: TagKey<Block>, vararg blocks: Pair<BlockState, Int>,
    ): OrePlacement {
        val list = SimpleWeightedRandomList.Builder<BlockState>()
        for ((block, weight) in blocks) {
            list.add(block, weight)
        }

        return OrePlacement(lookup(Registries.BLOCK).getOrThrow(tag), WeightedStateProvider(list))
    }

    fun color(color: Long) = color(color.toInt())
    fun color(color: Int): ColorParticleOption = ColorParticleOption.create(MOCParticleTypes.COLORED_EXPLOSION, color)

    fun texture(name: String): ResourceLocation = id("entity/ore_creeper/$name")

}