package com.x29naybla.bloom_and_doom.common.entity;

import com.x29naybla.bloom_and_doom.common.tag.BnDTags;
import com.x29naybla.bloom_and_doom.common.entity.goal.PlantShootGoal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class AbstractShootingPlant extends AbstractPlant implements RangedAttackMob {
    public net.minecraft.world.entity.AnimationState idleAnimationState = new net.minecraft.world.entity.AnimationState();
    public net.minecraft.world.entity.AnimationState shootingAnimationState = new net.minecraft.world.entity.AnimationState();
    protected static final EntityDataAccessor<Boolean> SHOOTING = SynchedEntityData.defineId(AbstractShootingPlant.class, EntityDataSerializers.BOOLEAN);

    //Properties
    public AbstractShootingPlant(EntityType<? extends AbstractShootingPlant> entityType, Level level, TagKey<Item> substrate, ItemStack seedPacket, @Nullable ItemStack pottedItem) {
        super(entityType, level, substrate, seedPacket, pottedItem);
        this.seedPacket = seedPacket;
        this.pottedItem = pottedItem;
    }

    //Goals and AI
    protected void registerGoals(){
        goalSelector.addGoal(1, new PlantShootGoal(this, 1.5F, 30, 8));
        goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false,
                (target) -> target instanceof LivingEntity livingEntity && (livingEntity.getType().is(BnDTags.Entities.PLANT_ENEMIES) || ((LivingEntityMixinAccess) livingEntity).BnD_isZombie())));
        goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }

    @Override
    public void performRangedAttack(@NotNull LivingEntity target, float distanceFactor) {
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.shootingAnimationState.animateWhen(isShooting(), tickCount);
        this.idleAnimationState.animateWhen(isAlive(), tickCount);
    }

    //Data
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(SHOOTING, false);
    }

    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        getEntityData().set(SHOOTING, compound.getBoolean("Shooting"));
    }

    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Shooting", getEntityData().get(SHOOTING));
    }

    public boolean isShooting(){
        return getEntityData().get(SHOOTING);
    }

    public void setShooting(boolean bool) {
        getEntityData().set(SHOOTING, bool);
    }
}
