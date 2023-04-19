package net.ashures.universalmod.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DivineHammerItem extends Item {
    private boolean isActive = false;

    public DivineHammerItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.kill();
        return super.postHit(stack, target, attacker);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return isActive;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            if (entity instanceof PlayerEntity player) {
                if (selected) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 5, 9));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 5, 9));
                    isActive = true;
                } else {
                    player.setNoGravity(false);
                    isActive = false;
                }
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
