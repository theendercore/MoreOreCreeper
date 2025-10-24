package org.teamvoided.more_ore_creeper.init

import net.minecraft.core.Registry
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.RegistryFixedCodec
import org.teamvoided.more_ore_creeper.MoreOreCreeper.id

object MOCDataComponents {
    @JvmField
    val VARIANT =
        register("variant") { it.persistent(RegistryFixedCodec.create(MOCRegistries.ORE_CREEPER_VARIANT)).build() }

    fun <T> register(
        name: String, build: (DataComponentType.Builder<T>) -> DataComponentType<T>,
    ): DataComponentType<T> =
        Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, id(name), build(DataComponentType.builder()))

    fun init() {}
}