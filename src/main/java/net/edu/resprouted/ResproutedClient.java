package net.edu.resprouted;

import net.edu.resprouted.entity.client.ModEntityRenderers;
import net.edu.resprouted.event.ModClientEvents;
import net.edu.resprouted.event.ModKeyInputHandler;
import net.edu.resprouted.registry.ResproutedClientRegistry;
import net.edu.resprouted.screen.ModScreenHandlers;
import net.edu.resprouted.screen.custom.AdvancedCondenserScreen;
import net.edu.resprouted.screen.custom.BrewingBarrelScreen;
import net.edu.resprouted.screen.custom.BasicCondenserScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class ResproutedClient implements ClientModInitializer {
    public static ResproutedClientConfiguration CLIENT_CONFIG = new ResproutedClientConfiguration();

    @Override
    public void onInitializeClient() {
        ResproutedClient.CLIENT_CONFIG = ResproutedClientConfiguration.load();
        ModClientEvents.registerClientEvents();
        ModKeyInputHandler.registerKeybinds();
        ResproutedClientRegistry.registerModClientStuffs();
        ModEntityRenderers.registerLivingEntityRenderers();
        HandledScreens.register(ModScreenHandlers.CONDENSER_SCREEN_HANDLER, BasicCondenserScreen::new);
        HandledScreens.register(ModScreenHandlers.ADVANCED_CONDENSER_SCREEN_HANDLER, AdvancedCondenserScreen::new);
        HandledScreens.register(ModScreenHandlers.BREWING_BARREL_SCREEN_HANDLER, BrewingBarrelScreen::new);
    }
}
