package tamerlan.fabric.gui.core;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec2f;
import org.joml.AxisAngle4d;
import org.joml.Quaternionf;
import org.joml.Vector3d;

public class TransformedElement implements IRenderable {
    IRenderable widget;
    Transform2D transform;

    public TransformedElement(IRenderable widget) {
        this.widget = widget;
    }
    public TransformedElement(IRenderable widget, Transform2D transform) {
        this(widget);
        this.transform = transform;
    }


    public TransformedElement(TransformedElement other) {
        this.widget = other.widget;
        this.transform = new Transform2D(other.transform);
    }

    public TransformedElement translate(Vec2f dir) {
        transform.translate(dir);
        return this;
    }

    public TransformedElement translated(Vec2f dir) {
        return new TransformedElement(this).translate(dir);
    }

    public TransformedElement setZ(int zOffset) {
        transform.zOffset = zOffset;
        return this;
    }

    public TransformedElement withZ(int z) {
        return new TransformedElement(this).setZ(z);
    }

    public TransformedElement scale(Vec2f scale) {
        transform.scale(scale);
        return this;
    }

    public TransformedElement scaled(Vec2f scale) {
        return new TransformedElement(this).scale(scale);
    }

    public TransformedElement rotate(float r) {
        transform.rotate(r);
        return this;
    }

    public TransformedElement rotated(float r) {
        return new TransformedElement(this).rotate(r);
    }

    @Override
    public void render(DrawContext context) {
        MatrixStack matrices = context.getMatrices();
        matrices.push();
        matrices.scale(transform.scale.x, transform.scale.y, 1);
        matrices.multiply(new Quaternionf(new AxisAngle4d(transform.rotation, new Vector3d(0, 0, 1))));
        matrices.translate(transform.pos.x, transform.pos.y, transform.zOffset);
        widget.render(context);
        matrices.pop();
    }
}
