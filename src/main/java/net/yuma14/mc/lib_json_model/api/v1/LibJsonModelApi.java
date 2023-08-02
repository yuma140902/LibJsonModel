package net.yuma14.mc.lib_json_model.api.v1;

import net.yuma14.mc.lib_json_model.impl.LibJsonModelApiImpl;

/**
 * API for LibJsonModel
 */
public interface LibJsonModelApi {
    public static final LibJsonModelApi INSTANCE = new LibJsonModelApiImpl();

    /**
     * Create a new {@link IJsonModelRegistry} instance. Usually, one instance per mod is sufficient.
     */
    IJsonModelRegistry newModelRegistry();
}
