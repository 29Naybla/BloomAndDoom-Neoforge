package com.x29naybla.bloom_and_doom.common.entity;

import com.x29naybla.bloom_and_doom.common.registry.BnDItems;
import com.x29naybla.bloom_and_doom.common.tag.BnDTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class WallNut extends AbstractPlant {
    public net.minecraft.world.entity.AnimationState idleAnimationState = new net.minecraft.world.entity.AnimationState();

    //Properties
    public WallNut(EntityType<? extends WallNut> entityType, Level level) {
        super(entityType, level, BnDTags.Items.SUSTAINS_WALL_NUTS, BnDItems.WALL_NUT_SEED_PACKET.toStack(), null);
    }

    public boolean canCollideWith(@NotNull Entity entity) {
        return canWallNutCollide(entity);
    }

    public static boolean canWallNutCollide(Entity entity) {
        return (entity.canBeCollidedWith() || entity.isPushable());
    }

    public boolean canBeCollidedWith() {
        return true;
    }

    //Goals and AI
    protected void registerGoals(){
        this.goalSelector.addGoal(1, new RandomLookAroundGoal(this));
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.idleAnimationState.animateWhen(isAlive(), tickCount);
    }
}
