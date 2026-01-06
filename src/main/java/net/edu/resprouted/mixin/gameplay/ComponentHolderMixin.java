package net.edu.resprouted.mixin.gameplay;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.component.ModDataComponentTypes;
import net.minecraft.component.ComponentHolder;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("unchecked")
@Mixin(ComponentHolder.class)
public interface ComponentHolderMixin {
    @Inject(method = "get(Lnet/minecraft/component/ComponentType;)Ljava/lang/Object;", at = @At("RETURN"), cancellable = true)
    default <T> void onGetComponent(ComponentType<T> type, CallbackInfoReturnable<T> cir) {

        if (type == DataComponentTypes.FOOD && cir.getReturnValue() != null) {
            ComponentHolder self = (ComponentHolder) this;

            if (self instanceof ItemStack stack) {
                if (stack.get(ModDataComponentTypes.OLIVE_OILED) != null && Boolean.TRUE.equals(stack.get(ModDataComponentTypes.OLIVE_OILED))) {
                    FoodComponent originalFood = (FoodComponent) cir.getReturnValue();

                    FoodComponent modifiedFood = modifyFoodComponent(originalFood);
                    cir.setReturnValue((T) modifiedFood);
                }
            }
        }
    }

    @Unique
    default FoodComponent modifyFoodComponent(FoodComponent original) {
        return new FoodComponent(original.nutrition() + Resprouted.COMMON_CONFIG.food.getOiledNutritionBonus(),
                Math.max(0.1f, original.saturation() * Resprouted.COMMON_CONFIG.food.getOiledSaturationModifier()),
                original.canAlwaysEat(),
                original.eatSeconds(),
                original.usingConvertsTo(),
                original.effects()
        );
    }
}
