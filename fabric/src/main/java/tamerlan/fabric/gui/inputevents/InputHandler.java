package tamerlan.fabric.gui.inputevents;

public class InputHandler implements InputEventsListener{
    public final KeyboardKeyEventHandler keyboardKeyEventHandler;
    public final KeyboardCharEventHandler keyboardCharEventHandler;
    public final MouseEventHandler mouseEventHandler;

    public InputHandler(KeyboardKeyEventHandler keyboardKeyEventHandler, KeyboardCharEventHandler keyboardCharEventHandler, MouseEventHandler mouseEventHandler) {
        this.keyboardKeyEventHandler = keyboardKeyEventHandler;
        this.keyboardCharEventHandler = keyboardCharEventHandler;
        this.mouseEventHandler = mouseEventHandler;
    }

    public void addListener(Object listener){}

    @Override
    public void onKeyboardEvent(KeyEvent event) {
        keyboardKeyEventHandler.onKeyboardEvent(event);
    }

    @Override
    public void onCharTyped(CharEvent event) {
        keyboardCharEventHandler.onCharTyped(event);
    }

    @Override
    public void onMouseEvent(MouseEvent event) {
        mouseEventHandler.onMouseEvent(event);
    }

    @Override
    public void mouseScrolled(MouseScrollEvent event) {
        mouseEventHandler.mouseScrolled(event);
    }
}
