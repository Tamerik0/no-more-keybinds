package tamerlan.fabric.menu;

import com.google.gson.JsonElement;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec2f;
import tamerlan.fabric.data.RoundGroupData;
import tamerlan.fabric.gui.core.BaseGUIElement;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractGroupMenuItem extends BaseGUIElement {
    public float excludedDirectionRange = 60;
    float excludedDir;
    AbstractGroupMenuItem openedChild;
    Set<BaseGUIElement> children = new ObjectArraySet<>();
    protected AbstractGroupMenuItem(BaseGUIElement parent, Vec2f pos) {
        super(parent, pos);
    }
    public BaseGUIElement addChild(BaseGUIElement child){
        super.addChild(child);
        children.add(child);
        return child;
    }
    public BaseGUIElement removeChild(BaseGUIElement child){
        children.remove(child);
        return child;
    }
    @Override
    protected void onMouseEntered() {
        if (openedChild != null) {
            openedChild.close();
            openedChild = null;
        }
        if (getParent() instanceof AbstractGroupMenuItem) {
            ((AbstractGroupMenuItem) getParent()).childOpened(this);
        }
        close();
        open();
    }

    protected abstract void open();


    void childOpened(AbstractGroupMenuItem item) {
        openedChild = item;
        for (var child : children) {
            if (child != item) {
                child.remove();
            }
        }
        children.clear();
        children.add(item);
    }

    void close() {
        for (var child : children) {
            if (child instanceof AbstractGroupMenuItem childGroup)
                childGroup.close();
            child.remove();
        }
        children.clear();
    }
    public void remove(){
        super.remove();
        children.clear();
    }
}
