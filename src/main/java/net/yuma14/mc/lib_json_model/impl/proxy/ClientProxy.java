package net.yuma14.mc.lib_json_model.impl.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.yuma14.mc.lib_json_model.impl.render.TestSimpleBlockRenderingHandler;

public class ClientProxy extends CommonProxy {
    @Override
    public int getNewRenderId() {
        return RenderingRegistry.getNextAvailableRenderId();
    }

    @Override
    public void registerRenderers() {
        RenderingRegistry.registerBlockHandler(new TestSimpleBlockRenderingHandler());
    }
}
