package tamerlan.fabric.gui.core;

public class InheritableTransform2D extends Transform2D {
    InheritableTransform2D parent;

    public InheritableTransform2D(InheritableTransform2D parent) {
        this.parent = parent;
    }

    public Transform2D getGlobalTransform() {
        if (parent == null)
            return new Transform2D(this);
        return parent.getGlobalTransform().apply(this);
    }
}
