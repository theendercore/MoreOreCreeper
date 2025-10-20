package org.teamvoided.more_ore_creeper.client.entity

import com.mojang.blaze3d.vertex.PoseStack
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.model.CreeperModel
import net.minecraft.client.model.geom.ModelLayers
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import org.teamvoided.more_ore_creeper.entity.ModdedOreCreeper


@Environment(EnvType.CLIENT)
class ModdedOreCreeperRenderer(context: EntityRendererProvider.Context) :
    MobRenderer<ModdedOreCreeper, CreeperModel<ModdedOreCreeper>>(
        context, CreeperModel(context.bakeLayer(ModelLayers.CREEPER)), 0.5f
    ) {
    override fun getTextureLocation(creeper: ModdedOreCreeper): ResourceLocation =
        creeper.variant.value().getTextureLoc()

    init {
        /* @Suppress("UNCHECKED_CAST")
         this.addLayer(
             CreeperPowerLayer(
                 this as RenderLayerParent<Creeper?, CreeperModel<Creeper?>?>,
                 context.modelSet
             ) as RenderLayer<ModdedOreCreeper?, CreeperModel<ModdedOreCreeper>?>
         )*/
    }

    override fun scale(creeper: ModdedOreCreeper, poseStack: PoseStack, f: Float) {
        var swelling = creeper.getSwelling(f)
        val h = 1.0f + Mth.sin(swelling * 100.0f) * swelling * 0.01f
        swelling = Mth.clamp(swelling, 0.0f, 1.0f)
        swelling *= swelling
        swelling *= swelling
        val width = (1.0f + swelling * 0.4f) * h
        val height = (1.0f + swelling * 0.1f) / h
        poseStack.scale(width, height, width)
    }

    override fun getWhiteOverlayProgress(creeper: ModdedOreCreeper, f: Float): Float {
        val g = creeper.getSwelling(f)
        return if ((g * 10.0f).toInt() % 2 == 0) 0.0f else Mth.clamp(g, 0.5f, 1.0f)
    }
}