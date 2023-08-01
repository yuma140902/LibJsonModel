package net.yuma14.mc.lib_json_model.impl.registry;

import com.google.gson.Gson;
import cpw.mods.fml.common.ProgressManager;
import net.yuma14.mc.lib_json_model.api.v1.registry.IJsonModelMap;
import net.yuma14.mc.lib_json_model.api.v1.registry.IJsonModelRegistry;
import net.yuma14.mc.lib_json_model.api.v1.render.IBlockModel;
import net.yuma14.mc.lib_json_model.impl.json.JsonBlockModel;
import net.yuma14.mc.lib_json_model.impl.model.BlockModel;
import net.yuma14.mc.lib_json_model.impl.model.Compiler;
import net.yuma14.mc.lib_json_model.impl.model.PartialBlockModel;

import java.util.HashMap;
import java.util.Map;

public class JsonModelRegistry implements IJsonModelRegistry {
    private final Gson gson = new Gson();
    private final Map<String, String> map = new HashMap<>();

    public JsonModelRegistry(){}

    @Override
    public void register(String name, String json) {
        // TODO: warn if duplicate name
        map.put(name, json);
    }

    @Override
    public IJsonModelMap compileAllModels() {
        ProgressManager.ProgressBar bar = ProgressManager.push("Compiling models", map.size());
        Map<String, PartialBlockModel> partialBlockModelMap = new HashMap<>();
        for(final Map.Entry<String, String> entry : this.map.entrySet()) {
            bar.step(entry.getKey());
            JsonBlockModel jsonBlockModel = gson.fromJson(entry.getValue(), JsonBlockModel.class);
            PartialBlockModel compiledBlockModel = Compiler.compileJsonBlockModel(entry.getKey(), jsonBlockModel);
            partialBlockModelMap.put(entry.getKey(), compiledBlockModel);
        }
        ProgressManager.pop(bar);
        Map<String, ? extends IBlockModel> map = Compiler.mergeBlockModels(partialBlockModelMap);
        return new JsonModelMap(map);
    }
}
