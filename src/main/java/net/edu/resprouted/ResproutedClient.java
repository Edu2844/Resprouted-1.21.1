package net.edu.resprouted;

import net.edu.resprouted.event.ModKeybinds;
import net.edu.resprouted.registry.ModClientRegistries;
import net.fabricmc.api.ClientModInitializer;

public class ResproutedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModKeybinds.registerKeybinds();
        ModClientRegistries.RegisterModClientStuffs();




    }
}
