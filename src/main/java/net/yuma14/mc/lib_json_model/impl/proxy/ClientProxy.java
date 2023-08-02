package net.yuma14.mc.lib_json_model.impl.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.yuma14.mc.lib_json_model.api.v1.IJsonModelRegistry;
import net.yuma14.mc.lib_json_model.impl.client.ClientSideJsonModelRegistry;
import net.yuma14.mc.lib_json_model.impl.handlers.ClientEventHandler;
import net.yuma14.mc.lib_json_model.impl.render.BlockWithJsonModelRenderer;

public class ClientProxy extends CommonProxy {
    @Override
    public int getNewRenderId() {
        return RenderingRegistry.getNextAvailableRenderId();
    }

    @Override
    public void registerRenderers() {
        RenderingRegistry.registerBlockHandler(new BlockWithJsonModelRenderer());
    }

    @Override
    public void registerEventHandlers() {
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }

    @Override
    public IJsonModelRegistry newModelRegistry() {
        return new ClientSideJsonModelRegistry();
    }
}
