package com.x29naybla.bloom_and_doom.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.client.animation.PeashooterAnimation;
import com.x29naybla.bloom_and_doom.common.entity.SnowPea;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class SnowPeaModel<T extends SnowPea> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "snow_pea"), "main");
    private final ModelPart snow_pea;
    private final ModelPart body;
    private final ModelPart head;

    public SnowPeaModel(ModelPart root) {
        this.snow_pea = root.getChild("snow_pea");
        this.body = this.snow_pea.getChild("body");
        this.head = this.body.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition snow_pea = partdefinition.addOrReplaceChild("snow_pea", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition base = snow_pea.addOrReplaceChild("base", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition front_left_leaf = base.addOrReplaceChild("front_left_leaf", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -1.0F));

        PartDefinition front_left_leaf_r1 = front_left_leaf.addOrReplaceChild("front_left_leaf_r1", CubeListBuilder.create().texOffs(16, 16).addBox(-3.0F, -1.0F, -4.0F, 5.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, -2.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition front_right_leaf = base.addOrReplaceChild("front_right_leaf", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.0F, 0.0F));

        PartDefinition front_right_leaf_r1 = front_right_leaf.addOrReplaceChild("front_right_leaf_r1", CubeListBuilder.create().texOffs(27, 16).addBox(-4.0F, -1.0F, -2.0F, 6.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, -0.5F, 0.0F, 0.0F, 0.3927F));

        PartDefinition back_right_leaf = base.addOrReplaceChild("back_right_leaf", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 1.0F));

        PartDefinition back_right_leaf_r1 = back_right_leaf.addOrReplaceChild("back_right_leaf_r1", CubeListBuilder.create().texOffs(28, 21).addBox(-3.0F, -1.0F, -2.0F, 5.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 2.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition back_left_leaf = base.addOrReplaceChild("back_left_leaf", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, 0.0F));

        PartDefinition back_left_leaf_r1 = back_left_leaf.addOrReplaceChild("back_left_leaf_r1", CubeListBuilder.create().texOffs(17, 22).addBox(-2.0F, -1.0F, -3.0F, 6.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.5F, 0.0F, 0.0F, -0.3927F));

        PartDefinition body = snow_pea.addOrReplaceChild("body", CubeListBuilder.create().texOffs(13, 17).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 23).addBox(-1.5F, -8.0F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -7.0F, -4.5F, 7.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

        PartDefinition ice = head.addOrReplaceChild("ice", CubeListBuilder.create(), PartPose.offset(0.0F, -3.5F, 4.5F));

        PartDefinition ice_r1 = ice.addOrReplaceChild("ice_r1", CubeListBuilder.create().texOffs(17, 1).addBox(-4.5F, 0.0F, -1.0F, 13.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 1.5F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition ice_r2 = ice.addOrReplaceChild("ice_r2", CubeListBuilder.create().texOffs(17, 1).mirror().addBox(-4.5F, 0.0F, -1.0F, 13.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.5F, -1.5F, 0.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(0, 16).addBox(-2.1F, -2.0F, -3.5F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -3.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(@NotNull SnowPea snowPea, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animate(snowPea.idleAnimationState, PeashooterAnimation.PEASHOOTER_IDLE, ageInTicks);
        this.animate(snowPea.shootingAnimationState, PeashooterAnimation.PEASHOOTER_SHOT, ageInTicks);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -15f, 15f);
        headPitch = Mth.clamp(headPitch, -25f, 45f);

        this.head.yRot = headYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
    }


    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        if (this.young) {
            head.xScale = 1.6f;
            head.yScale = 1.6f;
            head.zScale = 1.6f;
        }
        snow_pea.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public @NotNull ModelPart root() {
        return snow_pea;
    }
}
