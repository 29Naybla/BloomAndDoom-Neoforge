package com.x29naybla.bloom_and_doom.client.event;

import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.client.model.entity.*;
import com.x29naybla.bloom_and_doom.common.registry.*;
import com.x29naybla.bloom_and_doom.client.renderer.block.PlanterBlockEntityRenderer;
import com.x29naybla.bloom_and_doom.client.renderer.entity.*;
import com.x29naybla.bloom_and_doom.client.particle.SleepingParticles;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.component.DyedItemColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = BloomAndDoom.MOD_ID, value = Dist.CLIENT)
public class ClientSetupEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        BnDItemProperties.addCustomItemProperties();
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BnDBlockEntities.PLANTER_BE.get(), PlanterBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SproutModel.LAYER_LOCATION, SproutModel::createBodyLayer);
        event.registerLayerDefinition(SunflowerModel.LAYER_LOCATION, SunflowerModel::createBodyLayer);
        event.registerLayerDefinition(MarigoldModel.LAYER_LOCATION, MarigoldModel::createBodyLayer);
        event.registerLayerDefinition(MarigoldPetalsModel.LAYER_LOCATION, MarigoldPetalsModel::createBodyLayer);
        event.registerLayerDefinition(PeashooterModel.LAYER_LOCATION, PeashooterModel::createBodyLayer);
        event.registerLayerDefinition(SnowPeaModel.LAYER_LOCATION, SnowPeaModel::createBodyLayer);
        event.registerLayerDefinition(RepeaterModel.LAYER_LOCATION, RepeaterModel::createBodyLayer);
        event.registerLayerDefinition(WallNutModel.LAYER_LOCATION, WallNutModel::createBodyLayer);
        event.registerLayerDefinition(PotatoMineModel.LAYER_LOCATION, PotatoMineModel::createBodyLayer);
        event.registerLayerDefinition(ChomperModel.LAYER_LOCATION, ChomperModel::createBodyLayer);
        event.registerLayerDefinition(BonkChoyModel.LAYER_LOCATION, BonkChoyModel::createBodyLayer);
        event.registerLayerDefinition(SunShroomModel.LAYER_LOCATION, SunShroomModel::createBodyLayer);
        event.registerLayerDefinition(PuffShroomModel.LAYER_LOCATION, PuffShroomModel::createBodyLayer);
        event.registerLayerDefinition(DoomShroomModel.LAYER_LOCATION, DoomShroomModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(BnDEntities.SPROUT.get(), SproutRenderer::new);
        event.registerEntityRenderer(BnDEntities.SUNFLOWER.get(), SunflowerRenderer::new);
        event.registerEntityRenderer(BnDEntities.MARIGOLD.get(), MarigoldRenderer::new);
        event.registerEntityRenderer(BnDEntities.PEASHOOTER.get(), PeashooterRenderer::new);
        event.registerEntityRenderer(BnDEntities.SNOW_PEA.get(), SnowPeaRenderer::new);
        event.registerEntityRenderer(BnDEntities.REPEATER.get(), RepeaterRenderer::new);
        event.registerEntityRenderer(BnDEntities.WALL_NUT.get(), WallNutRenderer::new);
        event.registerEntityRenderer(BnDEntities.POTATO_MINE.get(), PotatoMineRenderer::new);
        event.registerEntityRenderer(BnDEntities.CHOMPER.get(), ChomperRenderer::new);
        event.registerEntityRenderer(BnDEntities.BONK_CHOY.get(), BonkChoyRenderer::new);
        event.registerEntityRenderer(BnDEntities.SUN_SHROOM.get(), SunShroomRenderer::new);
        event.registerEntityRenderer(BnDEntities.PUFF_SHROOM.get(), PuffShroomRenderer::new);
        event.registerEntityRenderer(BnDEntities.DOOM_SHROOM.get(), DoomShroomRenderer::new);

        event.registerEntityRenderer(BnDEntities.ZOMBIE_WOLF.get(), WolfRenderer::new);

        event.registerEntityRenderer(BnDEntities.PEA_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(BnDEntities.FROZEN_PEA_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(BnDEntities.SPORE_PROJECTILE.get(), ThrownItemRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(BnDParticles.SLEEPING_PARTICLES.get(), SleepingParticles.Provider::new);
    }

    @SubscribeEvent
    public static void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> tintIndex > 0 ? -1 : FastColor.ARGB32.opaque(DyedItemColor.getOrDefault(stack, DyeColor.WHITE.getTextureDiffuseColor())),
                BnDItems.POTTED_MARIGOLD.get());
    }
}
