package tamerlan.fabric.tamerlanlib.gui.core;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec2f;
import org.joml.AxisAngle4d;
import org.joml.Quaternionf;
import org.joml.Vector3d;

public class TransformableRenderable implements IRenderable {
    final IRenderable widget;
    public Transform2D transform;

    protected TransformableRenderable() {
        widget = this::renderWithoutTransform;
        transform = new Transform2D();
    }

    protected TransformableRenderable(Transform2D transform) {
        widget = this::renderWithoutTransform;
        this.transform = transform;
    }


    protected TransformableRenderable(TransformableRenderable other) {
        this.widget = other.widget;
        this.transform = new Transform2D(other.transform);
    }

    public TransformableRenderable translate(Vec2f dir) {
        transform.translate(dir);
        return this;
    }

    public TransformableRenderable translated(Vec2f dir) {
        return new TransformableRenderable(this).translate(dir);
    }

    public TransformableRenderable setZ(int zOffset) {
        transform.zOffset = zOffset;
        return this;
    }

    public TransformableRenderable withZ(int z) {
        return new TransformableRenderable(this).setZ(z);
    }

    public TransformableRenderable scale(Vec2f scale) {
        transform.scale(scale);
        return this;
    }

    public TransformableRenderable scaled(Vec2f scale) {
        return new TransformableRenderable(this).scale(scale);
    }

    public TransformableRenderable rotate(float r) {
        transform.rotate(r);
        return this;
    }

    public TransformableRenderable rotated(float r) {
        return new TransformableRenderable(this).rotate(r);
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

    public void renderWithoutTransform(DrawContext context) {
    }
}
