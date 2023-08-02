package net.yuma14.mc.lib_json_model.impl.server.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.yuma14.mc.lib_json_model.api.v1.IBlockModel;
import net.yuma14.mc.lib_json_model.impl.math.BCS;
import net.yuma14.mc.lib_json_model.impl.math.Cuboid;

import java.util.List;

@SideOnly(Side.SERVER)
public class ServerSideBlockModel implements IBlockModel {
    private final List<Cuboid<BCS>> cuboids;

    protected ServerSideBlockModel(List<Cuboid<BCS>> cuboids) {
        this.cuboids = cuboids;
    }

    public List<Cuboid<BCS>> getCuboids() {
        return cuboids;
    }

    @Override
    public void renderInventoryBlock() {

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer) {
        return false;
    }

    @Override
    public void renderTileEntity() {

    }
}
