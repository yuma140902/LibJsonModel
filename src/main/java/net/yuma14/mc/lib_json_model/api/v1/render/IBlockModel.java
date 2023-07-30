package net.yuma14.mc.lib_json_model.api.v1.render;

import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;

public interface IBlockModel {
    // for ISimpleBlockRenderingHandler
    void renderInventoryBlock();

    // for ISimpleBlockRenderingHandler
    void renderWorldBlock();

    // for TileEntitySpecialRenderer
    void renderTileEntity();
}
