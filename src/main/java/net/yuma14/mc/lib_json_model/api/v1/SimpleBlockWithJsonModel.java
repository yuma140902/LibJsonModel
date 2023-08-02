package net.yuma14.mc.lib_json_model.api.v1;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.yuma14.mc.lib_json_model.impl.ModLibJsonModel;

import java.util.List;

public abstract class SimpleBlockWithJsonModel extends Block implements IBlockWithJsonModel {
    public SimpleBlockWithJsonModel(Material material) {
        super(material);
    }

    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity entity) {
        IBlockModel model = getBlockModel(world, x, y, z);
        model.addCollisionBoxesToList(x, y, z, mask, list);
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
