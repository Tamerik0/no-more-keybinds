package tamerlan.fabric.gui.core;

import net.minecraft.client.gui.DrawContext;

public interface IRenderableWithRotation {
    void renderWithRotation(DrawContext context, float r);
}
