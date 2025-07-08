package net.edu.resprouted.event;

import net.edu.resprouted.effect.ModEffects;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {
    public static KeyBinding firepowerattack;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            if (client.player.hasStatusEffect(ModEffects.FIRE_POWER)) {

                Text keyName = firepowerattack.getBoundKeyLocalizedText();

                client.player.sendMessage(
                        Text.translatable("text.resprouted.press_to_launch",
                                keyName.copy().formatted(Formatting.WHITE)
                        ).formatted(Formatting.WHITE),
                        true
                );

            }

            if(firepowerattack.wasPressed()) {
                assert client.player != null;
                if (client.player.hasStatusEffect(ModEffects.FIRE_POWER)) {
                    ClientPlayNetworking.send(new FireballPayload());
                    client.player.swingHand(Hand.MAIN_HAND);
                }
            }
        });
    }
    public static void registerKeybinds() {

        firepowerattack = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.resprouted.firepower",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                "category.resprouted.keys"
        ));
        registerKeyInputs();
    }
}
