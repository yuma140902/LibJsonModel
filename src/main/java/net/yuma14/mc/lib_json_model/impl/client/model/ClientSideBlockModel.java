package net.yuma14.mc.lib_json_model.impl.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.yuma14.mc.lib_json_model.api.v1.IBlockModel;
import net.yuma14.mc.lib_json_model.impl.render.BlockModelRenderer;

import java.util.List;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class ClientSideBlockModel implements IBlockModel {
    public final List<Element> elements;
    public final boolean useAmbientOcclusion;
    public final Map<String, IIcon> texturesMap;

    protected ClientSideBlockModel(List<Element> elements, boolean useAmbientOcclusion, Map<String, IIcon> texturesMap) {
        this.elements = elements;
        this.useAmbientOcclusion = useAmbientOcclusion;
        this.texturesMap = texturesMap;
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
