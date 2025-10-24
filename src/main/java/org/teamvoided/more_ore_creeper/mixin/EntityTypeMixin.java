package org.teamvoided.more_ore_creeper.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.more_ore_creeper.data.OreCreeperVariants;
import org.teamvoided.more_ore_creeper.init.MOCDataComponents;

import java.util.function.Consumer;

import static org.teamvoided.more_ore_creeper.init.MOCAttachmentTypes.ORE_CREEPER_VARIANT;


@Mixin(EntityType.class)
public class EntityTypeMixin {

    @SuppressWarnings({"UnstableApiUsage", "OptionalGetWithoutIsPresent"})
    @ModifyReturnValue(method = "createDefaultStackConfig", at = @At("RETURN"))
    private static <T extends Entity> Consumer<T> setCustomColor(Consumer<T> original, ServerLevel serverLevel, ItemStack itemStack) {
        return (entity) -> {
            original.accept(entity);
            var variant = itemStack.get(MOCDataComponents.VARIANT);
            if (variant != null) {
                entity.setAttached(ORE_CREEPER_VARIANT, variant.unwrapKey().get());
                if (entity instanceof Mob mob && variant.unwrapKey().get() == OreCreeperVariants.MISSING) {
                    mob.setPersistenceRequired();
                }
            }
        };
    }
}
