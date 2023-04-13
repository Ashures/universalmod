package net.ashures.universalmod.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AdamantiumCompassItem extends Item {
    private static final BlockPos BLOCK_POS = new BlockPos(new Vec3d(0, 0, -60000000));

    public AdamantiumCompassItem(Settings settings) {
        super(settings);
    }

    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(world.isClient) {
            NbtCompound nbtCompound = stack.getOrCreateNbt();
            writeNbt(BLOCK_POS, nbtCompound);
        }
    }

    private void writeNbt(BlockPos pos, NbtCompound nbt) {
        nbt.put("NorthPos", NbtHelper.fromBlockPos(pos));
    }
}
