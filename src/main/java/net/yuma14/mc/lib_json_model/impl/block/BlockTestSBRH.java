package net.yuma14.mc.lib_json_model.impl.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.yuma14.mc.lib_json_model.impl.ModLibJsonModel;

public class BlockTestSBRH extends Block {
    public BlockTestSBRH() {
        super(Material.circuits);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return ModLibJsonModel.testSBRHRenderId;
    }
}
