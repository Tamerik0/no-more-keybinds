package tamerlan.fabric.gui.events;

import java.util.Objects;

public interface IEvent {
    EventType getEventType();
    class EventType {
        static int lastId;
        int id;
        public EventType(){
            id = ++lastId;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            return id == ((EventType)o).id;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }
    }
}
