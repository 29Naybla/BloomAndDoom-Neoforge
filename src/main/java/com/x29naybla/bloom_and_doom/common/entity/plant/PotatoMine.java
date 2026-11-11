package com.x29naybla.bloom_and_doom.common.entity.plant;

import com.x29naybla.bloom_and_doom.common.entity.ExplosivePlant;
import com.x29naybla.bloom_and_doom.common.registry.BnDItems;
import com.x29naybla.bloom_and_doom.common.tag.BnDTags;
import com.x29naybla.bloom_and_doom.common.registry.BnDSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class PotatoMine extends ExplosivePlant {
    protected static final EntityDataAccessor<Boolean> ARMED = SynchedEntityData.defineId(PotatoMine.class, EntityDataSerializers.BOOLEAN);
    public int armingTime = 0;
    public int maxArmingTime = 40;

    //Properties
    public PotatoMine(EntityType<? extends PotatoMine> entityType, Level level) {
        super(entityType, level, BnDTags.Items.SUSTAINS_POTATO_MINES, BnDItems.POTATO_MINE_SEED_PACKET.toStack(), BnDItems.POTTED_POTATO_MINE.toStack(), 1, 180, BnDSounds.SPUDOW);
    }

    //Goals and AI
    public void tick() {
        if(this.isAlive()){
            if (!getArmed()){
                this.armingTime += 1;
            }

            if (this.armingTime < 0) this.armingTime = 0;

            if (getArmed() || this.armingTime >= maxArmingTime){
                this.armingTime = maxArmingTime;
                setArmed(true);
            }
        }
        super.tick();
    }

    @Override
    public void aiStep() {
        super.aiStep();
    }

    //GeckoLib
    /*
    protected <E extends PotatoMine> PlayState animController(final AnimationState<E> event) {
        if (!getArmed()){
            event.setAnimation(ARMING);
        }
        return PlayState.CONTINUE;
    }
     */

    //Data
    public boolean getArmed(){
        return this.entityData.get(ARMED);
    }

    public void setArmed(boolean bool) {
        this.entityData.set(ARMED, bool);
    }

    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ARMED, false);
    }

    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setArmed(compound.getBoolean("Armed"));
    }

    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Armed", getArmed());
    }
}
