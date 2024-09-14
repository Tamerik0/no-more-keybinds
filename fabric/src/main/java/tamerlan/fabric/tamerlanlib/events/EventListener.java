package tamerlan.fabric.tamerlanlib.events;

public interface EventListener <T extends IEvent>{
    void listenEvent(T event);
}
