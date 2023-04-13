package net.ashures.universalmod.item;

import net.ashures.universalmod.UniversalMod;
import net.ashures.universalmod.item.custom.AdamantiumCompassItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item IMPURE_ADAMANTIUM = registerItem("impure_adamantium",
            new Item(new FabricItemSettings().group(ModItemGroup.UNIVERSAL)));
    public static final Item ADAMANTIUM_SHARD = registerItem("adamantium_shard",
            new Item(new FabricItemSettings().group(ModItemGroup.UNIVERSAL)));
    public static final Item ADAMANTIUM_INGOT = registerItem("adamantium_ingot",
            new Item(new FabricItemSettings().group(ModItemGroup.UNIVERSAL)));
    public static final Item ADAMANTIUM_COMPASS = registerItem("adamantium_compass",
            new AdamantiumCompassItem(new FabricItemSettings().group(ModItemGroup.UNIVERSAL)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(UniversalMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        UniversalMod.LOGGER.info("Registering Mod Items for " + UniversalMod.MOD_ID);
    }
}
