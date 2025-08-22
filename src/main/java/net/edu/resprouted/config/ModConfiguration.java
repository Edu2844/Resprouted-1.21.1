package net.edu.resprouted.config;

import net.fabricmc.loader.api.FabricLoader;

import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;

public class ModConfiguration {
    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "resprouted.json");

    private int OiledNutritionBonus = 2;
    private float OiledSaturationModifier = 1.5f;
    private List<String> OilableFoodList = new ArrayList<>();

    public ModConfiguration() {
        OilableFoodList.add("minecraft:beef");
        OilableFoodList.add("minecraft:cooked_beef");
        OilableFoodList.add("minecraft:porkchop");
        OilableFoodList.add("minecraft:cooked_porkchop");
        OilableFoodList.add("minecraft:mutton");
        OilableFoodList.add("minecraft:cooked_mutton");
        OilableFoodList.add("minecraft:chicken");
        OilableFoodList.add("minecraft:cooked_chicken");
        OilableFoodList.add("minecraft:rabbit");
        OilableFoodList.add("minecraft:cooked_rabbit");
        OilableFoodList.add("minecraft:salmon");
        OilableFoodList.add("minecraft:cooked_salmon");
        OilableFoodList.add("minecraft:cod");
        OilableFoodList.add("minecraft:cooked_cod");
        OilableFoodList.add("minecraft:tropical_fish");
        OilableFoodList.add("minecraft:bread");
        OilableFoodList.add("minecraft:baked_potato");
        OilableFoodList.add("minecraft:potato");
        OilableFoodList.add("minecraft:carrot");
        OilableFoodList.add("minecraft:golden_carrot");
        OilableFoodList.add("minecraft:beetroot");
        OilableFoodList.add("minecraft:sweet_berries");
        OilableFoodList.add("minecraft:glow_berries");
        OilableFoodList.add("minecraft:apple");
        OilableFoodList.add("minecraft:golden_apple");

        OilableFoodList.add("resprouted:tomato");
        OilableFoodList.add("resprouted:chili_pepper");
        OilableFoodList.add("resprouted:grapes");
        OilableFoodList.add("resprouted:iron_berries");
        OilableFoodList.add("resprouted:olives");

        OilableFoodList.add("farmersdelight:tomato");
        OilableFoodList.add("farmersdelight:onion");
        OilableFoodList.add("farmersdelight:cabbage_leaf");
    }
    public static ModConfiguration load() {
        ModConfiguration configuration = new ModConfiguration();
        if (!CONFIG_FILE.exists()) {
            save(configuration);
            return configuration;
        }
        try (Reader reader = Files.newBufferedReader(CONFIG_FILE.toPath())) {
            configuration = (new GsonBuilder().setPrettyPrinting().create()).fromJson(reader, ModConfiguration.class);
        } catch (IOException e) {
            System.err.println("Error loading Resprouted config: " + e.getMessage());
        }
        return configuration;
    }
    public static void save(ModConfiguration config) {
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
    public List<String> getOliveOilFood() {
        return OilableFoodList;
    }
    public void setOliveOilFood(List<String> foods) {
        this.OilableFoodList = foods != null ? foods : new ArrayList<>();
    }
    private static float limit(float min, float max, float value) {
        return Math.max(min, Math.min(max, value));
    }
    private static int limit(int min, int max, int value) {
        return Math.max(min, Math.min(max, value));
    }
}

