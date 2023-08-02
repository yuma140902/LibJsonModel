package net.yuma14.mc.lib_json_model.api.v1.math;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.AxisAlignedBB;

public class Cuboid<C> {
    protected final Vec3<C> from;
    protected final Vec3<C> to;

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

    public void setRenderBounds(RenderBlocks renderer) {
        renderer.setRenderBounds(from.x, from.y, from.z, to.x, to.y, to.z);
    }

    public AxisAlignedBB toAABB() {
        return AxisAlignedBB.getBoundingBox(this.from.x, this.from.y, this.from.z, this.to.x, this.to.y, this.to.z);
    }
}