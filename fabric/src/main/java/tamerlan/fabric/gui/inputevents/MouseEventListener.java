package tamerlan.fabric.gui.inputevents;

import net.minecraft.util.math.Vec2f;

public interface MouseEventListener {
    class MouseEvent{
        public final int button;
        public final int action;
        public final int mods;
        public final Vec2f mousePos;

        public MouseEvent(int button, int action, int mods, Vec2f mousePos) {
            this.button = button;
            this.action = action;
            this.mods = mods;
            this.mousePos = mousePos;
        }
    }
    class MouseScrollEvent{
        public final Vec2f mousePos;
        public final Vec2f scroll;

        public MouseScrollEvent(Vec2f mousePos, Vec2f scroll) {
            this.mousePos = mousePos;
            this.scroll = scroll;
        }
    }
    interface MouseScrollEventListener{
        void mouseScrolled(MouseScrollEvent event);
    }
    void onMouseEvent(MouseEvent event);
}
