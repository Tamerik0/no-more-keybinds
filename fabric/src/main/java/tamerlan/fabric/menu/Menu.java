package tamerlan.fabric.menu;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec2f;
import tamerlan.fabric.Mouse;
import tamerlan.fabric.data.RoundGroupData;
import tamerlan.fabric.Mod2Client;
import tamerlan.fabric.mixin.TexturesAccessor;
import tamerlan.fabric.tamerlanlib.gui.core.BaseGUIElement;
import tamerlan.fabric.tamerlanlib.events.MouseEvents;
import tamerlan.fabric.mixin.KeyBindingAccessor;
import tamerlan.fabric.mixininterfaces.IExtendedMouse;

import java.util.ArrayList;
import java.util.Objects;


@Environment(EnvType.CLIENT)
public class Menu {
    static Menu instance;


    public static void init() {
    }

    public static void render(DrawContext context) {
        var client = MinecraftClient.getInstance();
        if (instance != null) {
            int mouseX = (int) (((IExtendedMouse) client.mouse).getMenuCursorPos().x * (double) client.getWindow().getScaledWidth() / (double) client.getWindow().getWidth());
            int mouseY = (int) (((IExtendedMouse) client.mouse).getMenuCursorPos().y * (double) client.getWindow().getScaledHeight() / (double) client.getWindow().getHeight());
//            context.fill(mouseX-10, mouseY-10,mouseX+10,mouseY+10,10000,0xFFFFFF);
//            context.fill(mouseX-10, mouseY-10,mouseX+10,mouseY+10,-10000,0xFFFFFF);
            context.drawItem(Items.ACACIA_BOAT.getDefaultStack(), mouseX, mouseY);
            instance.render(context, mouseX, mouseY);
        }
    }

    public static boolean isOpened() {
        return instance != null;
    }

    static {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (InputUtil.isKeyPressed(client.getWindow().getHandle(), KeyBindingHelper.getBoundKeyOf(Mod2Client.keyBinding).getCode()) && !(MinecraftClient.getInstance().currentScreen instanceof EditorScreen)) {
                if (instance == null) {
                    instance = new Menu();
                }
            } else {
                if (instance != null) {
                    instance.remove();
                    instance = null;
                }
            }
        });
    }

    final Vec2f centerPos;
    BaseGUIElement rootItem;


    public Menu() {
        instance = this;
        ((IExtendedMouse) MinecraftClient.getInstance().mouse).onMenuOpened();
        var window = MinecraftClient.getInstance().getWindow();
        var t = ((IExtendedMouse) MinecraftClient.getInstance().mouse).getMenuCursorPos();
        centerPos = new Vec2f(t.x * window.getScaledWidth() / window.getWidth(), t.y * window.getScaledHeight() / window.getHeight());
        RoundGroupData data = new RoundGroupData();
        data.name = "main";
        var l = new ArrayList<RoundGroupData>();
        for (var keyBinding : KeyBindingAccessor.getKEYS_BY_ID().values()) {
            var category = keyBinding.getCategory();
            RoundGroupData d = null;
            for (var group : l) {
                if (Objects.equals(group.name, category)) {
                    d = group;
                }
            }
            if (d == null) {
                d = new RoundGroupData();
                d.name = category;
                l.add(d);
            }
            d.list.add(keyBinding);
        }
        RoundGroupData l1 = null;
        int j = 0;
        int m = Math.max(1, (int) Math.sqrt(l.size()));
        for (int i = 0; i < l.size(); i++) {
            if (i % m == 0) {
                l1 = new RoundGroupData();
                l1.name = String.valueOf(j);
                j++;
                data.list.add(l1);
            }
            l1.list.add(l.get(i));
        }
        rootItem = new RoundGroupMenuItem(null, centerPos, data, 0, 0);
    }

    public void render(DrawContext context, int mouseX, int mouseY) {

        rootItem.render(context);
    }

    void remove() {
        rootItem.remove();
        ((IExtendedMouse) MinecraftClient.getInstance().mouse).onMenuClosed();
    }

    public static void mouseClicked(MinecraftClient client, int button, int action, int mods) {
//        Mod2Client.LOGGER.info("mouseClicked");
        Mod2Client.LOGGER.info("mouseClickedMenu");
        instance.rootItem.getInputHandler().listenEvent(new MouseEvents.MouseEvent(MouseEvents.MouseEventType.COMMON.type, button, action, mods, Mouse.getMouseScreenPos()));
    }
}