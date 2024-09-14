package tamerlan.fabric.gui.events;

public interface EventListener <T extends IEvent>{
    void listenEvent(T event);
}
