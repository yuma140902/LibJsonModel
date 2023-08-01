package net.yuma14.mc.lib_json_model.impl.model;

import net.minecraftforge.common.util.ForgeDirection;

public class Face {
    public final double minU;
    public final double minV;
    public final double maxU;
    public final double maxV;
    public final TextureId texture;
    public final ForgeDirection cullFace;

    protected Face(double minU, double minV, double maxU, double maxV, TextureId texture, ForgeDirection cullFace) {
        this.minU = minU;
        this.minV = minV;
        this.maxU = maxU;
        this.maxV = maxV;
        this.texture = texture;
        this.cullFace = cullFace;
    }

}
