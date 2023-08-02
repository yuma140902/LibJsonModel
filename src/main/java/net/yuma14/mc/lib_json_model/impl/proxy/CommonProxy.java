package net.yuma14.mc.lib_json_model.impl.proxy;

import net.yuma14.mc.lib_json_model.api.v1.IJsonModelRegistry;

public abstract class CommonProxy {
    public abstract int getNewRenderId();
    public abstract void registerRenderers();

    public abstract IJsonModelRegistry newModelRegistry();
}
