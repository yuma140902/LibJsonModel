package net.yuma14.mc.lib_json_model.api.v1;

/**
 * Map of model names and models
 */
public interface IJsonModelMap {
    IBlockModel getModel(String name);
}
