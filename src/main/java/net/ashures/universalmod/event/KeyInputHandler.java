package net.ashures.universalmod.event;

import net.ashures.universalmod.item.ModItems;
import net.ashures.universalmod.item.custom.FireStaffItem;
import net.ashures.universalmod.item.custom.WorldEaterItem;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.ItemStack;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_UNIVERSAL = "key.category.universalmod.universal";
    public static final String KEY_INCREASE_POWER = "key.universalmod.increase_power";
    public static final String KEY_DECREASE_POWER = "key.universalmod.decrease_power";

    public static KeyBinding increasePowerKey;
    public static KeyBinding decreasePowerKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (increasePowerKey.wasPressed()) {
                if (!client.player.getMainHandStack().isEmpty()) {
                    ItemStack itemStack = client.player.getMainHandStack();
                    if (itemStack.getItem() == ModItems.FIRE_STAFF) {
                        FireStaffItem item = (FireStaffItem) itemStack.getItem();
                        int currentPower = item.getExplosionPower();

                        item.setExplosionPower(currentPower + 1, client.player);
                    }
                    if (itemStack.getItem() == ModItems.WORLD_EATER) {
                        WorldEaterItem item = (WorldEaterItem) itemStack.getItem();
                        int currentPower = item.getSpellPower();

                        item.setSpellPower(currentPower + 1, client.player);
                    }
                }
            }
            if (decreasePowerKey.wasPressed()) {
                if (!client.player.getMainHandStack().isEmpty()) {
                    ItemStack itemStack = client.player.getMainHandStack();
                    if (itemStack.getItem() == ModItems.FIRE_STAFF) {
                        FireStaffItem item = (FireStaffItem) itemStack.getItem();
                        int currentPower = item.getExplosionPower();

                        item.setExplosionPower(currentPower - 1, client.player);
                    }
                    if (itemStack.getItem() == ModItems.WORLD_EATER) {
                        WorldEaterItem item = (WorldEaterItem) itemStack.getItem();
                        int currentPower = item.getSpellPower();

                        item.setSpellPower(currentPower - 1, client.player);
                    }
                }
            }
        });
    }

    public static void register() {
        increasePowerKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_INCREASE_POWER,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_PERIOD,
                KEY_CATEGORY_UNIVERSAL
        ));
        decreasePowerKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_DECREASE_POWER,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_COMMA,
                KEY_CATEGORY_UNIVERSAL
        ));

        registerKeyInputs();
    }
}
