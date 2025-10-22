package org.teamvoided.more_ore_creeper

import com.mojang.brigadier.context.CommandContext
import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands.argument
import net.minecraft.commands.Commands.literal
import net.minecraft.commands.arguments.ResourceArgument
import net.minecraft.core.Holder
import net.minecraft.resources.ResourceLocation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.more_ore_creeper.config.TemplateConfig
import org.teamvoided.more_ore_creeper.entity.ModdedOreCreeper
import org.teamvoided.more_ore_creeper.entity.OreCreeperVariant
import org.teamvoided.more_ore_creeper.init.*
import org.teamvoided.more_ore_creeper.init.MOCRegistries.ORE_CREEPER_VARIANT

@Suppress("unused")
object MoreOreCreeper {
    const val MODID = "more_ore_creeper"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(MoreOreCreeper::class.simpleName)

    @JvmField
    var config = ConfigApi.registerAndLoadConfig(::TemplateConfig)

    fun init() {
        MOCParticleTypes.init()
        MOCRegistries.init()
        MOCAttachmentTypes.init()
        MOCEntityTypes.init()
        MOCItems.init()

        CommandRegistrationCallback.EVENT.register { dispatcher, ctx, _ ->
            val creeper = literal("creeper").build()
            dispatcher.root.addChild(creeper)
            val cVariant = argument("variant", ResourceArgument.resource(ctx, ORE_CREEPER_VARIANT))
                .executes { spawnCreeper(it, ResourceArgument.getResource(it, "variant", ORE_CREEPER_VARIANT)) }
                .build()
            creeper.addChild(cVariant)
        }
    }

    fun spawnCreeper(context: CommandContext<CommandSourceStack>, resource: Holder.Reference<OreCreeperVariant>): Int {
        val src = context.source ?: return -1
        val level = src.level ?: return -1
        val pos = src.position ?: return -1
        val creeper = ModdedOreCreeper(level)
        creeper.setPos(pos)
        creeper.variant = resource
        level.addFreshEntity(creeper)
        return 0
    }

    fun id(namespace: String, path: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(namespace, path)
    fun id(path: String) = id(MODID, path)
}
