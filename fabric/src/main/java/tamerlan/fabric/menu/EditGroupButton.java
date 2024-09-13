package tamerlan.fabric.menu;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.Vec2f;

public class EditGroupButton extends MenuItem {
    protected EditGroupButton(MenuItem parent, Vec2f pos) {
        super(parent, pos);
    }

    @Override
    protected boolean isPosInsideArea(Vec2f pos) {
        return Math.sqrt(this.pos.distanceSquared(pos)) < 50;
    }

    @Override
    protected void renderContent(DrawContext context, Vec2f mousePos) {
        context.drawText(MinecraftClient.getInstance().textRenderer, "Edit", (int) pos.x, (int) pos.y, 0xFFFFFF, true);
    }

    @Override
    protected void onClick(int button, int action, int mods) {
        MinecraftClient.getInstance().setScreen(new EditorScreen());
    }
}
