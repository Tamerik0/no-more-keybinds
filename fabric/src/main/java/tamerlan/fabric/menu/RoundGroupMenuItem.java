package tamerlan.fabric.menu;

import com.google.gson.JsonElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec2f;
import tamerlan.fabric.Mouse;
import tamerlan.fabric.data.RoundGroupData;

public class RoundGroupMenuItem extends AbstractGroupMenuItem {
    public static float elementRadius = 20;
    public static float elementRadius2 = 40;
    RoundGroupData groupData;

    public RoundGroupMenuItem(AbstractGroupMenuItem parent, Vec2f pos, RoundGroupData data, float excludedDir) {
        super(parent, pos);
        this.excludedDir = excludedDir;
        groupData = data;
    }

    public RoundGroupMenuItem(AbstractGroupMenuItem parent, Vec2f pos, RoundGroupData data, float excludedDir, float excludedDirectionRange) {
        super(parent, pos);
        groupData = data;
        this.excludedDir = excludedDir;
        this.excludedDirectionRange = excludedDirectionRange;
    }

    @Override
    protected void renderContent(DrawContext context) {
        super.renderContent(context);
        var text = Text.translatable(groupData.name).formatted(Formatting.ITALIC);
        context.drawText(MinecraftClient.getInstance().textRenderer, text, -MinecraftClient.getInstance().textRenderer.getWidth(text)/2, -MinecraftClient.getInstance().textRenderer.fontHeight/2, 0xFFFFFF, true);
    }

    public void load(JsonElement json) {
        groupData.name = json.getAsJsonObject().get("name").getAsString();
        groupData.renderName = json.getAsJsonObject().get("renderName").getAsBoolean();
    }

    protected void open() {
        float radius = 100;
        if(groupData.list.size() > 2)
            radius = elementRadius2/(float)Math.sin(Math.PI/groupData.list.size());
        for (int i = 0; i < groupData.list.size(); i++) {
            float angle;
            if (excludedDirectionRange != 0) {
                if (excludedDirectionRange <= (float) 360 / (groupData.list.size() + 1))
                    angle = excludedDir + (float) 360 / (groupData.list.size() + 1) * (i + 1);
                else
                    angle = excludedDir + excludedDirectionRange / 2 + (360 - excludedDirectionRange) / (groupData.list.size() - 1) * i;
            } else {
                angle = excludedDir + (float) 360 / groupData.list.size() * i;
            }
            var angleRad = angle / 180 * Math.PI;
            var childPos = new Vec2f((float) Math.cos(angleRad), (float) Math.sin(angleRad)).multiply(radius);
            if (groupData.list.get(i) instanceof RoundGroupData childData) {
                addChild(new RoundGroupMenuItem(this, childPos, childData, angle + 180));
            } else if (groupData.list.get(i) instanceof KeyBinding keyBind) {
                addChild(new KeyBindMenuItem(this, childPos, keyBind));
            }
        }
    }
}
