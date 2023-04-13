package net.ashures.universalmod.item.custom;

import com.mojang.logging.LogUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.slf4j.Logger;

public class AdamantiumGodRodItem extends Item {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final BlockPos BLOCK_POS = new BlockPos(new Vec3d(0, 0, -60000000));

    public AdamantiumGodRodItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        entity.kill();
        return super.useOnEntity(stack, user, entity, hand);
    }
}
