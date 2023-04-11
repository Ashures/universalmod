package net.ashures.universalmod.item;

import net.ashures.universalmod.UniversalMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item ADAMANTIUM_INGOT = registerItem("adamantium_ingot",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    public static final Item ADAMANTIUM_NUGGET = registerItem("adamantium_nugget",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    public static final Item ADAMANTIUM_CLUMP = registerItem("adamantium_clump",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(UniversalMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        UniversalMod.LOGGER.info("Registering Mod Items for " + UniversalMod.MOD_ID);
    }
}
