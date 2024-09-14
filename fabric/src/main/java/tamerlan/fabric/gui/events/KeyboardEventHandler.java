package tamerlan.fabric.gui.events;

import java.util.List;

public class KeyboardEventHandler extends EventDispatcher {
    @Override
    protected List<IEvent> dispatchEvent(IEvent event) {
        if (event instanceof KeyboardEvents.KeyEvent keyEvent) {
            if (keyEvent.action == 1) {
                return List.of(new KeyboardEvents.KeyEvent(KeyboardEvents.KeyboardEventType.PRESS.type, keyEvent.key, keyEvent.scancode, keyEvent.modifiers, keyEvent.action));
            } else if (keyEvent.action == 0) {
                return List.of(new KeyboardEvents.KeyEvent(KeyboardEvents.KeyboardEventType.RELEASE.type, keyEvent.key, keyEvent.scancode, keyEvent.modifiers, keyEvent.action));
            } else if (keyEvent.action == 2) {
                return List.of(new KeyboardEvents.KeyEvent(KeyboardEvents.KeyboardEventType.REPEAT.type, keyEvent.key, keyEvent.scancode, keyEvent.modifiers, keyEvent.action));
            }
        } else if (event instanceof KeyboardEvents.CharEvent) {
            return List.of(event);
        }
        return List.of();
    }
}
