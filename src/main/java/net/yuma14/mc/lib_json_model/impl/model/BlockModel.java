package net.yuma14.mc.lib_json_model.impl.model;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.yuma14.mc.lib_json_model.api.v1.ParentModelResolver;
import net.yuma14.mc.lib_json_model.api.v1.json.JsonBlockModel;
import net.yuma14.mc.lib_json_model.api.v1.render.IBlockModel;
import net.yuma14.mc.lib_json_model.impl.render.BlockModelRenderer;

import java.util.List;

public class BlockModel implements IBlockModel {
    public final List<Element> elements;

    protected BlockModel(List<Element> elements) {
        this.elements = elements;
    }

    public static BlockModel fromJsonModel(JsonBlockModel jsonModel, ParentModelResolver<JsonBlockModel> parentModelResolver) {
        //TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void renderInventoryBlock() {
        // TODO
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer) {
        return BlockModelRenderer.renderBlockModel(this, world, x, y, z, block, renderer);
    }

    @Override
    public void renderTileEntity() {
        // TODO
    }
}
