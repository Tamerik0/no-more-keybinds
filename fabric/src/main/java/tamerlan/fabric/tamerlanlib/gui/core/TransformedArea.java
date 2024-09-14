package tamerlan.fabric.tamerlanlib.gui.core;

import net.minecraft.util.math.Vec2f;

public class TransformedArea implements IGUIArea{
    public Transform2D transform;
    public IGUIArea area;
    public TransformedArea(Transform2D transform, IGUIArea area){
        this.transform = transform;
        this.area = area;
    }

    @Override
    public boolean isInsideArea(Vec2f pos) {
        return area.isInsideArea(transform.applyReverse(new Transform2D(pos)).pos);
    }
}
