package net.edu.resprouted.registry;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//Borrowed and modified from NaturesSpirit by Team Hibiscus
//Source:
//https://github.com/Team-Hibiscus/NaturesSpirit/blob/dev/src/main/java/net/hibiscus/naturespirit/registration/NSBoatTypes.java

public class ResproutedBoatTypes {
    private static final Map<BoatEntity.Type, Pair<Item, Item>> BOAT_TYPES_TO_ITEMS = new Object2ObjectLinkedOpenHashMap<>();

    public static void addBoatTypeItems(BoatEntity.Type type, Item boatItem, Item chestBoatItem) {
        BOAT_TYPES_TO_ITEMS.put(type, new Pair<>(boatItem, chestBoatItem));
    }

    public static Optional<Item> getBoatItem(BoatEntity.Type type) {
        return Optional.ofNullable(BOAT_TYPES_TO_ITEMS.getOrDefault(type, new Pair<>(null, null)).getLeft());
    }

    public static Optional<Item> getChestBoatItem(BoatEntity.Type type) {
        return Optional.ofNullable(BOAT_TYPES_TO_ITEMS.getOrDefault(type, new Pair<>(null, null)).getRight());
    }

    public static @NotNull List<Item> getAllBoatItems() {
        List<Item> items = new ArrayList<>();
        BOAT_TYPES_TO_ITEMS.forEach((type, pair) -> {
            items.add(pair.getLeft());
            items.add(pair.getRight());
        });
        return items;
    }

    public static BoatEntity.Type IRONWOOD;
    public static BoatEntity.Type OLIVE;

}
