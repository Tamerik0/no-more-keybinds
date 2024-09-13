package tamerlan.fabric.mixin;

import net.minecraft.client.util.InputUtil;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Map;

@Mixin(InputUtil.Key.class)
public interface InputUtilKeyAccessor {
    @Accessor("KEYS")
    static Map<String, InputUtil.Key> getKEYS() {
        throw new AssertionError();
    }

    @Invoker("<init>")
    static InputUtil.@Nullable Key invokeInit(String translationKey, InputUtil.Type type, int code) {
        return null;
    }
}
