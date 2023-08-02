package net.yuma14.mc.lib_json_model.impl;

import net.yuma14.mc.lib_json_model.api.v1.IJsonModelRegistry;
import net.yuma14.mc.lib_json_model.api.v1.LibJsonModelApi;

public class LibJsonModelApiImpl implements LibJsonModelApi {
    @Override
    public IJsonModelRegistry newModelRegistry() {
        return ModLibJsonModel.proxy.newModelRegistry();
    }
}
