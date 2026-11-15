package com.x29naybla.bloom_and_doom.common.entity;

import com.mojang.serialization.Codec;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;

public enum SproutVariant  implements StringRepresentable {
    SUNFLOWER(0, "sunflower"),
    MARIGOLD(1, "marigold"),
    PEASHOOTER(2, "peashooter"),
    SNOW_PEA(3, "snow_pea"),
    REPEATER(4, "repeater"),
    WALL_NUT(5, "wall_nut"),
    POTATO_MINE(6, "potato_mine"),
    CHOMPER(7, "chomper"),
    BONK_CHOY(8,"bonk_choy"),
    SUN_SHROOM(9, "sun_shroom"),
    PUFF_SHROOM(10, "puff_shroom"),
    DOOM_SHROOM(11, "doom_shroom");

    private static final SproutVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(SproutVariant::getId)).toArray(SproutVariant[]::new);
    public static final Codec<SproutVariant> CODEC = StringRepresentable.fromEnum(SproutVariant::values);
    private final int id;
    private final String name;

    SproutVariant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
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
