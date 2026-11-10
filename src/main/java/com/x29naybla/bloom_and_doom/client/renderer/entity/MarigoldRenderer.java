package com.x29naybla.bloom_and_doom.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.client.model.entity.MarigoldModel;
import com.x29naybla.bloom_and_doom.common.entity.Marigold;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class MarigoldRenderer extends MobRenderer<Marigold, MarigoldModel<Marigold>> {
    public MarigoldRenderer(EntityRendererProvider.Context context) {
        super(context, new MarigoldModel<>(context.bakeLayer(MarigoldModel.LAYER_LOCATION)), 0.4F);
        this.addLayer(new MarigoldPetalsLayer(this, context.getModelSet()));
    }

    @Override
    public void render(Marigold marigold, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        if (marigold.isBaby()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }
        super.render(marigold, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Marigold marigold) {
        return ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "textures/entity/marigold/marigold.png");
    }
}
