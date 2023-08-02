package net.yuma14.mc.lib_json_model.impl.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.yuma14.mc.lib_json_model.impl.math.*;
import net.yuma14.mc.lib_json_model.impl.util.McConst;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class Element {
    public final Face top;
    public final Face bottom;
    // north = positive z
    public final Face north;
    // south = positive z
    public final Face south;
    // west = negative x
    public final Face west;
    // east = positive x
    public final Face east;

    public final Cuboid<BCS> cuboid;

    protected Element(Vec3<BCS> from, Vec3<BCS> to, Face top, Face bottom, Face north, Face south, Face west, Face east) {
        this.top = top;
        this.bottom = bottom;
        this.north = north;
        this.south = south;
        this.west = west;
        this.east = east;

        this.cuboid = new Cuboid<>(from, to);
    }

    @Nullable
    public Face getFace(int side) {
        switch (side) {
            case McConst.SIDE_TOP: return top;
            case McConst.SIDE_BOTTOM: return bottom;
            case McConst.SIDE_NORTH: return north;
            case McConst.SIDE_SOUTH: return south;
            case McConst.SIDE_WEST: return west;
            case McConst.SIDE_EAST: return east;
            default: return null;
        }
    }
}
