package org.teamvoided.more_ore_creeper.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.more_ore_creeper.item.CustomSpawnEggItem;

import static org.teamvoided.more_ore_creeper.item.CustomSpawnEggItem.getCustomColor;

@Mixin(ItemColors.class)
public class ItemColorsMixin {

    @ModifyReturnValue(method = "method_1699", at = @At("RETURN"))
    private static int setCustomColor(int original, SpawnEggItem spawnEggItem, ItemStack stack, int layer) {
        if (spawnEggItem instanceof CustomSpawnEggItem) {
            var color = getCustomColor(stack, layer);
            if (color != null) return color;
        }
        return original;
    }
}
