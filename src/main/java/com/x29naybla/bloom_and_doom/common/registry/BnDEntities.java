package com.x29naybla.bloom_and_doom.common.registry;

import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.common.entity.*;
import com.x29naybla.bloom_and_doom.common.entity.projectile.FrozenPeaProjectile;
import com.x29naybla.bloom_and_doom.common.entity.projectile.PeaProjectile;
import com.x29naybla.bloom_and_doom.common.entity.projectile.SporeProjectile;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@EventBusSubscriber(modid = BloomAndDoom.MOD_ID)
public class BnDEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, BloomAndDoom.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<Sprout>> SPROUT = register("sprout", Sprout::new, 0.3f, 0.4f);
    public static final DeferredHolder<EntityType<?>, EntityType<Sunflower>> SUNFLOWER = register("sunflower", Sunflower::new, 0.7f, 0.9f);
    public static final DeferredHolder<EntityType<?>, EntityType<Marigold>> MARIGOLD = register("marigold", Marigold::new, 0.7f, 0.9f);
    public static final DeferredHolder<EntityType<?>, EntityType<Peashooter>> PEASHOOTER = register("peashooter", Peashooter::new, 0.5f, 0.9f);
    public static final DeferredHolder<EntityType<?>, EntityType<SnowPea>> SNOW_PEA = register("snow_pea", SnowPea::new, 0.5f, 0.9f);
    public static final DeferredHolder<EntityType<?>, EntityType<Repeater>> REPEATER = register("repeater", Repeater::new, 0.5f, 0.9f);
    public static final DeferredHolder<EntityType<?>, EntityType<WallNut>> WALL_NUT = register("wall_nut", WallNut::new, 0.8f, 1.3f);
    public static final DeferredHolder<EntityType<?>, EntityType<PotatoMine>> POTATO_MINE = register("potato_mine", PotatoMine::new, 0.7f, 0.7f);
    public static final DeferredHolder<EntityType<?>, EntityType<Chomper>> CHOMPER = register("chomper", Chomper::new, 1f, 1.65f);
    public static final DeferredHolder<EntityType<?>, EntityType<BonkChoy>> BONK_CHOY = register("bonk_choy", BonkChoy::new, 1f, 1f);
    public static final DeferredHolder<EntityType<?>, EntityType<SunShroom>> SUN_SHROOM = register("sun_shroom", SunShroom::new, 0.7f, 0.9f);
    public static final DeferredHolder<EntityType<?>, EntityType<PuffShroom>> PUFF_SHROOM = register("puff_shroom", PuffShroom::new, 0.5f, 0.5f);
    public static final DeferredHolder<EntityType<?>, EntityType<DoomShroom>> DOOM_SHROOM = register("doom_shroom", DoomShroom::new, 0.7f, 1f);

    public static final DeferredHolder<EntityType<?>, EntityType<ZombieWolf>> ZOMBIE_WOLF = register("zombie_wolf", ZombieWolf::new, 0.7f, 1f);

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        AttributeSupplier.Builder sproutAttributes = PathfinderMob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED,-99)
                .add(Attributes.MAX_HEALTH, 16)
                .add(Attributes.KNOCKBACK_RESISTANCE, 99)
                .add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, 99);

        AttributeSupplier.Builder sunflowerAttributes = PathfinderMob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED,-99)
                .add(Attributes.MAX_HEALTH, 16)
                .add(Attributes.KNOCKBACK_RESISTANCE, 99)
                .add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, 99);

        AttributeSupplier.Builder marigoldAttributes = PathfinderMob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED,-99)
                .add(Attributes.MAX_HEALTH, 16)
                .add(Attributes.KNOCKBACK_RESISTANCE, 99)
                .add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, 99);

        AttributeSupplier.Builder peashooterAttributes = PathfinderMob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED,-99)
                .add(Attributes.MAX_HEALTH, 16)
                .add(Attributes.KNOCKBACK_RESISTANCE, 99)
                .add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, 99)
                .add(Attributes.ATTACK_SPEED, 30)
                .add(Attributes.ATTACK_DAMAGE, 2)
                .add(Attributes.ENTITY_INTERACTION_RANGE, 9);

        AttributeSupplier.Builder snowPeaAttributes = PathfinderMob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED,-99)
                .add(Attributes.MAX_HEALTH, 16)
                .add(Attributes.KNOCKBACK_RESISTANCE, 99)
                .add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, 99)
                .add(Attributes.ATTACK_SPEED, 30)
                .add(Attributes.ATTACK_DAMAGE, 2)
                .add(Attributes.ENTITY_INTERACTION_RANGE, 9);

        AttributeSupplier.Builder repeaterAttributes = PathfinderMob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED,-99)
                .add(Attributes.MAX_HEALTH, 16)
                .add(Attributes.KNOCKBACK_RESISTANCE, 99)
                .add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, 99)
                .add(Attributes.ATTACK_SPEED, 30)
                .add(Attributes.ATTACK_DAMAGE, 2)
                .add(Attributes.ENTITY_INTERACTION_RANGE, 9);

        AttributeSupplier.Builder wallNutAttributes = PathfinderMob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED,-99)
                .add(Attributes.MAX_HEALTH, 216)
                .add(Attributes.KNOCKBACK_RESISTANCE, 99)
                .add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, 99);

        AttributeSupplier.Builder potatoMineAttributes = PathfinderMob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED,-99)
                .add(Attributes.MAX_HEALTH, 16)
                .add(Attributes.KNOCKBACK_RESISTANCE, 99)
                .add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, 99)
                .add(Attributes.ENTITY_INTERACTION_RANGE, 1);

        AttributeSupplier.Builder chomperAttributes = PathfinderMob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED,-99)
                .add(Attributes.MAX_HEALTH, 16)
                .add(Attributes.KNOCKBACK_RESISTANCE, 99)
                .add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, 99)
                .add(Attributes.ATTACK_SPEED, 350)
                .add(Attributes.ATTACK_DAMAGE, 4)
                .add(Attributes.ENTITY_INTERACTION_RANGE, 2);

        AttributeSupplier.Builder bonkChoyAttributes = PathfinderMob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED,-99)
                .add(Attributes.MAX_HEALTH, 16)
                .add(Attributes.KNOCKBACK_RESISTANCE, 99)
                .add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, 99)
                .add(Attributes.ATTACK_SPEED, 30)
                .add(Attributes.ATTACK_DAMAGE, 3)
                .add(Attributes.ENTITY_INTERACTION_RANGE, 2);

        AttributeSupplier.Builder puffShroomAttributes = PathfinderMob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED,-99)
                .add(Attributes.MAX_HEALTH, 16)
                .add(Attributes.KNOCKBACK_RESISTANCE, 99)
                .add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, 99)
                .add(Attributes.ATTACK_SPEED, 30)
                .add(Attributes.ATTACK_DAMAGE, 2)
                .add(Attributes.ENTITY_INTERACTION_RANGE, 4);

        AttributeSupplier.Builder doomShroomAttributes = PathfinderMob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED,-99)
                .add(Attributes.MAX_HEALTH, 16)
                .add(Attributes.KNOCKBACK_RESISTANCE, 99)
                .add(Attributes.EXPLOSION_KNOCKBACK_RESISTANCE, 99)
                .add(Attributes.ENTITY_INTERACTION_RANGE, 1);

        event.put(BnDEntities.SPROUT.get(), sproutAttributes.build());
        event.put(BnDEntities.SUNFLOWER.get(), sunflowerAttributes.build());
        event.put(BnDEntities.MARIGOLD.get(), marigoldAttributes.build());
        event.put(BnDEntities.PEASHOOTER.get(), peashooterAttributes.build());
        event.put(BnDEntities.SNOW_PEA.get(), snowPeaAttributes.build());
        event.put(BnDEntities.REPEATER.get(), repeaterAttributes.build());
        event.put(BnDEntities.WALL_NUT.get(), wallNutAttributes.build());
        event.put(BnDEntities.POTATO_MINE.get(), potatoMineAttributes.build());
        event.put(BnDEntities.CHOMPER.get(), chomperAttributes.build());
        event.put(BnDEntities.BONK_CHOY.get(), bonkChoyAttributes.build());
        event.put(BnDEntities.SUN_SHROOM.get(), sunflowerAttributes.build());
        event.put(BnDEntities.PUFF_SHROOM.get(), puffShroomAttributes.build());
        event.put(BnDEntities.DOOM_SHROOM.get(), doomShroomAttributes.build());
        event.put(BnDEntities.ZOMBIE_WOLF.get(), ZombieWolf.createAttributes().build());
    }

    public static final Supplier<EntityType<PeaProjectile>> PEA_PROJECTILE = ENTITY_TYPES.register("pea", () -> (
            EntityType.Builder.<PeaProjectile>of(PeaProjectile::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(16)
                    .build("pea")));

    public static final Supplier<EntityType<FrozenPeaProjectile>> FROZEN_PEA_PROJECTILE = ENTITY_TYPES.register("frozen_pea", () -> (
            EntityType.Builder.<FrozenPeaProjectile>of(FrozenPeaProjectile::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(16)
                    .build("frozen_pea")));

    public static final Supplier<EntityType<SporeProjectile>> SPORE_PROJECTILE = ENTITY_TYPES.register("spore", () -> (
            EntityType.Builder.<SporeProjectile>of(SporeProjectile::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(16)
                    .build("spore")));

    private static <T extends Mob> DeferredHolder<EntityType<?>, EntityType<T>> register(String name, EntityType.EntityFactory<T> entity, float width, float height) {
        return ENTITY_TYPES.register(name, () -> EntityType.Builder.of(entity, MobCategory.CREATURE).sized(width, height).build(name));
    }
}
