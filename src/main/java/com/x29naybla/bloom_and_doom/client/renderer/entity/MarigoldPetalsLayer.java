package com.x29naybla.bloom_and_doom.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.client.model.entity.MarigoldModel;
import com.x29naybla.bloom_and_doom.client.model.entity.MarigoldPetalsModel;
import com.x29naybla.bloom_and_doom.common.entity.plant.Marigold;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.DyeColor;
import org.jetbrains.annotations.NotNull;

public class MarigoldPetalsLayer extends RenderLayer<Marigold, MarigoldModel<Marigold>> {
    private static final ResourceLocation MARIGOLD_PETALS_LOCATION = ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID,"textures/entity/marigold/marigold_petals.png");
    private final MarigoldPetalsModel<Marigold> model;

    public MarigoldPetalsLayer(RenderLayerParent<Marigold, MarigoldModel<Marigold>> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.model = new MarigoldPetalsModel<>(modelSet.bakeLayer(
                new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "marigold"), "main")));
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight, Marigold marigold, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (marigold.isInvisible()) {
            Minecraft minecraft = Minecraft.getInstance();
            boolean flag = minecraft.shouldEntityAppearGlowing(marigold);
            if (flag) {
                this.getParentModel().copyPropertiesTo(this.model);
                this.model.prepareMobModel(marigold, limbSwing, limbSwingAmount, partialTicks);
                this.model.setupAnim(marigold, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
                VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.outline(MARIGOLD_PETALS_LOCATION));
                this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, LivingEntityRenderer.getOverlayCoords(marigold, 0.0F), -16777216);
            }
        } else {
            int i;
            if (marigold.hasCustomName() && "jeb_".equals(marigold.getName().getString())) {
                int j = 25;
                int k = marigold.tickCount / j + marigold.getId();
                int l = DyeColor.values().length;
                int i1 = k % l;
                int j1 = (k + 1) % l;
                float f = ((float)(marigold.tickCount % j) + partialTicks) / j;
                int k1 = Marigold.getColor(DyeColor.byId(i1));
                int l1 = Marigold.getColor(DyeColor.byId(j1));
                i = FastColor.ARGB32.lerp(f, k1, l1);
            } else {
                i = marigold.getColor().getTextureDiffuseColor();
            }

            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, MARIGOLD_PETALS_LOCATION, poseStack, buffer, packedLight, marigold, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, i);
        }
    }
}
