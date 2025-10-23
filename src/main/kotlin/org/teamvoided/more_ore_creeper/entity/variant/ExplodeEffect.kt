package org.teamvoided.more_ore_creeper.entity.variant

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.level.Level.ExplosionInteraction

class ExplodeEffect(val chance: Float, val power: Float, val type: ExplosionInteraction) {
    companion object {
        val CODEC: MapCodec<ExplodeEffect> = RecordCodecBuilder.mapCodec {
            it.group(
                Codec.floatRange(0f, 1f).fieldOf("chance").forGetter(ExplodeEffect::chance),
                Codec.floatRange(0f, 1024f).fieldOf("power").forGetter(ExplodeEffect::power),
                ExplosionInteraction.CODEC.fieldOf("type").forGetter(ExplodeEffect::type)
            ).apply(it, ::ExplodeEffect)
        }
    }
}