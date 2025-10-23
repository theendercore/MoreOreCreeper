package org.teamvoided.more_ore_creeper.config

import me.fzzyhmstrs.fzzy_config.annotations.Comment
import me.fzzyhmstrs.fzzy_config.annotations.NonSync
import me.fzzyhmstrs.fzzy_config.config.Config
import org.teamvoided.more_ore_creeper.MoreOreCreeper.MODID
import org.teamvoided.more_ore_creeper.MoreOreCreeper.id

@Suppress("unused")
class MOCConfig : Config(id(MODID)) {
    @Comment("Same setting as the original mod, but only for modded creepers")
    var moddedCreepersExplodeLikeVanillaOnes = false

    @Comment("Some modded creepers may still explode like regular crepers as specified by datapacks. This setting prevents this from happening")
    var customExplodeEffects = true

    @NonSync
    var creeperParticleAmount = 512

    fun isBlowUp() = moddedCreepersExplodeLikeVanillaOnes
}