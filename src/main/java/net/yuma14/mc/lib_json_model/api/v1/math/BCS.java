package net.yuma14.mc.lib_json_model.api.v1.math;

/**
 * Block Coordinate System
 * <p>
 * BCS is a coordinate system with the northwest lower vertices of a block as the origin.
 * The coordinates of the southeast upper vertex of the block are (1.0, 1.0, 1.0).
 * The x, y, and z axes are parallel to the axes of the World Coordinate System.
 * The scale is the same as the {@link WCS}.
 */
public interface BCS {
    // Nothing
    // This interface is just a tag for Vec3<C>
}
