package com.x29naybla.bloom_and_doom.integration.jade;

import com.x29naybla.bloom_and_doom.common.block.entity.PlanterBlockEntity;
import com.x29naybla.bloom_and_doom.common.entity.AbstractPlant;
import com.x29naybla.bloom_and_doom.common.tag.CommonTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.theme.IThemeHelper;

public enum SeedPacketsTimerProvider implements IEntityComponentProvider, IServerDataProvider<EntityAccessor> {
    SEED_PACKETS;

    @Override
    public void appendTooltip(ITooltip iTooltip, EntityAccessor entityAccessor, IPluginConfig iPluginConfig) {
        CompoundTag compound = entityAccessor.getServerData();
        AbstractPlant abstractPlant = (AbstractPlant) entityAccessor.getEntity();

        if (compound.contains("hasSeedPacket") && compound.contains("SeedPacketsTimer") && compound.getBoolean("isFromPlanter") && compound.getBoolean("onPlanter") && !(compound.getBoolean("onFlowerPot")) && !(abstractPlant.isBaby())) {
            iTooltip.add(Component.translatable("bloom_and_doom.seed_packets_timer", entityAccessor.getServerData().getInt("SeedPacketsTimer")));
            iTooltip.append(IThemeHelper.get().seconds(compound.getInt("SeedPacketsTimer"), 20));
        }
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, EntityAccessor entityAccessor) {
        AbstractPlant abstractPlant = (AbstractPlant) entityAccessor.getEntity();
        compoundTag.putInt("SeedPacketsTimer", abstractPlant.packetTime);
        compoundTag.putBoolean("isFromPlanter", abstractPlant.fromPlanter);
        if (abstractPlant.seedPacket != null) {
            compoundTag.putBoolean("hasSeedPacket", true);
        }
        compoundTag.putBoolean("onPlanter", (abstractPlant.level().getBlockEntity(abstractPlant.blockPosition().below()) instanceof PlanterBlockEntity planter && planter.content.getStackInSlot(0).is(abstractPlant.substrate)));
        compoundTag.putBoolean("onFlowerPot", (abstractPlant.level().getBlockState(abstractPlant.getOnPos()).is(CommonTags.Blocks.FLOWER_POTS)));
    }

    @Override
    public ResourceLocation getUid() {
        return JadeIntegration.SEED_PACKETS_TIMER;
    }

}
