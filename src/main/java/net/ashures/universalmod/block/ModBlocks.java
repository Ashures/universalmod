package net.ashures.universalmod.block;

import net.ashures.universalmod.UniversalMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final Block ADAMANTIUM_BLOCK = registerBlock("adamantium_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool().sounds(BlockSoundGroup.NETHERITE)), ItemGroup.BUILDING_BLOCKS);

    public static final Block ADAMANTIUM_ORE = registerBlock("adamantium_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE), UniformIntProvider.create(4, 8)), ItemGroup.BUILDING_BLOCKS);

    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(UniversalMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM, new Identifier(UniversalMod.MOD_ID, name), new BlockItem(block, new FabricItemSettings().group(group)));
    }

    public static void registerModBlocks() {
        UniversalMod.LOGGER.info("Registering Mod Blocks for " + UniversalMod.MOD_ID);
    }
}
