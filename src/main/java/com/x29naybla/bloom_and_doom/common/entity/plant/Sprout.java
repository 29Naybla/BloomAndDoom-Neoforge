package com.x29naybla.bloom_and_doom.common.entity.plant;

import com.x29naybla.bloom_and_doom.common.entity.Plant;
import com.x29naybla.bloom_and_doom.common.entity.SproutVariant;
import com.x29naybla.bloom_and_doom.common.registry.BnDItems;
import com.x29naybla.bloom_and_doom.common.tag.BnDTags;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class Sprout extends Plant {
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Sprout.class, EntityDataSerializers.INT);
    private int age;

    //Properties
    public Sprout(EntityType<? extends Sprout> entityType, Level level) {
        super(entityType, level, BnDTags.Items.SUSTAINS_SPROUTS, null, BnDItems.POTTED_SPROUT.toStack());
    }

    @Override
    public @NotNull SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor level, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        SproutVariant[] sproutVariant = Arrays.stream(SproutVariant.values()).filter(SproutVariant::accessibleRandom).toArray(SproutVariant[]::new);
        SproutVariant randomVariant = Util.getRandom(sproutVariant, this.random);
        if (this.getVariant() == null || !this.getVariant().accessibleRandom()) this.setVariant(randomVariant);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    //Goals and AI
    @Override
    protected void registerGoals(){}

    @Override
    public void aiStep() {
        super.aiStep();
    }

    //Data
    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setAge(compound.getInt("Age"));
        this.entityData.set(VARIANT, compound.getInt("Variant"));
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Age", this.age);
        compound.putInt("Variant", this.getTypeVariant());
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, 0);
    }

    private int getTypeVariant(){
        return this.entityData.get(VARIANT);
    }

    public SproutVariant getVariant(){
        return SproutVariant.byId(this.getTypeVariant() & 255);
    }

    public void setVariant(SproutVariant variant){
        this.entityData.set(VARIANT, variant.getId() & 255);
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public void ageUp(int offset) {
        this.setAge(this.age + offset * 20);
    }

    @Override
    public void setAge(int age) {
        this.age = age;
        if (this.age >= 0) {
            this.ageUp();
        }
    }

    private Plant checkPlantForGrowth() {
        if (EntityType.byString(this.getVariant().getPlant()).isPresent()) {
            return (Plant) EntityType.byString(this.getVariant().getPlant()).get().create(this.level());
        }
        SproutVariant[] sproutVariant = Arrays.stream(SproutVariant.values()).filter(SproutVariant::accessibleRandom).toArray(SproutVariant[]::new);
        SproutVariant randomVariant = Util.getRandom(sproutVariant, this.random);
        return EntityType.byString(randomVariant.getPlant()).isPresent() ? (Plant) EntityType.byString(randomVariant.getPlant()).get().create(this.level()) : null;
    }

    private void ageUp() {
        if (this.level() instanceof ServerLevel serverlevel) {
            Plant plant = checkPlantForGrowth();
            if (plant != null) {
                net.neoforged.neoforge.event.EventHooks.onLivingConvert(this, plant);
                plant.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
                plant.finalizeSpawn(serverlevel, this.level().getCurrentDifficultyAt(plant.blockPosition()), MobSpawnType.CONVERSION, null);
                plant.setNoAi(this.isNoAi());
                if (this.hasCustomName()) {
                    plant.setCustomName(this.getCustomName());
                    plant.setCustomNameVisible(this.isCustomNameVisible());
                }

                plant.setPersistenceRequired();
                plant.fudgePositionAfterSizeChange(this.getDimensions(this.getPose()));
                plant.setAge(BABY_START_AGE);
                plant.fromPlanter = true;
                this.playSound(SoundEvents.TADPOLE_GROW_UP, 0.15F, 1.0F);
                serverlevel.addFreshEntityWithPassengers(plant);
                this.discard();
            }
        }
    }
}
