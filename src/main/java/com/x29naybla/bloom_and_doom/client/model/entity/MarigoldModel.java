package com.x29naybla.bloom_and_doom.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.client.animation.SunflowerAnimation;
import com.x29naybla.bloom_and_doom.common.entity.Marigold;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class MarigoldModel<T extends Marigold> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "marigold"), "main");
    private final ModelPart marigold;
    private final ModelPart body;
    private final ModelPart head;

    public MarigoldModel(ModelPart root) {
        this.marigold = root.getChild("marigold");
        this.body = this.marigold.getChild("body");
        this.head = this.body.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition marigold = partdefinition.addOrReplaceChild("marigold", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition base = marigold.addOrReplaceChild("base", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition front_support = base.addOrReplaceChild("front_support", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -1.0F));

        PartDefinition front_support_r1 = front_support.addOrReplaceChild("front_support_r1", CubeListBuilder.create().texOffs(22, 1).addBox(-3.0F, -1.0F, -4.0F, 5.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, -2.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition right_support = base.addOrReplaceChild("right_support", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.0F, 0.0F));

        PartDefinition right_support_r1 = right_support.addOrReplaceChild("right_support_r1", CubeListBuilder.create().texOffs(33, 1).addBox(-4.0F, -1.0F, -2.0F, 6.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, -0.5F, 0.0F, 0.0F, 0.3927F));

        PartDefinition back_support = base.addOrReplaceChild("back_support", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 1.0F));

        PartDefinition back_support_r1 = back_support.addOrReplaceChild("back_support_r1", CubeListBuilder.create().texOffs(34, 6).addBox(-3.0F, -1.0F, -2.0F, 5.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 2.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition left_support = base.addOrReplaceChild("left_support", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, 0.0F));

        PartDefinition left_support_r1 = left_support.addOrReplaceChild("left_support_r1", CubeListBuilder.create().texOffs(23, 7).addBox(-2.0F, -1.0F, -3.0F, 6.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.5F, 0.0F, 0.0F, -0.3927F));

        PartDefinition body = marigold.addOrReplaceChild("body", CubeListBuilder.create().texOffs(19, 0).addBox(-1.0F, -10.5F, -1.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));

        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(7, 9).addBox(0.0F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -3.5F, 0.0F));

        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 9).addBox(-3.0F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -3.5F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -2.5F, -1.5F, 7.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.5F, 0.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition petals = head.addOrReplaceChild("petals", CubeListBuilder.create().texOffs(2, 16).addBox(-5.5F, -7.0F, -1.5F, 11.0F, 10.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.5F, 1.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(@NotNull Marigold marigold, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animate(marigold.idleAnimationState, SunflowerAnimation.FLOWER_IDLE, ageInTicks);
        this.animate(marigold.generatingAnimationState, SunflowerAnimation.FLOWER_GENERATE, ageInTicks);
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
        marigold.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public @NotNull ModelPart root() {
        return marigold;
    }
}
