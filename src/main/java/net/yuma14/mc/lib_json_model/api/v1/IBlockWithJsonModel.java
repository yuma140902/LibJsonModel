package net.yuma14.mc.lib_json_model.api.v1;

import net.minecraft.world.IBlockAccess;
import net.yuma14.mc.lib_json_model.api.v1.render.IBlockModel;

public interface IBlockWithJsonModel {
    IBlockModel getBlockModel(IBlockAccess world, int x, int y, int z);
}
