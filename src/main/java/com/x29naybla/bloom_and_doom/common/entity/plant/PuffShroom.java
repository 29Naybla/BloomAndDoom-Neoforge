package com.x29naybla.bloom_and_doom.common.entity.plant;

import com.x29naybla.bloom_and_doom.common.entity.ShootingPlant;
import com.x29naybla.bloom_and_doom.common.registry.BnDItems;
import com.x29naybla.bloom_and_doom.common.tag.BnDTags;
import com.x29naybla.bloom_and_doom.common.entity.projectile.SporeProjectile;
import com.x29naybla.bloom_and_doom.common.registry.BnDSounds;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class PuffShroom extends ShootingPlant {
    //Properties
    public PuffShroom(EntityType<? extends PuffShroom> entityType, Level level) {
        super(entityType, level, BnDTags.Items.SUSTAINS_PUFF_SHROOMS, BnDItems.PUFF_SHROOM_SEED_PACKET.toStack(), BnDItems.POTTED_PUFF_SHROOM.toStack());
        this.isMushroom = true;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return ShootingPlant.createAttributes()
                .add(Attributes.ENTITY_INTERACTION_RANGE, 4);
    }

    //Goals and AI
    @Override
    public void performRangedAttack(@NotNull LivingEntity target, float distanceFactor) {
        SporeProjectile spore = new SporeProjectile(this.level(), this);
        double d0 = target.getEyeY() - (double)1.1F;
        double d1 = target.getX() - this.getX();
        double d2 = d0 - spore.getY();
        double d3 = target.getZ() - this.getZ();
        double d4 = Math.sqrt(d1 * d1 + d3 * d3) * (double)0.2F;
        spore.shoot(d1, d2 + d4, d3, 1.6F, 0F);
        this.playSound(BnDSounds.PUFF.get(), 0.5F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level().addFreshEntity(spore);
    }
}
