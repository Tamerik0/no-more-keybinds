package tamerlan.fabric.keyemulation;

import net.minecraft.client.util.InputUtil;
import tamerlan.fabric.Mod2Client;
import tamerlan.fabric.mixin.InputUtilKeyAccessor;

public class Util {
    public static InputUtil.Key createVirtualKey() {
        for (int i = Integer.MAX_VALUE - 1000; ; i--) {
            var translationKey = "%s.virtualkey.%d".formatted(Mod2Client.MOD_ID, i);
            if (!InputUtilKeyAccessor.getKEYS().containsKey(translationKey))
                return InputUtilKeyAccessor.invokeInit(translationKey, InputUtil.Type.KEYSYM, i);
        }
    }
}
