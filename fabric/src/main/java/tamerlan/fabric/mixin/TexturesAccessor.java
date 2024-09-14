package tamerlan.fabric.mixin;

import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.Inject;

import java.util.Map;

@Mixin(TextureManager.class)
public interface TexturesAccessor {
    @Accessor
    Map<Identifier, AbstractTexture> getTextures();
}
