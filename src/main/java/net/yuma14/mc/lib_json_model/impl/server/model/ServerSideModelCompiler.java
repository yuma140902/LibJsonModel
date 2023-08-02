package net.yuma14.mc.lib_json_model.impl.server.model;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.yuma14.mc.lib_json_model.api.v1.math.BCS;
import net.yuma14.mc.lib_json_model.api.v1.math.Cuboid;
import net.yuma14.mc.lib_json_model.api.v1.math.Vec3;
import net.yuma14.mc.lib_json_model.impl.json.JsonBlockModel;
import net.yuma14.mc.lib_json_model.impl.json.JsonElement;
import net.yuma14.mc.lib_json_model.impl.util.ArrayUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.SERVER)
public class ServerSideModelCompiler {
    public static final Logger LOGGER = LogManager.getLogger("LibJsonModel Compiler");

    public static ServerSideBlockModel compileJsonBlockModel(String modelName, JsonBlockModel model) {
        List<Cuboid<BCS>> cuboids = new ArrayList<>();
        for (final JsonElement jsonElement : model.elements) {
            Cuboid<BCS> cuboid = compileJsonElement(modelName, jsonElement);
            cuboids.add(cuboid);
        }
        return new ServerSideBlockModel(cuboids);
    }

    private static Cuboid<BCS> compileJsonElement(String modelName, JsonElement element) {
        if (element.rotation != null) {
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
        return new Cuboid<>(from, to);
    }
}
