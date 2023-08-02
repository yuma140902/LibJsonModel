package net.yuma14.mc.lib_json_model.impl.util;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.yuma14.mc.lib_json_model.api.v1.math.BlockPos;

public class WorldUtil {
    public static Block getBlock(IBlockAccess world, BlockPos pos) {
        return world.getBlock(pos.x(), pos.y(), pos.z());
    }
}
