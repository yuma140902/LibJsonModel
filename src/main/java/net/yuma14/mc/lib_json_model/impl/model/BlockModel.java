package net.yuma14.mc.lib_json_model.impl.model;

import net.yuma14.mc.lib_json_model.api.v1.ParentModelResolver;
import net.yuma14.mc.lib_json_model.api.v1.json.JsonBlockModel;
import net.yuma14.mc.lib_json_model.api.v1.render.IBlockModel;

import java.util.List;

public class BlockModel implements IBlockModel {
    public final List<Face> faces;

    protected BlockModel(List<Face> faces) {
        this.faces = faces;
    }

    public static BlockModel fromJsonModel(JsonBlockModel jsonModel, ParentModelResolver<JsonBlockModel> parentModelResolver) {
        //TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void renderInventoryBlock() {
        //TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void renderWorldBlock() {
        //TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public void renderTileEntity() {
        //TODO
        throw new UnsupportedOperationException();
    }
}
