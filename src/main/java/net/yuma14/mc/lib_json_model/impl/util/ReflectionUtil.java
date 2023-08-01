package net.yuma14.mc.lib_json_model.impl.util;

import cpw.mods.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectionUtil {
    public static void setPrivateField(Class<?> cls, Object that, Object newValue, String... fieldNames) {
        try {
            Field field = ReflectionHelper.findField(cls, fieldNames);
            field.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.PRIVATE & ~Modifier.FINAL);

            field.set(that, newValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
