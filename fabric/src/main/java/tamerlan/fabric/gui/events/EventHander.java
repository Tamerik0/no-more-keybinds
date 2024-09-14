package tamerlan.fabric.gui.events;

import tamerlan.fabric.ContainerClass;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EventHander<T extends IEvent> extends ContainerClass<EventListener<T>, Set<EventListener<T>>> implements EventListener<T> {
    Set<EventListener<T>> listeners = new HashSet<>();

    public <V extends EventListener<T>> V addListener(V listener) {
        return (V) _addElement(listener);
    }

    public <V extends EventListener<T>> V removeListener(V listener) {
        return (V) _removeElement(listener);
    }

    public List<EventListener<T>> getAllListeners() {
        return listeners.stream().toList();
    }

    @Override
    public void listenEvent(T event) {
        for (var listener : listeners) {
            listener.listenEvent(event);
        }
    }

    @Override
    protected Set<EventListener<T>> _getContainer() {
        return listeners;
    }
}
