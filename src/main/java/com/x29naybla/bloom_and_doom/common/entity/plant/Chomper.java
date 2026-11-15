package com.x29naybla.bloom_and_doom.common.entity.plant;

import com.x29naybla.bloom_and_doom.common.entity.Plant;
import com.x29naybla.bloom_and_doom.common.entity.goal.ChompGoal;
import com.x29naybla.bloom_and_doom.common.registry.BnDItems;
import com.x29naybla.bloom_and_doom.common.tag.BnDTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class Chomper extends Plant {
    public AnimationState idleAnimationState = new AnimationState();
    public AnimationState breathingAnimationState = new AnimationState();
    public AnimationState chompAnimationState = new AnimationState();
    public AnimationState chewAnimationState = new AnimationState();
    public static final EntityDataAccessor<Integer> CHEWING = SynchedEntityData.defineId(Chomper.class, EntityDataSerializers.INT);
    public final int maxChewTime = 350;

    //Properties
    public Chomper(EntityType<? extends Plant> entityType, Level level) {
        super(entityType, level, BnDTags.Items.SUSTAINS_CHOMPERS, BnDItems.CHOMPER_SEED_PACKET.toStack(), null);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Plant.createAttributes()
                .add(Attributes.ATTACK_SPEED, 350)
                .add(Attributes.ATTACK_DAMAGE, 4)
                .add(Attributes.ENTITY_INTERACTION_RANGE, 2);
    }

    //Goals and AI
    protected void registerGoals(){
        goalSelector.addGoal(1, new ChompGoal(this, 2.0d));
        goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.idleAnimationState.animateWhen(isAlive(), tickCount);
        this.breathingAnimationState.animateWhen(!isChewing(), tickCount);
        this.chompAnimationState.animateWhen(getChewing()>=(15*20), tickCount);
        this.chewAnimationState.animateWhen(getChewing()<(16*20) && getChewing()>0, tickCount);
    }

    @Override
    public void tick() {
        if (!level().isClientSide) {
            if (getChewing() >= 1) {
                setChewing(getChewing()-1);
            } else
                setChewing(0);
        }
        super.tick();
    }

    //Data
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CHEWING, 0);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("Chewing")) {
            setChewing(compound.getInt("Chewing"));
        }
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Chewing", getEntityData().get(CHEWING));
    }

    public int getChewing() {
        return entityData.get(CHEWING);
    }

    public void setChewing(int chomping) {
        entityData.set(CHEWING, chomping);
    }

    public boolean isChewing() {
        return entityData.get(CHEWING) == 0;
    }

    public void startChewing() {
        entityData.set(CHEWING, maxChewTime);
    }

    public void eat(Entity entity) {
        entity.kill();
        entity.remove(RemovalReason.KILLED);
    }
}
