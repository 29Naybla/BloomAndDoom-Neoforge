package com.x29naybla.bloom_and_doom.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.common.entity.Chomper;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ChomperModel<T extends Chomper> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(BloomAndDoom.MOD_ID, "chomper"), "main");
    private final ModelPart chomper;

    public ChomperModel(ModelPart root) {
        this.chomper = root.getChild("chomper");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition chomper = partdefinition.addOrReplaceChild("chomper", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition base = chomper.addOrReplaceChild("base", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition front_support = base.addOrReplaceChild("front_support", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -1.0F));

        PartDefinition front_support_r1 = front_support.addOrReplaceChild("front_support_r1", CubeListBuilder.create().texOffs(11, 57).addBox(-3.0F, -1.0F, -5.0F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, -2.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition right_support = base.addOrReplaceChild("right_support", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.0F, 0.0F));

        PartDefinition right_support_r1 = right_support.addOrReplaceChild("right_support_r1", CubeListBuilder.create().texOffs(23, 57).addBox(-5.0F, -1.0F, -2.0F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, -0.5F, 0.0F, 0.0F, 0.3927F));

        PartDefinition back_support = base.addOrReplaceChild("back_support", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 1.0F));

        PartDefinition back_support_r1 = back_support.addOrReplaceChild("back_support_r1", CubeListBuilder.create().texOffs(25, 62).addBox(-3.0F, -1.0F, -2.0F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 2.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition left_support = base.addOrReplaceChild("left_support", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, 0.0F));

        PartDefinition left_support_r1 = left_support.addOrReplaceChild("left_support_r1", CubeListBuilder.create().texOffs(13, 64).addBox(-2.0F, -1.0F, -3.0F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.5F, 0.0F, 0.0F, -0.3927F));

        PartDefinition body = chomper.addOrReplaceChild("body", CubeListBuilder.create().texOffs(5, 50).addBox(-1.5F, -16.0F, -1.5F, 3.0F, 17.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 38).addBox(-1.5F, -3.0F, -6.5F, 3.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -16.0F, 0.0F));

        PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, -2.0F, -1.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition head_top = head.addOrReplaceChild("head_top", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -6.0F, -12.0F, 12.0F, 6.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(49, 6).addBox(-5.0F, 0.0F, -11.5F, 11.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(34, 24).addBox(0.0F, -9.0F, -15.0F, 0.0F, 7.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -5.5F));

        PartDefinition head_bottom = head.addOrReplaceChild("head_bottom", CubeListBuilder.create().texOffs(0, 19).addBox(-5.5F, 0.0F, -12.0F, 12.0F, 6.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(49, 25).addBox(-5.0F, -1.0F, -11.5F, 11.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -5.5F));

        PartDefinition top_leaf = head.addOrReplaceChild("top_leaf", CubeListBuilder.create().texOffs(16, 42).addBox(-2.5F, 0.0F, -2.0F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -3.5F, -4.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition bottom_leaf = head.addOrReplaceChild("bottom_leaf", CubeListBuilder.create().texOffs(16, 42).addBox(-2.5F, 0.0F, -1.0F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.5F, -5.0F, -0.3491F, 0.0F, 0.0F));

        PartDefinition right_leaf = head.addOrReplaceChild("right_leaf", CubeListBuilder.create().texOffs(16, 42).addBox(-2.5F, 0.0F, -1.0F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -1.0F, -4.5F, -0.3491F, 0.0F, 1.5708F));

        PartDefinition left_leaf = head.addOrReplaceChild("left_leaf", CubeListBuilder.create().texOffs(16, 42).addBox(-2.5F, -1.0F, -1.0F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -1.0F, -4.25F, 0.3491F, 0.0F, 1.5708F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(@NotNull Chomper chomper, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        chomper.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public @NotNull ModelPart root() {
        return chomper;
    }
}
