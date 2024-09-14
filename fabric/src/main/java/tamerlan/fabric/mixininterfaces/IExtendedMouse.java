package tamerlan.fabric.mixininterfaces;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec2f;

public interface IExtendedMouse {
    void onMenuOpened();
    void onMenuClosed();
    Vec2f getMenuCursorPos();
    void setTimer(long t);
}
