package com.x29naybla.bloom_and_doom.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.client.model.entity.SproutModel;
import com.x29naybla.bloom_and_doom.common.entity.Sprout;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SproutRenderer extends MobRenderer<Sprout, SproutModel<Sprout>> {
    public SproutRenderer(EntityRendererProvider.Context context) {
        super(context, new SproutModel<>(context.bakeLayer(SproutModel.LAYER_LOCATION)), 0.2F);
    }

    @Override
    public void render(@NotNull Sprout sprout, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        super.render(sprout, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Sprout sprout) {
        return ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "textures/entity/sprout/sprout.png");
    }
}
