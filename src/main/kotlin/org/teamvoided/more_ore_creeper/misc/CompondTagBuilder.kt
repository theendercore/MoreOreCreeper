package org.teamvoided.more_ore_creeper.misc

import net.minecraft.nbt.CompoundTag

fun buildTag(fn: CompoundTag.() -> Unit): CompoundTag {
    val tag = CompoundTag()
    fn(tag)
    return tag
}