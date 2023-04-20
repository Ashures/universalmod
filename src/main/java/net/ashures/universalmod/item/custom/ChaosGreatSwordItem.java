package net.ashures.universalmod.item.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class ChaosGreatSwordItem extends SwordItem {
    private final int LIGHTNING_SPAWN_COUNT = 5;

    public ChaosGreatSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = target.getWorld();

        for (int i = 0; i < LIGHTNING_SPAWN_COUNT; i++) {
            LightningEntity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
            lightningEntity.setPosition(target.getPos());
            world.spawnEntity(lightningEntity);
        }

        if (!target.isAlive()) {
            int currentAmplifier = 0;
            if (attacker.hasStatusEffect(StatusEffects.STRENGTH)) {
                currentAmplifier = attacker.getStatusEffect(StatusEffects.STRENGTH).getAmplifier() + 1;
            }

            attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, currentAmplifier));
        }
        stack.damage(1, attacker, e -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        return super.postHit(stack, target, attacker);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        HitResult raycast = user.raycast(30, 1, true);
        Vec3d blockCheck = raycast.getPos();

        if (!(raycast.getType() == HitResult.Type.MISS)) {
            TntEntity tntEntity = new TntEntity(world, blockCheck.x, blockCheck.y, blockCheck.z, user);
            tntEntity.setFuse(0);
            world.createExplosion(tntEntity, tntEntity.getX(), tntEntity.getBodyY(0.0625), tntEntity.getZ(), 5.0f, Explosion.DestructionType.BREAK);
            return TypedActionResult.success(user.getMainHandStack(), true);
        }

        return super.use(world, user, hand);
    }
}
