package net.ashures.universalmod.util;

import net.ashures.universalmod.UniversalMod;
import net.ashures.universalmod.item.ModItems;
import net.fabricmc.fabric.api.registry.FuelRegistry;

public class ModRegistries {
    public static void registerModExtras() {
        registerFuels();
    }

    private static void registerFuels() {
        UniversalMod.LOGGER.info("Registering Fuels for " + UniversalMod.MOD_ID);
        FuelRegistry registry = FuelRegistry.INSTANCE;

        registry.add(ModItems.ADAMANTIUM_DUST, 6400);
    }
}
