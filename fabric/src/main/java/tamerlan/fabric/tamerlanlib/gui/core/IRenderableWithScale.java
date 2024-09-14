package tamerlan.fabric.tamerlanlib.gui.core;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.Vec2f;

public interface IRenderableWithScale {
    void renderWithScale(DrawContext context, Vec2f scale);
}
