package net.edu.resprouted.config;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.ResproutedClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.Arrays;

@SuppressWarnings("all")
@Environment(EnvType.CLIENT)
public enum Category {
    GENERAL("config.resprouted.category.general", true,

            Entry.booleanEntry("config.resprouted.enable_honey_bottle_effect",
                    () -> Resprouted.COMMON_CONFIG.getGeneral().isEnableHoneyBottleRegenerationEffect(),
                    newValue -> Resprouted.COMMON_CONFIG.getGeneral().setEnableHoneyBottleRegenerationEffect(newValue),
                    true, "config.resprouted.enable_honey_bottle_effect.tooltip"),

            Entry.booleanEntry("config.resprouted.enable_vanta_oiling",
                    () -> Resprouted.COMMON_CONFIG.getGeneral().isEnableVantaOiling(),
                    newValue -> Resprouted.COMMON_CONFIG.getGeneral().setEnableVantaOiling(newValue),
                    true, "config.resprouted.enable_vanta_oiling.tooltip"),

            Entry.stringListEntry("config.resprouted.vanta_oil_whitelist",
                    () -> Resprouted.COMMON_CONFIG.getGeneral().getVantaOilWhiteList(),
                    newValue -> Resprouted.COMMON_CONFIG.getGeneral().setVantaOilWhiteList(newValue),
                    Arrays.asList("minecraft:bone", "farmersdelight:iron_knife", "farmersdelight:diamond_knife"),
                    "config.resprouted.vanta_oil_whitelist.tooltip"),

            Entry.stringListEntry("config.resprouted.vanta_oil_blacklist",
                    () -> Resprouted.COMMON_CONFIG.getGeneral().getVantaOilBlackList(),
                    newValue -> Resprouted.COMMON_CONFIG.getGeneral().setVantaOilBlackList(newValue),
                    Arrays.asList(),
                    "config.resprouted.vanta_oil_blacklist.tooltip")
    ),

    WORLD("config.resprouted.category.world", true,

            Entry.booleanEntry("config.resprouted.enable_seed_drops",
                    () -> Resprouted.COMMON_CONFIG.getWorld().isEnableSeedDrops(),
                    newValue -> Resprouted.COMMON_CONFIG.getWorld().setEnableSeedDrops(newValue),
                    true, "config.resprouted.enable_seed_drops.tooltip"),

            Entry.integerEntry("config.resprouted.seeds_drop_rate",
                    () -> Resprouted.COMMON_CONFIG.getWorld().getSeedsDropRate(),
                    newValue -> Resprouted.COMMON_CONFIG.getWorld().setSeedsDropRate(newValue),
                    7, 1, 100, "config.resprouted.seeds_drop_rate.tooltip"),

            Entry.booleanEntry("config.resprouted.grape_drop_needs_tools",
                    () -> Resprouted.COMMON_CONFIG.getWorld().isGrapeDropNeedsTool(),
                    newValue -> Resprouted.COMMON_CONFIG.getWorld().setGrapeDropNeedsTool(newValue),
                    false, "config.resprouted.grape_drop_needs_tools.tooltip"),

            Entry.stringListEntry("config.resprouted.grape_seeds_tool_whitelist",
                    () -> Resprouted.COMMON_CONFIG.getWorld().getGrapeSeedsToolWhitelist(),
                    newValue -> Resprouted.COMMON_CONFIG.getWorld().setGrapeSeedsToolWhitelist(newValue),
                    Arrays.asList("minecraft:iron_hoe", "minecraft:diamond_hoe", "minecraft:netherite_hoe"),
                    "config.resprouted.grape_seeds_tool_whitelist.tooltip")
    ),

    FOOD("config.resprouted.category.food", true,

            Entry.booleanEntry("config.resprouted.enable_olive_oiling",
                    () -> Resprouted.COMMON_CONFIG.getFood().isEnableOliveOiling(),
                    newValue -> Resprouted.COMMON_CONFIG.getFood().setEnableOliveOiling(newValue),
                    true, "config.resprouted.enable_olive_oiling.tooltip"),

            Entry.floatEntry("config.resprouted.oiled_saturation_modifier",
                    () -> Resprouted.COMMON_CONFIG.getFood().getOiledSaturationModifier(),
                    newValue -> Resprouted.COMMON_CONFIG.getFood().setOiledSaturationModifier(newValue),
                    1.5f, 0.1f, 5.0f,
                    "config.resprouted.oiled_saturation_modifier.tooltip"),

            Entry.integerEntry("config.resprouted.oiled_hunger_bonus",
                    () -> Resprouted.COMMON_CONFIG.getFood().getOiledNutritionBonus(),
                    newValue -> Resprouted.COMMON_CONFIG.getFood().setOiledNutritionBonus(newValue),
                    2, 0, 10,
                    "config.resprouted.oiled_hunger_bonus.tooltip"),

            Entry.stringListEntry("config.resprouted.oilable_food_whitelist",
                    () -> Resprouted.COMMON_CONFIG.getFood().getOilableFoodWhiteList(),
                    newValue -> Resprouted.COMMON_CONFIG.getFood().setOilableFoodWhiteList(newValue),
                    Arrays.asList("farmersdelight:tomato", "farmersdelight:onion", "farmersdelight:cabbage_leaf"),
                    "config.resprouted.oilable_food_whitelist.tooltip"),

            Entry.stringListEntry("config.resprouted.oilable_food_blacklist",
                    () -> Resprouted.COMMON_CONFIG.getFood().getOilableFoodBlackList(),
                    newValue -> Resprouted.COMMON_CONFIG.getFood().setOilableFoodBlackList(newValue),
                    Arrays.asList(),
                    "config.resprouted.oilable_food_blacklist.tooltip")
    ),

    BREWING("config.resprouted.category.brewing", true,

            Entry.integerEntry("config.resprouted.min_brew_quality_change",
                    () -> Resprouted.COMMON_CONFIG.getBrewing().getMinBrewQualityChange(),
                    newValue -> Resprouted.COMMON_CONFIG.getBrewing().setMinBrewQualityChange(newValue),
                    -1, -50, 50,
                    "config.resprouted.min_brew_quality_change.tooltip"),

            Entry.integerEntry("config.resprouted.max_brew_quality_change",
                    () -> Resprouted.COMMON_CONFIG.getBrewing().getMaxBrewQualityChange(),
                    newValue -> Resprouted.COMMON_CONFIG.getBrewing().setMaxBrewQualityChange(newValue),
                    4, -50, 50,
                    "config.resprouted.max_brew_quality_change.tooltip"),

            Entry.integerEntry("config.resprouted.max_brew_time",
                    () -> Resprouted.COMMON_CONFIG.getBrewing().getMaxBrewTime(),
                    newValue -> Resprouted.COMMON_CONFIG.getBrewing().setMaxBrewTime(newValue),
                    12000, 200, 120000,
                    "config.resprouted.max_brew_time.tooltip")
    ),


    CLIENT("config.resprouted.category.client", false,

            Entry.booleanEntry("config.resprouted.food_effect_tooltip",
                    () -> ResproutedClient.CLIENT_CONFIG.isFoodEffectTooltipsEnabled(),
                    newValue -> ResproutedClient.CLIENT_CONFIG.setFoodEffectTooltips(newValue),
                    true, "config.resprouted.food_effect_tooltip.tooltip"),

            Entry.booleanEntry("config.resprouted.bottle_effect_tooltip",
                    () -> ResproutedClient.CLIENT_CONFIG.isBottleEffectTooltipsEnabled(),
                    newValue -> ResproutedClient.CLIENT_CONFIG.setBottleEffectTooltips(newValue),
                    true, "config.resprouted.bottle_effect_tooltip.tooltip"),

            Entry.stringListEntry("config.resprouted.entity_overlay_blacklist",
                    () -> ResproutedClient.CLIENT_CONFIG.getEffectOverlayBlacklist(),
                    newValue ->  ResproutedClient.CLIENT_CONFIG.setEntityBlacklisted(String.valueOf((newValue))),
                    Arrays.asList("minecraft:shulker",
                            "minecraft:ender_dragon",
                            "minecraft:warden",
                            "minecraft:witch",
                            "minecraft:silverfish",
                            "minecraft:allay",
                            "minecraft:goat",
                            "minecraft:frog",
                            "minecraft:vex",
                            "minecraft:strider",
                            "minecraft:parrot"),
                    "config.resprouted.entity_overlay_blacklist.tooltip")
    ),

    COMMON("config.resprouted.category.common", false,
            new Category[]{GENERAL, WORLD, FOOD, BREWING}
    );

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