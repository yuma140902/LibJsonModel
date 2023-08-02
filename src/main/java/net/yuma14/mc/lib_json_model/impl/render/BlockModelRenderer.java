package net.yuma14.mc.lib_json_model.impl.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import net.yuma14.mc.lib_json_model.api.v1.math.BlockPos;
import net.yuma14.mc.lib_json_model.api.v1.math.CoordinateConverter;
import net.yuma14.mc.lib_json_model.api.v1.math.Cuboid;
import net.yuma14.mc.lib_json_model.api.v1.math.WCS;
import net.yuma14.mc.lib_json_model.impl.client.model.ClientSideBlockModel;
import net.yuma14.mc.lib_json_model.impl.client.model.Element;
import net.yuma14.mc.lib_json_model.impl.client.model.Face;
import net.yuma14.mc.lib_json_model.impl.util.McConst;
import net.yuma14.mc.lib_json_model.impl.util.WorldUtil;

import java.util.Map;

public class BlockModelRenderer {
    public static boolean renderBlockModel(ClientSideBlockModel model, IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer) {
        // TODO: 他のモード
        boolean renderedSomething = false;
        for (final Element element : model.elements) {
            if (renderElementWithAmbientOcclusion(element, model.texturesMap, world, x, y, z, block, renderer)) {
                renderedSomething = true;
            }
        }
        return renderedSomething;
    }

    private static boolean shouldRenderFace(Face face, BlockPos pos, IBlockAccess world) {
        if (face == null) return false;
        if (face.texture == null) return false;
        if (face.cullFace == ForgeDirection.UNKNOWN) return true;
        return !WorldUtil.getBlock(world, pos.offset(face.cullFace)).isOpaqueCube();
    }

    private static boolean renderElementWithAmbientOcclusion(Element element, Map<String, IIcon> texturesMap, IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer) {
        int colorMultiplier = block.colorMultiplier(renderer.blockAccess, x, y, z);
        float colorR = (float) (colorMultiplier >> 16 & 255) / 255.0F;
        float colorG = (float) (colorMultiplier >> 8 & 255) / 255.0F;
        float colorB = (float) (colorMultiplier & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable) {
            float colorRCnv = (colorR * 30.0F + colorG * 59.0F + colorB * 11.0F) / 100.0F;
            float colorGCnv = (colorR * 30.0F + colorG * 70.0F) / 100.0F;
            float colorBCnv = (colorR * 30.0F + colorB * 70.0F) / 100.0F;
            colorR = colorRCnv;
            colorG = colorGCnv;
            colorB = colorBCnv;
        }

        renderer.enableAO = true;
        float f3 = 0.0F;
        float f4 = 0.0F;
        float f5 = 0.0F;
        float f6 = 0.0F;
        boolean isNotGrassTop = true;
        int mixedBrightness = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(983055);

        if (renderer.getBlockIcon(block).getIconName().equals("grass_top")) {
            isNotGrassTop = false;
        }

        boolean renderedSomething = false;

        boolean flag2;
        boolean flag3;
        boolean flag4;
        boolean flag5;
        int i1;
        float f7;

        BlockPos pos = new BlockPos(x, y, z);

        if (shouldRenderFace(element.getFace(McConst.SIDE_BOTTOM), pos, renderer.blockAccess)) {
            if (element.cuboid.from().y() <= 0) {
                --y;
            }

            renderer.aoBrightnessXYNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
            renderer.aoBrightnessYZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
            renderer.aoBrightnessYZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
            renderer.aoBrightnessXYPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
            renderer.aoLightValueScratchXYNN = renderer.blockAccess.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
            renderer.aoLightValueScratchYZNN = renderer.blockAccess.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
            renderer.aoLightValueScratchYZNP = renderer.blockAccess.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
            renderer.aoLightValueScratchXYPN = renderer.blockAccess.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
            boolean opaqueXYPN = renderer.blockAccess.getBlock(x + 1, y - 1, z).getCanBlockGrass();
            boolean opaqueXYNN = renderer.blockAccess.getBlock(x - 1, y - 1, z).getCanBlockGrass();
            boolean opaqueZYPN = renderer.blockAccess.getBlock(x, y - 1, z + 1).getCanBlockGrass();
            boolean opaqueZYNN = renderer.blockAccess.getBlock(x, y - 1, z - 1).getCanBlockGrass();

            if (!opaqueZYNN && !opaqueXYNN) {
                renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXYNN;
                renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXYNN;
            } else {
                renderer.aoLightValueScratchXYZNNN = renderer.blockAccess.getBlock(x - 1, y, z - 1).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z - 1);
            }

            if (!opaqueZYPN && !opaqueXYNN) {
                renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXYNN;
                renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXYNN;
            } else {
                renderer.aoLightValueScratchXYZNNP = renderer.blockAccess.getBlock(x - 1, y, z + 1).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z + 1);
            }

            if (!opaqueZYNN && !opaqueXYPN) {
                renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXYPN;
                renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXYPN;
            } else {
                renderer.aoLightValueScratchXYZPNN = renderer.blockAccess.getBlock(x + 1, y, z - 1).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z - 1);
            }

            if (!opaqueZYPN && !opaqueXYPN) {
                renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXYPN;
                renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXYPN;
            } else {
                renderer.aoLightValueScratchXYZPNP = renderer.blockAccess.getBlock(x + 1, y, z + 1).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z + 1);
            }

            if (element.cuboid.from().y() <= 0.0D) {
                ++y;
            }

            int mixedBrightnessForBottom = mixedBrightness;

            if (element.cuboid.from().y() <= 0.0D || !renderer.blockAccess.getBlock(x, y - 1, z).isOpaqueCube()) {
                mixedBrightnessForBottom = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
            }

            float atLightValueBottom = renderer.blockAccess.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
            f3 = (renderer.aoLightValueScratchXYZNNP + renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchYZNP + atLightValueBottom) / 4.0F;
            f6 = (renderer.aoLightValueScratchYZNP + atLightValueBottom + renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXYPN) / 4.0F;
            f5 = (atLightValueBottom + renderer.aoLightValueScratchYZNN + renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXYZPNN) / 4.0F;
            f4 = (renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXYZNNN + atLightValueBottom + renderer.aoLightValueScratchYZNN) / 4.0F;
            renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXYNN, renderer.aoBrightnessYZNP, mixedBrightnessForBottom);
            renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessYZNP, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXYPN, mixedBrightnessForBottom);
            renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessYZNN, renderer.aoBrightnessXYPN, renderer.aoBrightnessXYZPNN, mixedBrightnessForBottom);
            renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXYNN, renderer.aoBrightnessXYZNNN, renderer.aoBrightnessYZNN, mixedBrightnessForBottom);

            if (isNotGrassTop) {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = colorR * 0.5F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = colorG * 0.5F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = colorB * 0.5F;
            } else {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.5F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.5F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.5F;
            }

            renderer.colorRedTopLeft *= f3;
            renderer.colorGreenTopLeft *= f3;
            renderer.colorBlueTopLeft *= f3;
            renderer.colorRedBottomLeft *= f4;
            renderer.colorGreenBottomLeft *= f4;
            renderer.colorBlueBottomLeft *= f4;
            renderer.colorRedBottomRight *= f5;
            renderer.colorGreenBottomRight *= f5;
            renderer.colorBlueBottomRight *= f5;
            renderer.colorRedTopRight *= f6;
            renderer.colorGreenTopRight *= f6;
            renderer.colorBlueTopRight *= f6;
            renderedSomething = renderBottomFace(element, texturesMap, x, y, z, renderer);
        }

        if (shouldRenderFace(element.getFace(McConst.SIDE_TOP), pos, renderer.blockAccess)) {
            if (element.cuboid.to().y() >= 1.0) {
                ++y;
            }

            renderer.aoBrightnessXYNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
            renderer.aoBrightnessXYPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
            renderer.aoBrightnessYZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
            renderer.aoBrightnessYZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
            renderer.aoLightValueScratchXYNP = renderer.blockAccess.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
            renderer.aoLightValueScratchXYPP = renderer.blockAccess.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
            renderer.aoLightValueScratchYZPN = renderer.blockAccess.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
            renderer.aoLightValueScratchYZPP = renderer.blockAccess.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
            flag2 = renderer.blockAccess.getBlock(x + 1, y + 1, z).getCanBlockGrass();
            flag3 = renderer.blockAccess.getBlock(x - 1, y + 1, z).getCanBlockGrass();
            flag4 = renderer.blockAccess.getBlock(x, y + 1, z + 1).getCanBlockGrass();
            flag5 = renderer.blockAccess.getBlock(x, y + 1, z - 1).getCanBlockGrass();

            if (!flag5 && !flag3) {
                renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXYNP;
                renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXYNP;
            } else {
                renderer.aoLightValueScratchXYZNPN = renderer.blockAccess.getBlock(x - 1, y, z - 1).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z - 1);
            }

            if (!flag5 && !flag2) {
                renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXYPP;
                renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXYPP;
            } else {
                renderer.aoLightValueScratchXYZPPN = renderer.blockAccess.getBlock(x + 1, y, z - 1).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z - 1);
            }

            if (!flag4 && !flag3) {
                renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXYNP;
                renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXYNP;
            } else {
                renderer.aoLightValueScratchXYZNPP = renderer.blockAccess.getBlock(x - 1, y, z + 1).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z + 1);
            }

            if (!flag4 && !flag2) {
                renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXYPP;
                renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXYPP;
            } else {
                renderer.aoLightValueScratchXYZPPP = renderer.blockAccess.getBlock(x + 1, y, z + 1).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z + 1);
            }

            if (element.cuboid.to().y() >= 1.0) {
                --y;
            }

            i1 = mixedBrightness;

            if (element.cuboid.to().y() >= 1.0 || !renderer.blockAccess.getBlock(x, y + 1, z).isOpaqueCube()) {
                i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
            }

            f7 = renderer.blockAccess.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
            f6 = (renderer.aoLightValueScratchXYZNPP + renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchYZPP + f7) / 4.0F;
            f3 = (renderer.aoLightValueScratchYZPP + f7 + renderer.aoLightValueScratchXYZPPP + renderer.aoLightValueScratchXYPP) / 4.0F;
            f4 = (f7 + renderer.aoLightValueScratchYZPN + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPN) / 4.0F;
            f5 = (renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPN + f7 + renderer.aoLightValueScratchYZPN) / 4.0F;
            renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXYZNPP, renderer.aoBrightnessXYNP, renderer.aoBrightnessYZPP, i1);
            renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPP, renderer.aoBrightnessXYZPPP, renderer.aoBrightnessXYPP, i1);
            renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPN, renderer.aoBrightnessXYPP, renderer.aoBrightnessXYZPPN, i1);
            renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessYZPN, i1);
            renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = colorR;
            renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = colorG;
            renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = colorB;
            renderer.colorRedTopLeft *= f3;
            renderer.colorGreenTopLeft *= f3;
            renderer.colorBlueTopLeft *= f3;
            renderer.colorRedBottomLeft *= f4;
            renderer.colorGreenBottomLeft *= f4;
            renderer.colorBlueBottomLeft *= f4;
            renderer.colorRedBottomRight *= f5;
            renderer.colorGreenBottomRight *= f5;
            renderer.colorBlueBottomRight *= f5;
            renderer.colorRedTopRight *= f6;
            renderer.colorGreenTopRight *= f6;
            renderer.colorBlueTopRight *= f6;

            renderedSomething = renderTopFace(element, texturesMap, x, y, z, renderer);
        }

        if (shouldRenderFace(element.getFace(McConst.SIDE_NORTH), pos, renderer.blockAccess)) {
            if (element.cuboid.from().z() <= 0) {
                --z;
            }

            renderer.aoLightValueScratchXZNN = renderer.blockAccess.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
            renderer.aoLightValueScratchYZNN = renderer.blockAccess.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
            renderer.aoLightValueScratchYZPN = renderer.blockAccess.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
            renderer.aoLightValueScratchXZPN = renderer.blockAccess.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
            renderer.aoBrightnessXZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
            renderer.aoBrightnessYZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
            renderer.aoBrightnessYZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
            renderer.aoBrightnessXZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
            flag2 = renderer.blockAccess.getBlock(x + 1, y, z - 1).getCanBlockGrass();
            flag3 = renderer.blockAccess.getBlock(x - 1, y, z - 1).getCanBlockGrass();
            flag4 = renderer.blockAccess.getBlock(x, y + 1, z - 1).getCanBlockGrass();
            flag5 = renderer.blockAccess.getBlock(x, y - 1, z - 1).getCanBlockGrass();

            if (!flag3 && !flag5) {
                renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
                renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
            } else {
                renderer.aoLightValueScratchXYZNNN = renderer.blockAccess.getBlock(x - 1, y - 1, z).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y - 1, z);
            }

            if (!flag3 && !flag4) {
                renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
                renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
            } else {
                renderer.aoLightValueScratchXYZNPN = renderer.blockAccess.getBlock(x - 1, y + 1, z).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y + 1, z);
            }

            if (!flag2 && !flag5) {
                renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
                renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
            } else {
                renderer.aoLightValueScratchXYZPNN = renderer.blockAccess.getBlock(x + 1, y - 1, z).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y - 1, z);
            }

            if (!flag2 && !flag4) {
                renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
                renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
            } else {
                renderer.aoLightValueScratchXYZPPN = renderer.blockAccess.getBlock(x + 1, y + 1, z).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y + 1, z);
            }

            if (element.cuboid.from().z() <= 0) {
                ++z;
            }

            i1 = mixedBrightness;

            if (element.cuboid.from().z() <= 0 || !renderer.blockAccess.getBlock(x, y, z - 1).isOpaqueCube()) {
                i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
            }

            f7 = renderer.blockAccess.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
            f3 = (renderer.aoLightValueScratchXZNN + renderer.aoLightValueScratchXYZNPN + f7 + renderer.aoLightValueScratchYZPN) / 4.0F;
            f4 = (f7 + renderer.aoLightValueScratchYZPN + renderer.aoLightValueScratchXZPN + renderer.aoLightValueScratchXYZPPN) / 4.0F;
            f5 = (renderer.aoLightValueScratchYZNN + f7 + renderer.aoLightValueScratchXYZPNN + renderer.aoLightValueScratchXZPN) / 4.0F;
            f6 = (renderer.aoLightValueScratchXYZNNN + renderer.aoLightValueScratchXZNN + renderer.aoLightValueScratchYZNN + f7) / 4.0F;
            renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNN, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessYZPN, i1);
            renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessYZPN, renderer.aoBrightnessXZPN, renderer.aoBrightnessXYZPPN, i1);
            renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessYZNN, renderer.aoBrightnessXYZPNN, renderer.aoBrightnessXZPN, i1);
            renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXYZNNN, renderer.aoBrightnessXZNN, renderer.aoBrightnessYZNN, i1);

            if (isNotGrassTop) {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = colorR * 0.8F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = colorG * 0.8F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = colorB * 0.8F;
            } else {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.8F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.8F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.8F;
            }

            renderer.colorRedTopLeft *= f3;
            renderer.colorGreenTopLeft *= f3;
            renderer.colorBlueTopLeft *= f3;
            renderer.colorRedBottomLeft *= f4;
            renderer.colorGreenBottomLeft *= f4;
            renderer.colorBlueBottomLeft *= f4;
            renderer.colorRedBottomRight *= f5;
            renderer.colorGreenBottomRight *= f5;
            renderer.colorBlueBottomRight *= f5;
            renderer.colorRedTopRight *= f6;
            renderer.colorGreenTopRight *= f6;
            renderer.colorBlueTopRight *= f6;

            renderedSomething = renderNorthFace(element, texturesMap, x, y, z, renderer);
        }

        if (shouldRenderFace(element.getFace(McConst.SIDE_SOUTH), pos, renderer.blockAccess)) {
            if (element.cuboid.to().z() >= 1.0) {
                ++z;
            }

            renderer.aoLightValueScratchXZNP = renderer.blockAccess.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
            renderer.aoLightValueScratchXZPP = renderer.blockAccess.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
            renderer.aoLightValueScratchYZNP = renderer.blockAccess.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
            renderer.aoLightValueScratchYZPP = renderer.blockAccess.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
            renderer.aoBrightnessXZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
            renderer.aoBrightnessXZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
            renderer.aoBrightnessYZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
            renderer.aoBrightnessYZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
            flag2 = renderer.blockAccess.getBlock(x + 1, y, z + 1).getCanBlockGrass();
            flag3 = renderer.blockAccess.getBlock(x - 1, y, z + 1).getCanBlockGrass();
            flag4 = renderer.blockAccess.getBlock(x, y + 1, z + 1).getCanBlockGrass();
            flag5 = renderer.blockAccess.getBlock(x, y - 1, z + 1).getCanBlockGrass();

            if (!flag3 && !flag5) {
                renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
                renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
            } else {
                renderer.aoLightValueScratchXYZNNP = renderer.blockAccess.getBlock(x - 1, y - 1, z).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y - 1, z);
            }

            if (!flag3 && !flag4) {
                renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
                renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
            } else {
                renderer.aoLightValueScratchXYZNPP = renderer.blockAccess.getBlock(x - 1, y + 1, z).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y + 1, z);
            }

            if (!flag2 && !flag5) {
                renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
                renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
            } else {
                renderer.aoLightValueScratchXYZPNP = renderer.blockAccess.getBlock(x + 1, y - 1, z).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y - 1, z);
            }

            if (!flag2 && !flag4) {
                renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
                renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
            } else {
                renderer.aoLightValueScratchXYZPPP = renderer.blockAccess.getBlock(x + 1, y + 1, z).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y + 1, z);
            }

            if (element.cuboid.to().z() >= 1.0) {
                --z;
            }

            i1 = mixedBrightness;

            if (element.cuboid.to().z() >= 1.0 || !renderer.blockAccess.getBlock(x, y, z + 1).isOpaqueCube()) {
                i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
            }

            f7 = renderer.blockAccess.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
            f3 = (renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchXYZNPP + f7 + renderer.aoLightValueScratchYZPP) / 4.0F;
            f6 = (f7 + renderer.aoLightValueScratchYZPP + renderer.aoLightValueScratchXZPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
            f5 = (renderer.aoLightValueScratchYZNP + f7 + renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXZPP) / 4.0F;
            f4 = (renderer.aoLightValueScratchXYZNNP + renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchYZNP + f7) / 4.0F;
            renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNP, renderer.aoBrightnessXYZNPP, renderer.aoBrightnessYZPP, i1);
            renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessYZPP, renderer.aoBrightnessXZPP, renderer.aoBrightnessXYZPPP, i1);
            renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessYZNP, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXZPP, i1);
            renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXZNP, renderer.aoBrightnessYZNP, i1);

            if (isNotGrassTop) {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = colorR * 0.8F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = colorG * 0.8F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = colorB * 0.8F;
            } else {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.8F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.8F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.8F;
            }

            renderer.colorRedTopLeft *= f3;
            renderer.colorGreenTopLeft *= f3;
            renderer.colorBlueTopLeft *= f3;
            renderer.colorRedBottomLeft *= f4;
            renderer.colorGreenBottomLeft *= f4;
            renderer.colorBlueBottomLeft *= f4;
            renderer.colorRedBottomRight *= f5;
            renderer.colorGreenBottomRight *= f5;
            renderer.colorBlueBottomRight *= f5;
            renderer.colorRedTopRight *= f6;
            renderer.colorGreenTopRight *= f6;
            renderer.colorBlueTopRight *= f6;

            renderedSomething = renderSouthFace(element, texturesMap, x, y, z, renderer);
        }

        if (shouldRenderFace(element.getFace(McConst.SIDE_WEST), pos, renderer.blockAccess)) {
            if (element.cuboid.from().x() <= 0) {
                --x;
            }

            renderer.aoLightValueScratchXYNN = renderer.blockAccess.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
            renderer.aoLightValueScratchXZNN = renderer.blockAccess.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
            renderer.aoLightValueScratchXZNP = renderer.blockAccess.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
            renderer.aoLightValueScratchXYNP = renderer.blockAccess.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
            renderer.aoBrightnessXYNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
            renderer.aoBrightnessXZNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
            renderer.aoBrightnessXZNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
            renderer.aoBrightnessXYNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
            flag2 = renderer.blockAccess.getBlock(x - 1, y + 1, z).getCanBlockGrass();
            flag3 = renderer.blockAccess.getBlock(x - 1, y - 1, z).getCanBlockGrass();
            flag4 = renderer.blockAccess.getBlock(x - 1, y, z - 1).getCanBlockGrass();
            flag5 = renderer.blockAccess.getBlock(x - 1, y, z + 1).getCanBlockGrass();

            if (!flag4 && !flag3) {
                renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
                renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
            } else {
                renderer.aoLightValueScratchXYZNNN = renderer.blockAccess.getBlock(x, y - 1, z - 1).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z - 1);
            }

            if (!flag5 && !flag3) {
                renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
                renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
            } else {
                renderer.aoLightValueScratchXYZNNP = renderer.blockAccess.getBlock(x, y - 1, z + 1).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z + 1);
            }

            if (!flag4 && !flag2) {
                renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
                renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
            } else {
                renderer.aoLightValueScratchXYZNPN = renderer.blockAccess.getBlock(x, y + 1, z - 1).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZNPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z - 1);
            }

            if (!flag5 && !flag2) {
                renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
                renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
            } else {
                renderer.aoLightValueScratchXYZNPP = renderer.blockAccess.getBlock(x, y + 1, z + 1).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZNPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z + 1);
            }

            if (element.cuboid.from().x() <= 0) {
                ++x;
            }

            i1 = mixedBrightness;

            if (element.cuboid.from().x() <= 0 || !renderer.blockAccess.getBlock(x - 1, y, z).isOpaqueCube()) {
                i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x - 1, y, z);
            }

            f7 = renderer.blockAccess.getBlock(x - 1, y, z).getAmbientOcclusionLightValue();
            f6 = (renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXYZNNP + f7 + renderer.aoLightValueScratchXZNP) / 4.0F;
            f3 = (f7 + renderer.aoLightValueScratchXZNP + renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPP) / 4.0F;
            f4 = (renderer.aoLightValueScratchXZNN + f7 + renderer.aoLightValueScratchXYZNPN + renderer.aoLightValueScratchXYNP) / 4.0F;
            f5 = (renderer.aoLightValueScratchXYZNNN + renderer.aoLightValueScratchXYNN + renderer.aoLightValueScratchXZNN + f7) / 4.0F;
            renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXYNN, renderer.aoBrightnessXYZNNP, renderer.aoBrightnessXZNP, i1);
            renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNP, renderer.aoBrightnessXYNP, renderer.aoBrightnessXYZNPP, i1);
            renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXZNN, renderer.aoBrightnessXYZNPN, renderer.aoBrightnessXYNP, i1);
            renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessXYZNNN, renderer.aoBrightnessXYNN, renderer.aoBrightnessXZNN, i1);

            if (isNotGrassTop) {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = colorR * 0.6F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = colorG * 0.6F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = colorB * 0.6F;
            } else {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.6F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.6F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.6F;
            }

            renderer.colorRedTopLeft *= f3;
            renderer.colorGreenTopLeft *= f3;
            renderer.colorBlueTopLeft *= f3;
            renderer.colorRedBottomLeft *= f4;
            renderer.colorGreenBottomLeft *= f4;
            renderer.colorBlueBottomLeft *= f4;
            renderer.colorRedBottomRight *= f5;
            renderer.colorGreenBottomRight *= f5;
            renderer.colorBlueBottomRight *= f5;
            renderer.colorRedTopRight *= f6;
            renderer.colorGreenTopRight *= f6;
            renderer.colorBlueTopRight *= f6;

            renderedSomething = renderWestFace(element, texturesMap, x, y, z, renderer);
        }

        if (shouldRenderFace(element.getFace(McConst.SIDE_EAST), pos, renderer.blockAccess)) {
            if (element.cuboid.to().x() >= 1.0) {
                ++x;
            }

            renderer.aoLightValueScratchXYPN = renderer.blockAccess.getBlock(x, y - 1, z).getAmbientOcclusionLightValue();
            renderer.aoLightValueScratchXZPN = renderer.blockAccess.getBlock(x, y, z - 1).getAmbientOcclusionLightValue();
            renderer.aoLightValueScratchXZPP = renderer.blockAccess.getBlock(x, y, z + 1).getAmbientOcclusionLightValue();
            renderer.aoLightValueScratchXYPP = renderer.blockAccess.getBlock(x, y + 1, z).getAmbientOcclusionLightValue();
            renderer.aoBrightnessXYPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z);
            renderer.aoBrightnessXZPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z - 1);
            renderer.aoBrightnessXZPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z + 1);
            renderer.aoBrightnessXYPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z);
            flag2 = renderer.blockAccess.getBlock(x + 1, y + 1, z).getCanBlockGrass();
            flag3 = renderer.blockAccess.getBlock(x + 1, y - 1, z).getCanBlockGrass();
            flag4 = renderer.blockAccess.getBlock(x + 1, y, z + 1).getCanBlockGrass();
            flag5 = renderer.blockAccess.getBlock(x + 1, y, z - 1).getCanBlockGrass();

            if (!flag3 && !flag5) {
                renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
                renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
            } else {
                renderer.aoLightValueScratchXYZPNN = renderer.blockAccess.getBlock(x, y - 1, z - 1).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z - 1);
            }

            if (!flag3 && !flag4) {
                renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
                renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
            } else {
                renderer.aoLightValueScratchXYZPNP = renderer.blockAccess.getBlock(x, y - 1, z + 1).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y - 1, z + 1);
            }

            if (!flag2 && !flag5) {
                renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
                renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
            } else {
                renderer.aoLightValueScratchXYZPPN = renderer.blockAccess.getBlock(x, y + 1, z - 1).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZPPN = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z - 1);
            }

            if (!flag2 && !flag4) {
                renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
                renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
            } else {
                renderer.aoLightValueScratchXYZPPP = renderer.blockAccess.getBlock(x, y + 1, z + 1).getAmbientOcclusionLightValue();
                renderer.aoBrightnessXYZPPP = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y + 1, z + 1);
            }

            if (element.cuboid.to().x() >= 1.0) {
                --x;
            }

            i1 = mixedBrightness;

            if (element.cuboid.to().x() >= 1.0 || !renderer.blockAccess.getBlock(x + 1, y, z).isOpaqueCube()) {
                i1 = block.getMixedBrightnessForBlock(renderer.blockAccess, x + 1, y, z);
            }

            f7 = renderer.blockAccess.getBlock(x + 1, y, z).getAmbientOcclusionLightValue();
            f3 = (renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXYZPNP + f7 + renderer.aoLightValueScratchXZPP) / 4.0F;
            f4 = (renderer.aoLightValueScratchXYZPNN + renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXZPN + f7) / 4.0F;
            f5 = (renderer.aoLightValueScratchXZPN + f7 + renderer.aoLightValueScratchXYZPPN + renderer.aoLightValueScratchXYPP) / 4.0F;
            f6 = (f7 + renderer.aoLightValueScratchXZPP + renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
            renderer.brightnessTopLeft = renderer.getAoBrightness(renderer.aoBrightnessXYPN, renderer.aoBrightnessXYZPNP, renderer.aoBrightnessXZPP, i1);
            renderer.brightnessTopRight = renderer.getAoBrightness(renderer.aoBrightnessXZPP, renderer.aoBrightnessXYPP, renderer.aoBrightnessXYZPPP, i1);
            renderer.brightnessBottomRight = renderer.getAoBrightness(renderer.aoBrightnessXZPN, renderer.aoBrightnessXYZPPN, renderer.aoBrightnessXYPP, i1);
            renderer.brightnessBottomLeft = renderer.getAoBrightness(renderer.aoBrightnessXYZPNN, renderer.aoBrightnessXYPN, renderer.aoBrightnessXZPN, i1);

            if (isNotGrassTop) {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = colorR * 0.6F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = colorG * 0.6F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = colorB * 0.6F;
            } else {
                renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = 0.6F;
                renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = 0.6F;
                renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = 0.6F;
            }

            renderer.colorRedTopLeft *= f3;
            renderer.colorGreenTopLeft *= f3;
            renderer.colorBlueTopLeft *= f3;
            renderer.colorRedBottomLeft *= f4;
            renderer.colorGreenBottomLeft *= f4;
            renderer.colorBlueBottomLeft *= f4;
            renderer.colorRedBottomRight *= f5;
            renderer.colorGreenBottomRight *= f5;
            renderer.colorBlueBottomRight *= f5;
            renderer.colorRedTopRight *= f6;
            renderer.colorGreenTopRight *= f6;
            renderer.colorBlueTopRight *= f6;

            renderedSomething = renderEastFace(element, texturesMap, x, y, z, renderer);
        }

        renderer.enableAO = false;
        return renderedSomething;
    }

    // -Y
    private static boolean renderBottomFace(Element element, Map<String, IIcon> texturesMap, int x, int y, int z, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.instance;
        Cuboid<WCS> cuboid = CoordinateConverter.BCStoWCS(element.cuboid, x, y, z);
        Face face = element.bottom;
        IIcon icon = face.texture.getIcon(texturesMap);
        if (icon == null) return false;

        double minU = icon.getInterpolatedU(face.minU);
        double maxU = icon.getInterpolatedU(face.maxU);
        double minV = icon.getInterpolatedV(face.minV);
        double maxV = icon.getInterpolatedV(face.maxV);

        if (renderer.enableAO) {
            tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
            tessellator.setBrightness(renderer.brightnessTopLeft);
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.from().y(), cuboid.to().z(), minU, maxV);
            tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
            tessellator.setBrightness(renderer.brightnessBottomLeft);
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.from().y(), cuboid.from().z(), minU, minV);
            tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
            tessellator.setBrightness(renderer.brightnessBottomRight);
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.from().y(), cuboid.from().z(), maxU, minV);
            tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
            tessellator.setBrightness(renderer.brightnessTopRight);
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.from().y(), cuboid.to().z(), maxU, maxV);
        } else {
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.from().y(), cuboid.to().z(), minU, maxV);
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.from().y(), cuboid.from().z(), minU, minV);
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.from().y(), cuboid.from().z(), maxU, minV);
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.from().y(), cuboid.to().z(), maxU, maxV);
        }

        return true;
    }

    // +Y
    public static boolean renderTopFace(Element element, Map<String, IIcon> texturesMap, int x, int y, int z, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.instance;
        Cuboid<WCS> cuboid = CoordinateConverter.BCStoWCS(element.cuboid, x, y, z);
        Face face = element.top;
        IIcon icon = face.texture.getIcon(texturesMap);
        if (icon == null) return false;

        double minU = icon.getInterpolatedU(face.minU);
        double maxU = icon.getInterpolatedU(face.maxU);
        double minV = icon.getInterpolatedV(face.minV);
        double maxV = icon.getInterpolatedV(face.maxV);

        if (renderer.enableAO) {
            tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
            tessellator.setBrightness(renderer.brightnessTopLeft);
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.to().y(), cuboid.to().z(), maxU, maxV);
            tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
            tessellator.setBrightness(renderer.brightnessBottomLeft);
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.to().y(), cuboid.from().z(), maxU, minV);
            tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
            tessellator.setBrightness(renderer.brightnessBottomRight);
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.to().y(), cuboid.from().z(), minU, minV);
            tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
            tessellator.setBrightness(renderer.brightnessTopRight);
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.to().y(), cuboid.to().z(), minU, maxV);
        } else {
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.to().y(), cuboid.to().z(), maxU, maxV);
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.to().y(), cuboid.from().z(), maxU, minV);
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.to().y(), cuboid.from().z(), minU, minV);
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.to().y(), cuboid.to().z(), minU, maxV);
        }

        return true;
    }

    // -Z
    private static boolean renderNorthFace(Element element, Map<String, IIcon> texturesMap, int x, int y, int z, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.instance;
        Cuboid<WCS> cuboid = CoordinateConverter.BCStoWCS(element.cuboid, x, y, z);
        Face face = element.north;
        IIcon icon = face.texture.getIcon(texturesMap);
        if (icon == null) return false;

        double minU = icon.getInterpolatedU(face.minU);
        double maxU = icon.getInterpolatedU(face.maxU);
        double minV = icon.getInterpolatedV(face.minV);
        double maxV = icon.getInterpolatedV(face.maxV);

        if (renderer.enableAO) {
            tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
            tessellator.setBrightness(renderer.brightnessTopLeft);
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.to().y(), cuboid.from().z(), maxU, minV);
            tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
            tessellator.setBrightness(renderer.brightnessBottomLeft);
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.to().y(), cuboid.from().z(), minU, minV);
            tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
            tessellator.setBrightness(renderer.brightnessBottomRight);
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.from().y(), cuboid.from().z(), minU, maxV);
            tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
            tessellator.setBrightness(renderer.brightnessTopRight);
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.from().y(), cuboid.from().z(), maxU, maxV);
        } else {
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.to().y(), cuboid.from().z(), maxU, minV);
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.to().y(), cuboid.from().z(), minU, minV);
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.from().y(), cuboid.from().z(), minU, maxV);
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.from().y(), cuboid.from().z(), maxU, maxV);
        }

        return true;
    }

    // +Z
    public static boolean renderSouthFace(Element element, Map<String, IIcon> texturesMap, int x, int y, int z, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.instance;
        Cuboid<WCS> cuboid = CoordinateConverter.BCStoWCS(element.cuboid, x, y, z);
        Face face = element.south;
        IIcon icon = face.texture.getIcon(texturesMap);
        if (icon == null) return false;

        double minU = icon.getInterpolatedU(face.minU);
        double maxU = icon.getInterpolatedU(face.maxU);
        double minV = icon.getInterpolatedV(face.minV);
        double maxV = icon.getInterpolatedV(face.maxV);

        if (renderer.enableAO) {
            tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
            tessellator.setBrightness(renderer.brightnessTopLeft);
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.to().y(), cuboid.to().z(), minU, minV);
            tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
            tessellator.setBrightness(renderer.brightnessBottomLeft);
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.from().y(), cuboid.to().z(), minU, maxV);
            tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
            tessellator.setBrightness(renderer.brightnessBottomRight);
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.from().y(), cuboid.to().z(), maxU, maxV);
            tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
            tessellator.setBrightness(renderer.brightnessTopRight);
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.to().y(), cuboid.to().z(), maxU, minV);
        } else {
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.to().y(), cuboid.to().z(), minU, minV);
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.from().y(), cuboid.to().z(), minU, maxV);
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.from().y(), cuboid.to().z(), maxU, maxV);
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.to().y(), cuboid.to().z(), maxU, minV);
        }

        return true;
    }

    // -X
    private static boolean renderWestFace(Element element, Map<String, IIcon> texturesMap, int x, int y, int z, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.instance;
        Cuboid<WCS> cuboid = CoordinateConverter.BCStoWCS(element.cuboid, x, y, z);
        Face face = element.west;
        IIcon icon = face.texture.getIcon(texturesMap);
        if (icon == null) return false;

        double minU = icon.getInterpolatedU(face.minU);
        double maxU = icon.getInterpolatedU(face.maxU);
        double minV = icon.getInterpolatedV(face.minV);
        double maxV = icon.getInterpolatedV(face.maxV);

        if (renderer.enableAO) {
            tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
            tessellator.setBrightness(renderer.brightnessTopLeft);
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.to().y(), cuboid.to().z(), maxU, minV);
            tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
            tessellator.setBrightness(renderer.brightnessBottomLeft);
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.to().y(), cuboid.from().z(), minU, minV);
            tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
            tessellator.setBrightness(renderer.brightnessBottomRight);
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.from().y(), cuboid.from().z(), minU, maxV);
            tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
            tessellator.setBrightness(renderer.brightnessTopRight);
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.from().y(), cuboid.to().z(), maxU, maxV);
        } else {
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.to().y(), cuboid.to().z(), maxU, minV);
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.to().y(), cuboid.from().z(), minU, minV);
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.from().y(), cuboid.from().z(), minU, maxV);
            tessellator.addVertexWithUV(cuboid.from().x(), cuboid.from().y(), cuboid.to().z(), maxU, maxV);
        }

        return true;
    }

    // +X
    public static boolean renderEastFace(Element element, Map<String, IIcon> texturesMap, int x, int y, int z, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.instance;
        Cuboid<WCS> cuboid = CoordinateConverter.BCStoWCS(element.cuboid, x, y, z);
        Face face = element.east;
        IIcon icon = face.texture.getIcon(texturesMap);
        if (icon == null) return false;

        double minU = icon.getInterpolatedU(face.minU);
        double maxU = icon.getInterpolatedU(face.maxU);
        double minV = icon.getInterpolatedV(face.minV);
        double maxV = icon.getInterpolatedV(face.maxV);

        if (renderer.enableAO) {
            tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
            tessellator.setBrightness(renderer.brightnessTopLeft);
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.from().y(), cuboid.to().z(), minU, maxV);
            tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
            tessellator.setBrightness(renderer.brightnessBottomLeft);
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.from().y(), cuboid.from().z(), maxU, maxV);
            tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
            tessellator.setBrightness(renderer.brightnessBottomRight);
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.to().y(), cuboid.from().z(), maxU, minV);
            tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
            tessellator.setBrightness(renderer.brightnessTopRight);
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.to().y(), cuboid.to().z(), minU, minV);
        } else {
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.from().y(), cuboid.to().z(), minU, maxV);
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.from().y(), cuboid.from().z(), maxU, maxV);
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.to().y(), cuboid.from().z(), maxU, minV);
            tessellator.addVertexWithUV(cuboid.to().x(), cuboid.to().y(), cuboid.to().z(), minU, minV);
        }

        return true;
    }
}
