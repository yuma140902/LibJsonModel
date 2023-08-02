package net.yuma14.mc.lib_json_model.impl.model;

import net.minecraft.util.AxisAlignedBB;
import net.yuma14.mc.lib_json_model.api.v1.IBlockModel;
import net.yuma14.mc.lib_json_model.impl.math.BCS;
import net.yuma14.mc.lib_json_model.impl.math.CoordinateConverter;
import net.yuma14.mc.lib_json_model.impl.math.Cuboid;
import net.yuma14.mc.lib_json_model.impl.math.WCS;

import java.util.List;
import java.util.stream.Stream;

public abstract class BlockModelBase implements IBlockModel {
    @Override
    public void addCollisionBoxesToList(int x, int y, int z, AxisAlignedBB mask, @SuppressWarnings("rawtypes") List list) {
        getCuboids().forEach(localCuboid -> {
            Cuboid<WCS> worldCuboid = CoordinateConverter.BCStoWCS(localCuboid, x, y, z);
            AxisAlignedBB aabb = worldCuboid.toAABB();
            if (mask == null) {
                //noinspection unchecked
                list.add(aabb);
            } else if (aabb != null && mask.intersectsWith(aabb)) {
                //noinspection unchecked
                list.add(aabb);
            }
        });
    }

    protected abstract Stream<Cuboid<BCS>> getCuboids();
}
