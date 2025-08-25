package net.edu.resprouted.config;

import net.edu.resprouted.Resprouted;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.Arrays;

@Environment(EnvType.CLIENT)
public enum Category {
    GENERAL("config.resprouted.category.general", false,
            Entry.floatEntry("config.resprouted.oiled_saturation_modifier",
                    () -> Resprouted.CONFIG.getOiledSaturationModifier(),
                    newValue -> Resprouted.CONFIG.setOiledSaturationModifier(newValue),
                    1.5f, 0.1f, 5.0f,
                    "config.resprouted.oiled_saturation_modifier.tooltip"),

            Entry.integerEntry("config.resprouted.oiled_hunger_bonus",
                    () -> Resprouted.CONFIG.getOiledNutritionBonus(),
                    newValue -> Resprouted.CONFIG.setOiledHungerBonus(newValue),
                    2, 0, 10,
                    "config.resprouted.oiled_hunger_bonus.tooltip"),

            Entry.stringListEntry("config.resprouted.oilable_food_list",
                    () -> Resprouted.CONFIG.getOliveOilFood(),
                    newValue -> Resprouted.CONFIG.setOliveOilFood(newValue),
                    Arrays.asList(
                            "minecraft:beef", "minecraft:cooked_beef", "minecraft:porkchop",
                            "minecraft:cooked_porkchop", "minecraft:mutton", "minecraft:cooked_mutton",
                            "minecraft:chicken", "minecraft:cooked_chicken", "minecraft:rabbit",
                            "minecraft:cooked_rabbit", "minecraft:salmon", "minecraft:cooked_salmon",
                            "minecraft:cod", "minecraft:cooked_cod", "minecraft:tropical_fish",
                            "minecraft:bread", "minecraft:baked_potato", "minecraft:potato",
                            "minecraft:carrot", "minecraft:golden_carrot", "minecraft:beetroot",
                            "minecraft:sweet_berries", "minecraft:glow_berries", "minecraft:apple",
                            "minecraft:golden_apple", "resprouted:tomato", "resprouted:chili_pepper",
                            "resprouted:grapes", "resprouted:iron_berries", "resprouted:olives",
                            "farmersdelight:tomato", "farmersdelight:onion", "farmersdelight:cabbage_leaf"
                    ),
                    "config.resprouted.oilable_food_list.tooltip")

    ),
    WORLDGEN("config.resprouted.category.worldgen", false);
    private final String text;
    private final Entry<?>[] entries;
    private final Category[] children;
    private final boolean isChild;

    Category(String text, boolean isChild, Entry<?>... entries) {
        this.text = text;
        this.entries = entries;
        this.children = new Category[0];
        this.isChild = isChild;
    }
    Category(String text, boolean isChild, Category[] children, Entry<?>... entries) {
        this.text = text;
        this.entries = entries;
        this.children = children;
        this.isChild = isChild;
    }
    public String text() {
        return text;
    }
    public Entry<?>[] entries() {
        return entries;
    }
    public Category[] children() {
        return children;
    }
    public boolean isChild() {
        return isChild;
    }
}
