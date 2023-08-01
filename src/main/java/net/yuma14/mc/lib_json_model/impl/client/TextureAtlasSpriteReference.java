package net.yuma14.mc.lib_json_model.impl.client;

import net.minecraft.util.IIcon;

public class TextureAtlasSpriteReference implements IIcon {
    private final String name;

    public TextureAtlasSpriteReference(String name){
        this.name = name;
    }

    @Override
    public int getIconWidth() {
        return TextureRegistry.getIIcon(name).getIconWidth();
    }

    @Override
    public int getIconHeight() {
        return TextureRegistry.getIIcon(name).getIconHeight();
    }

    @Override
    public float getMinU() {
        return TextureRegistry.getIIcon(name).getMinU();
    }

    @Override
    public float getMaxU() {
        return TextureRegistry.getIIcon(name).getMaxU();
    }

    @Override
    public float getInterpolatedU(double v) {
        return TextureRegistry.getIIcon(name).getInterpolatedU(v);
    }

    @Override
    public float getMinV() {
        return TextureRegistry.getIIcon(name).getMinV();
    }

    @Override
    public float getMaxV() {
        return TextureRegistry.getIIcon(name).getMaxV();
    }

    @Override
    public float getInterpolatedV(double v) {
        return TextureRegistry.getIIcon(name).getInterpolatedV(v);
    }

    @Override
    public String getIconName() {
        return TextureRegistry.getIIcon(name).getIconName();
    }
}
