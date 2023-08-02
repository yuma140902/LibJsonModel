package net.yuma14.mc.lib_json_model.impl.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.yuma14.mc.lib_json_model.impl.render.BlockModelOutlineRenderer;

public class ClientEventHandler {
    @SubscribeEvent
    public void onRenderBlockOutline(DrawBlockHighlightEvent event) {
        boolean renderedSomething = BlockModelOutlineRenderer.renderOutlineInWorld(event.context, event.player, event.target, event.partialTicks);
        event.setCanceled(renderedSomething);
    }
}
