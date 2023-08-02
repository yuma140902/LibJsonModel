package net.yuma14.mc.lib_json_model.impl.server.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.yuma14.mc.lib_json_model.impl.math.BCS;
import net.yuma14.mc.lib_json_model.impl.math.Cuboid;
import net.yuma14.mc.lib_json_model.impl.model.BlockModelBase;

import java.util.List;
import java.util.stream.Stream;

@SideOnly(Side.SERVER)
public class ServerSideBlockModel extends BlockModelBase {
    private final List<Cuboid<BCS>> cuboids;

    protected ServerSideBlockModel(List<Cuboid<BCS>> cuboids) {
        this.cuboids = cuboids;
    }

    @Override
    public Stream<Cuboid<BCS>> getCuboids() {
        return cuboids.stream();
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
