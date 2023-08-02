package net.yuma14.mc.lib_json_model.impl.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.yuma14.mc.lib_json_model.api.v1.IBlockModel;
import net.yuma14.mc.lib_json_model.api.v1.IBlockWithJsonModel;
import net.yuma14.mc.lib_json_model.api.v1.math.CoordinateConverter;
import net.yuma14.mc.lib_json_model.api.v1.math.Cuboid;
import net.yuma14.mc.lib_json_model.api.v1.math.WCS;
import org.lwjgl.opengl.GL11;

public class BlockModelOutlineRenderer {

    public static boolean renderOutlineInWorld(RenderGlobal renderer, EntityPlayer player, MovingObjectPosition target, float partialTicks) {
        if (target.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) return false;
        Block block = player.worldObj.getBlock(target.blockX, target.blockY, target.blockZ);
        if (!(block instanceof IBlockWithJsonModel)) return false;


        GL11.glEnable(GL11.GL_BLEND);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(false);

        double delta = 0.002D;
        double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double) partialTicks;
        double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double) partialTicks;
        double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double) partialTicks;

        IBlockModel model = ((IBlockWithJsonModel) block).getBlockModel(player.worldObj, target.blockX, target.blockY, target.blockZ);
        model.getCuboids().forEach(cuboid -> {
            Cuboid<WCS> worldCuboid = CoordinateConverter.BCStoWCS(cuboid, target.blockX, target.blockY, target.blockZ);
            AxisAlignedBB aabb = worldCuboid.toAABB()
                    .expand(delta, delta, delta)
                    .getOffsetBoundingBox(-d0, -d1, -d2);
            RenderGlobal.drawOutlinedBoundingBox(aabb, -1);
        });

        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);

        return true;
    }
}
