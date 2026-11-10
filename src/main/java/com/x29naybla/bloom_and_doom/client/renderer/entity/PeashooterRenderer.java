package com.x29naybla.bloom_and_doom.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.client.model.entity.PeashooterModel;
import com.x29naybla.bloom_and_doom.common.entity.Peashooter;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class PeashooterRenderer extends MobRenderer<Peashooter, PeashooterModel<Peashooter>> {
    public PeashooterRenderer(EntityRendererProvider.Context context) {
        super(context, new PeashooterModel<>(context.bakeLayer(PeashooterModel.LAYER_LOCATION)), 0.4F);
    }

    @Override
    public void render(Peashooter peashooter, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        if (peashooter.isBaby()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }
        super.render(peashooter, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Peashooter peashooter) {
        return ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "textures/entity/peashooter/peashooter.png");
    }
}
