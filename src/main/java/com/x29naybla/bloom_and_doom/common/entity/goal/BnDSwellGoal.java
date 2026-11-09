package com.x29naybla.bloom_and_doom.common.entity.goal;

import com.x29naybla.bloom_and_doom.common.entity.AbstractExplosivePlant;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class BnDSwellGoal extends Goal{
    private final AbstractExplosivePlant abstractExplosivePlant;
    @Nullable
    private LivingEntity target;

    public BnDSwellGoal(AbstractExplosivePlant abstractExplosivePlant) {
        this.abstractExplosivePlant = abstractExplosivePlant;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    public boolean canUse() {
        LivingEntity livingentity = this.abstractExplosivePlant.getTarget();
        return this.abstractExplosivePlant.getSwellDir() > 0 || livingentity != null && this.abstractExplosivePlant.distanceToSqr(livingentity) < (double)1.0F;
    }

    public void start() {
        this.abstractExplosivePlant.getNavigation().stop();
        this.target = this.abstractExplosivePlant.getTarget();
    }

    public void stop() {
        this.target = null;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }

    public void tick() {
        if (this.target == null) {
            this.abstractExplosivePlant.setSwellDir(-1);
        } else if (this.abstractExplosivePlant.distanceToSqr(this.target) > (double)49.0F) {
            this.abstractExplosivePlant.setSwellDir(-1);
        } else if (!this.abstractExplosivePlant.getSensing().hasLineOfSight(this.target)) {
            this.abstractExplosivePlant.setSwellDir(-1);
        } else {
            this.abstractExplosivePlant.setSwellDir(1);
        }

    }
}
