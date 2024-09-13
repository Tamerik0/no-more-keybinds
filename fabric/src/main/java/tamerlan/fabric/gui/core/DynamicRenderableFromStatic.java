package tamerlan.fabric.gui.core;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.Vec2f;

public class DynamicRenderableFromStatic implements IDynamicRenderable{
    IRenderable base;
    public DynamicRenderableFromStatic(IRenderable base){
        this.base = base;
    }
    @Override
    public void renderAtPosition(DrawContext context, Vec2f pos) {
        new TransformedElement(base).translate(pos).render(context);
    }

    @Override
    public void renderWithRotation(DrawContext context, float r) {
        new TransformedElement(base).rotate(r).render(context);
    }

    @Override
    public void renderWithScale(DrawContext context, Vec2f scale) {
        new TransformedElement(base).scale(scale).render(context);
    }

    @Override
    public void renderOnZLayer(DrawContext context, int z) {
        new TransformedElement(base).setZ(z).render(context);
    }

    @Override
    public void renderWithTransform(DrawContext context, Transform2D transform) {
        new TransformedElement(base, transform).render(context);
    }
}
