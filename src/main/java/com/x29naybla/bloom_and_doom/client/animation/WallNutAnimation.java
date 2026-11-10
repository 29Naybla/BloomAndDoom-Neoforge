package com.x29naybla.bloom_and_doom.client.animation;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class WallNutAnimation {
    public static final AnimationDefinition WALL_NUT_IDLE = AnimationDefinition.Builder.withLength(1.5F).looping()
		.addAnimation("wall_nut", new AnimationChannel(AnimationChannel.Targets.ROTATION,
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 1.25F, -1.67F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.1667F, KeyframeAnimations.degreeVec(0.0F, 1.67F, -1.76F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 2.41F, -2.5F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.5417F, KeyframeAnimations.degreeVec(0.0F, 2.5F, -2.22F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.625F, KeyframeAnimations.degreeVec(0.0F, 1.95F, -1.91F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 1.67F, -1.76F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.875F, KeyframeAnimations.degreeVec(0.0F, -1.25F, 1.67F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(0.9167F, KeyframeAnimations.degreeVec(0.0F, -1.67F, 1.76F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, -2.41F, 2.5F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(1.2917F, KeyframeAnimations.degreeVec(0.0F, -2.5F, 2.22F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(1.375F, KeyframeAnimations.degreeVec(0.0F, -1.95F, 1.91F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(1.4167F, KeyframeAnimations.degreeVec(0.0F, -1.67F, 1.76F), AnimationChannel.Interpolations.LINEAR),
            new Keyframe(1.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
            ))
            .build();
}
