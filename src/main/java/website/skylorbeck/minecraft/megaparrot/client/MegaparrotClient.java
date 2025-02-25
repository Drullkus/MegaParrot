package website.skylorbeck.minecraft.megaparrot.client;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import website.skylorbeck.minecraft.megaparrot.Declarar;
import website.skylorbeck.minecraft.megaparrot.ParrotConfig;
import website.skylorbeck.minecraft.megaparrot.entity.MegaParrotRenderer;

@Environment(EnvType.CLIENT)
public class MegaparrotClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AutoConfig.getGuiRegistry(ParrotConfig.class);
        EntityRendererRegistry.register(Declarar.MEGA_PARROT_ENTITY_TYPE, MegaParrotRenderer::new);
    }
}
