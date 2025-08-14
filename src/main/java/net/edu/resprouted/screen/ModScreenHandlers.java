package net.edu.resprouted.screen;

import net.edu.resprouted.Resprouted;
import net.edu.resprouted.screen.custom.AdvancedCondenserScreenHandler;
import net.edu.resprouted.screen.custom.CondenserScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {

    public static final ScreenHandlerType<CondenserScreenHandler> CONDENSER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Resprouted.MOD_ID, "condenser_screen_handler"),
                    new ExtendedScreenHandlerType<>(CondenserScreenHandler::new, BlockPos.PACKET_CODEC));

    public static final ScreenHandlerType<AdvancedCondenserScreenHandler> ADVANCED_CONDENSER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Resprouted.MOD_ID, "advanced_condenser_screen_handler"),
                    new ExtendedScreenHandlerType<>(AdvancedCondenserScreenHandler::new, BlockPos.PACKET_CODEC));


    public static void registerScreenHandlers() {
        Resprouted.LOGGER.info("Registering Screen Handlers for " + Resprouted.MOD_ID);
    }
}
