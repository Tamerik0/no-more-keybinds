package tamerlan.fabric.menu;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec2f;
import org.lwjgl.glfw.GLFW;
import tamerlan.fabric.Mod2Client;
import tamerlan.fabric.keyemulation.SimpleKeyBindingPressEmulator;
import tamerlan.fabric.keyemulation.Util;
import tamerlan.fabric.mixininterfaces.IExtendedMouse;

public class KeyBindMenuItem extends MenuItem {
    KeyBinding keyBinding;
    SimpleKeyBindingPressEmulator emulator;
    public KeyBindMenuItem(MenuItem parent, Vec2f pos, KeyBinding keyBinding) {
        super(parent, pos);
        this.keyBinding = keyBinding;
        if(KeyBindingHelper.getBoundKeyOf(keyBinding)==InputUtil.UNKNOWN_KEY){
            keyBinding.setBoundKey(Util.createVirtualKey());
            KeyBinding.updateKeysByCode();
        }
        emulator = new SimpleKeyBindingPressEmulator(keyBinding);
    }


    @Override
    protected boolean isPosInsideArea(Vec2f pos) {
        return Math.sqrt(this.pos.distanceSquared(pos)) < 50;
    }

    @Override
    protected void renderContent(DrawContext context, Vec2f mousePos) {
        emulator.tick();
        context.drawText(MinecraftClient.getInstance().textRenderer, Text.translatable(keyBinding.getTranslationKey()), (int) pos.x, (int) pos.y, 0xFFFFFF, true);
    }

    @Override
    protected void onClick(int button, int action, int mods) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_1) {
            Mod2Client.LOGGER.info(String.valueOf(action));
            if (action == 1) {
                ((IExtendedMouse) MinecraftClient.getInstance().mouse).setTimer(10);
                emulator.press();
            } else{
                emulator.release();
            }
        }
    }

    @Override
    protected void onMouseEntered() {

    }

    @Override
    protected void onMouseExit() {
        emulator.release();
    }
    public void remove(){
        super.remove();
        emulator.release();
    }
}
