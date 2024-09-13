package tamerlan.fabric.gui.inputevents;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class KeyboardKeyEventHandler implements KeyboardEventListener {
    Set<KeyboardEventListener> keyboardEventListeners = new ObjectArraySet<>();
    Set<KeyboardEventListener> keyPressListeners = new ObjectArraySet<>();
    Set<KeyboardEventListener> keyReleaseListeners = new ObjectArraySet<>();
    Set<KeyboardEventListener> keyRepeatListeners = new ObjectArraySet<>();
    public <T extends KeyboardEventListener> T addKeyboardEventListener(T listener){
        keyboardEventListeners.add(listener);
        return listener;
    }
    public <T extends KeyboardEventListener> T addKeyPressListener(T listener){
        keyPressListeners.add(listener);
        return listener;
    }
    public <T extends KeyboardEventListener> T addKeyReleaseListener(T listener){
        keyReleaseListeners.add(listener);
        return listener;
    }
    public <T extends KeyboardEventListener> T addKeyRepeatListener(T listener){
        keyRepeatListeners.remove(listener);
        return listener;
    }
    public <T extends KeyboardEventListener> T removeKeyboardEventListener(T listener){
        keyboardEventListeners.remove(listener);
        return listener;
    }
    public <T extends KeyboardEventListener> T removeKeyPressListener(T listener){
        keyPressListeners.remove(listener);
        return listener;
    }
    public <T extends KeyboardEventListener> T removeKeyReleaseListener(T listener){
        keyReleaseListeners.remove(listener);
        return listener;
    }
    public <T extends KeyboardEventListener> T removeKeyRepeatListener(T listener){
        keyRepeatListeners.remove(listener);
        return listener;
    }
    @Override
    public void onKeyboardEvent(KeyEvent event) {
        for (var listener : keyboardEventListeners)
            listener.onKeyboardEvent(event);
        if(event.action == 0)
            for (var listener : keyReleaseListeners)
                listener.onKeyboardEvent(event);
        else if(event.action == 1)
            for (var listener : keyPressListeners)
                listener.onKeyboardEvent(event);
        else if(event.action == 2){
            for (var listener : keyRepeatListeners)
                listener.onKeyboardEvent(event);
        }
    }
}
