package net.ashures.universalmod.item;

import net.ashures.universalmod.UniversalMod;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup UNIVERSAL = FabricItemGroupBuilder.build(new Identifier(UniversalMod.MOD_ID, "universal"),
            () -> new ItemStack(ModItems.ADAMANTIUM_INGOT));
}