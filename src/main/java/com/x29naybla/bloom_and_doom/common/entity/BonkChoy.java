package com.x29naybla.bloom_and_doom.common.entity;

import com.x29naybla.bloom_and_doom.common.registry.BnDItems;
import com.x29naybla.bloom_and_doom.common.tag.BnDTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.level.Level;

public class BonkChoy extends AbstractPlant {
    public net.minecraft.world.entity.AnimationState idleAnimationState = new net.minecraft.world.entity.AnimationState();

    //Properties
    public BonkChoy(EntityType<? extends AbstractPlant> entityType, Level level) {
        super(entityType, level, BnDTags.Items.SUSTAINS_BONK_CHOYS, BnDItems.BONK_CHOY_SEED_PACKET.toStack(), BnDItems.POTTED_BONK_CHOY.toStack());
    }

    //Goals and AI
    protected void registerGoals(){
        goalSelector.addGoal(1, new MeleeAttackGoal(this, 1f, true));
        goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, false, true,
                (target) -> target instanceof LivingEntity livingEntity && (livingEntity.getType().is(BnDTags.Entities.PLANT_ENEMIES) || ((LivingEntityMixinAccess) livingEntity).BnD_isZombie())));
        goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.idleAnimationState.animateWhen(isAlive(), tickCount);
    }
}
