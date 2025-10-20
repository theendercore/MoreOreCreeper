package org.teamvoided.template.particle

//import com.mojang.serialization.MapCodec
//import com.mojang.serialization.codecs.RecordCodecBuilder
//import io.netty.buffer.ByteBuf
//import net.minecraft.core.particles.ParticleOptions
//import net.minecraft.core.particles.ParticleType
//import net.minecraft.network.codec.ByteBufCodecs
//import net.minecraft.network.codec.StreamCodec
//import net.minecraft.util.ExtraCodecs
//import org.teamvoided.template.init.MOCParticleTypes

//typealias DualType = ParticleType<out TwoColorParticleOption>

//class TwoColorParticleOption(
//    val type: DualType, val colorOne: Int, val colorTwo: Int,
//) : ParticleOptions {
//    constructor(colorOne: Int, colorTwo: Int) : this(MOCParticleTypes.DUAL_COLOR_EXPLOSION_EMITTER, colorOne, colorTwo)
//
//    override fun getType(): DualType = type
//
//    companion object {
//        fun codec(type: DualType): MapCodec<TwoColorParticleOption> = RecordCodecBuilder.mapCodec {
//            it.group(
//                ExtraCodecs.ARGB_COLOR_CODEC.fieldOf("color_one").forGetter { x -> x.colorOne },
//                ExtraCodecs.ARGB_COLOR_CODEC.fieldOf("color_two").forGetter { x -> x.colorTwo }
//            ).apply(it) { one, two -> TwoColorParticleOption(type, one, two) }
//        }
//
//        fun streamCodec(type: DualType): StreamCodec<in ByteBuf, TwoColorParticleOption> = StreamCodec.composite(
//            ByteBufCodecs.INT, TwoColorParticleOption::colorOne,
//            ByteBufCodecs.INT, TwoColorParticleOption::colorTwo
//        ) { one, two -> TwoColorParticleOption(type, one, two) }
//    }
//}
//
