package net.ashures.universalmod.item;

import net.ashures.universalmod.UniversalMod;
import net.ashures.universalmod.item.custom.*;
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
    public static final Item LIGHTNING_STAFF = registerItem("lightning_staff",
            new LightningStaffItem(new FabricItemSettings().group(ModItemGroup.UNIVERSAL).maxCount(1)));
    public static final Item FIRE_STAFF = registerItem("fire_staff",
            new FireStaffItem(new FabricItemSettings().group(ModItemGroup.UNIVERSAL).maxCount(1)));
    public static final Item DRAGON_STAFF = registerItem("dragon_staff",
            new DragonStaffItem(new FabricItemSettings().group(ModItemGroup.UNIVERSAL).maxCount(1)));
    public static final Item ADAMANTIUM_GOD_ROD = registerItem("adamantium_god_rod",
            new AdamantiumGodRodItem(new FabricItemSettings().maxCount(1)));
    public static final Item DIVINE_HAMMER = registerItem("divine_hammer",
            new DivineHammerItem(new FabricItemSettings().maxCount(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(UniversalMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        UniversalMod.LOGGER.info("Registering Mod Items for " + UniversalMod.MOD_ID);
    }
}
