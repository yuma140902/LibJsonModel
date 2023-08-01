package net.yuma14.mc.lib_json_model.api.v1;

import net.yuma14.mc.lib_json_model.api.v1.registry.IJsonModelRegistry;
import net.yuma14.mc.lib_json_model.impl.LibJsonModelApiImpl;

public interface LibJsonModelApi {
    public static final LibJsonModelApi INSTANCE = new LibJsonModelApiImpl();

    IJsonModelRegistry newModelRegistry();
}
