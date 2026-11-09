package com.x29naybla.bloom_and_doom.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.x29naybla.bloom_and_doom.common.entity.LivingEntityMixinAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements LivingEntityMixinAccess {
    @Unique
    private static final EntityDataAccessor<Boolean> IS_ZOMBIE = SynchedEntityData.defineId(LivingEntityMixin.class, EntityDataSerializers.BOOLEAN);

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(at= {@At("TAIL")}, method = {"defineSynchedData"})
    private void registerData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(IS_ZOMBIE, false);
    }

    @Inject(at= {@At("TAIL")}, method = {"addAdditionalSaveData"})
    private void writeAdditional(CompoundTag compoundTag, CallbackInfo callbackInfo) {
        compoundTag.putBoolean("IsZombie", this.BnD_isZombie());
    }

    @Inject(at= {@At("TAIL")}, method = {"readAdditionalSaveData"})
    private void readAdditional(CompoundTag compoundTag, CallbackInfo ci) {
        this.BnD_setZombie(compoundTag.getBoolean("IsZombie"));
    }

    @Override
    public boolean BnD_isZombie() {
        return this.entityData.get(IS_ZOMBIE);
    }

    @Override
    public void BnD_setZombie(boolean value) {
        this.entityData.set(IS_ZOMBIE, value);
    }

    @ModifyReturnValue(at=@At("TAIL"), method = {"isInvertedHealAndHarm"})
    private boolean isInvertedHealAndHarm(boolean original) {
        return original || this.BnD_isZombie();
    }

    @Inject(at=@At("HEAD"), method = {"canBeAffected"}, cancellable = true)
    private void canBeAffected(MobEffectInstance effectInstance, CallbackInfoReturnable<Boolean> cir) {
        if (this.BnD_isZombie()) cir.setReturnValue(!effectInstance.is(MobEffects.REGENERATION) && !effectInstance.is(MobEffects.POISON));
    }
}
