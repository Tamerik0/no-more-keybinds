package tamerlan.fabric.gui.events;

import tamerlan.fabric.gui.core.InputHandlerProvider;

public class InputHandler extends EventHander<IEvent> {
    public final KeyboardEventHandler keyboardEventHandler;
    public final MouseEventHandler mouseEventHandler;

    public InputHandler() {
        keyboardEventHandler = addListener(new KeyboardEventHandler());
        mouseEventHandler = addListener(new MouseEventHandler());
    }

    public void addListener(InputHandlerProvider provider) {
        addListener(provider.getInputHandler());
    }
}
