package com.x29naybla.bloom_and_doom.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.client.model.entity.BonkChoyModel;
import com.x29naybla.bloom_and_doom.common.entity.plant.BonkChoy;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class BonkChoyRenderer extends MobRenderer<BonkChoy, BonkChoyModel<BonkChoy>> {
    public BonkChoyRenderer(EntityRendererProvider.Context context) {
        super(context, new BonkChoyModel<>(context.bakeLayer(BonkChoyModel.LAYER_LOCATION)), 0.4F);
    }

    @Override
    public void render(BonkChoy bonkChoy, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        if (bonkChoy.isBaby()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }
        super.render(bonkChoy, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull BonkChoy bonkChoy) {
        return ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "textures/entity/bonk_choy/bonk_choy.png");
    }
}
