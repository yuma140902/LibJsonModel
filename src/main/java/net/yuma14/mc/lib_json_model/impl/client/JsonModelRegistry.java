package net.yuma14.mc.lib_json_model.impl.client;

import com.google.gson.Gson;
import cpw.mods.fml.common.ProgressManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.yuma14.mc.lib_json_model.api.v1.IJsonModelMap;
import net.yuma14.mc.lib_json_model.api.v1.client.IJsonModelRegistry;
import net.yuma14.mc.lib_json_model.api.v1.IBlockModel;
import net.yuma14.mc.lib_json_model.impl.json.JsonBlockModel;
import net.yuma14.mc.lib_json_model.impl.model.PartialBlockModel;
import net.yuma14.mc.lib_json_model.impl.registry.JsonModelMap;

import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
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
