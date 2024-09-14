package tamerlan.fabric.tamerlanlib.events;

import java.util.List;

public class KeyboardEventDispatcher extends EventDispatcher {
    public KeyboardEventDispatcher(){
        addHandler(KeyboardEvents.KeyboardEventType.COMMON.type, new EventHandler<KeyboardEvents.KeyEvent>());
        addHandler(KeyboardEvents.KeyboardEventType.PRESS.type, new EventHandler<KeyboardEvents.KeyEvent>());
        addHandler(KeyboardEvents.KeyboardEventType.REPEAT.type, new EventHandler<KeyboardEvents.KeyEvent>());
        addHandler(KeyboardEvents.KeyboardEventType.RELEASE.type, new EventHandler<KeyboardEvents.KeyEvent>());
        addHandler(KeyboardEvents.KeyboardEventType.CHAR.type, new EventHandler<KeyboardEvents.CharEvent>());
    }
    @Override
    protected List<IEvent> dispatchEvent(IEvent event) {
        if (event instanceof KeyboardEvents.KeyEvent keyEvent) {
            if (event.getEventType() == KeyboardEvents.KeyboardEventType.COMMON.type) {
                if (keyEvent.action == 1) {
                    return List.of(new KeyboardEvents.KeyEvent(KeyboardEvents.KeyboardEventType.PRESS.type, keyEvent.key, keyEvent.scancode, keyEvent.modifiers, keyEvent.action));
                } else if (keyEvent.action == 0) {
                    return List.of(new KeyboardEvents.KeyEvent(KeyboardEvents.KeyboardEventType.RELEASE.type, keyEvent.key, keyEvent.scancode, keyEvent.modifiers, keyEvent.action));
                } else if (keyEvent.action == 2) {
                    return List.of(new KeyboardEvents.KeyEvent(KeyboardEvents.KeyboardEventType.REPEAT.type, keyEvent.key, keyEvent.scancode, keyEvent.modifiers, keyEvent.action));
                }
            }
        }
        return List.of();
    }
}
