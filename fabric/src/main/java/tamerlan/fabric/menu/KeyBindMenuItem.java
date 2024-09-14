package tamerlan.fabric.menu;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec2f;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;
import tamerlan.fabric.Mod2Client;
import tamerlan.fabric.tamerlanlib.gui.core.BaseGUIElement;
import tamerlan.fabric.tamerlanlib.events.MouseEvents;
import tamerlan.fabric.keyemulation.SimpleKeyBindingPressEmulator;
import tamerlan.fabric.keyemulation.Util;
import tamerlan.fabric.mixininterfaces.IExtendedMouse;

public class KeyBindMenuItem extends BaseGUIElement {
    KeyBinding keyBinding;
    SimpleKeyBindingPressEmulator emulator;

    public KeyBindMenuItem(BaseGUIElement parent, Vec2f pos, KeyBinding keyBinding) {
        super(parent, pos);
        this.keyBinding = keyBinding;
        if (KeyBindingHelper.getBoundKeyOf(keyBinding) == InputUtil.UNKNOWN_KEY) {
            keyBinding.setBoundKey(Util.createVirtualKey());
            KeyBinding.updateKeysByCode();
        }
        emulator = new SimpleKeyBindingPressEmulator(keyBinding);
    }


    @Override
    protected void renderContent(DrawContext context) {
        emulator.tick();
        Vec2f center = new Vec2f(0, 0);
        float size = 2*RoundGroupMenuItem.elementRadius;
        context.getMatrices().push();
        context.getMatrices().scale(size/256, size/256, 1);
        RenderSystem.enableBlend();
        context.drawTexture(Identifier.of("mod2","textures/ui/circle.png"),(int)center.x-128,(int)center.y-128,0,0,256,256);
        RenderSystem.disableBlend();
        context.getMatrices().pop();
        var text = Text.translatable(keyBinding.getTranslationKey());
        context.drawText(MinecraftClient.getInstance().textRenderer, text, -MinecraftClient.getInstance().textRenderer.getWidth(text)/2, -MinecraftClient.getInstance().textRenderer.fontHeight/2, 0xFFFFFF, true);
    }

    @Override
    protected void onClick(MouseEvents.MouseEvent event) {
        super.onClick(event);
        Mod2Client.LOGGER.info("onClickKeybind");
        if (event.button == GLFW.GLFW_MOUSE_BUTTON_1) {
            Mod2Client.LOGGER.info(String.valueOf(event.action));
            ((IExtendedMouse) MinecraftClient.getInstance().mouse).setTimer(10);
            emulator.press();
        }
    }

    protected void onRelease(MouseEvents.MouseEvent event) {
        if (event.button == GLFW.GLFW_MOUSE_BUTTON_1) {
            emulator.release();
        }
    }

    @Override
    protected void onMouseEntered() {

    }

    @Override
    protected void onMouseExit() {
        emulator.release();
    }

    public void remove() {
        super.remove();
        emulator.release();
    }
}
