package net.yuma14.mc.lib_json_model.impl.model;

import net.minecraft.util.IIcon;

import java.util.Collections;
import java.util.Map;

public class TextureVariable {
    public static final TextureVariable EMPTY = new TextureVariable(null) {
        @Override
        public IIcon getIcon(Map<String, IIcon> map) {
            return null;
        }
    };

    private final String textureVariableName;
    // TODO
    protected TextureVariable(String textureVariableName){
        this.textureVariableName = textureVariableName;
    }

    public IIcon getIcon(Map<String, IIcon> map) {
        return map.get(textureVariableName);
    }
}
