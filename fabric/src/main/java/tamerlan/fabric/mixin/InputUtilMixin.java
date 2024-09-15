package tamerlan.fabric.mixin;

import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tamerlan.fabric.NMKClient;

import java.util.Arrays;

@Mixin(InputUtil.class)
public class InputUtilMixin {
    @Inject(at = @At(value = "HEAD"), method = "fromTranslationKey", cancellable = true)
    private static void fromTranslationKey(String translationKey, CallbackInfoReturnable<InputUtil.Key> cir) {
        NMKClient.LOGGER.info("Loading key %s".formatted(translationKey));
        if (translationKey.startsWith("%s.virtualkey".formatted(NMKClient.MOD_ID))) {
            if (InputUtilKeyAccessor.getKEYS().containsKey(translationKey)) {
                cir.setReturnValue(InputUtilKeyAccessor.getKEYS().get(translationKey));
            } else {
                cir.setReturnValue(InputUtilKeyAccessor.invokeInit(translationKey, InputUtil.Type.KEYSYM, Integer.parseInt(Arrays.stream(translationKey.split("\\.")).toList().getLast())));
            }
        }
    }
}