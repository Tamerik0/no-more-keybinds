package tamerlan.fabric.tamerlanlib.gui.core;

import net.minecraft.client.gui.DrawContext;

public class StaticRenderableFromDynamic implements IRenderable{
    public final Transform2D transform = new Transform2D();
    private final IDynamicRenderable base;

    public StaticRenderableFromDynamic(IDynamicRenderable base) {
        this.base = base;
    }

    @Override
    public void render(DrawContext context) {
        base.renderWithTransform(context, transform);
    }
}
