package net.yuma14.mc.lib_json_model.impl.math;

import net.minecraftforge.common.util.ForgeDirection;

public class BlockPos {
    public final int x;
    public final int y;
    public final int z;

    public BlockPos(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public BlockPos offset(ForgeDirection direction, int distance) {
        return new BlockPos(
                x + direction.offsetX * distance,
                y + direction.offsetY * distance,
                z + direction.offsetZ * distance
        );
    }

    public BlockPos offset(ForgeDirection direction) {
        return offset(direction, 1);
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
