package net.yuma14.mc.lib_json_model.api.v1;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.yuma14.mc.lib_json_model.impl.ModLibJsonModel;

public abstract class SimpleBlockWithJsonModel extends Block implements IBlockWithJsonModel {
    public SimpleBlockWithJsonModel(Material material) {
        super(material);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return ModLibJsonModel.SIMPLE_BLOCK_WITH_JSON_MODEL_RENDER_TYPE;
    }
}
