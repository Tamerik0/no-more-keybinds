package tamerlan.fabric.gui.core;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec2f;
import org.joml.AxisAngle4d;
import org.joml.Quaternionf;
import org.joml.Vector3d;

public class Transform2D {
    public Vec2f pos;
    public int zOffset = 0;
    public Vec2f scale;
    public float rotation = 0;

    public Transform2D() {
    }

    public Transform2D(Transform2D other) {
        pos = other.pos;
        zOffset = other.zOffset;
        scale = other.scale;
        rotation = other.rotation;
    }

    public Transform2D translate(Vec2f dir) {
        pos = pos.add(dir);
        return this;
    }

    public Transform2D translated(Vec2f dir) {
        return new Transform2D(this).translate(dir);
    }

    public Transform2D setZ(int zOffset) {
        this.zOffset = zOffset;
        return this;
    }

    public Transform2D withZ(int z) {
        return new Transform2D(this).setZ(z);
    }

    public Transform2D scale(Vec2f scale) {
        this.scale = new Vec2f(this.scale.x * scale.x, this.scale.y * scale.y);
        return this;
    }

    public Transform2D scaled(Vec2f scale) {
        return new Transform2D(this).scale(scale);
    }

    public Transform2D rotate(float r) {
        rotation += r;
        return this;
    }

    public Transform2D rotated(float r) {
        return new Transform2D(this).rotate(r);
    }
}
