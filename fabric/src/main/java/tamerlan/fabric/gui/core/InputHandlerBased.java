package tamerlan.fabric.gui.core;

import tamerlan.fabric.gui.inputevents.InputEventsListener;
import tamerlan.fabric.gui.inputevents.InputHandler;

public interface InputHandlerBased extends InputEventsListener {
    InputHandler getInputHandler();
}
