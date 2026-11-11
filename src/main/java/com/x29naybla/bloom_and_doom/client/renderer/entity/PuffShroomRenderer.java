package com.x29naybla.bloom_and_doom.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.client.model.entity.PuffShroomModel;
import com.x29naybla.bloom_and_doom.common.entity.plant.PuffShroom;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class PuffShroomRenderer extends MobRenderer<PuffShroom, PuffShroomModel<PuffShroom>> {
    public PuffShroomRenderer(EntityRendererProvider.Context context) {
        super(context, new PuffShroomModel<>(context.bakeLayer(PuffShroomModel.LAYER_LOCATION)), 0.4F);
    }

    @Override
    public void render(PuffShroom puffShroom, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        if (puffShroom.isBaby()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }
        super.render(puffShroom, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull PuffShroom puffShroom) {
        if (puffShroom.getSleeping()) return ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "textures/entity/puff_shroom/puff_shroom_sleeping.png"); else
            return ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "textures/entity/puff_shroom/puff_shroom.png");
    }
}
