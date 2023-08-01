package net.yuma14.mc.lib_json_model.api.v1;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.yuma14.mc.lib_json_model.api.v1.client.IJsonModelRegistry;
import net.yuma14.mc.lib_json_model.impl.LibJsonModelApiImpl;

public interface LibJsonModelApi {
    public static final LibJsonModelApi INSTANCE = new LibJsonModelApiImpl();

    @SideOnly(Side.CLIENT)
    IJsonModelRegistry newModelRegistry();
}
