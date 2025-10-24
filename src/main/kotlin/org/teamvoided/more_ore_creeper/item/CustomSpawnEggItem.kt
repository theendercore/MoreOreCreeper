package org.teamvoided.more_ore_creeper.item

import net.minecraft.core.Holder
import net.minecraft.core.component.DataComponents
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.SpawnEggItem
import net.minecraft.world.item.component.CustomData
import org.teamvoided.more_ore_creeper.entity.variant.OreCreeperVariant
import org.teamvoided.more_ore_creeper.init.MOCDataComponents
import org.teamvoided.more_ore_creeper.init.MOCItems.MODDED_ORE_CREEPER_SPAWN_EGG
import org.teamvoided.more_ore_creeper.init.MOCRegistries
import org.teamvoided.more_ore_creeper.misc.buildTag

class CustomSpawnEggItem(entityType: EntityType<out Mob>, i: Int, j: Int, properties: Properties) :
    SpawnEggItem(entityType, i, j, properties) {

    override fun getDescriptionId(stack: ItemStack): String {
        val default = descriptionId
        val id = stack.get(MOCDataComponents.VARIANT)
            ?.unwrapKey()?.get()?.location()?.toString()
            ?: return default

        return "$descriptionId.${id.replace(":", ".")}"
    }

    companion object {
        const val A_KEY = "fabric:attachments"
        val VARIANT = MOCRegistries.ORE_CREEPER_VARIANT.location().toString()

        fun createEgg(variant: Holder<OreCreeperVariant>): ItemStack {
            val stack = MODDED_ORE_CREEPER_SPAWN_EGG.defaultInstance!!
            val tag = buildTag {
                put(A_KEY, buildTag {
                    putString(VARIANT, variant.unwrapKey().get().location().toString())
                })
                putString("id", "more_ore_creeper:modded_ore_creeper")
            }

            stack.set(DataComponents.ENTITY_DATA, CustomData.of(tag))
            stack.set(MOCDataComponents.VARIANT, variant)
            return stack
        }

        @Suppress("DEPRECATION")
        @JvmStatic
        fun getCustomColor(stack: ItemStack, i: Int): Int? {
            val variant = stack.get(MOCDataComponents.VARIANT)?.value() ?: return null
            return if (i == 0) variant.spawnEggColor1 else variant.spawnEggColor2
        }
    }
}