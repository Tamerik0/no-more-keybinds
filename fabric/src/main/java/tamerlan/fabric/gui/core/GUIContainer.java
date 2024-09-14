package tamerlan.fabric.gui.core;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.client.gui.DrawContext;
import tamerlan.fabric.ContainerClass;

import java.util.Set;

public class GUIContainer extends ContainerClass<IRenderable, Set<IRenderable>> implements IRenderable {
    Set<IRenderable> renderables = new ObjectArraySet<>();

    public <T extends IRenderable> T addRenderable(T renderable) {
        return (T) _addElement(renderable);
    }

    public <T extends IRenderable> T removeRenderable(T renderable) {
        return (T) _removeElement(renderable);
    }

    @Override
    public void render(DrawContext context) {
        for (var renderable : renderables)
            renderable.render(context);
    }

    @Override
    protected Set<IRenderable> _getContainer() {
        return renderables;
    }
}