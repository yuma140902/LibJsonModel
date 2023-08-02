package net.yuma14.mc.lib_json_model.api.v1.math;

// C: coordinate system

/**
 * Represents a 3D vector in the specified coordinate system
 *
 * @param <C> coordinate system. Usually {@link BCS} or {@link WCS}
 */
public class Vec3<C> {
    protected final double x;
    protected final double y;
    protected final double z;


    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    protected static <C> boolean almostEqual(Vec3<C> a, Vec3<C> b) {
        if (a == null || b == null) return false;

        final double eps = 1e-6;
        return almostEqual(a.x, b.x, eps) && almostEqual(a.y, b.y, eps) && almostEqual(a.z, b.z, eps);
    }

    private static boolean almostEqual(double a, double b, double eps) {
        double magnitude;
        if (a == 0.0) {
            magnitude = 1.0;
        } else {
            magnitude = a;
        }

        return Math.abs((a - b) / magnitude) <= eps;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double z() {
        return z;
    }

    public double length() {
        return Math.sqrt(x * 2 + y * 2 + z * 2);
    }

    public Vec3<C> normalized() {
        double len = length();
        return multiplied(1.0 / len);
    }

    public Vec3<C> multiplied(double d) {
        return new Vec3<>(x * d, y * d, z * d);
    }

    public Vec3<C> cross(Vec3<C> b) {
        Vec3<C> a = this;
        double x = a.y * b.z - a.z * b.y;
        double y = a.z * b.x - a.x * b.z;
        double z = a.x * b.y - a.y * b.x;
        return new Vec3<>(x, y, z);
    }

    @Override
    public String toString() {
        return String.format("(%f, %f, %f)", x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vec3)) return false;

        Vec3<?> vec3 = (Vec3<?>) o;

        if (Double.compare(vec3.x, x) != 0) return false;
        if (Double.compare(vec3.y, y) != 0) return false;
        return Double.compare(vec3.z, z) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
