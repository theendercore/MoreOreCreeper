package org.teamvoided.template.init

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate
import net.fabricmc.fabric.api.attachment.v1.AttachmentType
import net.minecraft.resources.ResourceKey
import org.teamvoided.template.Template.id
import org.teamvoided.template.data.OreCreeperVariants
import org.teamvoided.template.entity.OreCreeperVariant

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