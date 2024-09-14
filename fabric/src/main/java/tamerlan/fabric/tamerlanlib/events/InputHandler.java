package tamerlan.fabric.tamerlanlib.events;

import tamerlan.fabric.tamerlanlib.core.IRemovable;
import tamerlan.fabric.tamerlanlib.gui.core.InputHandlerProvider;

public class InputHandler implements EventListener<IEvent>, IRemovable {
    public final KeyboardEventDispatcher keyboardEventDispatcher;
    public final MouseEventDispatcher mouseEventDispatcher;
    EventHandler<IEvent> handler = new EventHandler<>();
    public InputHandler() {
        keyboardEventDispatcher = handler.addListener(new KeyboardEventDispatcher());
        mouseEventDispatcher = handler.addListener(new MouseEventDispatcher());
    }
    public void addListener(InputHandler inputHandler) {
        keyboardEventDispatcher.addListener(KeyboardEvents.KeyboardEventType.COMMON.type, inputHandler.keyboardEventDispatcher);
        mouseEventDispatcher.addListener(MouseEvents.MouseEventType.COMMON.type, inputHandler.mouseEventDispatcher);
    }
    public void addListener(InputHandlerProvider provider) {
        addListener(provider.getInputHandler());
    }
    public void removeListener(InputHandler inputHandler) {
        handler.removeListener(inputHandler);
        handler.removeListener(inputHandler.keyboardEventDispatcher);
        handler.removeListener(inputHandler.mouseEventDispatcher);
    }
    public void removeListener(InputHandlerProvider provider) {
        removeListener(provider.getInputHandler());
    }

    @Override
    public void listenEvent(IEvent event) {
        handler.listenEvent(event);
    }

    @Override
    public void remove() {
        handler.remove();
    }
}
