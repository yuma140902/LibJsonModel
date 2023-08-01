package net.yuma14.mc.lib_json_model.impl.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;
import net.yuma14.mc.lib_json_model.api.v1.IBlockModel;
import net.yuma14.mc.lib_json_model.impl.json.JsonBlockModel;
import net.yuma14.mc.lib_json_model.impl.json.JsonElement;
import net.yuma14.mc.lib_json_model.impl.json.JsonFace;
import net.yuma14.mc.lib_json_model.impl.math.BCS;
import net.yuma14.mc.lib_json_model.impl.math.Vec3;
import net.yuma14.mc.lib_json_model.impl.model.*;
import net.yuma14.mc.lib_json_model.impl.util.ArrayUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

@SideOnly(Side.CLIENT)
public class Compiler {
    public static final Logger LOGGER = LogManager.getLogger("LibJsonModel Compiler");

    public static PartialBlockModel compileJsonBlockModel(String modelName, JsonBlockModel model) {
        // TODO

        LOGGER.warn("[{}]: \"ambientocclusion\" is not supported for now.", modelName);
        if(model.display != null) {
            LOGGER.warn("[{}]: \"display\" is not supported for now.", modelName);
        }

        // register textures
        Map<String, IIcon> texturesMap = new HashMap<>();
        for(final Map.Entry<String, String> entry : model.textures.entrySet()) {
            String variableName = entry.getKey();
            String textureName = entry.getValue();
            IIcon icon = TextureRegistry.registerBlockTextureToGame(textureName);
            texturesMap.put(variableName, icon);
        }

        // compile elements
        List<Element> elementList = new ArrayList<>();
        for(final JsonElement jsonElement : model.elements) {
            Element element = compileJsonElement(modelName, jsonElement, texturesMap);
            elementList.add(element);
        }

        return new PartialBlockModel(model.parent, elementList, model.ambientocclusion, texturesMap);
    }

    private static Element compileJsonElement(String modelName, JsonElement element, Map<String, IIcon> texturesMap) {
        LOGGER.warn("[{}]: \"elements/shade\" is not supported for now.", modelName);
        if(element.rotation != null) {
            LOGGER.warn("[{}]: \"elements/rotation\" is not supported for now.", modelName);
        }
        double fromX = ArrayUtil.getAtOr0(element.from, 0) / 16.0;
        double fromY = ArrayUtil.getAtOr0(element.from, 1) / 16.0;
        double fromZ = ArrayUtil.getAtOr0(element.from, 2) / 16.0;
        double toX = ArrayUtil.getAtOr0(element.to, 0) / 16.0;
        double toY = ArrayUtil.getAtOr0(element.to, 1) / 16.0;
        double toZ = ArrayUtil.getAtOr0(element.to, 2) / 16.0;
        Vec3<BCS> from = new Vec3<>(fromX, fromY, fromZ);
        Vec3<BCS> to = new Vec3<>(toX, toY, toZ);
        Face top = compileJsonFace(modelName, element.faces.get("up"), texturesMap);
        Face bottom = compileJsonFace(modelName, element.faces.get("down"), texturesMap);
        Face north = compileJsonFace(modelName, element.faces.get("north"), texturesMap);
        Face south = compileJsonFace(modelName, element.faces.get("south"), texturesMap);
        Face west = compileJsonFace(modelName, element.faces.get("west"), texturesMap);
        Face east = compileJsonFace(modelName, element.faces.get("east"), texturesMap);

        return new Element(from, to, top, bottom, north, south, west, east);
    }

    private static Face compileJsonFace(String modelName, JsonFace face, Map<String, IIcon> texturesMap) {
        if(face == null) return Face.EMPTY;
        if(face.rotation != 0) {
            LOGGER.warn("[{}]: \"rotation\" is not supported for now.", modelName);
        }
        if(face.tintindex != 0) {
            LOGGER.warn("[{}]: \"tintindex\" is not supported for now.", modelName);
        }

        double minU = ArrayUtil.getAtOr(face.uv, 0, 0.0);
        double minV = ArrayUtil.getAtOr(face.uv, 1, 0.0);
        double maxU = ArrayUtil.getAtOr(face.uv, 2, 16.0);
        double maxV = ArrayUtil.getAtOr(face.uv, 3, 16.0);
        TextureVariable textureVariable;
        if(face.texture != null && face.texture.length() >= 1) {
            String textureVariableName = face.texture.substring(1);
            textureVariable = new TextureVariable(textureVariableName);
        }
        else {
            textureVariable = TextureVariable.EMPTY;
        }
        ForgeDirection cullFace = parseCullFace(modelName, face.cullface);
        return new Face(minU, minV, maxU, maxV, textureVariable, cullFace);
    }

    private static ForgeDirection parseCullFace(String modelName, String cullFace) {
        if(cullFace == null || cullFace.isEmpty()) {
            return ForgeDirection.UNKNOWN;
        }
        else if("down".equals(cullFace)) {
            return ForgeDirection.DOWN;
        }
        else if("up".equals(cullFace)) {
            return ForgeDirection.UP;
        }
        else if("north".equals(cullFace)) {
            return ForgeDirection.NORTH;
        }
        else if("south".equals(cullFace)) {
            return ForgeDirection.SOUTH;
        }
        else if("west".equals(cullFace)) {
            return ForgeDirection.WEST;
        }
        else if("east".equals(cullFace)) {
            return ForgeDirection.EAST;
        }
        LOGGER.warn("[{}]: \"cullface\" has invalid value \"{}\"", modelName, cullFace);
        return ForgeDirection.UNKNOWN;
    }

    public static Map<String, ? extends IBlockModel> mergeBlockModels(Map<String, PartialBlockModel> partialBlockModelMap) {
        Map<String, BlockModel> completeBlockModels = new HashMap<>();
        for(final String modelName : partialBlockModelMap.keySet()) {
            BlockModel model = mergeBlockModel(completeBlockModels, partialBlockModelMap, modelName);
            if(model != null) {
                completeBlockModels.put(modelName, model);
            }
        }
        return completeBlockModels;
    }

    public static BlockModel mergeBlockModel(Map<String, BlockModel> completeBlockModels, Map<String, PartialBlockModel> partialBlockModels, String modelName) {
        if(completeBlockModels.containsKey(modelName)) {
            return completeBlockModels.get(modelName);
        }

        if(modelName == null || modelName.isEmpty()) {
            PartialBlockModel partialBlockModel = partialBlockModels.get(modelName);
            if(partialBlockModel != null) {
                return new BlockModel(partialBlockModel.elements, partialBlockModel.useAmbientOcclusion, partialBlockModel.texturesMap);
            }
            return null;
        }

        PartialBlockModel child = partialBlockModels.get(modelName);
        if(child == null) return null;
        BlockModel parent = mergeBlockModel(completeBlockModels, partialBlockModels, child.parent);
        if(parent == null) {
            return new BlockModel(child.elements, child.useAmbientOcclusion, child.texturesMap);
        }
        // merge textures
        Map<String, IIcon> texturesMap = new HashMap<>();
        texturesMap.putAll(parent.texturesMap);
        texturesMap.putAll(child.texturesMap);
        // we do not have to merge element list
        return new BlockModel(child.elements, child.useAmbientOcclusion, texturesMap);
    }
}
