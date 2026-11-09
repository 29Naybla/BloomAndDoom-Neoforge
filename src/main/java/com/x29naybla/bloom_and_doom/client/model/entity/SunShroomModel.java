package com.x29naybla.bloom_and_doom.client.model.entity;

import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.common.entity.SunShroom;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SunShroomModel extends GeoModel<SunShroom> {
    private final ResourceLocation model = ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "geo/entity/sun_shroom/sun_shroom.geo.json");
    private final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "textures/entity/sun_shroom/sun_shroom.png");
    private final ResourceLocation texture_sleeping = ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "textures/entity/sun_shroom/sun_shroom_sleeping.png");
    private final ResourceLocation animations = ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "animations/entity/flower.animation.json");

    @Override
    public ResourceLocation getModelResource(SunShroom animatable) {
        return this.model;
    }

    @Override
    public ResourceLocation getTextureResource(SunShroom animatable) {
        if (animatable.getSleeping()) return texture_sleeping; else return texture;
    }

    @Override
    public ResourceLocation getAnimationResource(SunShroom animatable) {
        return this.animations;
    }
}
