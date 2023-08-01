package net.yuma14.mc.lib_json_model.api.v1.registry;

public interface IJsonModelRegistry {
    /**
     * Register a JSON model
     * @param name "mod_id:block/foobar"
     */
    void register(String name, String json);

    IJsonModelMap compileAllModels();
}
