package net.yuma14.mc.lib_json_model.api.v1.math;

/**
 * World Coordinate System
 * <p>
 * WCS is a coordinate system that represents a location in the world.
 * The direction from east to west is the positive direction of the x-axis,
 * and the direction from south to north is the positive direction of the z-axis.
 * Upward is the positive direction of the Y-axis.
 *
 * <h2>The origin</h2>
 * The origin is the block with coordinates (0, 0, 0).
 * More precisely, the lower northwest vertex of that block is the origin.
 *
 * <h2>Scale</h2>
 * The length of one side of the block is 1.
 */
public interface WCS {
    // Nothing
    // This interface is just a tag for Vec3<C>
}
