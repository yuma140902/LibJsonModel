package net.yuma14.mc.lib_json_model.impl;

import com.google.gson.Gson;
import net.yuma14.mc.lib_json_model.api.v1.LibJsonModelApi;
import net.yuma14.mc.lib_json_model.api.v1.ParentModelResolver;
import net.yuma14.mc.lib_json_model.api.v1.json.JsonBlockModel;
import net.yuma14.mc.lib_json_model.api.v1.render.IBlockModel;
import net.yuma14.mc.lib_json_model.impl.model.BlockModel;

public class LibJsonModelApiImpl implements LibJsonModelApi {
    Gson gson = new Gson();

    @Override
    public JsonBlockModel loadJsonBlockModel(String json) {
        return gson.fromJson(json, JsonBlockModel.class);
    }

    @Override
    public IBlockModel compileBlockModel(JsonBlockModel model) {
        return compileBlockModel(model, s -> null);
    }

    @Override
    public IBlockModel compileBlockModel(JsonBlockModel model, ParentModelResolver<JsonBlockModel> resolver) {
        return BlockModel.fromJsonModel(model, resolver);
    }
}
