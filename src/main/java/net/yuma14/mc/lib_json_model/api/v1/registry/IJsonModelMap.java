package net.yuma14.mc.lib_json_model.api.v1.registry;

import net.yuma14.mc.lib_json_model.api.v1.render.IBlockModel;

public interface IJsonModelMap {
    IBlockModel getModel(String name);
}
