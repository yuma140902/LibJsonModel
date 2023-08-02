package net.yuma14.mc.lib_json_model.impl;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.yuma14.mc.lib_json_model.api.v1.IBlockModel;
import net.yuma14.mc.lib_json_model.api.v1.IJsonModelMap;
import net.yuma14.mc.lib_json_model.api.v1.LibJsonModelApi;
import net.yuma14.mc.lib_json_model.api.v1.IJsonModelRegistry;
import net.yuma14.mc.lib_json_model.impl.block.BlockDummy;
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

    public static final BlockDummy dummy = new BlockDummy();
    public static final BlockTestSBRH TEST_SBRH = new BlockTestSBRH();
    public static IBlockModel TEST_SBRH_MODEL;

    public static final Logger LOGGER = LogManager.getLogger("LibJsonModel");

    @SidedProxy(modId = ModLibJsonModel.MOD_ID, clientSide = "net.yuma14.mc.lib_json_model.impl.proxy.ClientProxy", serverSide = "net.yuma14.mc.lib_json_model.impl.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        SIMPLE_BLOCK_WITH_JSON_MODEL_RENDER_TYPE = proxy.getNewRenderId();
        proxy.registerRenderers();

        String json = "{\n" +
                "  \"textures\": {\n" +
                "    \"stone\": \"minecraft:stone\",\n" +
                "    \"wood\": \"minecraft:planks_oak\"\n" +
                "  },\n" +
                "  \"elements\": [\n" +
                "    {\n" +
                "      \"from\": [0, 0, 0],\n" +
                "      \"to\": [16, 16, 8],\n" +
                "      \"faces\": {\n" +
                "        \"up\": {\n" +
                "          \"uv\": [0, 0, 16, 16],\n" +
                "          \"texture\": \"#stone\"\n" +
                "        },\n" +
                "        \"down\": {\n" +
                "          \"texture\": \"#stone\"\n" +
                "        },\n" +
                "        \"north\": {\n" +
                "          \"texture\": \"#stone\"\n" +
                "        },\n" +
                "        \"south\": {\n" +
                "          \"texture\": \"#stone\"\n" +
                "        },\n" +
                "        \"west\": {\n" +
                "          \"texture\": \"#stone\"\n" +
                "        },\n" +
                "        \"east\": {\n" +
                "          \"texture\": \"#stone\"\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"from\": [0, 0, 8],\n" +
                "      \"to\": [16, 8, 16],\n" +
                "      \"faces\": {\n" +
                "        \"up\": {\n" +
                "          \"texture\": \"#wood\"\n" +
                "        },\n" +
                "        \"down\": {\n" +
                "          \"texture\": \"#wood\"\n" +
                "        },\n" +
                "        \"north\": {\n" +
                "          \"texture\": \"#wood\"\n" +
                "        },\n" +
                "        \"south\": {\n" +
                "          \"texture\": \"#wood\"\n" +
                "        },\n" +
                "        \"west\": {\n" +
                "          \"texture\": \"#wood\"\n" +
                "        },\n" +
                "        \"east\": {\n" +
                "          \"texture\": \"#wood\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        IJsonModelRegistry jsonModelRegistry = LibJsonModelApi.INSTANCE.newModelRegistry();
        jsonModelRegistry.register("test_sbrh", json);
        IJsonModelMap jsonModelMap = jsonModelRegistry.compileAllModels();
        ModLibJsonModel.TEST_SBRH_MODEL = jsonModelMap.getModel("test_sbrh");

        GameRegistry.registerBlock(dummy, "dummy");
        GameRegistry.registerBlock(TEST_SBRH, "test_sbrh");
        TEST_SBRH.setCreativeTab(CreativeTabs.tabBlock);
    }
}
