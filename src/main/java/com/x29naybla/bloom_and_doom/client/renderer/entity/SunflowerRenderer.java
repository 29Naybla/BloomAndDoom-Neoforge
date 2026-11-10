package com.x29naybla.bloom_and_doom.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.client.model.entity.SunflowerModel;
import com.x29naybla.bloom_and_doom.common.entity.Sunflower;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SunflowerRenderer extends MobRenderer<Sunflower, SunflowerModel<Sunflower>> {
    public SunflowerRenderer(EntityRendererProvider.Context context) {
        super(context, new SunflowerModel<>(context.bakeLayer(SunflowerModel.LAYER_LOCATION)), 0.4F);
    }

    @Override
    public void render(Sunflower sunflower, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        if (sunflower.isBaby()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }
        super.render(sunflower, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Sunflower sunflower) {
        if (sunflower.getSleeping()) return ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "textures/entity/sunflower/sunflower_sleeping.png");
        else return ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "textures/entity/sunflower/sunflower.png");
    }
}
