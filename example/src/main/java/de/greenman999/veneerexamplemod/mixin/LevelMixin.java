package de.greenman999.veneerexamplemod.mixin;

import de.greenman999.Test;
import de.greenman999.layr.LayrMod;
import de.greenman999.veneerexamplemod.VeneerExampleMod;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class LevelMixin {

    @Inject(method = "loadLevel", at = @At("RETURN"))
    private void afterLoadLevel(CallbackInfo ci) {
        VeneerExampleMod.LOGGER.info("Level Loaded!");
        Test.test();
    }

}