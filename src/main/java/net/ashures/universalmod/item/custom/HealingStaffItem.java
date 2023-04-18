package net.ashures.universalmod.item.custom;

import net.ashures.universalmod.sound.ModSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class HealingStaffItem extends Item {
    public HealingStaffItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        castSpell(world, user);
        return TypedActionResult.success(user.getMainHandStack(), true);
    }

    public void castSpell(World world, PlayerEntity player) {
        float healAmount = player.getMaxHealth() - player.getHealth();
        BlockPos playerLocation = new BlockPos(player.getPos());
        player.heal(healAmount);
        world.playSound(player, playerLocation, ModSounds.HEALING_STAFF_SUCCESS, SoundCategory.PLAYERS, 1f, 1f);
    }
}
