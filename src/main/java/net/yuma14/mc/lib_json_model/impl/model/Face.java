package net.yuma14.mc.lib_json_model.impl.model;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.util.ForgeDirection;

public class Face {
    public static final Face EMPTY =
            new Face(0, 0, 16, 16, TextureVariable.EMPTY, ForgeDirection.UNKNOWN);
    public final double minU;
    public final double minV;
    public final double maxU;
    public final double maxV;
    public final TextureVariable texture;
    public final ForgeDirection cullFace;

    // minU, minV, maxU, maxVの取る値は0.0～16.0
    public Face(double minU, double minV, double maxU, double maxV, TextureVariable texture, ForgeDirection cullFace) {
        this.minU = minU;
        this.minV = minV;
        this.maxU = maxU;
        this.maxV = maxV;
        this.texture = texture;
        this.cullFace = cullFace;
    }

}
