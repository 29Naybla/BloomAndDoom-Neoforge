package com.x29naybla.bloom_and_doom.mixin;

import com.x29naybla.bloom_and_doom.common.entity.LivingEntityMixinAccess;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {

    @Inject(at= {@At("TAIL")}, method = {"restoreFrom"})
    public void restoreFrom(ServerPlayer that, boolean keepEverything, CallbackInfo ci) {
        ((LivingEntityMixinAccess)this).BnD_setZombie(((LivingEntityMixinAccess)that).BnD_isZombie());
    }
}
