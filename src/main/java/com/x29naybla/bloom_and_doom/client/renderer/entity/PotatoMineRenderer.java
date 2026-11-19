package com.x29naybla.bloom_and_doom.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.client.model.entity.PotatoMineModel;
import com.x29naybla.bloom_and_doom.common.entity.plant.PotatoMine;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class PotatoMineRenderer extends MobRenderer<PotatoMine, PotatoMineModel<PotatoMine>> {
    public PotatoMineRenderer(EntityRendererProvider.Context context) {
        super(context, new PotatoMineModel<>(context.bakeLayer(PotatoMineModel.LAYER_LOCATION)), 0.6F);
    }

    protected void scale(PotatoMine potatoMine, PoseStack poseStack, float partialTickTime) {
        float f = potatoMine.getSwelling(partialTickTime);
        float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
        f = Mth.clamp(f, 0.0F, 1.0F);
        f *= f;
        f *= f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        poseStack.scale(f2, f3, f2);
    }

    protected float getWhiteOverlayProgress(PotatoMine potatoMine, float partialTicks) {
        float f = potatoMine.getSwelling(partialTicks);
        return (int)(f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
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
        return BloomAndDoom.locationWithBnDNamespace("textures/entity/potato_mine/potato_mine.png");
    }
}
