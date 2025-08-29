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

public class ResproutedConfiguration {
    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "resprouted.json");

    public boolean EnableHoneyBottleEffect = true;
    public boolean EnableVantaOiling = true;
    public boolean EnableOliveOiling = true;
    public boolean BottleEffectTooltips = true;
    public boolean FoodEffectTooltips = false;
    private int OiledNutritionBonus = 2;
    private float OiledSaturationModifier = 1.5f;
    private List<String> OilableFoodWhiteList = new ArrayList<>();
    private List<String> OilableFoodBlackList = new ArrayList<>();
    private List<String> VantaOilWhiteList = new ArrayList<>();
    private List<String> VantaOilBlackList = new ArrayList<>();


    public ResproutedConfiguration() {

        OilableFoodWhiteList.add("farmersdelight:tomato");
        OilableFoodWhiteList.add("farmersdelight:onion");
        OilableFoodWhiteList.add("farmersdelight:cabbage_leaf");
        OilableFoodBlackList.add("modid:example_item");

        VantaOilWhiteList.add("minecraft:bone");
        VantaOilWhiteList.add("farmersdelight:iron_knife");
        VantaOilWhiteList.add("farmersdelight:diamond_knife");
        VantaOilBlackList.add("modid:example_item");

    }
    public static ResproutedConfiguration load() {
        ResproutedConfiguration configuration = new ResproutedConfiguration();
        if (!CONFIG_FILE.exists()) {
            save(configuration);
            return configuration;
        }
        try (Reader reader = Files.newBufferedReader(CONFIG_FILE.toPath())) {
            configuration = (new GsonBuilder().setPrettyPrinting().create()).fromJson(reader, ResproutedConfiguration.class);
        } catch (IOException e) {
            System.err.println("Error loading Resprouted config: " + e.getMessage());
        }
        return configuration;
    }
    public static void save(ResproutedConfiguration config) {
        try (Writer writer = Files.newBufferedWriter(CONFIG_FILE.toPath())) {
            (new GsonBuilder().setPrettyPrinting().create()).toJson(config, writer);
        } catch (IOException e) {
            System.err.println("Error saving Resprouted config: " + e.getMessage());
        }
    }
    public float getOiledSaturationModifier() {
        return OiledSaturationModifier;
    }
    public void setOiledSaturationModifier(float value) {
        this.OiledSaturationModifier = limit(0.1f, 5.0f, value);
    }
    public int getOiledNutritionBonus() {
        return OiledNutritionBonus;
    }
    public void setOiledHungerBonus(int value) {
        this.OiledNutritionBonus = limit(0, 10, value);
    }
    public List<String> getOliveOilFoodWhiteList() {
        return OilableFoodWhiteList;
    }
    public void setOliveOilFoodWhiteList(List<String> foods) {
        this.OilableFoodWhiteList = foods != null ? foods : new ArrayList<>();
    }
    public List<String> getOliveOilFoodBlackList() {
        return OilableFoodBlackList;
    }
    public void setOliveOilFoodBlackList(List<String> oilableFoodBlackList) {
        OilableFoodBlackList = oilableFoodBlackList;
    }
    public List<String> getVantaOilWhiteList() {
        return VantaOilWhiteList;
    }
    public void setVantaOilWhiteList(List<String> vantaOilWhiteList) {
        this.VantaOilWhiteList = vantaOilWhiteList;
    }
    public List<String> getVantaOilBlackList() {
        return VantaOilBlackList;
    }
    public void setVantaOilBlackList(List<String> vantaOilBlackList) {
        VantaOilBlackList = vantaOilBlackList;
    }
    private static float limit(float min, float max, float value) {
        return Math.max(min, Math.min(max, value));
    }
    private static int limit(int min, int max, int value) {
        return Math.max(min, Math.min(max, value));
    }
}

