package com.x29naybla.bloom_and_doom.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.client.model.entity.DoomShroomModel;
import com.x29naybla.bloom_and_doom.common.entity.plant.DoomShroom;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class DoomShroomRenderer extends MobRenderer<DoomShroom, DoomShroomModel<DoomShroom>> {
    public DoomShroomRenderer(EntityRendererProvider.Context context) {
        super(context, new DoomShroomModel<>(context.bakeLayer(DoomShroomModel.LAYER_LOCATION)), 0.4F);
    }

    @Override
    public void render(DoomShroom doomShroom, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        if (doomShroom.isBaby()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }
        super.render(doomShroom, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull DoomShroom doomShroom) {
        if (doomShroom.getSleeping()) return ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "textures/entity/doom_shroom/doom_shroom_sleeping.png"); else
            return ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "textures/entity/doom_shroom/doom_shroom.png");
    }
}
