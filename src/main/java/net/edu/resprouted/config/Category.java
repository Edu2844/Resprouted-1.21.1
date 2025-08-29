package net.edu.resprouted.config;

import net.edu.resprouted.Resprouted;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.Arrays;

@Environment(EnvType.CLIENT)
public enum Category {
    GENERAL("config.resprouted.category.general", false,

            Entry.booleanEntry("config.resprouted.enable_honey_bottle_effect",
                    () -> Resprouted.CONFIG.EnableHoneyBottleEffect,
                    newValue -> Resprouted.CONFIG.EnableHoneyBottleEffect = newValue, true, "config.resprouted.enable_honey_bottle_effect.tooltip"),


            Entry.booleanEntry("config.resprouted.enable_vanta_oiling",
                    () -> Resprouted.CONFIG.EnableVantaOiling,
                    newValue -> Resprouted.CONFIG.EnableVantaOiling = newValue, true, "config.resprouted.enable_vanta_oiling.tooltip"),

            Entry.booleanEntry("config.resprouted.enable_olive_oiling",
                    () -> Resprouted.CONFIG.EnableOliveOiling,
                    newValue -> Resprouted.CONFIG.EnableOliveOiling = newValue, true, "config.resprouted.enable_olive_oiling.tooltip"),

            Entry.booleanEntry("config.resprouted.food_effect_tooltip",
                    () -> Resprouted.CONFIG.FoodEffectTooltips,
                    newValue -> Resprouted.CONFIG.FoodEffectTooltips = newValue, true, "config.resprouted.food_effect_tooltip.tooltip"),

            Entry.booleanEntry("config.resprouted.bottle_effect_tooltip",
                    () -> Resprouted.CONFIG.BottleEffectTooltips,
                    newValue -> Resprouted.CONFIG.BottleEffectTooltips = newValue, true, "config.resprouted.bottle_effect_tooltip.tooltip"),

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

            Entry.stringListEntry("config.resprouted.vanta_oil_whitelist",
                    () -> Resprouted.CONFIG.getVantaOilWhiteList(),
                    newValue -> Resprouted.CONFIG.setVantaOilWhiteList(newValue),
                    Arrays.asList("minecraft:bone","farmersdelight:iron_knife","farmersdelight:diamond_knife"
                    ),
                    "config.resprouted.vanta_oil_whitelist.tooltip"),

            Entry.stringListEntry("config.resprouted.vanta_oil_blacklist",
                    () -> Resprouted.CONFIG.getVantaOilBlackList(),
                    newValue -> Resprouted.CONFIG.setVantaOilBlackList(newValue),
                    Arrays.asList("modid:example_item"),
                    "config.resprouted.vanta_oil_blacklist.tooltip"),

            Entry.stringListEntry("config.resprouted.oilable_food_whitelist",
                    () -> Resprouted.CONFIG.getOliveOilFoodWhiteList(),
                    newValue -> Resprouted.CONFIG.setOliveOilFoodWhiteList(newValue),
                    Arrays.asList(
                            "farmersdelight:tomato", "farmersdelight:onion", "farmersdelight:cabbage_leaf"
                    ),
                    "config.resprouted.oilable_food_whitelist.tooltip"),

            Entry.stringListEntry("config.resprouted.oilable_food_blacklist",
                    () -> Resprouted.CONFIG.getOliveOilFoodBlackList(),
                    newValue -> Resprouted.CONFIG.setOliveOilFoodBlackList(newValue),
                    Arrays.asList("modid:example_item"),
                    "config.resprouted.oilable_food_blacklist.tooltip")

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
