package net.yuma14.mc.lib_json_model.impl.model;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import net.yuma14.mc.lib_json_model.api.v1.ParentModelResolver;
import net.yuma14.mc.lib_json_model.api.v1.json.JsonBlockModel;
import net.yuma14.mc.lib_json_model.api.v1.render.IBlockModel;
import net.yuma14.mc.lib_json_model.impl.math.Vec3;
import net.yuma14.mc.lib_json_model.impl.render.BlockModelRenderer;

import java.util.ArrayList;
import java.util.List;

public class BlockModel implements IBlockModel {
    public static final BlockModel TEST1;
    static {
        List<Element> elems = new ArrayList<>();
        Face f = new Face(0, 0, 16, 16, new TextureId(Blocks.stone.getIcon(0, 0)), ForgeDirection.UNKNOWN);
        Face g = new Face(0, 0, 16, 16, new TextureId(Blocks.bookshelf.getIcon(0, 0)), ForgeDirection.UNKNOWN);

        elems.add(new Element(new Vec3<>(0, 0, 0), new Vec3<>(1, 1, 0.5), f, f, f, f, f, f));
        elems.add(new Element(new Vec3<>(0, 0, 0.5), new Vec3<>(1, 0.5, 1), g, g, g, g, g, g));
        TEST1 = new BlockModel(elems);
    }
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
