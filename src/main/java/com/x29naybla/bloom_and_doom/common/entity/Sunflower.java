package com.x29naybla.bloom_and_doom.common.entity;

import com.x29naybla.bloom_and_doom.common.registry.BnDItems;
import com.x29naybla.bloom_and_doom.common.tag.BnDTags;
import com.x29naybla.bloom_and_doom.common.entity.goal.PlantGenerateSunGoal;
import com.x29naybla.bloom_and_doom.common.registry.BnDSounds;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.level.Level;

public class Sunflower extends AbstractSolarPlant {
    public net.minecraft.world.entity.AnimationState idleAnimationState = new net.minecraft.world.entity.AnimationState();
    public net.minecraft.world.entity.AnimationState generatingAnimationState = new net.minecraft.world.entity.AnimationState();

    //Properties
    public Sunflower(EntityType<? extends Sunflower> entityType, Level level) {
        super(entityType, level, BnDTags.Items.SUSTAINS_SUNFLOWERS, BnDItems.SUNFLOWER_SEED_PACKET.toStack(), BnDItems.POTTED_SUNFLOWER.toStack(), false);
    }

    //Goals and AI
    protected void registerGoals(){
        this.goalSelector.addGoal(1, new PlantGenerateSunGoal(this, BnDSounds.SUNFLOWER_SUN.get()));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.generatingAnimationState.animateWhen(this.sunTime == 0, tickCount);
        this.idleAnimationState.animateWhen(isAlive(), tickCount);
    }
}
