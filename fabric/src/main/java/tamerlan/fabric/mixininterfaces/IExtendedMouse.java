package tamerlan.fabric.mixininterfaces;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec2f;

public interface IExtendedMouse {
    void onMenuOpened();
    void onMenuClosed();
    Vec2f getMenuCursorPos();
    Vec2f getPos();
    void setTimer(long t);
    static Vec2f toScreenSpace(Vec2f pos){
        var client = MinecraftClient.getInstance();
        float x = pos.x * (float)client.getWindow().getScaledWidth() / client.getWindow().getWidth();
        float y = pos.y * (float)client.getWindow().getScaledHeight() / client.getWindow().getHeight();
        return new Vec2f(x, y);
    }
}
