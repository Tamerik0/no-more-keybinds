package tamerlan.fabric.gui.core;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.Vec2f;
import tamerlan.fabric.IRemovable;
import tamerlan.fabric.Mouse;
import tamerlan.fabric.gui.events.InputHandler;
import tamerlan.fabric.gui.events.MouseEvents;

public class BaseGUIElement extends TransformableRenderable implements GUIContainerProvider, InputHandlerProvider, IAreaProvider, IRemovable {
    final GUIContainer container = new GUIContainer();
    final InputHandler inputHandler = new InputHandler();
    public IGUIArea area;
    BaseGUIElement parent;
    boolean underCursor;

    public BaseGUIElement() {
        super(new InheritableTransform2D(null));
        inputHandler.mouseEventHandler.addListener(MouseEvents.MouseEventType.CLICK.type, this::mouseClicked);
    }

    public BaseGUIElement(Vec2f pos) {
        this();
        transform.pos = pos;
    }

    public BaseGUIElement(BaseGUIElement parent) {
        this();
        setParent(parent);
    }

    public BaseGUIElement(BaseGUIElement parent, Vec2f pos) {
        this(pos);
        setParent(parent);
    }

    private void mouseClicked(MouseEvents.MouseEvent event) {
        if (underCursor) {
            onClick(event);
        }
    }


    public <T extends BaseGUIElement> T addChild(T child) {
        child.setParent(this);
        return child;
    }

    public <T extends IRenderable> T addChild(T child) {
        container.addRenderable(child);
        return child;
    }

    public <T extends InputHandlerProvider> T addChild(T child) {
        inputHandler.addListener(child.getInputHandler());
        return child;
    }

    public BaseGUIElement getParent() {
        return parent;
    }

    public void setParent(BaseGUIElement parent) {
        var oldTransform = transform;
        transform = new InheritableTransform2D((InheritableTransform2D) parent.transform);
        transform.pos = oldTransform.pos;
        transform.scale = oldTransform.scale;
        transform.zOffset = oldTransform.zOffset;
        transform.rotation = oldTransform.rotation;
        this.parent = parent;
        parent.container.addRenderable(this);
        parent.inputHandler.addListener(inputHandler);
    }

    @Override
    public GUIContainer getContainer() {
        return container;
    }

    @Override
    public InputHandler getInputHandler() {
        return inputHandler;
    }

    @Override
    public void renderWithoutTransform(DrawContext context) {
        var mousePos = Mouse.getMouseScreenPos();
        var underCursorNow = getArea().isInsideArea(mousePos);
        if (underCursor != underCursorNow) {
            if (underCursor = underCursorNow) onMouseEntered();
            else onMouseExit();
        }
        container.render(context);
        renderContent(context);
    }

    protected void renderContent(DrawContext context) {
    }

    protected void onClick(MouseEvents.MouseEvent event) {
    }

    protected void onMouseExit() {
    }

    protected void onMouseEntered() {
    }

    @Override
    public IGUIArea getArea() {
        return new TransformedArea(((InheritableTransform2D) this.transform).getGlobalTransform(), this.area);
    }

    @Override
    public void remove() {
        container.remove();
        inputHandler.remove();
        parent.container.removeRenderable(this);
        parent.inputHandler.removeListener(inputHandler);
    }
}
