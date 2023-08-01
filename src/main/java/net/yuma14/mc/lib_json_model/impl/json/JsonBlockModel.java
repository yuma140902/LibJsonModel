package net.yuma14.mc.lib_json_model.impl.json;

import java.util.List;
import java.util.Map;

public class JsonBlockModel {
    public String parent;
    public boolean ambientocclusion = true;
    public JsonDisplay display;
    public Map<String, String> textures;
    public List<JsonElement> elements;
}
