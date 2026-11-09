package com.x29naybla.bloom_and_doom.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.x29naybla.bloom_and_doom.common.entity.LivingEntityMixinAccess;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.sensing.VillagerHostilesSensor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(VillagerHostilesSensor.class)
public class VillagerHostilesSensorMixin {

    @ModifyReturnValue(at= {@At(value = "TAIL")}, method = "isMatchingEntity")
    private boolean BnD_isZombiePlayerClose (boolean original, LivingEntity attacker, LivingEntity target) {
        return original || ((LivingEntityMixinAccess) target).BnD_isZombie() && target.distanceToSqr(attacker) <= (double)(8 * 8);
    }
}
