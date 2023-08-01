package net.yuma14.mc.lib_json_model.impl;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.yuma14.mc.lib_json_model.api.v1.IBlockModel;
import net.yuma14.mc.lib_json_model.impl.block.BlockTestSBRH;
import net.yuma14.mc.lib_json_model.impl.proxy.CommonProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(modid = ModLibJsonModel.MOD_ID, version = ModLibJsonModel.MOD_VERSION)
public class ModLibJsonModel {
    public static final String MOD_ID = "libjsonmodel";
    public static final String MOD_VERSION = "0.1.0";
    public static final String MOD_NAME = "LibJsonModel";

    public static int SIMPLE_BLOCK_WITH_JSON_MODEL_RENDER_TYPE;
    public static final BlockTestSBRH TEST_SBRH = new BlockTestSBRH();
    public static IBlockModel TEST_SBRH_MODEL;

    public static final Logger LOGGER = LogManager.getLogger("LibJsonModel");

    @SidedProxy(modId = ModLibJsonModel.MOD_ID, clientSide = "net.yuma14.mc.lib_json_model.impl.proxy.ClientProxy", serverSide = "net.yuma14.mc.lib_json_model.impl.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        SIMPLE_BLOCK_WITH_JSON_MODEL_RENDER_TYPE = proxy.getNewRenderId();
        proxy.registerRenderers();

        proxy.registerJsonModels();

        GameRegistry.registerBlock(TEST_SBRH, "test_sbrh");
        TEST_SBRH.setCreativeTab(CreativeTabs.tabBlock);
    }
}
