package com.x29naybla.bloom_and_doom.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.x29naybla.bloom_and_doom.ClientConfigs;
import com.x29naybla.bloom_and_doom.client.model.entity.MarigoldModel;
import com.x29naybla.bloom_and_doom.common.entity.Marigold;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MarigoldRenderer extends GeoEntityRenderer<Marigold> {
    public MarigoldRenderer(EntityRendererProvider.Context context) {
        super(context, new MarigoldModel());
        this.addRenderLayer(new MarigoldPetalsRenderer(this));
    }

    @Override
    public void render(Marigold animatable, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (animatable.isBaby() && !ClientConfigs.NEW_BABY_MODELS.get()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }
        super.render(animatable, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
