package org.teamvoided.more_ore_creeper.init

import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.entity.monster.Creeper
import org.teamvoided.more_ore_creeper.MoreOreCreeper.id
import org.teamvoided.more_ore_creeper.entity.ModdedOreCreeper

object MOCEntityTypes {
    val ID =  id("modded_ore_creeper")
    val MODDED_ORE_CREEPER: EntityType<ModdedOreCreeper> = Registry.register(
        BuiltInRegistries.ENTITY_TYPE, ID,
        EntityType.Builder.of(::ModdedOreCreeper, MobCategory.MONSTER)
            .sized(0.6f, 1.7f)
            .clientTrackingRange(8)
            .build(ID.toString())
    )

    fun init() {
        FabricDefaultAttributeRegistry.register(MODDED_ORE_CREEPER, Creeper.createAttributes())
    }
}
