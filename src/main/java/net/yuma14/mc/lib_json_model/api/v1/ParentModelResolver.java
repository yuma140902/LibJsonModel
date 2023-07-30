package net.yuma14.mc.lib_json_model.api.v1;

@FunctionalInterface
public interface ParentModelResolver<T> {
    T resolve(String parentName);
}
