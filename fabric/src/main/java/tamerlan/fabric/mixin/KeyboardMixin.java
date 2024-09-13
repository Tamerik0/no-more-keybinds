package tamerlan.fabric.mixin;

import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tamerlan.fabric.mixininterfaces.IExtendedKeyboard;

@Mixin(Keyboard.class)
public class KeyboardMixin implements IExtendedKeyboard {
    int mods = 0;

    @Inject(at = @At(value = "HEAD"), method = "onKey")
    void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        mods = modifiers;
    }

    public int getMods() {
        return mods;
    }
}
