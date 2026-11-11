package com.x29naybla.bloom_and_doom.common.entity.plant;

import com.x29naybla.bloom_and_doom.common.entity.Plant;
import com.x29naybla.bloom_and_doom.common.registry.BnDItems;
import com.x29naybla.bloom_and_doom.common.tag.BnDTags;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class WallNut extends Plant {
    public AnimationState idleAnimationState = new AnimationState();

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

    public static AttributeSupplier.Builder createAttributes() {
        return Plant.createAttributes()
                .add(Attributes.MAX_HEALTH, 216);
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
