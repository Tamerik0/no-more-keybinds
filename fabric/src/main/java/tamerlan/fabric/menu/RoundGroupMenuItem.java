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

    RoundGroupData groupData;

    public RoundGroupMenuItem(AbstractGroupMenuItem parent, Vec2f pos, RoundGroupData data, float excludedDir) {
        super(parent, pos);
        this.excludedDir = excludedDir;
    }

    public RoundGroupMenuItem(AbstractGroupMenuItem parent, Vec2f pos, RoundGroupData data, float excludedDir, float excludedDirectionRange) {
        super(parent, pos);
        this.excludedDir = excludedDir;
        this.excludedDirectionRange = excludedDirectionRange;
    }

    @Override
    protected void renderContent(DrawContext context) {
        context.drawText(MinecraftClient.getInstance().textRenderer, Text.translatable(groupData.name).formatted(Formatting.ITALIC), (int)transform.pos.x, (int)transform.pos.y, 0xFFFFFF, true);
    }

    public void load(JsonElement json) {
        groupData.name = json.getAsJsonObject().get("name").getAsString();
        groupData.renderName = json.getAsJsonObject().get("renderName").getAsBoolean();
    }

    protected void open() {
        for (int i = 0; i < groupData.list.size(); i++) {
            var angle = excludedDir + excludedDirectionRange / 2 + (360 - excludedDirectionRange) / groupData.list.size() * i;
            var angleRad = angle / 180 * Math.PI;
            var childPos = transform.pos.add(new Vec2f((float) Math.cos(angleRad), (float) Math.sin(angleRad)).multiply(100));
            if (groupData.list.get(i) instanceof RoundGroupData childData) {
                addChild(new RoundGroupMenuItem(this, childPos, childData, angle + 180));
            } else if (groupData.list.get(i) instanceof KeyBinding keyBind) {
                addChild(new KeyBindMenuItem(this, childPos, keyBind));
            }
        }
    }
}
