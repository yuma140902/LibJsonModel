package net.yuma14.mc.lib_json_model.impl.proxy;

import net.yuma14.mc.lib_json_model.api.v1.IJsonModelRegistry;
import net.yuma14.mc.lib_json_model.impl.server.ServerSideJsonModelRegistry;

public class ServerProxy extends CommonProxy {
    @Override
    public int getNewRenderId() {
        return -1;
    }

    @Override
    public void registerRenderers() {
        // nothing
    }

    @Override
    public IJsonModelRegistry newModelRegistry() {
        return new ServerSideJsonModelRegistry();
    }
}
