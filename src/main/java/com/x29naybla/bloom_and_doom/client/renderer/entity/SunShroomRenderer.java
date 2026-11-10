package com.x29naybla.bloom_and_doom.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.client.model.entity.SunShroomModel;
import com.x29naybla.bloom_and_doom.common.entity.SunShroom;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SunShroomRenderer extends MobRenderer<SunShroom, SunShroomModel<SunShroom>> {
    public SunShroomRenderer(EntityRendererProvider.Context context) {
        super(context, new SunShroomModel<>(context.bakeLayer(SunShroomModel.LAYER_LOCATION)), 0.4F);
    }

    @Override
    public void render(SunShroom sunShroom, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        if (sunShroom.isBaby()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }
        super.render(sunShroom, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull SunShroom sunShroom) {
        if (sunShroom.getSleeping()) return ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "textures/entity/sun_shroom/sun_shroom_sleeping.png"); else
            return ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "textures/entity/sun_shroom/sun_shroom.png");
    }
}
