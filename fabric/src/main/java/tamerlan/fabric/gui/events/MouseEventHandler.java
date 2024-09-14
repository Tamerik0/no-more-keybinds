package tamerlan.fabric.gui.events;

import it.unimi.dsi.fastutil.objects.ObjectArraySet;

import java.util.List;
import java.util.Set;

public class MouseEventHandler extends EventDispatcher {
    @Override
    protected List<IEvent> dispatchEvent(IEvent event) {
        if (event instanceof MouseEvents.MouseEvent mouseEvent) {
            if(mouseEvent.action == 0){
                return List.of(new MouseEvents.MouseEvent(MouseEvents.MouseEventType.CLICK.type, mouseEvent.button, mouseEvent.action, mouseEvent.mods, mouseEvent.mousePos));
            }else if(mouseEvent.action == 1){
                return List.of(new MouseEvents.MouseEvent(MouseEvents.MouseEventType.RELEASE.type, mouseEvent.button, mouseEvent.action, mouseEvent.mods, mouseEvent.mousePos));
            }
        } else if (event instanceof MouseEvents.MouseScrollEvent) {
            return List.of(event);
        }
        return List.of();
    }
}
