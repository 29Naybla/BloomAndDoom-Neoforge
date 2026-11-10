package com.x29naybla.bloom_and_doom.client.animation;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class PotatoMineAnimation {
    public static final AnimationDefinition POTATO_MINE_ARMING = AnimationDefinition.Builder.withLength(2.0F)
            .addAnimation("potato-mine", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -7.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(1.9167F, KeyframeAnimations.posVec(0.0F, -7.5F, 0.0F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .build();

    public static final AnimationDefinition POTATO_MINE_ARMED = AnimationDefinition.Builder.withLength(0.0F)
            .addAnimation("potato-mine", new AnimationChannel(AnimationChannel.Targets.POSITION,
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .build();
}
