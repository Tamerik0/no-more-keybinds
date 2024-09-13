package tamerlan.fabric.gui.inputevents;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;

import java.util.Set;

public class MouseEventHandler implements MouseEventListener, MouseEventListener.MouseScrollEventListener {
    Set<MouseEventListener> mouseEventListeners = new ObjectArraySet<>();
    Set<MouseEventListener> clickListeners = new ObjectArraySet<>();
    Set<MouseEventListener> releaseListeners = new ObjectArraySet<>();
    Set<MouseEventListener.MouseScrollEventListener> scrollListeners = new ObjectArraySet<>();
    public <T extends MouseEventListener> T addMouseEventListener(T listener){
        mouseEventListeners.add(listener);
        return listener;
    }
    public <T extends MouseEventListener> T addClickListener(T listener){
        clickListeners.add(listener);
        return listener;
    }
    public <T extends MouseEventListener> T addReleaseListener(T listener){
        releaseListeners.add(listener);
        return listener;
    }
    public <T extends MouseEventListener.MouseScrollEventListener> T addScrollListener(T listener){
        scrollListeners.remove(listener);
        return listener;
    }
    public <T extends MouseEventListener> T removeMouseEventListener(T listener){
        mouseEventListeners.remove(listener);
        return listener;
    }
    public <T extends MouseEventListener> T removeClickListener(T listener){
        clickListeners.remove(listener);
        return listener;
    }
    public <T extends MouseEventListener> T removeReleaseListener(T listener){
        releaseListeners.remove(listener);
        return listener;
    }
    public <T extends MouseEventListener.MouseScrollEventListener> T removeScrollListener(T listener){
        scrollListeners.remove(listener);
        return listener;
    }
    @Override
    public void onMouseEvent(MouseEvent event) {
        for(var listener : mouseEventListeners)
            listener.onMouseEvent(event);
        if(event.action == 0){
            for(var listener : releaseListeners)
                listener.onMouseEvent(event);
        }else if(event.action == 1){
            for(var listener : clickListeners)
                listener.onMouseEvent(event);
        }
    }

    @Override
    public void mouseScrolled(MouseScrollEvent event) {
        for (var listener : scrollListeners)
            listener.mouseScrolled(event);
    }
}
