package net.yuma14.mc.lib_json_model.impl.block;

import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.yuma14.mc.lib_json_model.api.v1.SimpleBlockWithJsonModel;
import net.yuma14.mc.lib_json_model.api.v1.IBlockModel;
import net.yuma14.mc.lib_json_model.impl.ModLibJsonModel;

public class BlockTestSBRH extends SimpleBlockWithJsonModel {
    public BlockTestSBRH() {
        super(Material.circuits);
    }

    @Override
    public IBlockModel getBlockModel(IBlockAccess world, int x, int y, int z) {
        return ModLibJsonModel.TEST_SBRH_MODEL;
    }
}
