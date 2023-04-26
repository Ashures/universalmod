package net.ashures.universalmod.item.custom;

import net.ashures.universalmod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.client.particle.FireworksSparkParticle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class WorldEaterItem extends Item {
    private int spellPower = 4;
    public WorldEaterItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        HitResult raycast = user.raycast(30, 1f, true);
        if (!(raycast.getType() == HitResult.Type.MISS)) {
            castSpell(world, raycast, user);
            return TypedActionResult.success(user.getMainHandStack(), true);
        }
        return super.use(world, user, hand);
    }

    private void castSpell(World world, HitResult raycast, PlayerEntity player){
        Vec3d pos = raycast.getPos();
        Sphere sphere = new Sphere(spellPower, pos.x, pos.y, pos.z);

        for (int point = 0; point < sphere.getNumberOfPoints(); point++) {
            double[] currentPos = sphere.points.get(point);
            BlockPos blockPos = new BlockPos(new Vec3d(currentPos[0], currentPos[1], currentPos[2]));
            BlockState block = world.getBlockState(blockPos);

            if (block.getBlock() instanceof FluidBlock) {
                world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 3);
            } else if (!block.isAir() && !(block.getBlock() == Blocks.BEDROCK)) {
                world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 3);
            }
        }

        LightningEntity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
        lightningEntity.setPosition(pos);
        lightningEntity.setCosmetic(true);

        world.spawnEntity(lightningEntity);
        world.playSound(player, new BlockPos(pos), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.BLOCKS, 1.0f, 1.0f);
        world.playSound(player, new BlockPos(player.getPos()), SoundEvents.ENTITY_ARROW_HIT, SoundCategory.PLAYERS, 1.0f, 1.0f);
    }

    public int getSpellPower() {
        return this.spellPower;
    }

    public void setSpellPower(int power, PlayerEntity player) {
        if (!player.isCreative()) {
            if (power > 6) {
                player.sendMessage(Text.of("Maximum Spell Power reached!"), true);
                return;
            }
        } else {
            if (power > 30) {
                player.sendMessage(Text.of("Maximum Spell Power reached!"), true);
                return;
            }
        }

        if (power < 1) {
            player.sendMessage(Text.of("Minimum Spell Power reached!"), true);
            return;
        }
        this.spellPower = power;
        player.sendMessage(Text.of("Spell Power set to: " + this.spellPower), true);
    }

    public static class Sphere {
        private final int radius;
        private final double[] centre;
        private List<double[]> points;
        public Sphere(int radius, double centreX, double centreY, double centreZ) {
            this.radius = radius;
            this.centre = new double[] { centreX, centreY, centreZ };

            System.out.println("Sphere created.");

            calculatePoints();
        }

        private void calculatePoints() {
            double h = centre[0];
            double k = centre[1];
            double l = centre[2];
            this.points = new ArrayList<>();

            System.out.println("Generating points...");

            for (double x = h - radius; x <= h + radius; x++) {
                for (double y = k - radius; y <= k + radius; y++) {
                    for (double z = l - radius; z <= l + radius; z++) {
                        if (Math.sqrt(Math.pow(h - x, 2) + Math.pow(k - y, 2) + Math.pow(l - z, 2)) < radius) {
                            double[] currentPoint = new double[] { x, y, z };
                            points.add(currentPoint);
                            System.out.println("Point (" + currentPoint[0] + ", " + currentPoint[1] + ", " + currentPoint[2] + ") is a point in the sphere");
                        }
                    }
                }
            }
        }

        public int getNumberOfPoints() {
            return points.size();
        }
    }
}
