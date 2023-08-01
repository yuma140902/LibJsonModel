package net.yuma14.mc.lib_json_model.impl;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.yuma14.mc.lib_json_model.api.v1.LibJsonModelApi;
import net.yuma14.mc.lib_json_model.api.v1.client.IJsonModelRegistry;
import net.yuma14.mc.lib_json_model.impl.client.JsonModelRegistry;

public class LibJsonModelApiImpl implements LibJsonModelApi {
    @Override
    @SideOnly(Side.CLIENT)
    public IJsonModelRegistry newModelRegistry() {
        return new JsonModelRegistry();
    }
}
