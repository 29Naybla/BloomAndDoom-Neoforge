package com.x29naybla.bloom_and_doom.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.client.model.entity.RepeaterModel;
import com.x29naybla.bloom_and_doom.common.entity.Repeater;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class RepeaterRenderer extends MobRenderer<Repeater, RepeaterModel<Repeater>> {
    public RepeaterRenderer(EntityRendererProvider.Context context) {
        super(context, new RepeaterModel<>(context.bakeLayer(RepeaterModel.LAYER_LOCATION)), 0.4F);
    }

    @Override
    public void render(Repeater repeater, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        if (repeater.isBaby()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }
        super.render(repeater, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Repeater repeater) {
        return ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "textures/entity/peashooter/repeater.png");
    }
}
