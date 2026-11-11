package com.x29naybla.bloom_and_doom.common.entity.plant;

import com.x29naybla.bloom_and_doom.common.entity.ExplosivePlant;
import com.x29naybla.bloom_and_doom.common.registry.BnDItems;
import com.x29naybla.bloom_and_doom.common.tag.BnDTags;
import com.x29naybla.bloom_and_doom.common.registry.BnDSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class DoomShroom extends ExplosivePlant {
    //Properties
    public DoomShroom(EntityType<? extends DoomShroom> entityType, Level level) {
        super(entityType, level, BnDTags.Items.SUSTAINS_DOOM_SHROOMS, BnDItems.DOOM_SHROOM_SEED_PACKET.toStack(), null, 4, 180, BnDSounds.DOOM);
        this.isMushroom = true;
    }

    //Data
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
    }

    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
    }
}
