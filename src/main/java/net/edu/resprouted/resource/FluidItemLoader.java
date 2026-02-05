package net.edu.resprouted.resource;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.edu.resprouted.Resprouted;
import net.edu.resprouted.fluid.data.FluidItemMapping;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FluidItemLoader implements SimpleSynchronousResourceReloadListener {
    public static final Identifier ID = Identifier.of(Resprouted.MOD_ID, "fluid_items");
    private static final List<FluidItemMapping> ENTRIES = new ArrayList<>();

    public static List<FluidItemMapping> getEntries() {
        return ENTRIES;
    }

    @Override
    public Identifier getFabricId() {
        return ID;
    }

    @Override
    public void reload(ResourceManager manager) {
        ENTRIES.clear();
        String folder = "fluid_items";
        Map<Identifier, Resource> resources = manager.findResources(folder, path -> path.getPath().endsWith(".json"));

        for (Map.Entry<Identifier, Resource> entry : resources.entrySet()) {
            Identifier id = entry.getKey();
            Resource resource = entry.getValue();
            try (InputStream stream = resource.getInputStream();
                 Reader reader = new InputStreamReader(stream)) {
                JsonElement json = JsonParser.parseReader(reader);
                Codec<FluidItemMapping> codec = FluidItemMapping.CODEC;
                DataResult<FluidItemMapping> result = codec.parse(JsonOps.INSTANCE, json);

                result.resultOrPartial(error -> System.err.println("[Resprouted] Error parsing fluid item " + id + ": " + error)).ifPresent(ENTRIES::add);

            } catch (Exception e) {
                System.err.println("[Resprouted] Error loading fluid item " + id + ": " + e);
            }
        }

        System.out.println("[Resprouted] Loaded " + ENTRIES.size() + " fluid item mappings.");
    }
}
