package org.teamvoided.more_ore_creeper.item

import net.minecraft.core.Holder
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.SpawnEggItem
import org.teamvoided.more_ore_creeper.entity.variant.OreCreeperVariant
import org.teamvoided.more_ore_creeper.init.MOCDataComponents
import org.teamvoided.more_ore_creeper.init.MOCItems.MODDED_ORE_CREEPER_SPAWN_EGG

class CustomSpawnEggItem(entityType: EntityType<out Mob>, i: Int, j: Int, properties: Properties) :
    SpawnEggItem(entityType, i, j, properties) {

    override fun getDescriptionId(stack: ItemStack): String {
        val default = descriptionId
        val id = stack.get(MOCDataComponents.VARIANT)
            ?.unwrapKey()?.get()?.location()
            ?.toString()?.replace(":", ".")
            ?: return default

        return "$descriptionId.$id"
    }

    companion object {

        fun createEgg(variant: Holder<OreCreeperVariant>): ItemStack {
            val stack = MODDED_ORE_CREEPER_SPAWN_EGG.defaultInstance!!
            stack.set(MOCDataComponents.VARIANT, variant)
            return stack
        }

        @JvmStatic
        fun getCustomColor(stack: ItemStack, layer: Int): Int? {
            return stack.get(MOCDataComponents.VARIANT)?.value()?.spawnEggColors?.getOrNull(layer)
        }
    }
}