package net.yuma14.mc.lib_json_model.impl.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    @Override
    public int getNewRenderId() {
        return RenderingRegistry.getNextAvailableRenderId();
    }
}
