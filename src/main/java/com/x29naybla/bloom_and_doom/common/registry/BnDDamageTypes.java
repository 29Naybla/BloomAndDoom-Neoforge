package com.x29naybla.bloom_and_doom.common.registry;

import com.x29naybla.bloom_and_doom.BloomAndDoom;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class BnDDamageTypes {

    public static final ResourceKey<DamageType> PEA_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, BloomAndDoom.locationWithBnDNamespace("pea_projectile"));

    public static final ResourceKey<DamageType> FROZEN_PEA_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, BloomAndDoom.locationWithBnDNamespace("frozen_pea_projectile"));

    public static final ResourceKey<DamageType> SPORE_DAMAGE =
            ResourceKey.create(Registries.DAMAGE_TYPE, BloomAndDoom.locationWithBnDNamespace("spore_projectile"));

    public static final ResourceKey<DamageType> PLANT_EXPLOSION =
            ResourceKey.create(Registries.DAMAGE_TYPE, BloomAndDoom.locationWithBnDNamespace("plant_explosion"));

    public static final ResourceKey<DamageType> PLANT_PUNCH =
            ResourceKey.create(Registries.DAMAGE_TYPE, BloomAndDoom.locationWithBnDNamespace("plant_punch"));
}
