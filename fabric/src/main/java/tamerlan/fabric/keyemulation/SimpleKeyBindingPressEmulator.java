package tamerlan.fabric.keyemulation;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import tamerlan.fabric.Mouse;
import tamerlan.fabric.mixininterfaces.IExtendedKeyboard;
import tamerlan.fabric.mixininterfaces.IExtendedMouse;

public class SimpleKeyBindingPressEmulator {
    KeyBinding keyBinding;
    InputUtil.Key key;
    private int state;
    private long timer;
    private long lastTickTime;

    public SimpleKeyBindingPressEmulator(KeyBinding keyBinding) {
        this.keyBinding = keyBinding;
        this.key = KeyBindingHelper.getBoundKeyOf(keyBinding);
    }

    public void press() {
        MinecraftClient.getInstance().keyboard.onKey(MinecraftClient.getInstance().getWindow().getHandle(),key.getCategory() == InputUtil.Type.KEYSYM ? key.getCode() : -1, key.getCategory() == InputUtil.Type.SCANCODE ? key.getCode() : -1,1, ((IExtendedKeyboard) MinecraftClient.getInstance().keyboard).getMods());
//        if (!updateScreen(1))
//            KeyBinding.onKeyPressed(KeyBindingHelper.getBoundKeyOf(keyBinding));
//        KeyBinding.setKeyPressed(KeyBindingHelper.getBoundKeyOf(keyBinding), true);
        lastTickTime = System.currentTimeMillis();
        if (state == 0) {
            timer = 0;
            state = 1;
        }
    }

    public void release() {
        MinecraftClient.getInstance().keyboard.onKey(MinecraftClient.getInstance().getWindow().getHandle(),key.getCategory() == InputUtil.Type.KEYSYM ? key.getCode() : -1, key.getCategory() == InputUtil.Type.SCANCODE ? key.getCode() : -1,0, ((IExtendedKeyboard) MinecraftClient.getInstance().keyboard).getMods());
//        updateScreen(0);
//        KeyBinding.setKeyPressed(KeyBindingHelper.getBoundKeyOf(keyBinding), false);
        state = 0;
        timer = 0;
    }

    public void tick() {
        if (state == 0)
            return;
        timer += System.currentTimeMillis() - lastTickTime;
        lastTickTime = System.currentTimeMillis();
        if (state == 1) {
            if (timer >= 500) {
                state = 2;
                tick();
            }
//        } else if (!updateScreen(2))
//            if (KeyBindingHelper.getBoundKeyOf(keyBinding).getCategory() != InputUtil.Type.MOUSE)
//                KeyBinding.onKeyPressed(KeyBindingHelper.getBoundKeyOf(keyBinding));
        }else{
            MinecraftClient.getInstance().keyboard.onKey(MinecraftClient.getInstance().getWindow().getHandle(),key.getCategory() == InputUtil.Type.KEYSYM ? key.getCode() : -1, key.getCategory() == InputUtil.Type.SCANCODE ? key.getCode() : -1,2, ((IExtendedKeyboard) MinecraftClient.getInstance().keyboard).getMods());
        }
//        KeyBinding.setKeyPressed(KeyBindingHelper.getBoundKeyOf(keyBinding), true);
    }

    boolean updateScreen(int action) {
        var screen = MinecraftClient.getInstance().currentScreen;
        var key = KeyBindingHelper.getBoundKeyOf(keyBinding);
        if (screen == null)
            return false;
        if (key.getCategory() == InputUtil.Type.MOUSE) {
            var pos = Mouse.getMouseScreenPos();
            if (action == 0)
                screen.mouseReleased(pos.x, pos.y, key.getCode());
            else if (action == 1)
                screen.mouseClicked(pos.x, pos.y, key.getCode());

        } else {
            if (action == 0)
                screen.keyReleased(key.getCategory() == InputUtil.Type.KEYSYM ? key.getCode() : -1, key.getCategory() == InputUtil.Type.SCANCODE ? key.getCode() : -1, ((IExtendedKeyboard) MinecraftClient.getInstance().keyboard).getMods());
            else
                screen.keyPressed(key.getCategory() == InputUtil.Type.KEYSYM ? key.getCode() : -1, key.getCategory() == InputUtil.Type.SCANCODE ? key.getCode() : -1, ((IExtendedKeyboard) MinecraftClient.getInstance().keyboard).getMods());
        }
        return true;
    }
}
