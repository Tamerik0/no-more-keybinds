package tamerlan.fabric.tamerlanlib.gui.core;

import net.minecraft.client.gui.DrawContext;

public class TransformedElement extends TransformableRenderable {
    IRenderable widget;

    public TransformedElement(IRenderable widget) {
        this(widget, new Transform2D());
    }
    public TransformedElement(IRenderable widget, Transform2D transform) {
        super(transform);
        this.widget = widget;
    }


    public TransformedElement(TransformedElement other) {
        super(other);
        this.widget = other.widget;
    }

    @Override
    public void renderWithoutTransform(DrawContext context) {
        widget.render(context);
    }
}
