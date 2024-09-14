package tamerlan.fabric.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.util.math.Vec2f;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tamerlan.fabric.mixininterfaces.IExtendedMouse;
import tamerlan.fabric.menu.Menu;

@Mixin(Mouse.class)
public abstract class MouseMixin implements IExtendedMouse {
    @Shadow private double x;
    @Shadow private double y;
    private Vec2f menuCursorPos;
    private long fuckingTimer = Long.MAX_VALUE;

    public void setTimer(long t) {
        fuckingTimer = t;
    }

    public Vec2f getMenuCursorPos() {
        return menuCursorPos;
    }

    @Inject(at = @At(value = "HEAD"), method = "tick", cancellable = true)
    public void tick(CallbackInfo ci) {
        if (Menu.isOpened()) {
            if (fuckingTimer >= 0) {
                if (fuckingTimer == 0) {
                    cursorState = GLFW.glfwGetInputMode(MinecraftClient.getInstance().getWindow().getHandle(), 208897);
                    cursorPos = new Vec2f((float) ((Mouse) (Object) this).getX(), (float) ((Mouse) (Object) this).getY());
                } else {
                    ci.cancel();
                }
                fuckingTimer--;
            }
            ci.cancel();
        }
    }

    @Inject(at = @At(value = "HEAD"), method = "onMouseButton", cancellable = true)
    private void onMouseButton(long window, int button, int action, int mods, CallbackInfo ci) {
        if (Menu.isOpened()) {
            Menu.mouseClicked(MinecraftClient.getInstance(), button, action, mods);
            ci.cancel();
        }
    }

    @Inject(at = @At(value = "HEAD"), method = "onMouseScroll", cancellable = true)
    private void onMouseScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        if (Menu.isOpened()) {
            ci.cancel();
        }
    }

    @Inject(at = @At(value = "HEAD"), method = "onCursorPos", cancellable = true)
    private void onCursorPos(long window, double x, double y, CallbackInfo ci) {
        if (Menu.isOpened()) {
            menuCursorPos = menuCursorPos.add(new Vec2f((float) x, (float) y).add(cursorPos.multiply(-1)));
            GLFW.glfwSetCursorPos(MinecraftClient.getInstance().getWindow().getHandle(), cursorPos.x, cursorPos.y);
            ci.cancel();
        }
    }

    @Inject(at = @At(value = "HEAD"), method = "updateMouse", cancellable = true)
    private void updateMouse(double timeDelta, CallbackInfo ci) {
        if (Menu.isOpened()) {
            ci.cancel();
        }
    }

    private int cursorState;
    private Vec2f cursorPos;

    public void onMenuOpened() {
        cursorState = GLFW.glfwGetInputMode(MinecraftClient.getInstance().getWindow().getHandle(), 208897);
        cursorPos = new Vec2f((float) ((Mouse) (Object) this).getX(), (float) ((Mouse) (Object) this).getY());
        if (cursorState == GLFW.GLFW_CURSOR_DISABLED) {
            menuCursorPos = new Vec2f((float) MinecraftClient.getInstance().getWindow().getWidth() / 2, (float) MinecraftClient.getInstance().getWindow().getHeight() / 2);
        } else {
            menuCursorPos = cursorPos;
        }
    }

    public void onMenuClosed() {
        GLFW.glfwSetCursorPos(MinecraftClient.getInstance().getWindow().getHandle(), cursorPos.x, cursorPos.y);
//        GLFW.glfwSetInputMode(MinecraftClient.getInstance().getWindow().getHandle(), 208897, cursorState);
    }
}
