package tamerlan.fabric.gui.events;

import tamerlan.fabric.IRemovable;

import java.util.HashMap;
import java.util.List;

public abstract class EventDispatcher implements EventListener<IEvent>, IRemovable {
    HashMap<IEvent.EventType, EventHander> handlers = new HashMap<>();

    public void addHandler(IEvent.EventType eventType, EventHander handler) {
        handlers.put(eventType, handler);
    }

    public <T extends IEvent> EventListener<T> addListener(IEvent.EventType type, EventListener<T> listener) {
        handlers.get(type).addListener(listener);
        return listener;
    }

    public <T extends IEvent> EventListener<T> removeListener(IEvent.EventType type, EventListener<T> listener) {
        handlers.get(type).removeListener(listener);
        return listener;
    }

    public <T extends IEvent> EventListener<T> removeListener(EventListener<T> listener) {
        for (var handler : handlers.values())
            handler.removeListener(listener);
        return listener;
    }

    @Override
    public void listenEvent(IEvent event) {
        boolean f = true;
        for (var ev : dispatchEvent(event)) {
            if (ev.getEventType() == event.getEventType())
                f = false;
            if (handlers.containsKey(ev.getEventType()))
                handlers.get(ev.getEventType()).listenEvent(ev);
        }
        if (f && handlers.containsKey(event.getEventType()))
            handlers.get(event.getEventType()).listenEvent(event);
    }

    protected abstract List<IEvent> dispatchEvent(IEvent event);

    @Override
    public void remove() {
        for (var handler : handlers.values())
            handler.remove();
    }
}
