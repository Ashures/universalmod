package net.ashures.universalmod.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
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
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        LightningEntity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
        lightningEntity.setCosmetic(true);
        lightningEntity.setPosition(user.getPos());
        world.spawnEntity(lightningEntity);

        return super.use(world, user, hand);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return isActive;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player) {
            if (selected) {
                if (world.isClient()) {
                    isActive = true;
                } else {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 5, 9, false, false));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 5, 9, false, false));
                }
            } else {
                if (world.isClient()) {
                    isActive = false;
                }
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
