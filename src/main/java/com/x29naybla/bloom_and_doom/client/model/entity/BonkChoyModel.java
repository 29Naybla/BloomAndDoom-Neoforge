package com.x29naybla.bloom_and_doom.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.client.animation.BonkChoyAnimation;
import com.x29naybla.bloom_and_doom.common.entity.BonkChoy;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class BonkChoyModel<T extends BonkChoy> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "bonk_choy"), "main");
    private final ModelPart bonk_choy;

    public BonkChoyModel(ModelPart root) {
        this.bonk_choy = root.getChild("bonk_choy");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bonk_choy = partdefinition.addOrReplaceChild("bonk_choy", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition leaves = bonk_choy.addOrReplaceChild("leaves", CubeListBuilder.create().texOffs(40, 0).addBox(-5.0F, -6.0F, -1.0F, 10.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 0.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition left_arm = bonk_choy.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(1, 18).addBox(-2.0F, -6.0F, -8.0F, 4.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, -4.0F, -0.3927F, -0.2182F, 0.0F));

        PartDefinition right_arm = bonk_choy.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(27, 17).addBox(-2.0F, -5.0F, 0.0F, 5.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 4.0F, 0.3927F, -0.2182F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(@NotNull BonkChoy bonkChoy, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(bonkChoy.idleAnimationState, BonkChoyAnimation.BONK_CHOY_IDLE, ageInTicks);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        bonk_choy.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public @NotNull ModelPart root() {
        return bonk_choy;
    }
}
