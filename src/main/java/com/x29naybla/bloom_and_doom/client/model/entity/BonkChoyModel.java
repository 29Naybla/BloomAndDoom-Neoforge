package com.x29naybla.bloom_and_doom.client.model.entity;

import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.common.entity.BonkChoy;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BonkChoyModel extends GeoModel<BonkChoy> {
    private final ResourceLocation model = ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "geo/entity/bonk_choy/bonk_choy.geo.json");
    private final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "textures/entity/bonk_choy/bonk_choy.png");
    private final ResourceLocation animations = ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "animations/entity/bonk_choy.animation.json");

    @Override
    public ResourceLocation getModelResource(BonkChoy animatable) {
        return this.model;
    }

    @Override
    public ResourceLocation getTextureResource(BonkChoy animatable) {
        return this.texture;
    }

    @Override
    public ResourceLocation getAnimationResource(BonkChoy animatable) {
        return this.animations;
    }
}
