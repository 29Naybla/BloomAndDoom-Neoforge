package com.x29naybla.bloom_and_doom.common.entity;

import com.x29naybla.bloom_and_doom.common.tag.BnDTags;
import com.x29naybla.bloom_and_doom.common.entity.goal.PlantGenerateSunGoal;
import com.x29naybla.bloom_and_doom.common.registry.BnDItems;
import com.x29naybla.bloom_and_doom.common.registry.BnDSounds;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.level.Level;

public class SunShroom extends AbstractSolarPlant {
    //Properties
    public SunShroom(EntityType<? extends SunShroom> entityType, Level level) {
        super(entityType, level, BnDTags.Items.SUSTAINS_SUN_SHROOMS, BnDItems.SUN_SHROOM_SEED_PACKET.toStack(), BnDItems.POTTED_SUN_SHROOM.toStack(), true);
        this.isMushroom = true;
        this.sunTime = maxSunTime*2;
    }

    //Goals and AI
    protected void registerGoals(){
        this.goalSelector.addGoal(1, new PlantGenerateSunGoal(this, BnDSounds.SUN_SHROOM_SUN.get()));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
    }
}
