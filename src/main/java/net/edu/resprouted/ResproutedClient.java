package net.edu.resprouted;

import net.edu.resprouted.event.ModKeybinds;
import net.edu.resprouted.registry.ModClientRegistry;
import net.edu.resprouted.screen.ModScreenHandlers;
import net.edu.resprouted.screen.custom.CondenserScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ResproutedClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModKeybinds.registerKeybinds();
        ModClientRegistry.RegisterModClientStuffs();
        HandledScreens.register(ModScreenHandlers.CONDENSER_SCREEN_HANDLER, CondenserScreen::new);
    }
}
