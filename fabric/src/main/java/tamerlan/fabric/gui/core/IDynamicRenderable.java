package tamerlan.fabric.gui.core;

import net.minecraft.client.gui.DrawContext;

public interface IDynamicRenderable extends IRenderableWithPosition, IRenderableWithScale, IRenderableWithRotation, IRenderableWithZOffset {
    void renderWithTransform(DrawContext context, Transform2D transform);
}
