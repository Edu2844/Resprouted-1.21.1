package net.edu.resprouted;

import net.fabricmc.loader.api.FabricLoader;

import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;

public class ResproutedCommonConfiguration {
    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "resprouted-common.json");
    public GeneralSettings general = new GeneralSettings();
    public WorldSettings world = new WorldSettings();
    public FoodSettings food = new FoodSettings();
    public BrewingSettings brewing = new BrewingSettings();

    public static class GeneralSettings {
        private boolean enableHoneyBottleRegenerationEffect = true;
        private boolean enableDyeByRightClick = false;
        private boolean enablePaintedPlanks = true;
        private boolean enableTables = true;
        private boolean enableChairs = true;
        private boolean enableStools = true;
        private boolean enableClayWalls = true;
        private boolean enableVanillaBlockVariations = true;
        private boolean enableVantaOiling = true;
        private List<String> vantaOilWhiteList = new ArrayList<>();
        private List<String> vantaOilBlackList = new ArrayList<>();

        public GeneralSettings() {
            vantaOilWhiteList.add("minecraft:bone");
            vantaOilWhiteList.add("farmersdelight:iron_knife");
            vantaOilWhiteList.add("farmersdelight:diamond_knife");
        }

        //Painted Planks
        public boolean isEnablePaintedPlanks() {
            return enablePaintedPlanks;
        }

        public void setEnablePaintedPlanks(boolean enablePaintedPlanks) {
            this.enablePaintedPlanks = enablePaintedPlanks;
        }

        //Tables
        public boolean isEnableTables() {
            return enableTables;
        }

        public void setEnableTables(boolean enableTables) {
            this.enableTables = enableTables;
        }

        //Chairs
        public boolean isEnableChairs() {
            return enableChairs;
        }

        public void setEnableChairs(boolean enableChairs) {
            this.enableChairs = enableChairs;
        }

        //Stools
        public boolean isEnableStools() {
            return enableStools;
        }

        public void setEnableStools(boolean enableStools) {
            this.enableStools = enableStools;
        }

        //Clay Walls
        public boolean isEnableClayWalls() {
            return enableClayWalls;
        }

        public void setEnableClayWalls(boolean enableClayWallBlocks) {
            this.enableClayWalls = enableClayWallBlocks;
        }

        //Vanilla stones
        public boolean isEnableVanillaBlockVariations() {
            return enableVanillaBlockVariations;
        }

        public void setEnableVanillaBlockVariations(boolean enableVanillaBlockVariations) {
            this.enableVanillaBlockVariations = enableVanillaBlockVariations;
        }

        //Honey Bottle
        public boolean isEnableHoneyBottleRegenerationEffect() {
            return enableHoneyBottleRegenerationEffect;
        }

        public void setEnableHoneyBottleRegenerationEffect(boolean enableHoneyBottleRegenerationEffect) {
            this.enableHoneyBottleRegenerationEffect = enableHoneyBottleRegenerationEffect;
        }

        //Dyeable Jars & Urns
        public boolean isEnableDyeByRightClick() {
            return enableDyeByRightClick;
        }

        public void setEnableDyeByRightClick(boolean enableDyeByRightClick) {
            this.enableDyeByRightClick = enableDyeByRightClick;
        }

        //Vanta Oiling
        public boolean isEnableVantaOiling() {
            return enableVantaOiling;
        }

        public void setEnableVantaOiling(boolean enableVantaOiling) {
            this.enableVantaOiling = enableVantaOiling;
        }

        public List<String> getVantaOilWhiteList() {
            return vantaOilWhiteList;
        }

        public void setVantaOilWhiteList(List<String> vantaOilWhiteList) {
            this.vantaOilWhiteList = vantaOilWhiteList != null ? vantaOilWhiteList : new ArrayList<>();
        }

        public List<String> getVantaOilBlackList() {
            return vantaOilBlackList;
        }

        public void setVantaOilBlackList(List<String> vantaOilBlackList) {
            this.vantaOilBlackList = vantaOilBlackList != null ? vantaOilBlackList : new ArrayList<>();
        }
    }

    public static class WorldSettings {
        private boolean enableSeedDrops = true;
        private int seedsDropRate = 7;
        private boolean grapeDropNeedsTool = false;
        private List<String> grapeSeedsToolWhitelist = new ArrayList<>();

        public WorldSettings() {
            grapeSeedsToolWhitelist.add("minecraft:iron_hoe");
            grapeSeedsToolWhitelist.add("minecraft:diamond_hoe");
            grapeSeedsToolWhitelist.add("minecraft:netherite_hoe");
        }

        public boolean isEnableSeedDrops() {
            return enableSeedDrops;
        }

        public void setEnableSeedDrops(boolean enableSeedDrops) {
            this.enableSeedDrops = enableSeedDrops;
        }

        public int getSeedsDropRate() {
            return seedsDropRate;
        }

        public void setSeedsDropRate(int seedsDropRate) {
            this.seedsDropRate = limit(1, 100, seedsDropRate);
        }

        public boolean isGrapeDropNeedsTool() {
            return grapeDropNeedsTool;
        }

        public void setGrapeDropNeedsTool(boolean grapeDropNeedsTool) {
            this.grapeDropNeedsTool = grapeDropNeedsTool;
        }

        public List<String> getGrapeSeedsToolWhitelist() {
            return grapeSeedsToolWhitelist;
        }

        public void setGrapeSeedsToolWhitelist(List<String> grapeSeedsToolWhitelist) {
            this.grapeSeedsToolWhitelist = grapeSeedsToolWhitelist != null ? grapeSeedsToolWhitelist : new ArrayList<>();
        }
    }

    public static class FoodSettings {
        private boolean enableOliveOiling = true;
        private int oiledNutritionBonus = 2;
        private float oiledSaturationModifier = 1.5f;
        private List<String> oilableFoodWhiteList = new ArrayList<>();
        private List<String> oilableFoodBlackList = new ArrayList<>();

        public FoodSettings() {
            oilableFoodWhiteList.add("farmersdelight:tomato");
            oilableFoodWhiteList.add("farmersdelight:onion");
            oilableFoodWhiteList.add("farmersdelight:cabbage_leaf");
        }

        public boolean isEnableOliveOiling() {
            return enableOliveOiling;
        }

        public void setEnableOliveOiling(boolean enableOliveOiling) {
            this.enableOliveOiling = enableOliveOiling;
        }

        public int getOiledNutritionBonus() {
            return oiledNutritionBonus;
        }

        public void setOiledNutritionBonus(int oiledNutritionBonus) {
            this.oiledNutritionBonus = limit(0, 10, oiledNutritionBonus);
        }

        public float getOiledSaturationModifier() {
            return oiledSaturationModifier;
        }

        public void setOiledSaturationModifier(float oiledSaturationModifier) {
            this.oiledSaturationModifier = limit(0.1f, 5.0f, oiledSaturationModifier);
        }

        public List<String> getOilableFoodWhiteList() {
            return oilableFoodWhiteList;
        }

        public void setOilableFoodWhiteList(List<String> oilableFoodWhiteList) {
            this.oilableFoodWhiteList = oilableFoodWhiteList != null ? oilableFoodWhiteList : new ArrayList<>();
        }

        public List<String> getOilableFoodBlackList() {
            return oilableFoodBlackList;
        }

        public void setOilableFoodBlackList(List<String> oilableFoodBlackList) {
            this.oilableFoodBlackList = oilableFoodBlackList != null ? oilableFoodBlackList : new ArrayList<>();
        }
    }

    public static class BrewingSettings {
        private int minBrewQualityChange = -1;
        private int maxBrewQualityChange = 4;
        private int maxBrewTime = 12000;

        public int getMinBrewQualityChange() {
            return minBrewQualityChange;
        }

        public void setMinBrewQualityChange(int minBrewQualityChange) {
            this.minBrewQualityChange = minBrewQualityChange;
        }

        public int getMaxBrewQualityChange() {
            return maxBrewQualityChange;
        }

        public void setMaxBrewQualityChange(int maxBrewQualityChange) {
            this.maxBrewQualityChange = maxBrewQualityChange;
        }

        public int getMaxBrewTime() {
            return maxBrewTime;
        }

        public void setMaxBrewTime(int maxBrewTime) {
            this.maxBrewTime = limit(200, 120000, maxBrewTime);
        }
    }

    public ResproutedCommonConfiguration() {
    }

    public static ResproutedCommonConfiguration load() {
        ResproutedCommonConfiguration configuration = new ResproutedCommonConfiguration();
        if (!CONFIG_FILE.exists()) {
            save(configuration);
            Resprouted.LOGGER.info("Created new common config file for " + Resprouted.MOD_ID);
            return configuration;
        }
        try (Reader reader = Files.newBufferedReader(CONFIG_FILE.toPath())) {
            configuration = (new GsonBuilder().setPrettyPrinting().create()).fromJson(reader, ResproutedCommonConfiguration.class);
            Resprouted.LOGGER.info("Common config loaded successfully for " + Resprouted.MOD_ID);
        } catch (IOException e) {
            Resprouted.LOGGER.error("Error loading Resprouted common config: {}", e.getMessage());
        }
        return configuration;
    }

    public static void save(ResproutedCommonConfiguration config) {
        try (Writer writer = Files.newBufferedWriter(CONFIG_FILE.toPath())) {
            (new GsonBuilder().setPrettyPrinting().create()).toJson(config, writer);
            Resprouted.LOGGER.info("Common config saved successfully for " + Resprouted.MOD_ID);
        } catch (IOException e) {
            Resprouted.LOGGER.error("Error saving Resprouted common config: {}", e.getMessage());
        }
    }

    private static float limit(float min, float max, float value) {
        return Math.max(min, Math.min(max, value));
    }

    private static int limit(int min, int max, int value) {
        return Math.max(min, Math.min(max, value));
    }

    public GeneralSettings getGeneral() {
        return general;
    }

    public WorldSettings getWorld() {
        return world;
    }

    public FoodSettings getFood() {
        return food;
    }

    public BrewingSettings getBrewing() {
        return brewing;
    }
}
