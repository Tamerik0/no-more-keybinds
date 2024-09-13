package tamerlan.fabric.gui.inputevents;

import java.util.Set;

public class KeyboardCharEventHandler implements KeyboardEventListener.charEventListener {
    Set<KeyboardEventListener.charEventListener> listeners;
    public <T extends KeyboardEventListener.charEventListener> T addListener(T listener){
        listeners.add(listener);
        return listener;
    }
    public <T extends KeyboardEventListener.charEventListener> T removeListener(T listener){
        listeners.remove(listener);
        return listener;
    }

    @Override
    public void onCharTyped(KeyboardEventListener.CharEvent event) {
        for (var listener : listeners)
            listener.onCharTyped(event);
    }
}
