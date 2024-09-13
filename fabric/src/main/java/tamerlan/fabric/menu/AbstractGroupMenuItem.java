package tamerlan.fabric.menu;

import com.google.gson.JsonElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec2f;
import tamerlan.fabric.data.RoundGroupData;

public abstract class AbstractGroupMenuItem extends MenuItem {
    public float excludedDirectionRange = 60;
    float excludedDir;
    AbstractGroupMenuItem openedChild;

    protected AbstractGroupMenuItem(MenuItem parent, Vec2f pos) {
        super(parent, pos);
    }

    @Override
    protected boolean isPosInsideArea(Vec2f pos) {
        return Math.sqrt(this.pos.distanceSquared(pos)) < 30;
    }

    @Override
    protected void renderContent(DrawContext context, Vec2f mousePos) {}

    @Override
    protected void onMouseEntered() {
        if (openedChild != null) {
            openedChild.close();
            openedChild = null;
        }
        if (parent instanceof AbstractGroupMenuItem) {
            ((AbstractGroupMenuItem) parent).childOpened(this);
        }
        close();
        open();
    }

    protected abstract void open();


    void childOpened(AbstractGroupMenuItem item) {
        openedChild = item;
        for (var child : children) {
            if (child != item) {
                child.remove();
            }
        }
        children.clear();
        children.add(item);
    }

    void close() {
        for (var child : children) {
            if (child instanceof AbstractGroupMenuItem childGroup)
                childGroup.close();
            child.remove();
        }
        children.clear();
    }
}
