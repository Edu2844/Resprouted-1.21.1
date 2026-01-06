package net.edu.resprouted;

import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ResproutedClientConfiguration {
    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "resprouted-client.json");

    public boolean bottleEffectTooltips = true;
    public boolean foodEffectTooltips = false;
    public List<String> effectOverlayBlacklist = new ArrayList<>();

    public ResproutedClientConfiguration() {
        effectOverlayBlacklist.add("minecraft:shulker");
        effectOverlayBlacklist.add("minecraft:ender_dragon");
        effectOverlayBlacklist.add("minecraft:warden");
        effectOverlayBlacklist.add("minecraft:witch");
        effectOverlayBlacklist.add("minecraft:silverfish");
        effectOverlayBlacklist.add("minecraft:allay");
        effectOverlayBlacklist.add("minecraft:goat");
        effectOverlayBlacklist.add("minecraft:frog");
        effectOverlayBlacklist.add("minecraft:vex");
        effectOverlayBlacklist.add("minecraft:strider");
        effectOverlayBlacklist.add("minecraft:parrot");
    }

    public static ResproutedClientConfiguration load() {
        ResproutedClientConfiguration configuration = new ResproutedClientConfiguration();
        if (!CONFIG_FILE.exists()) {
            save(configuration);
            Resprouted.LOGGER.info("Created new client config file for " + Resprouted.MOD_ID);
            return configuration;
        }
        try (Reader reader = Files.newBufferedReader(CONFIG_FILE.toPath())) {
            configuration = (new GsonBuilder().setPrettyPrinting().create()).fromJson(reader, ResproutedClientConfiguration.class);

            if (configuration.effectOverlayBlacklist == null) {
                configuration.effectOverlayBlacklist = new ArrayList<>();
            }

            Resprouted.LOGGER.info("Client config loaded successfully for " + Resprouted.MOD_ID);
        } catch (IOException e) {
            Resprouted.LOGGER.error("Error loading Resprouted client config: {}", e.getMessage());
        }
        return configuration;
    }

    public static void save(ResproutedClientConfiguration config) {
        try (Writer writer = Files.newBufferedWriter(CONFIG_FILE.toPath())) {
            (new GsonBuilder().setPrettyPrinting().create()).toJson(config, writer);
            Resprouted.LOGGER.info("Client config saved successfully for " + Resprouted.MOD_ID);
        } catch (IOException e) {
            Resprouted.LOGGER.error("Error saving Resprouted client config: {}", e.getMessage());
        }
    }

    public boolean isBottleEffectTooltipsEnabled() {
        return bottleEffectTooltips;
    }

    public void setBottleEffectTooltips(boolean v) {
        bottleEffectTooltips = v;
    }

    public boolean isFoodEffectTooltipsEnabled() {
        return foodEffectTooltips;
    }

    public void setFoodEffectTooltips(boolean v) {
        foodEffectTooltips = v;
    }

    public boolean setEntityBlacklisted(String entityId) {
        return effectOverlayBlacklist != null && effectOverlayBlacklist.contains(entityId);
    }

    public List<String> getEffectOverlayBlacklist() {
        return effectOverlayBlacklist;
    }
}
