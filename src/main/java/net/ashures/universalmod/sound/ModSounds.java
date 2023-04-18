package net.ashures.universalmod.sound;

import net.ashures.universalmod.UniversalMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds{
    public static final SoundEvent HEALING_STAFF_SUCCESS = register("healing_staff_success");

    public static SoundEvent register(String name) {
        Identifier id = new Identifier(UniversalMod.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }
}
