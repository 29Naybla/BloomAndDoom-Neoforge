package com.x29naybla.bloom_and_doom.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.common.entity.LivingEntityMixinAccess;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {
    @Shadow
    private final RandomSource random = RandomSource.create();
    @Shadow
    private int tickCount;

    @Unique
    private static final ResourceLocation FOOD_EMPTY_HUNGER_SPRITE_ZOMBIE = ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "hud/z_food_empty_hunger");
    @Unique
    private static final ResourceLocation FOOD_HALF_HUNGER_SPRITE_ZOMBIE = ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "hud/z_food_half_hunger");
    @Unique
    private static final ResourceLocation FOOD_FULL_HUNGER_SPRITE_ZOMBIE = ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "hud/z_food_full_hunger");
    @Unique
    private static final ResourceLocation FOOD_EMPTY_SPRITE_ZOMBIE = ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "hud/z_food_empty");
    @Unique
    private static final ResourceLocation FOOD_HALF_SPRITE_ZOMBIE = ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "hud/z_food_half");
    @Unique
    private static final ResourceLocation FOOD_FULL_SPRITE_ZOMBIE = ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "hud/z_food_full");


    @Inject(method = {"renderFood"}, at = @At("HEAD"), cancellable = true)
    private void renderFood(GuiGraphics guiGraphics, Player player, int y, int x, CallbackInfo ci) {
        boolean isZombie = ((LivingEntityMixinAccess) player).BnD_isZombie();
        if (isZombie) {
            FoodData fooddata = player.getFoodData();
            int i = fooddata.getFoodLevel();
            RenderSystem.enableBlend();

            for (int j = 0; j < 10; j++) {
                int k = y;

                ResourceLocation food_empty;
                ResourceLocation food_half;
                ResourceLocation food_full;

                if (player.hasEffect(MobEffects.HUNGER)) {
                    food_empty = FOOD_EMPTY_HUNGER_SPRITE_ZOMBIE;
                    food_half = FOOD_HALF_HUNGER_SPRITE_ZOMBIE;
                    food_full = FOOD_FULL_HUNGER_SPRITE_ZOMBIE;
                } else {
                    food_empty = FOOD_EMPTY_SPRITE_ZOMBIE;
                    food_half = FOOD_HALF_SPRITE_ZOMBIE;
                    food_full = FOOD_FULL_SPRITE_ZOMBIE;
                }

                if (player.getFoodData().getSaturationLevel() <= 0.0F && this.tickCount % (i * 3 + 1) == 0) {
                    k = y + (this.random.nextInt(3) - 1);
                }

                int l = x - j * 8 - 9;
                guiGraphics.blitSprite(food_empty, l, k, 9, 9);
                if (j * 2 + 1 < i) {
                    guiGraphics.blitSprite(food_full, l, k, 9, 9);
                }

                if (j * 2 + 1 == i) {
                    guiGraphics.blitSprite(food_half, l, k, 9, 9);
                }
            }

            RenderSystem.disableBlend();
            ci.cancel();
        }
    }
}
