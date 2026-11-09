package com.x29naybla.bloom_and_doom.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.x29naybla.bloom_and_doom.ClientConfigs;
import com.x29naybla.bloom_and_doom.client.model.entity.RepeaterModel;
import com.x29naybla.bloom_and_doom.common.entity.Repeater;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RepeaterRenderer extends GeoEntityRenderer<Repeater> {
    public RepeaterRenderer(EntityRendererProvider.Context context) {
        super(context, new RepeaterModel());
    }

    @Override
    public void render(Repeater animatable, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        if (animatable.isBaby() && !ClientConfigs.NEW_BABY_MODELS.get()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
