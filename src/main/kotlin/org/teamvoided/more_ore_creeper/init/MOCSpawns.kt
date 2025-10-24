package org.teamvoided.more_ore_creeper.init

import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors.*
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.entity.SpawnPlacementTypes
import net.minecraft.world.entity.SpawnPlacements
import net.minecraft.world.level.levelgen.Heightmap
import org.teamvoided.more_ore_creeper.MoreOreCreeper.config
import org.teamvoided.more_ore_creeper.data.tags.MOCBiomeTags
import org.teamvoided.more_ore_creeper.entity.ModdedOreCreeper
import java.util.function.Predicate

object MOCSpawns {
    fun init() {
        val spawns = config.spawnSettings
        addSpawn(
            foundInOverworld(), spawns.overworldSpawnWeight, spawns.overworldMinGroupSize, spawns.overworldMinGroupSize
        )
        addSpawn(foundInTheNether(), spawns.netherSpawnWeight, spawns.netherMinGroupSize, spawns.netherMinGroupSize)
        addSpawn(foundInTheEnd(), spawns.endSpawnWeight, spawns.endMinGroupSize, spawns.endMinGroupSize)
        addSpawn(
            (foundInOverworld().or(foundInTheNether()).or(foundInTheEnd()).or(tag(MOCBiomeTags.GLOBAL_BLACKLIST)))
                .negate(),
            spawns.otherSpawnWeight, spawns.otherMinGroupSize, spawns.otherMinGroupSize
        )

        SpawnPlacements.register(
            MOCEntityTypes.MODDED_ORE_CREEPER,
            SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
            ModdedOreCreeper::canSpawn
        )
    }

    fun addSpawn(biomeSelector: Predicate<BiomeSelectionContext>, weight: Int, minGroupSize: Int, maxGroupSize: Int) {
        BiomeModifications.addSpawn(
            biomeSelector, MobCategory.MONSTER, MOCEntityTypes.MODDED_ORE_CREEPER,
            weight, minGroupSize, maxGroupSize
        )
    }
}