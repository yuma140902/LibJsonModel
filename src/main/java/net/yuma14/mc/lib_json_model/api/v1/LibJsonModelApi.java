package net.yuma14.mc.lib_json_model.api.v1;

import net.yuma14.mc.lib_json_model.api.v1.json.JsonBlockModel;
import net.yuma14.mc.lib_json_model.api.v1.render.IBlockModel;
import net.yuma14.mc.lib_json_model.impl.LibJsonModelApiImpl;

public interface LibJsonModelApi {
    public static final LibJsonModelApi INSTANCE = new LibJsonModelApiImpl();

    JsonBlockModel loadJsonBlockModel(String json);
    IBlockModel compileBlockModel(JsonBlockModel model);
    IBlockModel compileBlockModel(JsonBlockModel model, ParentModelResolver<JsonBlockModel> resolver);
}
