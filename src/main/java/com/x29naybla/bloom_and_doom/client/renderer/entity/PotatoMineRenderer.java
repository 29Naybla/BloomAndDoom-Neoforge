package com.x29naybla.bloom_and_doom.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.client.model.entity.PotatoMineModel;
import com.x29naybla.bloom_and_doom.common.entity.plant.PotatoMine;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class PotatoMineRenderer extends MobRenderer<PotatoMine, PotatoMineModel<PotatoMine>> {
    public PotatoMineRenderer(EntityRendererProvider.Context context) {
        super(context, new PotatoMineModel<>(context.bakeLayer(PotatoMineModel.LAYER_LOCATION)), 0.4F);
    }

    @Override
    public void render(PotatoMine potatoMine, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        if (potatoMine.isBaby()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }
        super.render(potatoMine, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull PotatoMine potatoMine) {
        return ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "textures/entity/potato_mine/potato_mine.png");
    }
}
