package tamerlan.fabric.menu;

import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec2f;
import tamerlan.fabric.tamerlanlib.gui.core.BaseGUIElement;

import java.util.Set;

public abstract class AbstractGroupMenuItem extends BaseGUIElement {
    public float excludedDirectionRange = 100;
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
    protected void renderContent(DrawContext context){
        Vec2f center = new Vec2f(0, 0);
        float size = 2*RoundGroupMenuItem.elementRadius;
        context.getMatrices().push();
        context.getMatrices().scale(size/256, size/256, 1);
        RenderSystem.enableBlend();
        context.drawTexture(Identifier.of("mod2","textures/ui/circle.png"),(int)center.x-128,(int)center.y-128,0,0,256,256);
        RenderSystem.disableBlend();
        context.getMatrices().pop();
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
