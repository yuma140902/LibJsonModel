package net.yuma14.mc.lib_json_model.impl.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@SideOnly(Side.CLIENT)
public class TextureRegistry {
    private static final List<String> textureNames = new ArrayList<>();

    private static final Map<String, IIcon> icons = new HashMap<>();

    /**
     * @param textureName "minecraft:stone", "mod_id:texture_name"
     */
    public static IIcon registerBlockTextureToGame(String textureName) {
        textureNames.add(textureName);
        return new TextureAtlasSpriteReference(textureName);
    }

    public static Stream<String> streamTextureNames() {
        return textureNames.stream();
    }

    // BlockDummyから呼ぶことを想定している
    @Deprecated
    public static void registerIIcon(String textureName, IIcon icon) {
        icons.put(textureName, icon);
    }

    // TextureAtlasSpriteReferenceから呼ぶことを想定している
    @Deprecated
    public static IIcon getIIcon(String textureName) {
        return icons.get(textureName);
    }

}
