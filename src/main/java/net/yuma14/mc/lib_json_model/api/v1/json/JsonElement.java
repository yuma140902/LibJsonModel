package net.yuma14.mc.lib_json_model.api.v1.json;

import java.util.Map;

public class JsonElement {
    public double[] from;
    public double[] to;
    public JsonElementRotation rotation;
    public boolean shade = true;
    public Map<String, JsonFace> faces;
}
