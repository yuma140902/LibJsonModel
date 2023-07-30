package net.yuma14.mc.lib_json_model.impl;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.init.Blocks;
import net.yuma14.mc.lib_json_model.impl.proxy.CommonProxy;

@Mod(modid = ModLibJsonModel.MOD_ID, version = ModLibJsonModel.MOD_VERSION)
public class ModLibJsonModel {
    public static final String MOD_ID = "libjsonmodel";
    public static final String MOD_VERSION = "0.1.0";
    public static final String MOD_NAME = "LibJsonModel";

    @SidedProxy(modId = ModLibJsonModel.MOD_ID, clientSide = "net.yuma14.mc.lib_json_model.impl.proxy.ClientProxy", serverSide = "net.yuma14.mc.lib_json_model.impl.proxy.ServerProxy")
    public static CommonProxy proxy;

    public void init(FMLInitializationEvent event) {
        System.out.println("DIRT BLOCK >> " + Blocks.dirt.getUnlocalizedName());
    }
}
