package com.x29naybla.bloom_and_doom.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.client.animation.WallNutAnimation;
import com.x29naybla.bloom_and_doom.common.entity.WallNut;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class WallNutModel<T extends WallNut> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "wall_nut"), "main");
    private final ModelPart wall_nut;

    public WallNutModel(ModelPart root) {
        this.wall_nut = root.getChild("wall_nut");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition wall_nut = partdefinition.addOrReplaceChild("wall_nut", CubeListBuilder.create().texOffs(9, 52).addBox(-6.0F, 0.0F, -5.0F, 12.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(3, 20).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 14.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-5.0F, -13.0F, -7.25F, 10.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(10, 0).addBox(-6.0F, -18.0F, -5.0F, 12.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(WallNut wallNut, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(wallNut.idleAnimationState, WallNutAnimation.WALL_NUT_IDLE, ageInTicks);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        wall_nut.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public @NotNull ModelPart root() {
        return wall_nut;
    }
}
