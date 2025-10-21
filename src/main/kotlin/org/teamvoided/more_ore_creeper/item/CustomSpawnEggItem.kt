package org.teamvoided.more_ore_creeper.item

import net.minecraft.core.Holder
import net.minecraft.core.component.DataComponents
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.SpawnEggItem
import net.minecraft.world.item.component.CustomData
import org.teamvoided.more_ore_creeper.MoreOreCreeper.id
import org.teamvoided.more_ore_creeper.entity.OreCreeperVariant
import org.teamvoided.more_ore_creeper.init.MOCItems.MODDED_ORE_CREEPER_SPAWN_EGG
import org.teamvoided.more_ore_creeper.init.MOCRegistries

class CustomSpawnEggItem(entityType: EntityType<out Mob>, i: Int, j: Int, properties: Properties) :
    SpawnEggItem(entityType, i, j, properties) {

    @Suppress("DEPRECATION")
    override fun getDescriptionId(stack: ItemStack): String {
        val default = descriptionId
        val nbt = stack.get(DataComponents.ENTITY_DATA)?.unsafe ?: return default

        if (!nbt.contains(A_KEY)) return default
        val attachments = nbt.getCompound(A_KEY)
        if (!attachments.contains(VARIANT)) return default
        val id = attachments.getString(VARIANT)

        return "$descriptionId.${id.replace(":", ".")}"
    }

    companion object {
        val COLOR = id("color").toString()
        const val A_KEY = "fabric:attachments"
        val VARIANT = MOCRegistries.ORE_CREEPER_VARIANT.location().toString()

        fun createEgg(variant: Holder<OreCreeperVariant>): ItemStack {
            val stack = MODDED_ORE_CREEPER_SPAWN_EGG.defaultInstance!!
            val attachments = CompoundTag()
            attachments.putString(VARIANT, variant.unwrapKey().get().location().toString())
            val tag = CompoundTag()

            tag.put(A_KEY, attachments)
            tag.putString("id", "more_ore_creeper:modded_ore_creeper")
            tag.putString(COLOR, "${variant.value().spawnEggColor1}:${variant.value().spawnEggColor2}")

            stack.set(DataComponents.ENTITY_DATA, CustomData.of(tag))
            return stack
        }

        fun getVariant() {
        }

        @Suppress("DEPRECATION")
        @JvmStatic
        fun getCustomColor(stack: ItemStack, i: Int): Int? {
            val nbt = stack.get(DataComponents.ENTITY_DATA)?.unsafe ?: return null
            if (nbt.contains(COLOR)) {
                try {
                    val colors = nbt.getString(COLOR).split(":")
                    return colors[i].toInt()
                } catch (_: Exception) {
                }
            }
            return null
        }
    }
}