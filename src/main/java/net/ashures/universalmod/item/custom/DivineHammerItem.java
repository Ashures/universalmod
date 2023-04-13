package net.ashures.universalmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DivineHammerItem extends Item {
    public DivineHammerItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.kill();
        return super.postHit(stack, target, attacker);
    }
}
