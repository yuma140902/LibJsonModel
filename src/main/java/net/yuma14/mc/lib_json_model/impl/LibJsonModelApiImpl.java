package net.yuma14.mc.lib_json_model.impl;

import com.google.gson.Gson;
import net.yuma14.mc.lib_json_model.api.v1.LibJsonModelApi;
import net.yuma14.mc.lib_json_model.api.v1.registry.IJsonModelRegistry;
import net.yuma14.mc.lib_json_model.impl.registry.JsonModelRegistry;

public class LibJsonModelApiImpl implements LibJsonModelApi {
    @Override
    public IJsonModelRegistry newModelRegistry() {
        return new JsonModelRegistry();
    }
}
