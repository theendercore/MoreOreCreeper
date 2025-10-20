package org.teamvoided.more_ore_creeper.entity

import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.BlockPos
import net.minecraft.core.HolderSet
import net.minecraft.core.RegistryCodecs
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider

class OrePlacement(
    val replaceList: HolderSet<Block>,
    val oreState: BlockStateProvider,
) {
    fun tryPlace(world: Level, pos: BlockPos): Boolean {
        if (world.getBlockState(pos).`is`(replaceList)) {
            world.setBlock(pos, oreState.getState(world.random, pos), 3)
            return true
        }
        return false
    }

    companion object {
        val CODEC = RecordCodecBuilder.mapCodec {
            it.group(
                RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("replace_list").forGetter(OrePlacement::replaceList),
                BlockStateProvider.CODEC.fieldOf("ore_block").forGetter(OrePlacement::oreState),
            ).apply(it, ::OrePlacement)
        }
    }
}