package net.yuma14.mc.lib_json_model.impl;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.yuma14.mc.lib_json_model.api.v1.LibJsonModelApi;
import net.yuma14.mc.lib_json_model.api.v1.IJsonModelRegistry;

public class LibJsonModelApiImpl implements LibJsonModelApi {
    @Override
    public IJsonModelRegistry newModelRegistry() {
        return ModLibJsonModel.proxy.newModelRegistry();
    }
}
