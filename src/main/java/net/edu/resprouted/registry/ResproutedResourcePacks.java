package net.edu.resprouted.registry;

import net.edu.resprouted.Resprouted;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ResproutedResourcePacks {

    private static void registerBuiltinResourcePack(ModContainer modContainer, String forModID) {
        ResourceManagerHelper.registerBuiltinResourcePack(
                Identifier.of(Resprouted.MOD_ID, forModID + "_rp"),
                modContainer,
                Text.translatable("pack." + Resprouted.MOD_ID + "." + forModID),
                ResourcePackActivationType.ALWAYS_ENABLED
        );
    }
    private static void registerBuiltinDataPack(ModContainer modContainer, String packId) {
        ResourceManagerHelper.registerBuiltinResourcePack(
                Identifier.of(Resprouted.MOD_ID, packId + "_dp"),
                modContainer,
                Text.translatable("datapack." + Resprouted.MOD_ID + "." + packId),
                ResourcePackActivationType.ALWAYS_ENABLED
        );
    }

    public static void registerBuiltinResourcePacks() {
        Optional<ModContainer> modContainer = FabricLoader.getInstance().getModContainer(Resprouted.MOD_ID);

        if (modContainer.isPresent()) {

            if (Resprouted.isModLoaded(Resprouted.EATING_ANIMATIONS_MOD_ID)) {
                registerBuiltinResourcePack(modContainer.get(), Resprouted.EATING_ANIMATIONS_MOD_ID);
            }
            if (Resprouted.isModLoaded(Resprouted.AMENDMENTS_MOD_ID)) {
                registerBuiltinDataPack(modContainer.get(), Resprouted.AMENDMENTS_MOD_ID);
            }
        }
    }
}

