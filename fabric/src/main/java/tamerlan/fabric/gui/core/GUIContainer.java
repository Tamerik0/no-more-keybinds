package tamerlan.fabric.gui.core;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.client.gui.DrawContext;

import java.util.Set;

public class GUIContainer implements IRenderable {
    Set<IRenderable> renderables = new ObjectArraySet<>();

    public <T extends IRenderable> T addRenderable(T renderable) {
        renderables.add(renderable);
        return renderable;
    }

    public <T extends IRenderable> void removeRenderable(T renderable) {
        renderables.remove(renderable);
    }

    @Override
    public void render(DrawContext context) {
        for (var renderable : renderables)
            renderable.render(context);
    }
}