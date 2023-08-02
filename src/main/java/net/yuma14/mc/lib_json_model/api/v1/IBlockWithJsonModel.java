package net.yuma14.mc.lib_json_model.api.v1;

import net.minecraft.world.IBlockAccess;

/**
 * Block with JSON model
 * <p>
 * Normally you should use {@link SimpleBlockWithJsonModel} instead of implementing this interface yourself.
 * This is because {@link SimpleBlockWithJsonModel} has features such as render type settings, collisions, etc.
 * If you want to implement this interface directly without {@link SimpleBlockWithJsonModel},
 * you need to implement these functions by yourself referring to {@link SimpleBlockWithJsonModel}.
 */
public interface IBlockWithJsonModel {
    /**
     * Returns the model corresponding to the block in the specified coordinates in the world.
     * <p>
     * Normally one model is returned, but if conditional rendering is required,
     * you can switch the model returned depending on the metadata or tile entities.
     */
    IBlockModel getBlockModel(IBlockAccess world, int x, int y, int z);
}
