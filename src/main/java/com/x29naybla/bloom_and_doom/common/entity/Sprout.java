package com.x29naybla.bloom_and_doom.common.entity;

import com.x29naybla.bloom_and_doom.common.registry.BnDEntities;
import com.x29naybla.bloom_and_doom.common.registry.BnDItems;
import com.x29naybla.bloom_and_doom.common.tag.BnDTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class Sprout extends AbstractPlant {
    private int age;

    //Properties
    public Sprout(EntityType<? extends AbstractPlant> entityType, Level level) {
        super(entityType, level, BnDTags.Items.SUSTAINS_SPROUTS, null, BnDItems.POTTED_SPROUT.toStack());
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
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Age", this.age);
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

    private void ageUp() {
        if (this.level() instanceof ServerLevel serverlevel) {
            if (!net.neoforged.neoforge.event.EventHooks.canLivingConvert(this, EntityType.FROG, (timer) -> {})) return;
            Sunflower sunflower = BnDEntities.SUNFLOWER.get().create(this.level());
            if (sunflower != null) {
                net.neoforged.neoforge.event.EventHooks.onLivingConvert(this, sunflower);
                sunflower.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
                sunflower.finalizeSpawn(serverlevel, this.level().getCurrentDifficultyAt(sunflower.blockPosition()), MobSpawnType.CONVERSION, null);
                sunflower.setNoAi(this.isNoAi());
                if (this.hasCustomName()) {
                    sunflower.setCustomName(this.getCustomName());
                    sunflower.setCustomNameVisible(this.isCustomNameVisible());
                }

                sunflower.setPersistenceRequired();
                sunflower.fudgePositionAfterSizeChange(this.getDimensions(this.getPose()));
                sunflower.setAge(BABY_START_AGE);
                sunflower.fromPlanter = true;
                this.playSound(SoundEvents.TADPOLE_GROW_UP, 0.15F, 1.0F);
                serverlevel.addFreshEntityWithPassengers(sunflower);
                this.discard();
            }
        }
    }
}
