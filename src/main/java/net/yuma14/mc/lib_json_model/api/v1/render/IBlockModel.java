package net.yuma14.mc.lib_json_model.api.v1.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public interface IBlockModel {
    // for ISimpleBlockRenderingHandler
    void renderInventoryBlock();

    // for ISimpleBlockRenderingHandler
    boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer);

    // for TileEntitySpecialRenderer
    void renderTileEntity();
}
