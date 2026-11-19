package com.x29naybla.bloom_and_doom.common.item;

import com.google.common.collect.Maps;
import com.x29naybla.bloom_and_doom.common.entity.*;
import com.x29naybla.bloom_and_doom.common.entity.plant.Marigold;
import com.x29naybla.bloom_and_doom.common.entity.plant.PotatoMine;
import com.x29naybla.bloom_and_doom.common.entity.plant.Sprout;
import com.x29naybla.bloom_and_doom.common.registry.BnDDataComponents;
import com.x29naybla.bloom_and_doom.common.registry.BnDItems;
import com.x29naybla.bloom_and_doom.common.registry.BnDSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.BundleContents;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static net.minecraft.world.entity.AgeableMob.BABY_START_AGE;

public class PottedPlantItem extends PlantHolderItem {
    private static final Map<DyeColor, Component> MARIGOLD_COLORS = Util.make(Maps.newEnumMap(DyeColor.class), map -> {
        map.put(DyeColor.WHITE, Component.translatable("item.bloom_and_Doom.potted_marigold.white"));
        map.put(DyeColor.LIGHT_GRAY, Component.translatable("item.bloom_and_Doom.potted_marigold.light_gray"));
        map.put(DyeColor.GRAY, Component.translatable("item.bloom_and_Doom.potted_marigold.gray"));
        map.put(DyeColor.BLACK, Component.translatable("item.bloom_and_Doom.potted_marigold.black"));
        map.put(DyeColor.BROWN, Component.translatable("item.bloom_and_Doom.potted_marigold.brown"));
        map.put(DyeColor.RED, Component.translatable("item.bloom_and_Doom.potted_marigold.red"));
        map.put(DyeColor.ORANGE, Component.translatable("item.bloom_and_Doom.potted_marigold.orange"));
        map.put(DyeColor.YELLOW, Component.translatable("item.bloom_and_Doom.potted_marigold.yellow"));
        map.put(DyeColor.LIME, Component.translatable("item.bloom_and_Doom.potted_marigold.lime"));
        map.put(DyeColor.GREEN, Component.translatable("item.bloom_and_Doom.potted_marigold.green"));
        map.put(DyeColor.CYAN, Component.translatable("item.bloom_and_Doom.potted_marigold.cyan"));
        map.put(DyeColor.LIGHT_BLUE, Component.translatable("item.bloom_and_Doom.potted_marigold.light_blue"));
        map.put(DyeColor.BLUE, Component.translatable("item.bloom_and_Doom.potted_marigold.blue"));
        map.put(DyeColor.PURPLE, Component.translatable("item.bloom_and_Doom.potted_marigold.purple"));
        map.put(DyeColor.MAGENTA, Component.translatable("item.bloom_and_Doom.potted_marigold.magenta"));
        map.put(DyeColor.PINK, Component.translatable("item.bloom_and_Doom.potted_marigold.pink"));
    });
    private static final Map<SproutVariant, Component> SPROUT_PLANTS = Util.make(Maps.newEnumMap(SproutVariant.class), map -> {
        map.put(SproutVariant.SUNFLOWER, Component.translatable("entity.bloom_and_doom.sunflower"));
        map.put(SproutVariant.MARIGOLD, Component.translatable("entity.bloom_and_doom.marigold"));
        map.put(SproutVariant.PEASHOOTER, Component.translatable("entity.bloom_and_doom.peashooter"));
        map.put(SproutVariant.SNOW_PEA, Component.translatable("entity.bloom_and_doom.snow_pea"));
        map.put(SproutVariant.REPEATER, Component.translatable("entity.bloom_and_doom.repeater"));
        map.put(SproutVariant.WALL_NUT, Component.translatable("entity.bloom_and_doom.wall_nut"));
        map.put(SproutVariant.POTATO_MINE, Component.translatable("entity.bloom_and_doom.potato_mine"));
        map.put(SproutVariant.CHOMPER, Component.translatable("entity.bloom_and_doom.chomper"));
        map.put(SproutVariant.BONK_CHOY, Component.translatable("entity.bloom_and_doom.bonk_choy"));
        map.put(SproutVariant.SUN_SHROOM, Component.translatable("entity.bloom_and_doom.sun_shroom"));
        map.put(SproutVariant.PUFF_SHROOM, Component.translatable("entity.bloom_and_doom.puff_shroom"));
        map.put(SproutVariant.DOOM_SHROOM, Component.translatable("entity.bloom_and_doom.doom_shroom"));
        map.put(SproutVariant.FUME_SHROOM, Component.translatable("entity.bloom_and_doom.fume_shroom"));
        map.put(SproutVariant.HYPNO_SHROOM, Component.translatable("entity.bloom_and_doom.hypno_shroom"));
        map.put(SproutVariant.JALAPENO, Component.translatable("entity.bloom_and_doom.jalapeno"));
        map.put(SproutVariant.COFFEE_BEAN, Component.translatable("entity.bloom_and_doom.coffee_bean"));
    });

    public PottedPlantItem(EntityType<? extends Plant> defaultType, Properties properties) {
        super(defaultType, properties);
    }

    @Override
    public int getMaxStackSize(@NotNull ItemStack stack) {
        if (stack.is(BnDItems.POTTED_SPROUT)) return 16;
        return 1;
    }

    public @NotNull String getDescriptionId() {
        return this.getOrCreateDescriptionId();
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        if (stack.is(BnDItems.POTTED_MARIGOLD)) {
            return MARIGOLD_COLORS.get(stack.get(DataComponents.BASE_COLOR)).copy();
        }
        return super.getName(stack);
    }

    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        BundleContents bundlecontents = stack.get(DataComponents.BUNDLE_CONTENTS);

        if (bundlecontents != null) {
            ItemStack flowerPot = bundlecontents.getItemUnsafe(0).getItem().getDefaultInstance();
            String flowerPotName = flowerPot.getDisplayName().getString().substring(1, flowerPot.getDisplayName().getString().length() - 1);

            tooltipComponents.add(CommonComponents.EMPTY);
            tooltipComponents.add(Component.translatable("bloom_and_doom.description.potted_plant_planted_in").withStyle(ChatFormatting.GREEN));
            tooltipComponents.add(CommonComponents.SPACE.copy().append(Component.translatable(flowerPotName).withStyle(ChatFormatting.GRAY)));
        }

        if (stack.is(BnDItems.POTTED_SPROUT)) {
            if (stack.get(BnDDataComponents.SPROUT_VARIANT) != null && stack.get(BnDDataComponents.SPROUT_VARIANT) >= 12) {
                tooltipComponents.add(Component.translatable("item.bloom_and_doom.potted_sprout.not_added_yet").withStyle(ChatFormatting.RED));
            } else if (stack.get(BnDDataComponents.SPROUT_VARIANT) != null && stack.get(BnDDataComponents.SPROUT_VARIANT) == 1) {
                tooltipComponents.add(Component.translatable("item.bloom_and_doom.potted_sprout.growth_result").withStyle(ChatFormatting.GREEN));
                tooltipComponents.add(CommonComponents.SPACE.copy().append((SPROUT_PLANTS.get(SproutVariant.byId(stack.get(BnDDataComponents.SPROUT_VARIANT))).copy().withStyle(ChatFormatting.GRAY))));
            } else if (tooltipFlag.isCreative()) {
                tooltipComponents.add(Component.translatable("item.bloom_and_doom.potted_sprout.growth_result").withStyle(ChatFormatting.GREEN));
                if (stack.get(BnDDataComponents.SPROUT_VARIANT) != null) {
                    tooltipComponents.add(CommonComponents.SPACE.copy().append((SPROUT_PLANTS.get(SproutVariant.byId(stack.get(BnDDataComponents.SPROUT_VARIANT))).copy().withStyle(ChatFormatting.GRAY))));
                }
                else tooltipComponents.add(CommonComponents.SPACE.copy().append(Component.translatable("item.bloom_and_doom.potted_sprout.random").withStyle(ChatFormatting.GRAY)));
            }
        }
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context){
        Level level = context.getLevel();
        Player player = context.getPlayer();
        assert player != null;
        BlockPlaceContext blockplacecontext = new BlockPlaceContext(context);
        BlockPos blockpos = blockplacecontext.getClickedPos();
        ItemStack stack = context.getItemInHand().copyWithCount(1);
        ItemStack flowerPot = stack.get(DataComponents.BUNDLE_CONTENTS).getItemUnsafe(0);
        Direction direction = context.getClickedFace();

        if (level instanceof ServerLevel serverLevel) {
            Plant plant = (Plant) this.getType(stack).create(serverLevel, EntityType.createDefaultStackConfig(serverLevel, stack, context.getPlayer()), blockpos, MobSpawnType.BUCKET, false, false);
            Vec3 vec3 = Vec3.atBottomCenterOf(blockpos);
            AABB aabb = this.getType(stack).getDimensions().makeBoundingBox(vec3.x(), vec3.y(), vec3.z());
            if(serverLevel.noCollision(null, aabb) && level.getEntitiesOfClass(Plant.class, aabb).isEmpty()){
                if(serverLevel.isEmptyBlock(blockpos)) {
                    if (direction == Direction.DOWN) {
                        return InteractionResult.FAIL;
                    } else {
                        if (player.isCrouching()) {
                            BlockItem flowerPotBlock = (BlockItem) flowerPot.getItem();
                            serverLevel.setBlock(blockpos, flowerPotBlock.getBlock().defaultBlockState(), 3);
                            placePlant(stack, serverLevel, context, blockpos, true);
                            context.getItemInHand().consume(1,player);
                            return InteractionResult.SUCCESS;
                        } else if (plant.onRightSubstrate(serverLevel, blockpos)){
                            placePlant(stack, serverLevel, context, blockpos, false);
                            if (!player.isCreative()) player.addItem(flowerPot.copy());
                            context.getItemInHand().consume(1,player);
                            return InteractionResult.SUCCESS;
                        }
                    }
                }
            }
        }
        return InteractionResult.FAIL;
    }

    private void placePlant(ItemStack stack, ServerLevel serverlevel, UseOnContext context, BlockPos blockpos, Boolean onFlowerPot) {
        Entity entity = this.getType(stack).create(serverlevel, EntityType.createDefaultStackConfig(serverlevel, stack, context.getPlayer()), blockpos, MobSpawnType.BUCKET, false, false);
        if (entity != null) {
            if (entity instanceof Plant plant) {
                float f = (float)Mth.floor((Mth.wrapDegrees(context.getRotation()) + 22.5F) / 45.0F) * 45.0F;
                if (onFlowerPot) {
                    plant.moveTo(entity.getX(), entity.getY()+0.375, entity.getZ(), 0, 0);
                    serverlevel.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.STONE_PLACE, SoundSource.BLOCKS, 1F, 0.8F);
                } else {
                    plant.moveTo(entity.getX(), entity.getY(), entity.getZ(), 0, 0);
                    serverlevel.playSound(null, entity.getX(), entity.getY(), entity.getZ(), BnDSounds.POTTED_PLANT_PLANT.get(), SoundSource.BLOCKS, 0.75F, 0.8F);
                }
                plant.setYRot(f);
                plant.setXRot(0);
                if (stack.has(DataComponents.CUSTOM_NAME)) plant.setCustomName(stack.getHoverName());
                if (stack.has(DataComponents.BASE_COLOR)) ((Marigold) plant).setColor(stack.get(DataComponents.BASE_COLOR));
                if (stack.has(BnDDataComponents.AGE)) {
                    plant.setAge(stack.get(BnDDataComponents.AGE));
                } else if (plant instanceof Sprout) {
                    plant.setAge(BABY_START_AGE);
                    Integer plant_id = stack.get(BnDDataComponents.SPROUT_VARIANT);
                    SproutVariant[] sproutVariant = Arrays.stream(SproutVariant.values()).filter(SproutVariant::accessibleRandom).toArray(SproutVariant[]::new);
                    SproutVariant randomVariant = Util.getRandom(sproutVariant, RandomSource.create());
                    if (plant_id != null || SproutVariant.byId(plant_id).accessibleRandom()) {
                        ((Sprout) plant).setVariant(SproutVariant.byId(plant_id));
                    } else {
                        ((Sprout) plant).setVariant(randomVariant);
                    }
                }
                if (stack.has(BnDDataComponents.HEALTH)) plant.setHealth(stack.get(BnDDataComponents.HEALTH));
                if (plant instanceof PotatoMine potatoMine) potatoMine.setArmed(true);
                plant.fromPlanter = true;

                serverlevel.addFreshEntityWithPassengers(entity);
                plant.gameEvent(GameEvent.ENTITY_PLACE, context.getPlayer());
            }
        }
    }
}
