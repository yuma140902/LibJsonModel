package net.yuma14.mc.lib_json_model.impl.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.yuma14.mc.lib_json_model.impl.client.TextureRegistry;

public class BlockDummy extends Block {
    public BlockDummy() {
        super(Material.circuits);
    }

    @Override
    public void registerBlockIcons(IIconRegister textureMap) {
        TextureRegistry.streamTextureNames().forEach(name -> {
            IIcon icon = textureMap.registerIcon(name);
            TextureRegistry.registerIIcon(name, icon);
        });
    }
}
