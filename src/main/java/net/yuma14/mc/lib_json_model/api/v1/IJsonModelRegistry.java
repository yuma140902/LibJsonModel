package net.yuma14.mc.lib_json_model.api.v1;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public interface IJsonModelRegistry {
    /**
     * Register a JSON model
     * @param name "mod_id:block/foobar"
     */
    void register(String name, String json);

    IJsonModelMap compileAllModels();
}
