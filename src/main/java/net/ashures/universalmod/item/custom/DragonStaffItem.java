package net.ashures.universalmod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DragonStaffItem extends Item {
    public DragonStaffItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        castSpell(world, user);
        return TypedActionResult.success(user.getMainHandStack(), true);
    }

    public void castSpell(World world, PlayerEntity player) {
        Vec3d playerRotation = player.getRotationVec(1f);
        Vec3d direction = new Vec3d(playerRotation.x, playerRotation.y, playerRotation.z);
        Vec3d position = new Vec3d(player.getX(), player.getEyeY() - 0.4, player.getZ());

        DragonFireballEntity entity = new DragonFireballEntity(world, player, direction.x, direction.y, direction.z);
        entity.speed = 4.0f;
        entity.setPosition(position);

        world.spawnEntity(entity);
    }
}
