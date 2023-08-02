package net.yuma14.mc.lib_json_model.api.v1.math;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.AxisAlignedBB;

/**
 * Represents a cuboid in the specified coordinate system
 *
 * @param <C> coordinate system. Usually {@link BCS} or {@link WCS}
 */
public class Cuboid<C> {
    protected final Vec3<C> from;
    protected final Vec3<C> to;

    /**
     * Create a cuboid by specifying two vertices
     *
     * @param from The closer of the two vertices to the origin
     * @param to   The more distant of the two vertices
     */
    public Cuboid(Vec3<C> from, Vec3<C> to) {
        this.from = from;
        this.to = to;
    }

    public Vec3<C> from() {
        return from;
    }

    public Vec3<C> to() {
        return to;
    }

    /**
     * Set up the renderer to match this Cuboid.
     * <p>
     * Normally, this method should be called for Cuboid&lt;BCS&gt;, not Cuboid&lt;WCS&gt;.
     */
    public void setRenderBounds(RenderBlocks renderer) {
        renderer.setRenderBounds(from.x, from.y, from.z, to.x, to.y, to.z);
    }

    /**
     * Returns an AABB that contains this Cuboid.
     * <p>
     * If you have a Cuboid&lt;BCS&gt;,
     * you probably want to convert it to a Cuboid&lt;WCS&gt; using {@link CoordinateConverter}
     * before calling this method.
     * This is because AABBs in block coordinate systems are useless.
     */
    public AxisAlignedBB toAABB() {
        return AxisAlignedBB.getBoundingBox(this.from.x, this.from.y, this.from.z, this.to.x, this.to.y, this.to.z);
    }
}