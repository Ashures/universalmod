package net.ashures.universalmod;

import net.ashures.universalmod.event.KeyInputHandler;
import net.fabricmc.api.ClientModInitializer;

public class UniversalModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
    }
}
