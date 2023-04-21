package net.ashures.universalmod.event;

import net.ashures.universalmod.item.ModItems;
import net.ashures.universalmod.item.custom.FireStaffItem;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_UNIVERSAL = "key.category.universalmod.universal";
    public static final String KEY_INCREASE_EXPLOSION = "key.universalmod.increase_explosion";
    public static final String KEY_DECREASE_EXPLOSION = "key.universalmod.decrease_explosion";

    public static KeyBinding increaseExplosionKey;
    public static KeyBinding decreaseExplosionKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (increaseExplosionKey.wasPressed()) {
                if (!client.player.getMainHandStack().isEmpty()) {
                    ItemStack itemStack = client.player.getMainHandStack();
                    if (itemStack.getItem() == ModItems.FIRE_STAFF) {
                        FireStaffItem item = (FireStaffItem) itemStack.getItem();
                        int currentPower = item.getExplosionPower();

                        String message = item.setExplosionPower(currentPower + 1, client.player);
                        client.player.sendMessage(Text.of(message), false);
                    }
                }
            }
            if (decreaseExplosionKey.wasPressed()) {
                if (!client.player.getMainHandStack().isEmpty()) {
                    ItemStack itemStack = client.player.getMainHandStack();
                    if (itemStack.getItem() == ModItems.FIRE_STAFF) {
                        FireStaffItem item = (FireStaffItem) itemStack.getItem();
                        int currentPower = item.getExplosionPower();

                        String message = item.setExplosionPower(currentPower - 1, client.player);
                        client.player.sendMessage(Text.of(message), false);
                    }
                }
            }
        });
    }

    public static void register() {
        increaseExplosionKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_INCREASE_EXPLOSION,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_PERIOD,
                KEY_CATEGORY_UNIVERSAL
        ));
        decreaseExplosionKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DECREASE_EXPLOSION,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_COMMA,
                KEY_CATEGORY_UNIVERSAL
        ));

        registerKeyInputs();
    }
}
