package tamerlan.fabric.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tamerlan.fabric.menu.Menu;

@Mixin(GameRenderer.class)
public class RenderMixin {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;draw()V"), method = "render")
    private void render(RenderTickCounter tickCounter, boolean tick, CallbackInfo ci, @Local DrawContext drawContext) {
        drawContext.getMatrices().push();
        drawContext.getMatrices().translate(0, 0, 1000);
        Menu.render(drawContext);
        drawContext.getMatrices().pop();
    }
}