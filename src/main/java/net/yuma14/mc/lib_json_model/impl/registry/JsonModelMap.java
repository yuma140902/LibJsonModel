package net.yuma14.mc.lib_json_model.impl.registry;

import net.yuma14.mc.lib_json_model.api.v1.IJsonModelMap;
import net.yuma14.mc.lib_json_model.api.v1.IBlockModel;

import java.util.Map;

public class JsonModelMap implements IJsonModelMap {

    private final Map<String, ? extends IBlockModel> map;

    public JsonModelMap(Map<String, ? extends IBlockModel> map) {
        this.map = map;
    }

    @Override
    public IBlockModel getModel(String name) {
        return map.get(name);
    }
}
