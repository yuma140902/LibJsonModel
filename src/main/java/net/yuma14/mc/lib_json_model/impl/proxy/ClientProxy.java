package net.yuma14.mc.lib_json_model.impl.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.yuma14.mc.lib_json_model.api.v1.LibJsonModelApi;
import net.yuma14.mc.lib_json_model.api.v1.registry.IJsonModelMap;
import net.yuma14.mc.lib_json_model.api.v1.registry.IJsonModelRegistry;
import net.yuma14.mc.lib_json_model.impl.ModLibJsonModel;
import net.yuma14.mc.lib_json_model.impl.render.BlockWithJsonModelRenderer;

public class ClientProxy extends CommonProxy {
    @Override
    public int getNewRenderId() {
        return RenderingRegistry.getNextAvailableRenderId();
    }

    @Override
    public void registerRenderers() {
        RenderingRegistry.registerBlockHandler(new BlockWithJsonModelRenderer());
    }

    @Override
    public void registerJsonModels() {
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
                "        \"top\": {\n" +
                "          \"uv\": [0, 0, 16, 16],\n" +
                "          \"texture\": \"#stone\"\n" +
                "        },\n" +
                "        \"bottom\": {\n" +
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
                "        \"top\": {\n" +
                "          \"texture\": \"#wood\"\n" +
                "        },\n" +
                "        \"bottom\": {\n" +
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
    }
}
