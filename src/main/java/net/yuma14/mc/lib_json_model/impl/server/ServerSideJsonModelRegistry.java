package net.yuma14.mc.lib_json_model.impl.server;

import com.google.gson.Gson;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.yuma14.mc.lib_json_model.api.v1.IJsonModelMap;
import net.yuma14.mc.lib_json_model.api.v1.IJsonModelRegistry;
import net.yuma14.mc.lib_json_model.impl.json.JsonBlockModel;
import net.yuma14.mc.lib_json_model.impl.registry.JsonModelMap;
import net.yuma14.mc.lib_json_model.impl.server.model.ServerSideBlockModel;
import net.yuma14.mc.lib_json_model.impl.server.model.ServerSideModelCompiler;

import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.SERVER)
public class ServerSideJsonModelRegistry implements IJsonModelRegistry {
    private final Gson gson = new Gson();
    private final Map<String, String> map = new HashMap();
    @Override
    public void register(String name, String json) {
        map.put(name, json);
    }

    @Override
    public IJsonModelMap compileAllModels() {
        Map<String, ServerSideBlockModel> map = new HashMap<>();
        for(Map.Entry<String, String> entry : this.map.entrySet()) {
            String modelName = entry.getKey();
            String json = entry.getValue();
            JsonBlockModel blockModel = gson.fromJson(json, JsonBlockModel.class);
            ServerSideBlockModel compiled = ServerSideModelCompiler.compileJsonBlockModel(modelName, blockModel);
            map.put(modelName, compiled);
        }

        return new JsonModelMap(map);
    }
}
