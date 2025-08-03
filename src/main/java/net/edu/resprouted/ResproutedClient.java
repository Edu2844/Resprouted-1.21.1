package net.edu.resprouted;

import net.edu.resprouted.event.ModKeybinds;
import net.edu.resprouted.registry.ModClientRegistry;
import net.fabricmc.api.ClientModInitializer;

public class ResproutedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModKeybinds.registerKeybinds();
        ModClientRegistry.RegisterModClientStuffs();
    }
}
