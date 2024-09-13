package tamerlan.fabric.menu;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.Vec2f;

import java.util.ArrayList;
import java.util.List;

public abstract class MenuItem {
    protected MenuItem parent;
    protected List<MenuItem> children = new ArrayList<>();
    boolean underCursor = false;
    Vec2f pos;

    public void remove() {
        for (var child : children) {
            child.remove();
        }
        children.clear();
        parent = null;
    }

    public void fullRenderAndUpdate(DrawContext context, Vec2f mousePos) {
        var size1 = 0;
        while (size1 != children.size()) {
            for (int i = 0; i < children.size(); i++) {
                children.get(i).fullRenderAndUpdate(context, mousePos);
            }
            size1 = children.size();
        }
        var underCursorNow = isPosInsideArea(mousePos);
        if (underCursor != underCursorNow) {
            if (underCursor = underCursorNow)
                onMouseEntered();
            else
                onMouseExit();
        }
        renderContent(context, mousePos);
    }

    public void mouseClicked(MinecraftClient client, int button, int action, int mods) {
        if (underCursor) {
            onClick(button, action, mods);
        }
        for (var child : children) {
            child.mouseClicked(client, button, action, mods);
        }
    }

    protected MenuItem(MenuItem parent, Vec2f pos) {
        this.parent = parent;
        this.pos = pos;
    }

    protected <T extends MenuItem> T addChild(T child) {
        children.add(child);
        child.parent = this;
        return child;
    }

    protected abstract boolean isPosInsideArea(Vec2f pos);

    protected void renderContent(DrawContext context, Vec2f mousePos) {};

    protected void onClick(int button, int action, int mods) {};

    protected void onMouseEntered() {};

    protected void onMouseExit() {};
}
