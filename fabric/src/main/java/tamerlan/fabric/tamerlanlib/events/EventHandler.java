package tamerlan.fabric.tamerlanlib.events;

import tamerlan.fabric.tamerlanlib.core.ContainerClass;
import tamerlan.fabric.NMKClient;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EventHandler<T extends IEvent> extends ContainerClass<EventListener<T>, Set<EventListener<T>>> implements EventListener<T> {
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
        NMKClient.LOGGER.info("listenEvent "+getClass().getName());
        for (var listener : listeners) {
            NMKClient.LOGGER.info("listener "+listener.getClass().getName());
            listener.listenEvent(event);
        }
    }

    @Override
    protected Set<EventListener<T>> _getContainer() {
        return listeners;
    }
}
