package org.teamvoided.more_ore_creeper.init

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate
import net.fabricmc.fabric.api.attachment.v1.AttachmentType
import net.minecraft.resources.ResourceKey
import org.teamvoided.more_ore_creeper.MoreOreCreeper.id
import org.teamvoided.more_ore_creeper.data.OreCreeperVariants
import org.teamvoided.more_ore_creeper.entity.OreCreeperVariant

@Suppress("UnstableApiUsage")
object MOCAttachmentTypes {
    fun init() {}

    @JvmField
    val ORE_CREEPER_VARIANT: AttachmentType<ResourceKey<OreCreeperVariant>> =
        AttachmentRegistry.create(id("ore_creeper_variant")) { builder ->
            builder
                .initializer { OreCreeperVariants.DEFAULT }
                .persistent(ResourceKey.codec(MOCRegistries.ORE_CREEPER_VARIANT))
                .syncWith(ResourceKey.streamCodec(MOCRegistries.ORE_CREEPER_VARIANT), AttachmentSyncPredicate.all())
        }
}