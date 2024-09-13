package tamerlan.fabric.menu;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class EditorScreen extends Screen {
    protected EditorScreen() {
        super(Text.literal("editor"));
    }

    protected void init() {
        addDrawableChild(ButtonWidget.builder(Text.literal("huy"), button -> {}).position(200,200).build());
    }
}
