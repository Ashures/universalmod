package net.ashures.universalmod;

import net.ashures.universalmod.block.ModBlocks;
import net.ashures.universalmod.item.ModItems;
import net.ashures.universalmod.sound.ModSounds;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UniversalMod implements ModInitializer {
	public static final String MOD_ID = "universalmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModSounds.registerModSounds();
	}
}
