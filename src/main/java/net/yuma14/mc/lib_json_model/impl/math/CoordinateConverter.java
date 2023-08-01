package net.yuma14.mc.lib_json_model.impl.math;

public class CoordinateConverter {
    public static Vec3<WCS> BCStoWCS(Vec3<BCS> v, BlockPos p) {
        return BCStoWCS(v, p.x, p.y, p.z);
    }

    public static Vec3<WCS> BCStoWCS(Vec3<BCS> v, int blockX, int blockY, int blockZ) {
        return new Vec3<>(v.x + blockX, v.y + blockY, v.z + blockZ);
    }

    public static Cuboid<WCS> BCStoWCS(Cuboid<BCS> c, BlockPos p) {
        return new Cuboid<>(BCStoWCS(c.from, p), BCStoWCS(c.to, p));
    }

    public static Cuboid<WCS> BCStoWCS(Cuboid<BCS> c, int blockX, int blockY, int blockZ) {
        return new Cuboid<>(BCStoWCS(c.from, blockX, blockY, blockZ), BCStoWCS(c.to, blockX, blockY, blockZ));
    }
}
