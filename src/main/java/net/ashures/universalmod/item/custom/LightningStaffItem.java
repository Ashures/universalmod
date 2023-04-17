package net.ashures.universalmod.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LightningStaffItem extends Item {
    public LightningStaffItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        HitResult blockCheck = user.raycast(40, 0, true);
        BlockState hitBlock = world.getBlockState(new BlockPos(blockCheck.getPos()));

        if (!hitBlock.getBlock().equals(Blocks.AIR)) {
            user.sendMessage(hitBlock.getBlock().getName(), false);
            castSpell(world, blockCheck.getPos(), user);
            return TypedActionResult.success(user.getMainHandStack(), true);
        }

        return super.use(world, user, hand);
    }

    public void castSpell(World world, Vec3d spawnPos, PlayerEntity player) {
        LightningEntity entity = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
        entity.setPosition(spawnPos);

        world.spawnEntity(entity);
    }
}
