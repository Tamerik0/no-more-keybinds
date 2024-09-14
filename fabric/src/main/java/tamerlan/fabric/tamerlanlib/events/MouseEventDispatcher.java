package tamerlan.fabric.tamerlanlib.events;

import tamerlan.fabric.Mod2Client;

import java.util.List;

public class MouseEventDispatcher extends EventDispatcher {
    public MouseEventDispatcher(){
        addHandler(MouseEvents.MouseEventType.COMMON.type, new EventHandler<MouseEvents.MouseEvent>());
        addHandler(MouseEvents.MouseEventType.CLICK.type, new EventHandler<MouseEvents.MouseEvent>());
        addHandler(MouseEvents.MouseEventType.RELEASE.type, new EventHandler<MouseEvents.MouseEvent>());
        addHandler(MouseEvents.MouseEventType.SCROLL.type, new EventHandler<MouseEvents.MouseScrollEvent>());
    }
    @Override
    protected List<IEvent> dispatchEvent(IEvent event) {
        Mod2Client.LOGGER.info("MouseEventHandler(Dispatcher) "+event.getEventType());
        if (event instanceof MouseEvents.MouseEvent mouseEvent) {
            Mod2Client.LOGGER.info("owowowo "+mouseEvent.mousePos +" "+mouseEvent.action);
            if(event.getEventType() == MouseEvents.MouseEventType.COMMON.type) {
                Mod2Client.LOGGER.info("brbrbrbbaba "+event.getEventType());
                if (mouseEvent.action == 1) {
                    return List.of(new MouseEvents.MouseEvent(MouseEvents.MouseEventType.CLICK.type, mouseEvent.button, mouseEvent.action, mouseEvent.mods, mouseEvent.mousePos));
                } else if (mouseEvent.action == 0) {
                    return List.of(new MouseEvents.MouseEvent(MouseEvents.MouseEventType.RELEASE.type, mouseEvent.button, mouseEvent.action, mouseEvent.mods, mouseEvent.mousePos));
                }
            }
        }
        return List.of();
    }
}
