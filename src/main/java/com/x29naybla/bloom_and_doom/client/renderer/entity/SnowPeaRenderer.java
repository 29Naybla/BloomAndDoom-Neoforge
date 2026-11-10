package com.x29naybla.bloom_and_doom.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.client.model.entity.SnowPeaModel;
import com.x29naybla.bloom_and_doom.common.entity.SnowPea;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SnowPeaRenderer extends MobRenderer<SnowPea, SnowPeaModel<SnowPea>> {
    public SnowPeaRenderer(EntityRendererProvider.Context context) {
        super(context, new SnowPeaModel<>(context.bakeLayer(SnowPeaModel.LAYER_LOCATION)), 0.4F);
    }

    @Override
    public void render(SnowPea snowPea, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        if (snowPea.isBaby()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }
        super.render(snowPea, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull SnowPea snowPea) {
        return ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "textures/entity/peashooter/snow_pea.png");
    }
}
