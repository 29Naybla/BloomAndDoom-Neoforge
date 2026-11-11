package com.x29naybla.bloom_and_doom.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.client.model.entity.WallNutModel;
import com.x29naybla.bloom_and_doom.common.entity.plant.WallNut;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class WallNutRenderer extends MobRenderer<WallNut, WallNutModel<WallNut>> {
    public WallNutRenderer(EntityRendererProvider.Context context) {
        super(context, new WallNutModel<>(context.bakeLayer(WallNutModel.LAYER_LOCATION)), 0.6F);
    }

    @Override
    public void render(WallNut wallNut, float entityYaw, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        if (wallNut.isBaby()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
        } else {
            poseStack.scale(1F, 1F, 1F);
        }
        super.render(wallNut, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull WallNut wallNut) {
        if (wallNut.getHealth() <= (wallNut.getMaxHealth()/3)) return ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "textures/entity/wall_nut/wall_nut_dying.png");
        else if (wallNut.getHealth() <= ((wallNut.getMaxHealth()/3)*2)) return ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "textures/entity/wall_nut/wall_nut_hurt.png");
        else return ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "textures/entity/wall_nut/wall_nut_well.png");
    }
}
