package tamerlan.fabric.tamerlanlib.gui.core;

import net.minecraft.util.math.Vec2f;
import org.joml.*;

public class Transform2D {
    public Vec2f pos = new Vec2f(0, 0);
    public int zOffset = 0;
    public Vec2f scale = new Vec2f(1, 1);
    public float rotation = 0;

    public Transform2D() {
    }
    public Transform2D(Vec2f pos) {
        this.pos = pos;
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

    public Transform2D apply(Transform2D other) {
        var ans = new Transform2D(this);
        var p = new Vector2f(other.pos.x, other.pos.y).mul(new Matrix2f().rotate(rotation));
        ans.translate(new Vec2f(p.x, p.y));
        ans.scale(other.scale);
        ans.rotate(other.rotation);
        ans.setZ(ans.zOffset + other.zOffset);
        return ans;
    }

    public Transform2D applyReverse(Transform2D other) {
        var ans = new Transform2D();
        ans.zOffset = other.zOffset - zOffset;
        ans.scale = new Vec2f(other.scale.x / this.scale.x, other.scale.y / this.scale.y);
        ans.rotation = other.rotation - this.rotation;
        var p1 = other.pos.add(this.pos.multiply(-1));
        var p = new Vector2f(p1.x, p1.y).mul(new Matrix2f().rotate(-rotation));
        ans.pos = new Vec2f(p.x, p.y);
        return ans;
    }
}
