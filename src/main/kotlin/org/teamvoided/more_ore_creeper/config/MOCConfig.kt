package org.teamvoided.more_ore_creeper.config

import me.fzzyhmstrs.fzzy_config.annotations.Action
import me.fzzyhmstrs.fzzy_config.annotations.Comment
import me.fzzyhmstrs.fzzy_config.annotations.NonSync
import me.fzzyhmstrs.fzzy_config.annotations.RequiresAction
import me.fzzyhmstrs.fzzy_config.config.Config
import me.fzzyhmstrs.fzzy_config.config.ConfigSection
import org.teamvoided.more_ore_creeper.MoreOreCreeper.MODID
import org.teamvoided.more_ore_creeper.MoreOreCreeper.id

@Suppress("unused")
class MOCConfig : Config(id(MODID)) {
    @RequiresAction(Action.RESTART)
    @Comment("All the options form mob spawns")
    var spawnSettings = SpawnSettingsSection()

    class SpawnSettingsSection : ConfigSection() {

        @RequiresAction(Action.RESTART)
        @Comment("Controls how often all of the ore creeps can spawn in the Overworld")
        var overworldSpawnWeight = 50

        @RequiresAction(Action.RESTART)
        var overworldMinGroupSize = 1

        @RequiresAction(Action.RESTART)
        var overworldMaxGroupSize = 3


        @RequiresAction(Action.RESTART)
        @Comment("Controls how often all of the ore creeps can spawn in the Nether")
        var netherSpawnWeight = 10

        @RequiresAction(Action.RESTART)
        var netherMinGroupSize = 1

        @RequiresAction(Action.RESTART)
        var netherMaxGroupSize = 2


        @RequiresAction(Action.RESTART)
        @Comment("Controls how often all of the ore creeps can spawn in the End")
        var endSpawnWeight = 1

        @RequiresAction(Action.RESTART)
        var endMinGroupSize = 1

        @RequiresAction(Action.RESTART)
        var endMaxGroupSize = 1


        @RequiresAction(Action.RESTART)
        @Comment("Controls how often all of the ore creeps can spawn in Other modded dimensions")
        var otherSpawnWeight = 4

        @RequiresAction(Action.RESTART)
        var otherMinGroupSize = 1

        @RequiresAction(Action.RESTART)
        var otherMaxGroupSize = 2
    }

    @Comment("Same setting as the original mod, but only for modded creepers")
    var moddedCreepersExplodeLikeVanillaOnes = false

    @Comment("Some modded creepers may still explode like regular crepers as specified by datapacks. This setting prevents this from happening")
    var customExplodeEffects = true

    @NonSync
    var creeperParticleAmount = 512

    fun isBlowUp() = moddedCreepersExplodeLikeVanillaOnes
}