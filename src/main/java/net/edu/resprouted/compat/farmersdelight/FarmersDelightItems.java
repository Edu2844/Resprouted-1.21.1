package net.edu.resprouted.compat.farmersdelight;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.compat.item.CompatItem;
import net.edu.resprouted.item.ModFoodComponents;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class FarmersDelightItems {
    public static final Item IRON_BERRY_CAKE_SLICE = registerCompatItem("iron_berry_cake_slice",
            new CompatItem(Resprouted.FARMERS_DELIGHT_MOD_ID,
                    new Item.Settings().food(ModFoodComponents.IRON_BERRIES)
            )
    );

    private static Item registerCompatItem(String name, Item item) {
        return Registry.register(
                Registries.ITEM,
                Identifier.of(Resprouted.MOD_ID, name),
                item
        );
    }

    public static void initialize() {
        Resprouted.LOGGER.info("Registering Farmer's Delight compat items");
    }
}