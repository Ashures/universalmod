package net.ashures.universalmod.mixin;

import com.mojang.authlib.GameProfile;
import net.ashures.universalmod.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class ModPlayerData extends LivingEntity {
    @Shadow @Final private PlayerAbilities abilities;
    @Shadow public abstract boolean isCreative();


    protected ModPlayerData(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo info) {
        if (!isCreative() && !isSpectator()) {
            if (getMainHandStack().getItem() == ModItems.DIVINE_HAMMER) {
                abilities.flying = true;
                abilities.invulnerable = true;
            } else {
                abilities.flying = false;
                abilities.invulnerable = false;
            }
        }
    }
}
