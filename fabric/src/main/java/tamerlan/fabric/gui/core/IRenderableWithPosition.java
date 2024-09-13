package tamerlan.fabric.gui.core;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.Vec2f;

public interface IRenderableWithPosition {
    void renderAtPosition(DrawContext context, Vec2f pos);
}
