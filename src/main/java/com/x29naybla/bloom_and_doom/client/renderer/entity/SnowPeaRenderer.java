package com.x29naybla.bloom_and_doom.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.x29naybla.bloom_and_doom.ClientConfigs;
import com.x29naybla.bloom_and_doom.client.model.entity.SnowPeaModel;
import com.x29naybla.bloom_and_doom.common.entity.SnowPea;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SnowPeaRenderer extends GeoEntityRenderer<SnowPea> {
    public SnowPeaRenderer(EntityRendererProvider.Context context) {
        super(context, new SnowPeaModel());
    }

    @Override
    public void render(SnowPea animatable, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        if (animatable.isBaby() && !ClientConfigs.NEW_BABY_MODELS.get()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
