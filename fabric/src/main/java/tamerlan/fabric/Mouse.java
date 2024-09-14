package tamerlan.fabric;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec2f;

public class Mouse {
    public static Vec2f getMousePos(){
        var mouse = MinecraftClient.getInstance().mouse;
        return new Vec2f((float) mouse.getX(), (float) mouse.getY());
    }
    public static Vec2f getMouseScreenPos(){
        return toScreenSpace(getMousePos());
    }
    public static Vec2f toScreenSpace(Vec2f pos){
        var client = MinecraftClient.getInstance();
        float x = pos.x * (float)client.getWindow().getScaledWidth() / client.getWindow().getWidth();
        float y = pos.y * (float)client.getWindow().getScaledHeight() / client.getWindow().getHeight();
        return new Vec2f(x, y);
    }
}
