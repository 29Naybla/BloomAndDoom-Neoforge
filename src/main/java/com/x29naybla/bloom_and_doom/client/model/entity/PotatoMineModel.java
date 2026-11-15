package com.x29naybla.bloom_and_doom.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.client.animation.PotatoMineAnimation;
import com.x29naybla.bloom_and_doom.common.entity.plant.PotatoMine;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import org.jetbrains.annotations.NotNull;

public class PotatoMineModel<T extends PotatoMine> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(BloomAndDoom.locationWithBnDNamespace("potato_mine"), "main");
    private final ModelPart potato_mine;

    public PotatoMineModel(ModelPart root) {
        this.potato_mine = root.getChild("potato_mine");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition potato_mine = partdefinition.addOrReplaceChild("potato_mine", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));

        PartDefinition body = potato_mine.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 7.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(17, 18).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.5F, 0.0F));

        PartDefinition ball_off = body.addOrReplaceChild("ball_off", CubeListBuilder.create().texOffs(0, 18).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, 0.0F));

        PartDefinition ball_on = body.addOrReplaceChild("ball_on", CubeListBuilder.create().texOffs(0, 28).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(@NotNull PotatoMine potatoMine, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.animate(potatoMine.armingAnimationState, PotatoMineAnimation.POTATO_MINE_ARMING, ageInTicks);
        this.animate(potatoMine.idleAnimationState, PotatoMineAnimation.POTATO_MINE_IDLE, ageInTicks);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        potato_mine.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public @NotNull ModelPart root() {
        return potato_mine;
    }
}
