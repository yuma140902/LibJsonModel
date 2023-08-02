package net.yuma14.mc.lib_json_model.api.v1;

/**
 * Per-MOD registry for JSON models.
 * <p>
 * JSON models cannot be compiled individually because they may refer to other models by their `parent` values.
 * All models must be registered and then compiled together.
 */
public interface IJsonModelRegistry {
    /**
     * Register a JSON model
     *
     * @param name An arbitrary string. It may or may not be in the format "mod_id:model_name".
     *             The registry is separate for each mod, so there is no need to worry about duplication with other mods.
     *             <p>
     *             It is also the name of the model when specified by `parent` in the JSON models.
     */
    void register(String name, String json);

    /**
     * After you have registered all the models your mod requires, call this method and all the models will be compiled.
     */
    IJsonModelMap compileAllModels();
}
