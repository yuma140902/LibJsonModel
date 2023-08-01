package net.yuma14.mc.lib_json_model.impl.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.yuma14.mc.lib_json_model.impl.ModLibJsonModel;
import net.yuma14.mc.lib_json_model.impl.model.BlockModel;

public class TestSimpleBlockRenderingHandler implements ISimpleBlockRenderingHandler {
    @Override
    public void renderInventoryBlock(Block block, int i, int i1, RenderBlocks renderBlocks) {
        // TODO
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess iBlockAccess, int i, int i1, int i2, Block block, int i3, RenderBlocks renderBlocks) {
        if(getRenderId() == i3) {
            BlockModel.TEST.renderWorldBlock(iBlockAccess, i, i1, i2, block, i3, renderBlocks);
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int i) {
        return false;
    }

    @Override
    public int getRenderId() {
        return ModLibJsonModel.testSBRHRenderId;
    }
}
