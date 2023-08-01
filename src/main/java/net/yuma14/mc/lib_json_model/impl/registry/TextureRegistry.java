package net.yuma14.mc.lib_json_model.impl.registry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IIcon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: 他のクラスにもSideOnlyをつける
@SideOnly(Side.CLIENT)
public class TextureRegistry {
    /**
     * @param textureName "minecraft:stone", "mod_id:texture_name"
     */
    public static IIcon registerBlockTextureToGame(String textureName) {
        return Minecraft.getMinecraft().getTextureMapBlocks().registerIcon(textureName);
    }
}
