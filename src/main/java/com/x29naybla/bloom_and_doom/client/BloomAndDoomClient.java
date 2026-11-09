package com.x29naybla.bloom_and_doom.client;

import com.x29naybla.bloom_and_doom.BloomAndDoom;
import com.x29naybla.bloom_and_doom.client.gui.AlmanacScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = BloomAndDoom.MOD_ID, dist = Dist.CLIENT)
public class BloomAndDoomClient {
    public BloomAndDoomClient(ModContainer modContainer) {
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

	public static void openAtlas() {
        Minecraft.getInstance().setScreen(new AlmanacScreen(Component.translatable(BloomAndDoom.MOD_ID + ".gui.almanac")));
    }
}
