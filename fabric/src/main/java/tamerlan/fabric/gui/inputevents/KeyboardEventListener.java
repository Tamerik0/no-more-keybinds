package tamerlan.fabric.gui.inputevents;

public interface KeyboardEventListener {
    class KeyEvent{
        public final int key;
        public final int scancode;
        public final int modifiers;
        public final int action;
        public KeyEvent(int key, int scancode, int modifiers, int action){
            this.key = key;
            this.scancode = scancode;
            this.modifiers = modifiers;
            this.action = action;
        }
    }
    class CharEvent{
        public char character;
        public int modifiers;
    }
    void onKeyboardEvent(KeyEvent event);
    interface charEventListener{
        void onCharTyped(CharEvent event);
    }
}
