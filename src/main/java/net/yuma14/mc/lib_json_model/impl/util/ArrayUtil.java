package net.yuma14.mc.lib_json_model.impl.util;

public class ArrayUtil {
    public static double getAtOr0(double[] array, int index) {
        if(array != null && index < array.length) {
            return array[index];
        }
        return 0.0;
    }

    public static double getAtOr(double[] array, int index, double dflt) {
        if(array != null && index < array.length) {
            return array[index];
        }
        return dflt;
    }
}
