package tamerlan.fabric.tamerlanlib.gui.core;

import net.minecraft.client.gui.DrawContext;

public interface IRenderableWithZOffset {
    void renderOnZLayer(DrawContext context, int z);
}
