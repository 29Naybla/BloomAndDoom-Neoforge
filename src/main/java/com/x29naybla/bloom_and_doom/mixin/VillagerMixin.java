package com.x29naybla.bloom_and_doom.mixin;

import com.x29naybla.bloom_and_doom.common.entity.LivingEntityMixinAccess;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Villager.class)
public class VillagerMixin {
    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    protected void mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if(((LivingEntityMixinAccess) player).BnD_isZombie()) {
            this.setUnhappy();
            cir.setReturnValue(InteractionResult.FAIL);
        }
    }

    @Shadow
    private void setUnhappy() {}
}
