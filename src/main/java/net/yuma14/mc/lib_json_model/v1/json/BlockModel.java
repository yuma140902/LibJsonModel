package net.yuma14.mc.lib_json_model.v1.json;

import java.util.List;
import java.util.Map;

public class BlockModel {
    public String parent;
    public boolean ambientocclusion = true;
    public Display display;
    public Map<String, String> textures;
    public List<Element> elements;
}
