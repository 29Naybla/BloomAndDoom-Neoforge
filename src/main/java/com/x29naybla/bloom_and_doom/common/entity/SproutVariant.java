package com.x29naybla.bloom_and_doom.common.entity;

import com.mojang.serialization.Codec;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;

public enum SproutVariant  implements StringRepresentable {
    SUNFLOWER(0, "sunflower", true),
    MARIGOLD(1, "marigold", false),
    PEASHOOTER(2, "peashooter", true),
    SNOW_PEA(3, "snow_pea", true),
    REPEATER(4, "repeater", true),
    WALL_NUT(5, "wall_nut", true),
    POTATO_MINE(6, "potato_mine", true),
    CHOMPER(7, "chomper", true),
    BONK_CHOY(8,"bonk_choy", true),
    SUN_SHROOM(9, "sun_shroom", true),
    PUFF_SHROOM(10, "puff_shroom", true),
    DOOM_SHROOM(11, "doom_shroom", false),
    FUME_SHROOM(12, "fume_shroom", false),
    HYPNO_SHROOM(13, "hypno_shroom", false),
    JALAPENO(14, "jalapeno", false),
    COFFEE_BEAN(15, "coffee_bean", false);

    private static final SproutVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(SproutVariant::getId)).toArray(SproutVariant[]::new);
    public static final Codec<SproutVariant> CODEC = StringRepresentable.fromEnum(SproutVariant::values);
    private final int id;
    private final String name;
    private final boolean randomizable;

    SproutVariant(int id, String name, boolean randomizable) {
        this.id = id;
        this.name = name;
        this.randomizable = randomizable;
    }

    public int getId() {
        return id;
    }

    public boolean accessibleRandom() {
        return randomizable;
    }

    public static SproutVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }

    public String getPlant(){
        return BloomAndDoom.MOD_ID+":"+this.name;
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.name;
    }
}
