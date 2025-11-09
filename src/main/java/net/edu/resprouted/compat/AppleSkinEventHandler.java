package net.edu.resprouted.compat;

import net.edu.resprouted.component.ModDataComponentTypes;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.ItemStack;
import squeek.appleskin.api.AppleSkinApi;
import squeek.appleskin.api.event.FoodValuesEvent;

public class AppleSkinEventHandler implements AppleSkinApi {
    @Override
    public void registerEvents() {
        FoodValuesEvent.EVENT.register(event -> {
            ItemStack stack = event.itemStack;

            if (stack.get(ModDataComponentTypes.OLIVE_OILED) != null && Boolean.TRUE.equals(stack.get(ModDataComponentTypes.OLIVE_OILED))) {

                FoodComponent currentFood = stack.get(DataComponentTypes.FOOD);
                if (currentFood != null) {
                    event.modifiedFoodComponent = currentFood;
                }
            }
        });
    }
}
