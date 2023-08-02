package net.yuma14.mc.lib_json_model.impl.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class PartialBlockModel {
    public final @Nullable String parent;
    public final List<Element> elements;
    public final boolean useAmbientOcclusion;
    public final Map<String, IIcon> texturesMap;

    protected PartialBlockModel(@Nullable String parent, List<Element> elements, boolean useAmbientOcclusion, Map<String, IIcon> texturesMap) {
        this.parent = parent;
        this.elements = elements;
        this.useAmbientOcclusion = useAmbientOcclusion;
        this.texturesMap = texturesMap;
    }
}
