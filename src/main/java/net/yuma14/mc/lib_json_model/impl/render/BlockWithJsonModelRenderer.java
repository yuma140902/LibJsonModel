package net.yuma14.mc.lib_json_model.impl.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.yuma14.mc.lib_json_model.api.v1.IBlockModel;
import net.yuma14.mc.lib_json_model.api.v1.IBlockWithJsonModel;
import net.yuma14.mc.lib_json_model.impl.ModLibJsonModel;

public class BlockWithJsonModelRenderer implements ISimpleBlockRenderingHandler {
    @Override
    public void renderInventoryBlock(Block block, int i, int i1, RenderBlocks renderBlocks) {
        // TODO
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int renderId, RenderBlocks renderer) {
        if (getRenderId() == renderId) {
            if (block instanceof IBlockWithJsonModel) {
                IBlockWithJsonModel blockWithJsonModel = (IBlockWithJsonModel) block;
                IBlockModel model = blockWithJsonModel.getBlockModel(world, x, y, z);
                return model.renderWorldBlock(world, x, y, z, block, renderer);
            }
        }
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int i) {
        return false;
    }

    @Override
    public int getRenderId() {
        return ModLibJsonModel.SIMPLE_BLOCK_WITH_JSON_MODEL_RENDER_TYPE;
    }
}
