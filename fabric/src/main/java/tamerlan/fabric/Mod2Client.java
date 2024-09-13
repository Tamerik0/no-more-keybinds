package tamerlan.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tamerlan.fabric.menu.Menu;

public final class Mod2Client implements ClientModInitializer {
    public static String MOD_ID = "mod2";
    public static Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final KeyBinding keyBinding = new KeyBinding("key.tamerlan.openscreen", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_ALT, "Tamerlan");

    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(keyBinding);
        Menu.init();
    }
}
