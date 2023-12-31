package net.yuma14.mc.lib_json_model.api.v1.math;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * Represents the position of a block in the world
 */
public class BlockPos {
    protected final int x;
    protected final int y;
    protected final int z;

    public BlockPos(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a new instance that moved by the specified distance in the specified direction.
     */
    public BlockPos offset(ForgeDirection direction, int distance) {
        return new BlockPos(
                x + direction.offsetX * distance,
                y + direction.offsetY * distance,
                z + direction.offsetZ * distance
        );
    }

    /**
     * Creates a new instance moved by distance 1 in the specified direction.
     */
    public BlockPos offset(ForgeDirection direction) {
        return offset(direction, 1);
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int z() {
        return z;
    }

    @Override
    public String toString() {
        return String.format("BlockPos(%d, %d, %d)", x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockPos)) return false;

        BlockPos blockPos = (BlockPos) o;

        if (x != blockPos.x) return false;
        if (y != blockPos.y) return false;
        return z == blockPos.z;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }
}
